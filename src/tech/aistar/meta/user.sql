drop table Users1;
create table Users1(
  id int(7) PRIMARY KEY AUTO_INCREMENT,
  username varchar(20),
  pwd varchar(20),
  birthday date
);
insert into Users1 values(1,'tom1','tom123','2010-09-09');
insert into Users1 values(2,'jack2','jack123','2010-09-09');
insert into Users1 values(3,'mike3','mike23','2010-09-09');