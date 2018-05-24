drop table  if exists user;
create table user (
	id int(11) NOT NULL  AUTO_INCREMENT,
	username varchar(40),
	name varchar(20),
	age int(3),
	balance decimal(10,2),
	primary key (id)
);