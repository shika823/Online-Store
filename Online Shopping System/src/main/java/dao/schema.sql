/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  shika823
 * Created: 4/08/2020
 */
create table Product (
Product_Id varchar(20),
Name varchar(50) not null, 
Description varchar(100) ,
Category varchar(20) ,
List_Price decimal(5,2) not null,
Quantity_In_Stock decimal(5,2) ,
constraint Product_PK primary key (Product_Id)
);

create table Customer (
Customer_ID integer auto_increment (7),
Username varchar(20) not null unique,
Firstname varchar(20),
Surname varchar(20),
Password varchar(30) not null,
Email_Address varchar(30),
Shipping_Address varchar (30),
constraint Customer_PK primary key (Customer_ID)
);

create table Sale (
Sale_Id integer auto_increment (7),
Customer_Id integer not null,
Status varchar(20),
Date timestamp default current_timestamp,
constraint Sale_Pk primary key (Sale_Id),
constraint Sale_Fk foreign key (Customer_Id) references Customer
);

create table Sale_Item(
Quantity_Purchased decimal(5,2) not null,
Sale_Price decimal(5,2) not null,
Sale_Id integer (7),
Product_Id integer (7),
constraint Sale_Item_PK primary key (Sale_Id, Product_Id),
constraint Sale_Item_FK_Sale foreign key (Sale_Id) references Sale,
constraint Sale_Item_FK_Product foreign key (Product_Id) references Product
);
