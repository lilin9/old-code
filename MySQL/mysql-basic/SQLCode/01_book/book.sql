create database book;

use book;

create table t_user(
	`id` int primary key auto_increment,
	`username` varchar(20) not null unique,
	`password` varchar(40) not null,
	`email` varchar(200),
);

create table t_book(
	`id` int primary key auto_increment,
	`bookname` varchar(100),
	`price` decimal(20, 2),
	`author` varchar(100),
	`sales` int,
	`stock` int,
	`img_path` varchar(200)
);

insert into t_user(`username`, `password`, `email`) VALUES('admin', 'admin', 'admin@qq.com');

insert into t_book(bookname, author, price, sales, stock, img_path) 
VALUES('Java从入门到放弃', '国哥', 80, 9999, 9, 'static/img/default.jpg');

insert into t_book(bookname, author, price, sales, stock, img_path) 
VALUES('数据结构与算法', '严敏军', 78.5, 9999, 13, 'static/img/数据结构与算法.jfif');

insert into t_book(bookname, author, price, sales, stock, img_path) 
VALUES('怎样拐跑别人家的媳妇', '龙伍', 68, 9999, 52, 'static/img/default.jpg');

insert into t_book(bookname, author, price, sales, stock, img_path) 
VALUES('木虚肉盖饭', '小胖', 16, 1000, 50, 'static/img/default.jpg');

insert into t_book(bookname, author, price, sales, stock, img_path) 
VALUES('C++编程思想', '刚哥', 45.5, 9999, 95, 'static/img/default.jpg');

insert into t_book(bookname, author, price, sales, stock, img_path) 
VALUES('蛋炒饭', '周星星', 9.9, 9999, 53, 'static/img/default.jpg');

insert into t_book(bookname, author, price, sales, stock, img_path) 
VALUES('赌神', '龙伍', 66.5, 9999, 535, 'static/img/default.jpg');

insert into t_book(bookname, author, price, sales, stock, img_path) 
VALUES('Java核心技术卷一', 'Cay.S.HorstMan', 99, 9999, 100, 'static/img/Java核心技术卷一.jpg');

insert into t_book(bookname, author, price, sales, stock, img_path) 
VALUES('零基础入门学习Python', '小甲鱼', 89, 9999, 50, 'static/img/零基础入门学习python.jpg');

insert into t_book(bookname, author, price, sales, stock, img_path) 
VALUES('编译原理', 'Alfred V.Aho', 89, 99999, 100, 'static/img/编译原理.jpg');

insert into t_book(bookname, author, price, sales, stock, img_path) 
VALUES('天年', '何夕', 32.00, 9999, 10, 'static/img/天年.jpg');

insert into t_book(bookname, author, price, sales, stock, img_path) 
VALUES('聊斋志异', '蒲松龄', 18.8, 9999, 20, 'static/img/聊斋志异.jfif');

insert into t_book(bookname, author, price, sales, stock, img_path) 
VALUES('红楼梦', '曹雪芹', 46.6, 9999, 20, 'static/img/红楼梦.jfif');

insert into t_book(bookname, author, price, sales, stock, img_path) 
VALUES('三国演义', '罗贯中', 26, 700, 20, 'static/img/三国演义.jpg');

insert into t_book(bookname, author, price, sales, stock, img_path) 
VALUES('西游记', '吴承恩', 31, 9999, 20, 'static/img/西游记.jfif');

insert into t_book(bookname, author, price, sales, stock, img_path) 
VALUES('水浒传', '施耐庵', 33, 9999, 20, 'static/img/水浒传.jfif');

insert into t_book(bookname, author, price, sales, stock, img_path) 
VALUES('山海经', null, 11.8, 9999, 20, 'static/img/山海经.jpg');

select * from t_book
drop table t_book