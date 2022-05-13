DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS products;
CREATE TABLE products(id int not null auto_increment primary key, product_name VARCHAR(255),quantity int, model_number VARCHAR(255), brandId int);
insert into products(product_name, quantity, model_number, brandId) values ('iPhone', 15, 'i345',100);
insert into products(product_name, quantity, model_number, brandId) values ('iWatch', 25, 'i009',120);

CREATE TABLE items(id int not null auto_increment primary key, price double, product_id int);
insert into items(price, product_id) values (57000, 1);
insert into items(price, product_id) values (40000, 2);