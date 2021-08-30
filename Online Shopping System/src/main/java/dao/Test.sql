/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  shika823
 * Created: 4/08/2020
 */

insert into product (Product_Id, name, description, category, List_price, quantity_In_Stock) values (111,'John lennon record','second hand','rock','45','2');
select * from product where PRODUCT_ID = 12345
select * from product where category = 'metal'
insert into customer ( username, firstname, surname, password, Email_Address, Shipping_Address ) values ('john33','steven','johnson','gaf','john55555@gmail.com','44 something place')
select * from customer where username = 'john33'
select * from customer where username = 'john33' and password = 'gaf'
update Product set Quantity_In_Stock = 1 where Product_Id = 12345 