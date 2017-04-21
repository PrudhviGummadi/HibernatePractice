CREATE TABLE EMPLOYEE(
id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(30) NOT NULL,
`salary` FLOAT(10,2),
`bonus` int(11),
`pay_per_hour` FLOAT(4,2),
`contract_period` varchar(30),
`type` varchar(10)
);

