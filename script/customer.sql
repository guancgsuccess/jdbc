DROP TABLE db_orders;
DROP TABLE db_customer;
create table db_customer(
  id int(7) PRIMARY KEY AUTO_INCREMENT,
  cname varchar(20) NOT NULL
);
insert into db_customer values(1,'tom');
insert into db_customer values(2,'jack');

create table db_orders(
  id int(7) PRIMARY KEY AUTO_INCREMENT,
  ordno varchar(20) NOT NULL UNIQUE,
  price double(7,2) DEFAULT 0.0,
  createdata date,
  status enum('A','B','C','D'),
  cid int(7)
);
insert into db_orders values(1,'1001',200.0,'2010-09-09','A',1);
insert into db_orders values(2,'1002',300.0,'2011-09-09','B',1);
