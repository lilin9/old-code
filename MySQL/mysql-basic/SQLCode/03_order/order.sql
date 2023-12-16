use book
create table t_order(
	`order_id` varchar(100) primary key,
	`create_time` datetime,
	`price` decimal(11, 2),
	`status` int,
	`user_id` int,
	foreign key(`user_id`) references t_user(`id`)
);
create table t_order_item(
	`id` int primary key auto_increment,
	`name` varchar(100),
	`count` int,
	`price` decimal(11, 2),
	`total_price` decimal(11, 2),
	`order_id` varchar(100),
	foreign key(`order_id`) references t_order(`order_id`)
);

select * from t_order