CREATE DATABASE `performance_appraisal`;
USE `performance_appraisal`;

DROP TABLE IF EXISTS `appraise_bases`;
CREATE TABLE `appraise_bases` (
	`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`base_type` VARCHAR(32) DEFAULT NULL COMMENT '身份类型',
	`appraisal_base` INT(11) DEFAULT NULL COMMENT '年终奖基数',
	`is_del` BOOL DEFAULT NULL COMMENT '是否删除',
	PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '身份基数';

INSERT INTO `appraise_bases`(`base_type`, `appraisal_base`, `is_del`) VALUES
('政法编制', 20000, FALSE),
('行政编制', 50000, FALSE),
('事业编制（管理类）', 30000, FALSE),
('事业编制（专业技术类）', 40000, FALSE),
('事业编制（工勤类）', 45000, FALSE),
('社会化用工', 10000, FALSE);


DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
	`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`username` VARCHAR(32) DEFAULT NULL COMMENT '用户名',
	`sex` CHAR(3) DEFAULT NULL COMMENT '性别',
	`password` VARCHAR(32) DEFAULT NULL COMMENT '登陆密码',
	`base_type_id` INT(11) DEFAULT NULL COMMENT '基数类型id',
	`is_del` BOOL DEFAULT NULL COMMENT '是否删除：0 表示未删除，1 表示删除',
	PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '用户';

INSERT INTO `users`(`username`, `sex`, `password`, `base_type_id`, `is_del`) VALUES
('张三', '男', '111', 8, FALSE),
('李四', '女', '111', 3, FALSE),
('王五', '男', '111', 7, FALSE),
('刘六', '男', '111', 4, FALSE),
('唐七', '女', '111', 5, FALSE),
('朱八', '男', '111', 3, FALSE);


DROP TABLE IF EXISTS `appraisal_coefficients`;
CREATE TABLE `appraisal_coefficients` (
	`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`appraisal_type` VARCHAR(32) DEFAULT NULL COMMENT '系数类型',
	`appraisal_coefficient` DOUBLE DEFAULT NULL COMMENT '系数评价',
	`calculation_method` INT(3) DEFAULT NULL COMMENT '计算方法',
	`is_del` BOOL DEFAULT NULL COMMENT '是否删除：0 表示未删除，1 表示删除',
	PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '系数表';

INSERT INTO `appraisal_coefficients`(`appraisal_type`,`appraisal_coefficient`,`calculation_method`,`is_del`) VALUES
('请假', 0.1, -1, FALSE),
('迟到', 0.05, -1, FALSE),
('加班', 0.1, 1, FALSE),
('办案数量', 0.005, 1, FALSE),
('维护次数', 0.002, 1, FALSE),
('项目开发', 0.3, 1, FALSE);


DROP TABLE IF EXISTS `user_appraisal`;
CREATE TABLE `user_appraisal` (
	`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`user_id` INT(11) DEFAULT NULL COMMENT '用户表id',
	`coefficients_id` INT(11) DEFAULT NULL COMMENT '系数表id',
	`count` DOUBLE DEFAULT NULL COMMENT '请假次数',
	`assessment_year` INT(11) DEFAULT NULL COMMENT '考核年度',
	`is_del` BOOL DEFAULT NULL COMMENT '是否删除：0 表示未删除，1 表示删除',
	PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '用户基数表';

INSERT INTO `user_appraisal`(`user_id`,`coefficients_id`,`count`,`assessment_year`,`is_del`) VALUES
(4,4,8,2018,FALSE),
(6,3,11,2018,FALSE),
(6,4,22,2018,FALSE),
(6,5,33,2018,FALSE),
(6,6,44,2018,FALSE),
(6,7,55,2018,FALSE),
(6,8,66,2018,FALSE),
(6,3,1,2018,FALSE),
(3,4,0,2018,FALSE),
(3,5,8,2018,FALSE),
(3,6,5,2018,FALSE),
(3,7,7,2018,FALSE),
(5,8,4,2018,FALSE),
(5,3,3,2018,FALSE),
(5,4,5,2018,FALSE);