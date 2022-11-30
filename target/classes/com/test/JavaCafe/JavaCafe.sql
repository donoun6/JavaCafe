DROP TABLE Category;

CREATE TABLE Category(
	category			VARCHAR(20)			NOT NULL PRIMARY KEY
)

DELETE FROM Category WHERE category = '에이드'

SELECT * FROM Category;

SELECT category FROM Category;
INSERT INTO Category (category)
VALUE ('커피');
INSERT INTO Category (category)
VALUE ('에이드');
INSERT INTO Category (category)
VALUE ('차');

-- ============================================================================================================================ --
DROP TABLE Menu;

CREATE TABLE Menu(
	category		VARCHAR(20)			NOT NULL,
	name			VARCHAR(20)			NOT NULL UNIQUE KEY,
	price			DOUBLE				NOT NULL,
	image			VARCHAR(50)			NULL DEFAULT '짬뽕.jpg',
	CONSTRAINT Menu FOREIGN KEY (category) REFERENCES Category(category)
)

SELECT * FROM Menu;

SELECT * FROM menu WHERE name = '아메리카노';

DELETE FROM Menu WHERE category = '에이드';

DELETE FROM Menu WHERE name = '자동차';

SELECT *FROM Menu m INNER JOIN Category c ON m.category = c.category WHERE c.category = '커피';

DELETE Menu m INNER JOIN Category c ON m.category = c.category WHERE c.category = '차';

INSERT INTO Menu (category, name, price)
VALUES ('ade','포도','3000');

-- ============================================================================================================================ --
DROP TABLE Cart;

CREATE TABLE Cart(
	name			VARCHAR(20)				NOT NULL,
	price			DOUBLE					NOT NULL,
	count			DOUBLE					NOT NULL
)

SELECT * FROM Cart;
SELECT count FROM Cart WHERE name = '초코케이크';

DELETE FROM Cart

SELECT SUM(price) from Cart;

INSERT INTO Cart (name,price)
VALUE ('카페', '2500' );

DROP TABLE OrderList;
CREATE TABLE OrderList (
	orderNum		BIGINT					NOT NULL,
	name			VARCHAR(20)				NOT NULL,
	price			DOUBLE					NOT NULL
	
)

SELECT count(distinct orderNum) as cnt FROM OrderList

SELECT * FROM OrderList WHERE orderNum = 1005;
INSERT INTO OrderList(name,price,orderNum) SELECT Cart.name, Cart.price, Cart.count FROM Cart;

SELECT * FROM OrderList;