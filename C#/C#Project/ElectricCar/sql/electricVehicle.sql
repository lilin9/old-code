CREATE DATABASE `electricVehicle`;

DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `UserId` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '用户表Id',
  `Username` VARCHAR(32) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `Password` VARCHAR(32) NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `Sex` VARCHAR(2) DEFAULT NULL COMMENT '用户性别（男，女）',
  `RealName` VARCHAR(32) DEFAULT 'NULL' COMMENT '真实姓名',
  `Telephone` VARCHAR(32) DEFAULT NULL COMMENT '手机号',
  `Address` VARCHAR(32) DEFAULT NULL COMMENT '用户地址',
  `Rank` CHAR(1) DEFAULT '1' COMMENT '1 普通用户,2 管理员',
   PRIMARY KEY (`UserId`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '用户表';

INSERT INTO `User`(`Username`, `Password`, `Sex`, `RealName`, `Telephone`, `Address`) 
VALUES('Tom', '123abc', '男', 'Tom', '12345678901', '');

DROP TABLE IF EXISTS `Car`;
CREATE TABLE `Car` (
  `CarId` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '电动车表Id',
  `CategoryId` BIGINT(20) NOT NULL COMMENT '分类id',
  `Title` VARCHAR(128) NOT NULL DEFAULT 'NULL' COMMENT '标题',
  `Description` VARCHAR(128) NOT NULL DEFAULT 'NULL' COMMENT '描述',
  `CarImage` VARCHAR(64) DEFAULT NULL COMMENT '图片',
  `SalePrice` BIGINT(32) DEFAULT NULL COMMENT '商品单价',
  `Quantity` INT(10) DEFAULT NULL COMMENT '库存数量',
   PRIMARY KEY (`CarId`)
  
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '电动车表';

INSERT INTO `Car`(`CategoryId`, `Title`, `Description`, `CarImage`, `SalePrice`, `Quantity`)
VALUES('1', '【门店自提】雅迪冠能奢享Q9电动轻便摩托车长续航高颜值电动车女', '【门店自提】雅迪冠能奢享Q9电动轻便摩托车长续航高颜值电动车女', '/car.jpg', 3790, 100);


DROP TABLE IF EXISTS `Category`;
CREATE TABLE `Category` (
  `CategoryId` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `CategoryName` VARCHAR(32) NOT NULL DEFAULT 'NULL' COMMENT '分类名',
  `Description` VARCHAR(128) NOT NULL DEFAULT 'NULL' COMMENT '描述',
   PRIMARY KEY (`CategoryId`)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '电动车类别表';

INSERT INTO `Category`(`CategoryName`, `Description`) VALUES('奢享Q9', '更高品质 超长续航');

DROP TABLE IF EXISTS `ShoppingCart`;
CREATE TABLE `ShoppingCart` (
	`scId` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
	`userId` BIGINT(20) NOT NULL COMMENT '用户id',
	`carId` BIGINT(20) NOT NULL COMMENT '电动车id',
	`quantity` INT(10) DEFAULT NULL COMMENT '购买数量',
	PRIMARY KEY (`scId`)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '购物车表';

INSERT INTO `ShoppingCart`(`userId`, `carId`, `quantity`) 
VALUES(1, 1, 1);

DROP TABLE IF EXISTS `Order`;
CREATE TABLE `Order` (
	`OrderId` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
	`UserId` BIGINT(20) NOT NULL COMMENT '用户id',
	`OrderDate`  DATETIME DEFAULT NULL COMMENT '更新时间',
	`Telephone` VARCHAR(32) DEFAULT NULL COMMENT '手机号',
	`Address` VARCHAR(32) DEFAULT NULL COMMENT '用户地址',
	`RealName` VARCHAR(32) DEFAULT 'NULL' COMMENT '真实姓名',
	`TotalPrice` BIGINT(32) DEFAULT NULL COMMENT '订单总价',
	PRIMARY KEY (`orderId`)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '订单表';

INSERT INTO `Order`(`OrderId`,`UserId`,`OrderDate`,`Telephone`,`Address`,`RealName`,`TotalPrice`) 
VALUES(1, 1, "2023-05-21 14:45", "12345678901", "", "Tom", "33660");

DROP TABLE IF EXISTS `OrderDetail`;
CREATE TABLE `OrderDetail`(
	`OdId` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '订单详情id',
	`OrderId` BIGINT(20) NOT NULL COMMENT '订单id',
	`CarId` BIGINT(20) NOT NULL COMMENT '电动车id',
	`SalePrice` BIGINT(32) DEFAULT NULL COMMENT '商品单价',
	`Quantity` INT(10) DEFAULT NULL COMMENT '订单数量',
	`Status` CHAR(1) DEFAULT '1' COMMENT '订单状态',
	PRIMARY KEY (`OdId`)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '订单详情表';

INSERT INTO `OrderDetail`(`OrderId`, `CarId`, `SalePrice`, `Quantity`) 
VALUES(1, 1, 6330, 1);