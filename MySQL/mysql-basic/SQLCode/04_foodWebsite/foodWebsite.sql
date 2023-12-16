create database food_website

use food_website
create table `user`(
	`id` int primary key auto_increment,
	`username` varchar(20) not null unique,
	`password` varchar(40) not null,
	`email` varchar(200),
	`phone` varchar(20) not null
)

create table `food`(
	`id` int primary key auto_increment,
	`name` varchar(100) not null,
	`price` decimal(20, 2) not null,
	`sales` int(10) not null,
	`stock` int(10) not null,
	`img_path` varchar(200) not null
)

insert into `food`(`id`, `name`, `price`, `sales`, `stock`, `img_path`)
VALUES(null, '柳州螺蛳粉', 10, 1000, 10, 'images/banner/1.png');

insert into `food`(`id`, `name`, `price`, `sales`, `stock`, `img_path`)
VALUES(null, '柳州螺蛳粉', 10, 1000, 10, 'images/banner/1.png');

insert into `food`(`id`, `name`, `price`, `sales`, `stock`, `img_path`)
VALUES(null, '老友粉', 8, 999, 10, 'images/banner/1.png');

insert into `food`(`id`, `name`, `price`, `sales`, `stock`, `img_path`)
VALUES(null, '柠檬鸭', 15, 10000, 100, 'images/banner/1.png');

insert into `food`(`id`, `name`, `price`, `sales`, `stock`, `img_path`)
VALUES(null, '巴马香猪', 30, 100000, 100, 'images/banner/1.png');

insert into `food`(`id`, `name`, `price`, `sales`, `stock`, `img_path`)
VALUES(null, '糯米猪血肠', 15, 1000, 10, 'images/banner/1.png');

insert into `food`(`id`, `name`, `price`, `sales`, `stock`, `img_path`)
VALUES(null, '博白白切鸡', 20, 10000, 10, 'images/banner/1.png');

insert into `food`(`id`, `name`, `price`, `sales`, `stock`, `img_path`)
VALUES(null, '壮家三夹', 17, 887, 10, 'images/banner/1.png');

insert into `food`(`id`, `name`, `price`, `sales`, `stock`, `img_path`)
VALUES(null, '黄田扣肉', 20, 998, 10, 'images/banner/1.png');

insert into `food`(`id`, `name`, `price`, `sales`, `stock`, `img_path`)
VALUES(null, '钦州海鸭蛋', 5, 778, 10, 'images/banner/1.png');

insert into `food`(`id`, `name`, `price`, `sales`, `stock`, `img_path`)
VALUES(null, '钦州海鸭蛋', 5, 778, 10, 'images/banner/1.png');

select * from `user`
select `username`,`password`,`email`,`phone` from `user` where `username`='Tom' and `password`='123'

select * from `food`
delete from `food` where `id` =11
update `food` set `name`='钦州海鸭蛋', `price`=4, `sales`=778, `stock`=10, `img_path`='images/banner/1.png' where `id` = 10;