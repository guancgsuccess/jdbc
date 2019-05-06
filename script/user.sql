drop table Users;
create table Users(
  id int(7) PRIMARY KEY AUTO_INCREMENT,
  username varchar(20),
  password varchar(20),
  birthday date
);
insert into Users values(1,'tom','tom123','2010-09-09');
insert into Users values(2,'jack','jack123','2010-09-09');
insert into Users values(3,'mike','mike23','2010-09-09');
select * from Users;