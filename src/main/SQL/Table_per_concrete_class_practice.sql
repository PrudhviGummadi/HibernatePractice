create table employee1(
id int(11) not null primary key auto_increment,
name varchar(30) not null);


create table reg_employee1(
id int(11) not null primary key auto_increment,
name varchar(30) not null,
salary float(7,2) not null,
bonus int(11) not null);


create table cont_employee1(
id int(11) not null primary key auto_increment,
name varchar(30) not null,
pay_per_hour float(5,2) not null,
period varchar(30) not null);