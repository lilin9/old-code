create database mybatis
use mybatis
create table if not exists `t_user`(
	id int primary key auto_increment,
	username varchar(20),
	`password` varchar(20),
	age int,
	sex CHAR(5),
	email varchar(20)
);
create table if not exists `t_emp`(
	eid int primary key auto_increment,
	emp_name varchar(20),
	age int,
	sex CHAR(10),
	email varchar(20),
	did int
);
create table if not exists `t_dept`(
	did int primary key auto_increment,
	dept_name varchar(20)
);

insert into `t_emp` values(null,'tom',18,'boy','tom@qq.com',1);
insert into `t_emp` values(null,'tony',15,'boy','tony@qq.com',2);
insert into `t_emp` values(null,'jery',16,'boy','jery@qq.com',2);
insert into `t_emp` values(null,'amy',18,'girl','amy@qq.com',1);
insert into `t_emp` values(null,'jenny',18,'girl','jenny@qq.com',1);

insert into `t_dept`(dept_name) values("前端")
insert into `t_dept`(dept_name) values("后端")

select * from `t_emp`
select * from `t_dept`