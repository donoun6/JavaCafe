package com.test.JavaCafe.kiosk.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.test.JavaCafe.kiosk.Command.CartCommand;
import com.test.JavaCafe.kiosk.Command.CategoryCommand;
import com.test.JavaCafe.kiosk.Command.OrderCommand;
import com.test.JavaCafe.kiosk.Command.PriceCommand;
import com.test.JavaCafe.menuitem.Command.MenuCommand;

public class KioskDao {
	private JdbcTemplate jdbcTemplate;
	//============================================JDBC data================================================
	public KioskDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//============================================메뉴 출력================================================		
	public List<MenuCommand> allFindMenu(){
		String sql = "SELECT * FROM Menu m INNER JOIN Category c ON m.category = c.category";
		return jdbcTemplate.query(sql, new RowMapper<MenuCommand>() {
			@Override
			public MenuCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
				MenuCommand menuCommand = new MenuCommand(rs.getString(1),rs.getString(2),
						rs.getDouble(3),rs.getString(4));
				return menuCommand;
			}
			
		});
	}
	
	//============================================카테고리 찾기================================================

	public List<CategoryCommand> findCategory() {
			String sql = "SELECT category FROM Category";
			return jdbcTemplate.query(sql, new RowMapper<CategoryCommand>() {
				@Override
				public CategoryCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
					CategoryCommand categoryCommand = new CategoryCommand(rs.getString(1));
					return categoryCommand;
				}
			});
		}
		
	//============================================카테고리 JOIN 출력================================================
		public List<MenuCommand> viewMenu(String category) {
			String sql = "SELECT * FROM Menu m INNER JOIN Category c ON m.category = c.category WHERE c.category = ? ";
			return jdbcTemplate.query(sql, new RowMapper<MenuCommand>() {
				@Override 
				public MenuCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
					MenuCommand menuCommand = new MenuCommand(rs.getString(1),rs.getString(2)
							,rs.getDouble(3),rs.getString(4));
					return menuCommand;
				}
			}, category);
		}
		
	//============================================장바구니 담을정보================================================
		public List<MenuCommand> menuInfo(String name) {
			String sql = "SELECT * FROM menu WHERE name = ? ";
			return jdbcTemplate.query(sql, new RowMapper<MenuCommand>() {
				@Override
				public MenuCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
					MenuCommand menuCommand = new MenuCommand(rs.getString(1),rs.getString(2),
							rs.getDouble(3),rs.getString(4));
					return menuCommand;
				}
			},name);
		}
		
	//============================================장바구니 추가================================================
		
		public void addCart(CartCommand cart) {
			String sql = "INSERT INTO Cart (name, price, count) VALUES (?,?,?)";
			jdbcTemplate.update(sql,cart.getName(),cart.getPrice(),cart.getCount());
		}	
		
	//============================================장바구니 추가================================================
		public List<CartCommand> allFindCart(){
			String sql = "SELECT distinct * FROM Cart";
			return jdbcTemplate.query(sql, new RowMapper<CartCommand>() {

				@Override
				public CartCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
					CartCommand cartCommand = new CartCommand(rs.getString(1),rs.getDouble(2),
							rs.getDouble(3));
					return cartCommand;
				}
			});
		}
	
	//============================================메뉴 개수================================================
		public long getCount(String name) {
			String sql = "SELECT count(*) as cnt FROM Cart WHERE name = ?";
			return jdbcTemplate.queryForObject(sql, new RowMapper<Long>() {

				@Override
				public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getLong("cnt");
				}
				
			},name);
			
		}
		
	//============================================주문번호================================================
		public long getOrderCount() {
			String sql = "SELECT count(distinct orderNum) as cnt FROM OrderList";
			return jdbcTemplate.queryForObject(sql, new RowMapper<Long>() {

				@Override
				public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getLong("cnt");
				}
				
			});
		}
		
	//============================================가격 더하기================================================
		public List<PriceCommand> addPrice() {
			String sql = "SELECT SUM(price) from Cart";
			return jdbcTemplate.query(sql, new RowMapper<PriceCommand>() {

				@Override
				public PriceCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
					PriceCommand priceCommand = new PriceCommand(rs.getDouble(1));
					return priceCommand;
				}
			});
		}	
		
		public void deleteCart(String name) {
			String sql ="DELETE FROM Cart WHERE name = ?";
			jdbcTemplate.update(sql,name);
		}
		
		public void addOrder() {
			String sql ="INSERT INTO OrderList(name,price,orderNum) SELECT Cart.name, Cart.price, Cart.count FROM Cart";
			jdbcTemplate.update(sql);
		}
		
		public void deleteCartAll() {
			String sql ="DELETE FROM Cart";
			jdbcTemplate.update(sql);
		}
		
		public List<OrderCommand> allFindOrder(long orderNum){
			String sql = "SELECT * FROM OrderList WHERE orderNum = ?";
			return jdbcTemplate.query(sql, new RowMapper<OrderCommand>() {

				@Override
				public OrderCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
					OrderCommand order = new OrderCommand(rs.getLong("orderNum"),rs.getString("name"),rs.getDouble("price"));
					return order;
				}
			},orderNum);
		}
}
