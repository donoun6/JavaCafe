package com.test.JavaCafe.menuitem.Dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.test.JavaCafe.menuitem.Command.MenuCommand;
import com.test.JavaCafe.menuitem.Provider.CategoryProvider;


public class MenuDao {
	private JdbcTemplate jdbcTemplate;
	
	//============================================JDBC data================================================
	public MenuDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//============================================메뉴 추가=================================================
	public void addMenu(MenuCommand menu) {
		String sql = "INSERT INTO Menu (category, name, price, image) VALUES (?,?,?,?)";
		jdbcTemplate.update(sql,menu.getCategory(),menu.getName(),menu.getPrice(),menu.getImage());
	}	
	
	//============================================메뉴 삭제================================================
	public void deleteMenu(MenuCommand menu) {
		String sql = "DELETE FROM Menu WHERE name = ?";
		jdbcTemplate.update(sql,menu.getName());
	}
	
	
	//============================================카테고리 추가================================================
	public void addCategory(MenuCommand menu) {
		String sql = "INSERT INTO Category (category) VALUES(?)";
		jdbcTemplate.update(sql,menu.getCategory());
	}
	
	//============================================카테고리 삭제================================================
		public void deleteCategory(MenuCommand menu) {
			String sql = "DELETE FROM Menu WHERE category = ?";
			jdbcTemplate.update(sql,menu.getCategory());
		}
		
		public void deleteCategory2(MenuCommand menu) {
			String sql = "DELETE FROM Category WHERE category = ?";
			jdbcTemplate.update(sql,menu.getCategory());
		}
		
	//============================================카테고리 찾기================================================
	public List<CategoryProvider> findCategory() {
		String sql = "SELECT category FROM Category";
		return jdbcTemplate.query(sql, new RowMapper<CategoryProvider>() {

			@Override
			public CategoryProvider mapRow(ResultSet rs, int rowNum) throws SQLException {
				CategoryProvider categoryProvider = new CategoryProvider(rs.getString("category"));
				return categoryProvider;
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
		},category);
	}
	

}
