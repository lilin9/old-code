create database closeCourse
use closeCourse

create table course(
	id int primary key auto_increment,
	courseName varchar(20) not null unique,
	`number` int
);

insert into course(courseName, `number`) VALUES('Java', 0);
insert into course(courseName, `number`) VALUES('JavaScript', 0);
insert into course(courseName, `number`) VALUES('Python', 0);
insert into course(courseName, `number`) VALUES('C++', 0);

update course set `number`=0 where courseName='C++';

select * from course