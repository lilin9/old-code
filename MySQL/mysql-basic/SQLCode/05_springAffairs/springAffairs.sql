create database springAffairs
use springAffairs

create table `s_user`(
	id int primary key auto_increment,
	username varchar(20),
	money decimal(20, 2)
)

insert into `s_user`(`username`, `money`) VALUES('Lucy', 1000);
insert into `s_user`(`username`, `money`) VALUES('Mary', 1000);

select * from `s_user`

drop database springAffairs