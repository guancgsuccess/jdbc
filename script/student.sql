drop table db_student;
create table db_student(
  id int(7) primary key AUTO_INCREMENT,
  sno varchar(20) not null UNIQUE,
  sname varchar(20) not null,
  birthday date,
  gender ENUM('F','M')
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
insert into db_student(sno,sname,birthday,gender) values('1004','successs','2018-04-28','F');
select * from db_student;
