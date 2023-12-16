 /*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : cspticw

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2018-05-31 22:16:58
*/ 

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '菜单表id',
	`parent_id` BIGINT(20) DEFAULT NULL COMMENT '父菜单id，一级菜单id为0',
	`name` VARCHAR(20) DEFAULT '默认' COMMENT '菜单名字',
	`path` VARCHAR(128) DEFAULT NULL COMMENT '点击菜单会跳转的路径',
	`create_by` VARCHAR(20) DEFAULT NULL COMMENT '创建人',
	`create_time` DATETIME DEFAULT '2018-03-20 00:00:00' COMMENT '创建时间',
	`update_by` VARCHAR(20) DEFAULT NULL COMMENT '更新人',
	`update_time` DATETIME DEFAULT '2018-03-20 00:00:00' COMMENT '更新时间',
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '菜单表';


INSERT INTO `menu` VALUES ('1', '0', '用户管理', NULL, NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `menu` VALUES ('2', '0', '城市管理', NULL, NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `menu` VALUES ('3', '0', '职位管理', NULL, NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `menu` VALUES ('4', '1', '用户信息', '/users', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `menu` VALUES ('5', '1', '用户收藏', '/usersCollect', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `menu` VALUES ('6', '1', '关注列表', '/usersCare', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `menu` VALUES ('7', '2', '城市信息', '/city', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `menu` VALUES ('8', '3', '职位信息', '/positions', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');

-- ----------------------------
-- Table structure for user_care
-- ----------------------------
DROP TABLE IF EXISTS `user_care`;
CREATE TABLE `user_care` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) DEFAULT NULL COMMENT '用户 id',
  `care_id` BIGINT(20) DEFAULT NULL COMMENT '关注的用户 id',
  `collect_user` VARCHAR(20) NOT NULL COMMENT '收藏发起人0学生发起，1企业发起，2超级管理员',
  `is_delete` VARCHAR(20) DEFAULT '0' COMMENT '是否被删除：0未删除，1已删除',
  `create_by` VARCHAR(20) DEFAULT NULL COMMENT '创建人',
  `create_time` DATETIME DEFAULT '2018-03-20 00:00:00' COMMENT '创建时间',
  `update_by` VARCHAR(20) DEFAULT NULL COMMENT '更新人',
  `update_time` DATETIME DEFAULT '2018-03-20 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_care
-- ----------------------------
INSERT INTO `user_care` VALUES ('1','5', '1', '2', '0', 'admin', '2018-03-20 00:00:00', 'admin', '2018-03-20 00:00:00');
INSERT INTO `user_care` VALUES ('2','5', '2', '2', '0', 'admin', '2018-03-20 00:00:00', 'admin', '2018-03-20 00:00:00');
INSERT INTO `user_care` VALUES ('3','5', '3', '2', '0', 'admin', '2018-03-20 00:00:00', 'admin', '2018-03-20 00:00:00');
INSERT INTO `user_care` VALUES ('4','5', '4', '2', '0', 'admin', '2018-03-20 00:00:00', 'admin', '2018-03-20 00:00:00');

-- ----------------------------
-- Table structure for apply_record
-- ----------------------------
DROP TABLE IF EXISTS `apply_record`;
CREATE TABLE `apply_record` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `create_time` DATETIME NOT NULL,
  `modify_time` DATETIME NOT NULL,
  `resume_id` BIGINT(20) NOT NULL COMMENT '投递的简历',
  `comp_job_id` BIGINT(20) NOT NULL COMMENT 'j接受的岗位',
  `is_check` INT(11) NOT NULL DEFAULT '0' COMMENT '是否被查看0未查看1已查看',
  `status` INT(11) NOT NULL DEFAULT '0' COMMENT '状态0未查看，1已接收，2不合适',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apply_record
-- ----------------------------
INSERT INTO `apply_record` VALUES ('1', '2018-05-27 20:05:29', '2018-05-27 20:05:29', '1', '1', '0', '0');
INSERT INTO `apply_record` VALUES ('2', '2018-05-27 20:05:32', '2018-05-27 20:10:15', '2', '1', '0', '1');
INSERT INTO `apply_record` VALUES ('3', '2018-05-27 20:05:36', '2018-05-27 20:05:36', '6', '1', '0', '0');
INSERT INTO `apply_record` VALUES ('4', '2018-05-27 20:05:40', '2018-05-27 20:05:40', '7', '1', '0', '0');
INSERT INTO `apply_record` VALUES ('5', '2018-05-27 20:05:43', '2018-05-27 20:10:17', '8', '1', '0', '1');
INSERT INTO `apply_record` VALUES ('6', '2018-05-27 20:05:52', '2018-05-27 20:10:22', '1', '2', '0', '2');
INSERT INTO `apply_record` VALUES ('7', '2018-05-27 20:05:56', '2018-05-27 20:05:56', '2', '2', '0', '0');
INSERT INTO `apply_record` VALUES ('8', '2018-05-27 20:05:59', '2018-05-27 20:10:10', '7', '2', '0', '2');
INSERT INTO `apply_record` VALUES ('9', '2018-05-27 20:08:16', '2018-05-27 20:08:16', '1', '3', '0', '0');
INSERT INTO `apply_record` VALUES ('10', '2018-05-27 20:08:19', '2018-05-27 20:08:19', '6', '3', '0', '0');
INSERT INTO `apply_record` VALUES ('11', '2018-05-27 20:08:23', '2018-05-27 20:10:07', '8', '3', '0', '2');
INSERT INTO `apply_record` VALUES ('12', '2018-05-27 20:08:27', '2018-05-27 20:08:41', '2', '3', '0', '1');

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `city_id` VARCHAR(255) NOT NULL COMMENT '城市Id',
  `city` VARCHAR(255) NOT NULL COMMENT '城市名称',
  `province_id` VARCHAR(255) DEFAULT NULL COMMENT '省份Id',
  `create_by` VARCHAR(20) DEFAULT NULL COMMENT '创建人',
  `create_time` DATETIME DEFAULT '2018-03-20 00:00:00' COMMENT '创建时间',
  `update_by` VARCHAR(20) DEFAULT NULL COMMENT '更新人',
  `update_time` DATETIME DEFAULT '2018-03-20 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=346 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('1', '110100', '北京市', '110000', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `city` VALUES ('3', '120100', '天津市', '120000', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `city` VALUES ('5', '130100', '石家庄市', '130000', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `city` VALUES ('6', '130200', '唐山市', '130000', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `city` VALUES ('7', '130300', '秦皇岛市', '130000', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `city` VALUES ('8', '130400', '邯郸市', '130000', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `city` VALUES ('9', '130500', '邢台市', '130000', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `city` VALUES ('10', '130600', '保定市', '130000', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');

-- ----------------------------
-- Table structure for collect_record
-- ----------------------------
DROP TABLE IF EXISTS `collect_record`;
CREATE TABLE `collect_record` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) DEFAULT NULL COMMENT '用户 id',
  `job_id` BIGINT(20) DEFAULT NULL COMMENT '收藏的工作',
  `resume_id` BIGINT(20) DEFAULT NULL COMMENT '收藏的简历',
  `collect_user` INT(11) NOT NULL COMMENT '收藏发起人：0 学生发起，1 企业发起，2 管理员发起',
  `is_delete` VARCHAR(20 DEFAULT '0' COMMENT '是否被删除0未删除，1已删除',
  `create_by` VARCHAR(20) DEFAULT NULL COMMENT '创建人',
  `create_time` DATETIME DEFAULT '2018-03-20 00:00:00' COMMENT '创建时间',
  `update_by` VARCHAR(20) DEFAULT NULL COMMENT '更新人',
  `update_time` DATETIME DEFAULT '2018-03-20 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collect_record
-- ----------------------------
INSERT INTO `collect_record` VALUES ('1', NULL, NULL, '4', '11', '1', '0', NULL, '2018-05-27 20:08:28', NULL, '2018-05-27 20:08:28');
INSERT INTO `collect_record` VALUES ('2', NULL, NULL, '1', '6', '1', '0', NULL, '2018-05-27 20:08:28', NULL, '2018-05-27 20:08:28');
INSERT INTO `collect_record` VALUES ('3', NULL, NULL, '1', '2', '1', '0', NULL, '2018-05-27 20:08:28', NULL, '2018-05-27 20:08:28');
INSERT INTO `collect_record` VALUES ('4', NULL, NULL, '1', '3', '1', '0', NULL, '2018-05-27 20:08:28', NULL, '2018-05-27 20:08:28');
INSERT INTO `collect_record` VALUES ('5', '2', '2', NULL, NULL, '0', '0', NULL, '2018-05-27 20:08:28', NULL, '2018-05-27 20:08:28');
INSERT INTO `collect_record` VALUES ('6', '2', '3', NULL, NULL, '0', '0', NULL, '2018-05-27 20:08:28', NULL, '2018-05-27 20:08:28');

-- ----------------------------
-- Table structure for complaint_record
-- ----------------------------
DROP TABLE IF EXISTS `complaint_record`;
CREATE TABLE `complaint_record` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `create_time` DATETIME NOT NULL,
  `modify_time` DATETIME NOT NULL,
  `resume_id` BIGINT(20) DEFAULT NULL,
  `comp_id` BIGINT(20) DEFAULT NULL COMMENT '投诉的企业',
  `job_id` BIGINT(20) DEFAULT NULL,
  `stu_id` BIGINT(20) DEFAULT NULL COMMENT '投诉的学生',
  `complaint_mode` INT(11) NOT NULL COMMENT '投诉模式0学生投诉，1企业投诉',
  `complaint_title` VARCHAR(50) NOT NULL COMMENT '标题',
  `complaint_content` VARCHAR(255) NOT NULL COMMENT '内容',
  `handle_result` VARCHAR(255) DEFAULT NULL COMMENT '处理结论',
  `status` INT(11) NOT NULL DEFAULT '0' COMMENT '处理状态0未处理，1已处理',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of complaint_record
-- ----------------------------
INSERT INTO `complaint_record` VALUES ('1', '2018-05-27 20:03:05', '2018-05-27 20:03:05', '3', '1', NULL, NULL, '1', '信息虚假', '学历不符合', NULL, '0');
INSERT INTO `complaint_record` VALUES ('2', '2018-05-27 20:03:55', '2018-05-27 20:03:55', '4', '1', NULL, NULL, '1', '兼职态度', '兼职态度不好，不认真工作，', NULL, '0');

-- ----------------------------
-- Table structure for comp_job_info
-- ----------------------------
DROP TABLE IF EXISTS `comp_job_info`;
CREATE TABLE `comp_job_info` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `job_cate` VARCHAR(100) DEFAULT NULL COMMENT '兼职类别 类别名称',
  `job_place` VARCHAR(100) DEFAULT NULL COMMENT '工作地点',
  `job_detail_place` VARCHAR(128) DEFAULT NULL COMMENT '详细工作地点',
  `job_treat` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '工作待遇',
  `treat_method` VARCHAR(20) NOT NULL COMMENT '待遇方式 小时/天/月',
  `pay_method` VARCHAR(20) DEFAULT NULL COMMENT '结算方式 当日结，次日结，月结，完工结',
  `job_title` VARCHAR(20) NOT NULL COMMENT '兼职标题',
  `job_content` VARCHAR(255) DEFAULT NULL COMMENT '工作内容',
  `job_require` VARCHAR(255) DEFAULT NULL COMMENT '工作要求',
  `person_num` VARCHAR(20) DEFAULT NULL COMMENT '招聘人数 人数/不限',
  `contact_id` BIGINT(20) DEFAULT NULL COMMENT '联系人ID',
  `is_available` INT(11) NOT NULL DEFAULT '0' COMMENT '是否启用0启用1不启用2删除',
  `resu_hot` INT(11) NOT NULL DEFAULT '0' COMMENT '热度',
  `create_by` VARCHAR(20) DEFAULT NULL COMMENT '创建人',
  `create_time` DATETIME DEFAULT '2018-03-20 00:00:00' COMMENT '创建时间',
  `update_by` VARCHAR(20) DEFAULT NULL COMMENT '更新人',
  `update_time` DATETIME DEFAULT '2018-03-20 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comp_job_info
-- ----------------------------
INSERT INTO `comp_job_info` VALUES ('1', '餐厅工作', '北京市', '江苏省 苏州市 张家港市 世纪大道1589号长泰国际金融大厦1楼07单元', '20.00', '小时', '月结', '星巴克兼职招聘', '全日制大专以上在校学生，每月可以提供80小时工时，可以适应轮班制工作方式，17.5元每小时. 福利：工作满4小时可享受一杯含咖啡饮料，工作满三个月每月享受10张饮料电子券，享受门市福利品发放.\n不招暑期工，谢谢！', '全日制大专以上在校学生', '12', '5', '0', '10', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `comp_job_info` VALUES ('2', '餐厅工作', '天津市', '江苏省 苏州市 张家港市 七莘路-地铁站', '22.00', '小时', '月结', '急招服务员包吃住', '1，主动热情的为客户提供加油收银服务；\n2，做好加油站的安全，卫生工作；\n3，做好便利店的销售工作；\n4，完成上级处置的其他任务；', '1，男女不限，年龄38周岁以内，身体健康，初中以上文化；\n2，为人诚实，有一定的服务意识，服从安排，责任心强，无不良社会记录；\n3，有加油站工作经验优先，需上晚班；', '20', '5', '0', '7', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `comp_job_info` VALUES ('3', '超/便利店小时工', '石家庄市', '江苏省 苏州市 张家港市 阿斯达所阿斯达阿斯达', '200.00', '天', '当日结', '每日优鲜补货员', '补货', '18-43岁 男女不限 初中及以上学历【上班时间】早上8：00-21：00 做六休一', '0', '5', '0', '9', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `comp_job_info` VALUES ('4', '超/便利店小时工', '唐山市', '江苏省 苏州市 张家港市 阿萨斯阿萨斯', '220.00', '天', '月结', '麦德龙打包分拣', '负责所有与顾客订单相关的活动。整理货物', '开始日期：\n2017年12月20日\n \n白班:\n05:00-16:0012:00-23:00\n结束日期：\n2018年12月19日', '40', '5', '0', '0', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `comp_job_info` VALUES ('5', '递送/快递员', '北京市', '江苏省 苏州市 张家港市 撒旦收到啦时间到了', '215.00', '天', '月结', '便利蜂全职，自带车配送员', '根据订单及时安全将货物送到指定地点，并将产品按要求摆放到货架中；\n2、货物按照要求标准进行摆放、陈列、上架整理；\n3、及时反馈订单的异常状况，做好订单交接工作；\n4、提供高质量的送货服务，保护货物不受损坏、不丢失；\n5、负责为客户配送休闲食品，熟悉成都市区郊区路线；\n6、负责落实好公司规定的各项服务措施，树立良好的服务形象；爱岗敬业，礼貌待客', '自带车300/月补贴，6个月', '20', '5', '0', '0', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `comp_job_info` VALUES ('6', '游戏测试/试玩/代练', '天津市', '江苏省 苏州市 张家港市 打手拉手', '123.00', '天', '当日结', '游戏代练代打', '项目一：手游主要经营王者荣耀、球球大作战、热血传奇等。\n网游主要经营LOL、CF、DNF、魔兽世界、梦幻西游、QQ飞车、逆战、剑灵、守望先锋、梦三国、神武、问道、大话西游、天涯明月刀、征途、新天龙八部、魔域、Dota2等。\n薪资待遇：一部手机约25元一小时，多部手机或者多台电脑，收入倍增', '随教随会，不限地点，手机，电脑，平板，都可完成操作。\n学历不限，学生 宝妈 上班族皆宜 每日工作3个小时 在家就可日赚100-500元，当天结算。', '33', '5', '0', '0', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `comp_job_info` VALUES ('7', '餐厅工作', '石家庄市', '江苏省 苏州市 张家港市 萨姆大搜的安师大', '220.00', '天', '月结', '饮品培训师', '1.具备相关饮品培训和工作经验，对水吧调试制作有相当浓厚的兴趣；\n2.能够独立进行咖啡、饮品、奶茶技术研发，产品制作指导。\n3.负责公司各类新饮品的前期研发、制作、调试。\n4.了解饮品机器设备的相关原理，维护保养方法等，具有一定的知识技能传授能力。\n5.具备敏锐的饮品业新趋势，能从国内及国外饮品行业的角度对国内进行准确分析，根据消费者需求进行产品升级和新品研发。\n6、有食品/饮品相关专业资格证书', '1.中专及以上学历，食品及相关专业（有2年以上相关研发经验）优先，有类似经验或有能力者兼可；\n2.负责公司新产品（饮品）的研发和培训工作；\n3、负责公司技术团队的研发及管理工作；\n4、对奶茶、果汁、咖啡等有一定的了解，熟悉市场的需求和大众口味，熟悉相关供应商；喜欢尝试新口味，有创新思维；\n5、能根据营销计划制定年度新品研发计划；及进行新品制作培训；\n6、熟悉产品制作相关设备的使用与维护；熟悉相关材料特性,进行产品研发成本及效果分析；', '22', '5', '0', '0', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `comp_job_info` VALUES ('8', '促销员/导购员', '唐山市', '江苏省 苏州市 张家港市 到那时电视蓝色的开始', '22.00', '小时', '当日结', '凯德广场招聘导购员', '马克华菲品牌招聘服装导购员', '做一休一 个人提成\n底薪+提成3.5%左右 月均收入5000左右 旺季7000左右', '22', '5', '0', '0', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `comp_job_info` VALUES ('9', '促销员/导购员', '北京市', '江苏省 苏州市 张家港市', '24.00', '小时', '当日结', '美容院优惠活动促销员', '女子专业美容院促销活动招聘兼职促销员20名', '1、年龄不限、性别不限、学历不限；\n2、性格开朗、善于与陌生人交流沟通；\n3、敢于挑战自我，善于销售；\n4、周六周日在各大超市或者商场销售美容体检优惠卡；\n5、工资日结、多劳多得、最高可日收入几千元。', '2', '5', '0', '0', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `comp_job_info` VALUES ('10', '促销员/导购员', '天津市', '江苏省 苏州市 张家港市 撒旦教奥斯带上你', '12.00', '小时', '月结', '影院检票零食促销', '岗位职责:电影票售卖，卖品售卖，检票，卫生等', '大地数字影院现招聘全职服务人员若干。具体要求如下：???18周岁以上，男女不限，工作认真，五官端正，有责任心，有良好的沟通能力和团队合作精神，服从上级安排，有良好的服务意识，能适应倒班工作。有意者可到影院前台咨询或致电', '123', '5', '0', '0', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `comp_job_info` VALUES ('11', '促销员/导购员', '石家庄市', '江苏省 苏州市 张家港市 啊实打实大所', '33.00', '小时', '当日结', '家乐福直招短期促销员', '1.便利店促销产品\n2.解答顾客的咨询，促进销售', '1.有较强的沟通意识及服务意识/五官端正\n\n3.可以及时上班的，每周时间至少3天以上\n4.薪资150-160元/天\n（薪资及时结算）', '23', '5', '0', '0', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `comp_job_info` VALUES ('12', '促销员/导购员', '唐山市', '上海市 上海市 徐汇区 撒叫哦我回', '0.00', '小时', '当日结', '大卖场急招新品促销人员日结', '：上海促销导购兼职', '兼职活动长期都有的，室内活，不累，男女不限，18-??????35岁 性格温和 着装不邋遢??? 思维逻辑清晰 必须服从管理 无不良嗜好，听从工作人员管理与安排 会根据自己住址就近安排分配。', '33', '5', '0', '0', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `comp_job_info` VALUES ('13', '网页设计/制作/美工', '北京市', '江苏省 苏州市 吴中区 苏州工业园区兼职', '18.00', '小时', '当日结', '韩国料理店招兼职', '招 男，女服务员，形象端正，手脚灵敏', '18～30岁??? 兼职。18点～22点。\n14～18元/小时。\n13906262734 黄女士', '0', '5', '0', '0', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `comp_job_info` VALUES ('14', '程序员', '天津市', '江苏省 苏州市 高新区 滨河路金狮大', '123.00', '天', '月结', '大数据销售代表', '公司氛围\n<年轻+梦想+快乐正能量+踏实进取>\n我们是一支年轻的团队，我们是一群怀揣梦想的人。\n在这里没有勾心斗角尔虞我诈，没有霸道专横独裁统治。\n这里有的，只是一帮有理想有抱负的奋斗族，一群充满快乐和正能量的小伙伴和一次前途光明可遇而不可求的创业机会。\n我们不会高谈阔论，我们不爱画饼充饥，我们只是踏实进取。\n我们用靠谱的技术堆砌梦想的堡垒，用丰富的经验助力梦想的脚步，永不停歇。', '1、反应敏捷、表达能力强，具有较强的沟通能力及交际技巧，具有亲和力；\n2、具备一定的市场分析及判断能力；\n3、有责任心，能承受较大的工作压力；\n4、有团队协作精神，善于挑战。', '12', '5',  '0', '0', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `comp_job_info` VALUES ('15', '超/便利店小时工', '石家庄市', '江苏省 苏州市 吴中区 枫津路13-18号', '22.00', '小时', '次日结', '吴中经济技术开发区涛涛副食品店', '主要负责，帮别人开通机器你就有钱，60-100一台，这是所有公司都开不出的价，现在几乎人人都有信用卡，帮助养卡提额，应急用，机器都是大机器免费送，都有央行颁发的支付牌照放心使用', '，你身边有朋友，亲戚，机器都是公司送你的，可以摆摊，多种方法，一天10台，你一天就是1000，销售要放的开，工资日结', '11', '5', '0', '0', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `comp_job_info` VALUES ('16', '餐厅工作', '唐山市', '上海市 上海市 浦东新区 张杨路601号', '100.00', '天', '当日结', '厨房间员工', '吃苦耐劳，服从安排，适应轮班制工作，团队合作', '吃苦耐劳，服从安排，适应轮班制工作，团队合作', '22', '5', '0', '0', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');
INSERT INTO `comp_job_info` VALUES ('17', '餐厅工作', '北京市', '上海市 上海市 闵行区 莘庄镇莘沥路580号', '33.00', '小时', '当日结', '兼职服务员', '兼职服务员', '下午15:00~22:00上班 有工作餐1餐', '232', '5', '0', '0', NULL, '2018-03-20 00:00:00', NULL, '2018-03-20 00:00:00');

-- ----------------------------
-- Table structure for comp_user_info
-- ----------------------------
DROP TABLE IF EXISTS `comp_user_info`;
CREATE TABLE `comp_user_info` (

  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `create_time` DATETIME NOT NULL,
  `modify_time` DATETIME NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `is_available` INT(11) DEFAULT '0' COMMENT '是否启用0启用1禁 ',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comp_user_info
-- ----------------------------
INSERT INTO `comp_user_info` VALUES ('1', '2018-05-27 15:42:55', '2018-05-27 15:42:55', '17362202126', '3c9464df4238458157098974b98592b1', '0');
INSERT INTO `comp_user_info` VALUES ('2', '2018-05-27 15:43:09', '2018-05-27 15:43:09', '17362202125', '7e29bef5ac18e1a4af3e0616ea91815d', '0');
INSERT INTO `comp_user_info` VALUES ('3', '2018-05-27 15:43:37', '2018-05-27 15:43:37', '17362202124', '258ab6431aa6c0a771e4a876b36bcec8', '0');
INSERT INTO `comp_user_info` VALUES ('4', '2018-05-27 15:43:47', '2018-05-27 15:43:47', '17362202123', 'd24ba3072507688ffe04947807808815', '0');
INSERT INTO `comp_user_info` VALUES ('5', '2018-05-27 15:44:05', '2018-05-27 15:44:05', '17362202122', '69264b0715cd9256c4d2cd6633ac2e80', '0');
INSERT INTO `comp_user_info` VALUES ('6', '2018-05-27 15:44:16', '2018-05-27 15:44:16', '17362202121', '093f76de12975c392fec7c2f0277f35c', '0');
INSERT INTO `comp_user_info` VALUES ('7', '2018-05-27 15:44:38', '2018-05-27 15:44:38', '17362202120', '850efc89d88b4bc4bd1d3728df6ac7c2', '0');

-- ----------------------------
-- Table structure for job_category
-- ----------------------------
DROP TABLE IF EXISTS `job_category`;
CREATE TABLE `job_category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `create_time` DATETIME NOT NULL,
  `modify_time` DATETIME NOT NULL,
  `category_name` VARCHAR(50) NOT NULL COMMENT '类别名称',
  `parent_id` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '父类别',
  `is_available` INT(11) NOT NULL DEFAULT '0' COMMENT '是否启用0启用1非',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_category
-- ----------------------------
INSERT INTO `job_category` VALUES ('1', '2018-04-30 21:33:00', '2018-04-30 21:33:03', '计算机/网络', '0', '0');
INSERT INTO `job_category` VALUES ('2', '2018-04-30 21:33:35', '2018-04-30 21:33:37', '语言/文字/文职', '0', '0');
INSERT INTO `job_category` VALUES ('3', '2018-04-30 21:34:17', '2018-04-30 21:34:19', '商业/销售/促销', '0', '0');
INSERT INTO `job_category` VALUES ('4', '2018-04-30 23:07:50', '2018-04-30 23:07:50', '礼仪/演艺', '0', '0');
INSERT INTO `job_category` VALUES ('5', '2018-04-30 23:09:29', '2018-04-30 23:09:29', '加工制作', '0', '0');
INSERT INTO `job_category` VALUES ('6', '2018-04-30 23:13:16', '2018-04-30 23:13:16', '图形/影像/设计', '0', '0');
INSERT INTO `job_category` VALUES ('7', '2018-04-30 23:35:39', '2018-04-30 23:35:39', '客服/场调查', '0', '0');
INSERT INTO `job_category` VALUES ('8', '2018-04-30 23:35:47', '2018-04-30 23:35:47', '餐饮/服务/旅游', '0', '0');
INSERT INTO `job_category` VALUES ('9', '2018-04-30 23:35:55', '2018-04-30 23:35:55', '财务/法律/咨询', '0', '0');
INSERT INTO `job_category` VALUES ('10', '2018-04-30 23:36:11', '2018-04-30 23:36:11', '教育/培训/教练', '0', '0');
INSERT INTO `job_category` VALUES ('11', '2018-04-30 23:36:16', '2018-04-30 23:36:16', '其他', '0', '0');
INSERT INTO `job_category` VALUES ('12', '2018-04-30 23:36:47', '2018-04-30 23:36:47', '网页设计/制作/美工', '1', '0');
INSERT INTO `job_category` VALUES ('13', '2018-04-30 23:37:03', '2018-04-30 23:37:03', '建站服务', '1', '0');
INSERT INTO `job_category` VALUES ('14', '2018-04-30 23:37:09', '2018-04-30 23:37:09', '动画/多媒体制作', '1', '0');
INSERT INTO `job_category` VALUES ('15', '2018-04-30 23:37:13', '2018-04-30 23:37:13', '程序员', '1', '0');
INSERT INTO `job_category` VALUES ('16', '2018-04-30 23:37:18', '2018-04-30 23:37:18', '网站推广/营销', '1', '0');
INSERT INTO `job_category` VALUES ('17', '2018-04-30 23:37:25', '2018-04-30 23:37:25', '网络录入/发帖员', '1', '0');
INSERT INTO `job_category` VALUES ('18', '2018-04-30 23:37:30', '2018-04-30 23:37:30', '游戏测试/试玩/代练', '1', '0');
INSERT INTO `job_category` VALUES ('19', '2018-04-30 23:37:35', '2018-04-30 23:37:35', '网站管理员/站长/版主', '1', '0');
INSERT INTO `job_category` VALUES ('20', '2018-04-30 23:37:40', '2018-04-30 23:37:40', '软件界面设计', '1', '0');
INSERT INTO `job_category` VALUES ('21', '2018-04-30 23:37:45', '2018-04-30 23:37:45', '日薪技术员工', '1', '0');
INSERT INTO `job_category` VALUES ('22', '2018-04-30 23:37:50', '2018-04-30 23:37:50', 'seo优化', '1', '0');
INSERT INTO `job_category` VALUES ('23', '2018-04-30 23:37:56', '2018-04-30 23:37:56', '友情链接员', '1', '0');
INSERT INTO `job_category` VALUES ('24', '2018-04-30 23:38:18', '2018-04-30 23:38:18', '外文翻译', '2', '0');
INSERT INTO `job_category` VALUES ('25', '2018-04-30 23:38:23', '2018-04-30 23:38:23', '现场口译', '2', '0');
INSERT INTO `job_category` VALUES ('26', '2018-04-30 23:38:29', '2018-04-30 23:38:29', '作家/撰稿人', '2', '0');
INSERT INTO `job_category` VALUES ('27', '2018-04-30 23:38:34', '2018-04-30 23:38:34', '编辑/记者', '2', '0');
INSERT INTO `job_category` VALUES ('28', '2018-04-30 23:38:38', '2018-04-30 23:38:38', '校队/排版', '2', '0');
INSERT INTO `job_category` VALUES ('29', '2018-04-30 23:38:43', '2018-04-30 23:38:43', '口语陪练', '2', '0');
INSERT INTO `job_category` VALUES ('30', '2018-04-30 23:38:48', '2018-04-30 23:38:48', '字幕翻译', '2', '0');
INSERT INTO `job_category` VALUES ('31', '2018-04-30 23:38:52', '2018-04-30 23:38:52', '数据整理/资料录入', '2', '0');
INSERT INTO `job_category` VALUES ('32', '2018-04-30 23:38:57', '2018-04-30 23:38:57', '新闻(博客)写手', '2', '0');
INSERT INTO `job_category` VALUES ('33', '2018-04-30 23:39:02', '2018-04-30 23:39:02', '解说员', '2', '0');
INSERT INTO `job_category` VALUES ('34', '2018-04-30 23:39:08', '2018-04-30 23:39:08', '行政/文员', '2', '0');
INSERT INTO `job_category` VALUES ('35', '2018-04-30 23:39:12', '2018-04-30 23:39:12', '打字员', '2', '0');
INSERT INTO `job_category` VALUES ('36', '2018-04-30 23:39:24', '2018-04-30 23:39:24', '销售/业务员', '3', '0');
INSERT INTO `job_category` VALUES ('37', '2018-04-30 23:39:30', '2018-04-30 23:39:30', '校园代理', '3', '0');
INSERT INTO `job_category` VALUES ('38', '2018-04-30 23:39:35', '2018-04-30 23:39:35', '商务公关/文秘', '3', '0');
INSERT INTO `job_category` VALUES ('39', '2018-04-30 23:39:39', '2018-04-30 23:39:39', '发单员/派发员', '3', '0');
INSERT INTO `job_category` VALUES ('40', '2018-04-30 23:39:44', '2018-04-30 23:39:44', '电话销售', '3', '0');
INSERT INTO `job_category` VALUES ('41', '2018-04-30 23:39:48', '2018-04-30 23:39:48', '保险代理', '3', '0');
INSERT INTO `job_category` VALUES ('42', '2018-04-30 23:39:53', '2018-04-30 23:39:53', '招生/招商代理', '3', '0');
INSERT INTO `job_category` VALUES ('43', '2018-04-30 23:39:58', '2018-04-30 23:39:58', '促销员/导购员', '3', '0');
INSERT INTO `job_category` VALUES ('44', '2018-04-30 23:40:03', '2018-04-30 23:40:03', '营业员', '3', '0');
INSERT INTO `job_category` VALUES ('45', '2018-04-30 23:40:08', '2018-04-30 23:40:08', '超/便利店小时工', '3', '0');
INSERT INTO `job_category` VALUES ('46', '2018-04-30 23:40:12', '2018-04-30 23:40:12', '网络销售', '3', '0');
INSERT INTO `job_category` VALUES ('47', '2018-04-30 23:40:23', '2018-04-30 23:40:23', '门童/迎宾', '4', '0');
INSERT INTO `job_category` VALUES ('48', '2018-04-30 23:40:42', '2018-04-30 23:40:42', '群众/临时演员', '4', '0');
INSERT INTO `job_category` VALUES ('49', '2018-04-30 23:41:01', '2018-04-30 23:41:01', '歌手', '4', '0');
INSERT INTO `job_category` VALUES ('50', '2018-04-30 23:41:33', '2018-04-30 23:41:33', '夜场', '4', '0');
INSERT INTO `job_category` VALUES ('51', '2018-04-30 23:41:39', '2018-04-30 23:41:39', '模特', '4', '0');
INSERT INTO `job_category` VALUES ('52', '2018-04-30 23:41:43', '2018-04-30 23:41:43', '商演/路演', '4', '0');
INSERT INTO `job_category` VALUES ('53', '2018-04-30 23:41:48', '2018-04-30 23:41:48', '主持', '4', '0');
INSERT INTO `job_category` VALUES ('54', '2018-04-30 23:41:52', '2018-04-30 23:41:52', '婚庆司仪/宴会司仪', '4', '0');
INSERT INTO `job_category` VALUES ('55', '2018-04-30 23:41:57', '2018-04-30 23:41:57', 'DJ/酒吧驻唱/领舞', '4', '0');
INSERT INTO `job_category` VALUES ('56', '2018-04-30 23:42:09', '2018-04-30 23:42:09', '日新/计件工作', '5', '0');
INSERT INTO `job_category` VALUES ('57', '2018-04-30 23:42:14', '2018-04-30 23:42:14', '手工制作', '5', '0');
INSERT INTO `job_category` VALUES ('58', '2018-04-30 23:42:19', '2018-04-30 23:42:19', '编织/工艺', '5', '0');
INSERT INTO `job_category` VALUES ('59', '2018-04-30 23:42:28', '2018-04-30 23:42:28', '图像处理', '6', '0');
INSERT INTO `job_category` VALUES ('60', '2018-04-30 23:42:35', '2018-04-30 23:42:35', '影像处理/影视处理', '6', '0');
INSERT INTO `job_category` VALUES ('61', '2018-04-30 23:43:03', '2018-04-30 23:43:03', '摄影摄像', '6', '0');
INSERT INTO `job_category` VALUES ('62', '2018-04-30 23:43:08', '2018-04-30 23:43:08', '平面设计/logo设计/vi设计', '6', '0');
INSERT INTO `job_category` VALUES ('63', '2018-04-30 23:43:12', '2018-04-30 23:43:12', '建筑装潢设计', '6', '0');
INSERT INTO `job_category` VALUES ('64', '2018-04-30 23:43:17', '2018-04-30 23:43:17', '工业设计', '6', '0');
INSERT INTO `job_category` VALUES ('65', '2018-04-30 23:43:23', '2018-04-30 23:43:23', 'CAD设计', '6', '0');
INSERT INTO `job_category` VALUES ('66', '2018-04-30 23:43:28', '2018-04-30 23:43:28', '插图漫画', '6', '0');
INSERT INTO `job_category` VALUES ('67', '2018-04-30 23:43:40', '2018-04-30 23:43:40', '电话访问/回访', '7', '0');
INSERT INTO `job_category` VALUES ('68', '2018-04-30 23:43:45', '2018-04-30 23:43:45', '客户咨询热线', '7', '0');
INSERT INTO `job_category` VALUES ('69', '2018-04-30 23:43:50', '2018-04-30 23:43:50', '呼叫中心人员', '7', '0');
INSERT INTO `job_category` VALUES ('70', '2018-04-30 23:43:54', '2018-04-30 23:43:54', '客服/电话客服', '7', '0');
INSERT INTO `job_category` VALUES ('71', '2018-04-30 23:43:59', '2018-04-30 23:43:59', '售后服务', '7', '0');
INSERT INTO `job_category` VALUES ('72', '2018-04-30 23:44:03', '2018-04-30 23:44:03', '场调查员', '7', '0');
INSERT INTO `job_category` VALUES ('73', '2018-04-30 23:44:08', '2018-04-30 23:44:08', '问卷调查', '7', '0');
INSERT INTO `job_category` VALUES ('74', '2018-04-30 23:44:19', '2018-04-30 23:44:19', '餐厅工作', '8', '0');
INSERT INTO `job_category` VALUES ('75', '2018-04-30 23:44:30', '2018-04-30 23:44:30', '酒吧ktv', '8', '0');
INSERT INTO `job_category` VALUES ('76', '2018-04-30 23:44:34', '2018-04-30 23:44:34', '递送/快递员', '8', '0');
INSERT INTO `job_category` VALUES ('77', '2018-04-30 23:44:39', '2018-04-30 23:44:39', '送餐小时工', '8', '0');
INSERT INTO `job_category` VALUES ('78', '2018-04-30 23:44:44', '2018-04-30 23:44:44', '家政保洁', '8', '0');
INSERT INTO `job_category` VALUES ('79', '2018-04-30 23:44:49', '2018-04-30 23:44:49', '钟点工', '8', '0');
INSERT INTO `job_category` VALUES ('80', '2018-04-30 23:44:54', '2018-04-30 23:44:54', '导游', '8', '0');
INSERT INTO `job_category` VALUES ('81', '2018-04-30 23:45:03', '2018-04-30 23:45:03', '会计/审计', '9', '0');
INSERT INTO `job_category` VALUES ('82', '2018-04-30 23:45:08', '2018-04-30 23:45:08', '财务咨询', '9', '0');
INSERT INTO `job_category` VALUES ('83', '2018-04-30 23:45:13', '2018-04-30 23:45:13', '法律工作/资讯', '9', '0');
INSERT INTO `job_category` VALUES ('84', '2018-04-30 23:45:17', '2018-04-30 23:45:17', '咨询员', '9', '0');
INSERT INTO `job_category` VALUES ('85', '2018-04-30 23:45:27', '2018-04-30 23:45:27', '家教', '10', '0');
INSERT INTO `job_category` VALUES ('86', '2018-04-30 23:45:32', '2018-04-30 23:45:32', '教师', '10', '0');
INSERT INTO `job_category` VALUES ('87', '2018-04-30 23:45:36', '2018-04-30 23:45:36', '健身/舞蹈教练', '10', '0');
INSERT INTO `job_category` VALUES ('88', '2018-04-30 23:45:41', '2018-04-30 23:45:41', '汽车陪练', '10', '0');
INSERT INTO `job_category` VALUES ('89', '2018-04-30 23:45:47', '2018-04-30 23:45:47', '客户协调', '10', '0');
INSERT INTO `job_category` VALUES ('90', '2018-04-30 23:45:52', '2018-04-30 23:45:52', '运动教练', '10', '0');
INSERT INTO `job_category` VALUES ('91', '2018-04-30 23:46:05', '2018-04-30 23:46:05', '其他工作', '11', '0');

-- ----------------------------
-- Table structure for recruit_record
-- ----------------------------
DROP TABLE IF EXISTS `recruit_record`;
CREATE TABLE `recruit_record` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `create_time` DATETIME NOT NULL,
  `modify_time` DATETIME NOT NULL,
  `resume_id` BIGINT(20) NOT NULL,
  `comp_job_id` BIGINT(20) NOT NULL,
  `status` INT(11) NOT NULL DEFAULT '0' COMMENT '0未查看，1已接收，2不合适',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recruit_record
-- ----------------------------
INSERT INTO `recruit_record` VALUES ('1', '2018-05-27 18:41:30', '2018-05-27 18:41:30', '11', '14', '0');
INSERT INTO `recruit_record` VALUES ('2', '2018-05-27 20:01:05', '2018-05-27 20:01:05', '6', '1', '0');
INSERT INTO `recruit_record` VALUES ('3', '2018-05-27 20:01:09', '2018-05-27 20:01:09', '6', '2', '0');
INSERT INTO `recruit_record` VALUES ('4', '2018-05-27 20:01:12', '2018-05-27 20:01:12', '6', '3', '0');
INSERT INTO `recruit_record` VALUES ('5', '2018-05-27 20:01:16', '2018-05-27 20:01:16', '6', '5', '0');
INSERT INTO `recruit_record` VALUES ('6', '2018-05-27 20:01:29', '2018-05-27 20:01:29', '2', '1', '0');
INSERT INTO `recruit_record` VALUES ('7', '2018-05-27 20:01:32', '2018-05-27 20:01:32', '2', '5', '0');
INSERT INTO `recruit_record` VALUES ('8', '2018-05-27 20:01:37', '2018-05-27 20:01:37', '2', '4', '0');
INSERT INTO `recruit_record` VALUES ('9', '2018-05-27 20:01:56', '2018-05-27 20:01:56', '8', '1', '0');
INSERT INTO `recruit_record` VALUES ('10', '2018-05-27 20:01:59', '2018-05-27 20:10:38', '8', '3', '2');
INSERT INTO `recruit_record` VALUES ('11', '2018-05-27 20:02:03', '2018-05-27 20:10:39', '8', '5', '1');
INSERT INTO `recruit_record` VALUES ('12', '2018-05-27 20:02:10', '2018-05-27 20:02:10', '3', '1', '0');
INSERT INTO `recruit_record` VALUES ('13', '2018-05-27 20:02:15', '2018-05-27 20:02:15', '3', '4', '0');
INSERT INTO `recruit_record` VALUES ('14', '2018-05-27 20:04:29', '2018-05-27 20:10:36', '1', '6', '2');
INSERT INTO `recruit_record` VALUES ('15', '2018-05-27 20:04:33', '2018-05-27 20:10:33', '1', '7', '1');

-- ----------------------------
-- Table structure for resume_job
-- ----------------------------
DROP TABLE IF EXISTS `resume_job`;
CREATE TABLE `resume_job` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `create_time` DATETIME NOT NULL,
  `modify_time` DATETIME NOT NULL,
  `resu_id` BIGINT(20) DEFAULT NULL COMMENT '简历id',
  `job_cate_id` BIGINT(20) NOT NULL COMMENT '行业分类id',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resume_job
-- ----------------------------
INSERT INTO `resume_job` VALUES ('1', '2018-05-27 15:52:45', '2018-05-27 15:52:45', '1', '39');
INSERT INTO `resume_job` VALUES ('2', '2018-05-27 15:52:45', '2018-05-27 15:52:45', '1', '36');
INSERT INTO `resume_job` VALUES ('3', '2018-05-27 15:52:45', '2018-05-27 15:52:45', '1', '43');
INSERT INTO `resume_job` VALUES ('4', '2018-05-27 15:55:10', '2018-05-27 15:55:10', '2', '85');
INSERT INTO `resume_job` VALUES ('5', '2018-05-27 15:55:10', '2018-05-27 15:55:10', '2', '86');
INSERT INTO `resume_job` VALUES ('14', '2018-05-27 15:58:03', '2018-05-27 15:58:03', '3', '18');
INSERT INTO `resume_job` VALUES ('15', '2018-05-27 15:58:03', '2018-05-27 15:58:03', '3', '17');
INSERT INTO `resume_job` VALUES ('16', '2018-05-27 16:00:19', '2018-05-27 16:00:19', '4', '36');
INSERT INTO `resume_job` VALUES ('17', '2018-05-27 16:00:19', '2018-05-27 16:00:19', '4', '40');
INSERT INTO `resume_job` VALUES ('18', '2018-05-27 16:00:19', '2018-05-27 16:00:19', '4', '44');
INSERT INTO `resume_job` VALUES ('19', '2018-05-27 16:02:46', '2018-05-27 16:02:46', '5', '36');
INSERT INTO `resume_job` VALUES ('20', '2018-05-27 16:02:46', '2018-05-27 16:02:46', '5', '37');
INSERT INTO `resume_job` VALUES ('21', '2018-05-27 16:02:46', '2018-05-27 16:02:46', '5', '38');
INSERT INTO `resume_job` VALUES ('22', '2018-05-27 16:02:46', '2018-05-27 16:02:46', '5', '43');
INSERT INTO `resume_job` VALUES ('23', '2018-05-27 16:05:59', '2018-05-27 16:05:59', '6', '85');
INSERT INTO `resume_job` VALUES ('24', '2018-05-27 16:05:59', '2018-05-27 16:05:59', '6', '86');
INSERT INTO `resume_job` VALUES ('25', '2018-05-27 16:08:31', '2018-05-27 16:08:31', '7', '24');
INSERT INTO `resume_job` VALUES ('26', '2018-05-27 16:08:31', '2018-05-27 16:08:31', '7', '28');
INSERT INTO `resume_job` VALUES ('27', '2018-05-27 16:08:31', '2018-05-27 16:08:31', '7', '31');
INSERT INTO `resume_job` VALUES ('28', '2018-05-27 16:10:38', '2018-05-27 16:10:38', '8', '36');
INSERT INTO `resume_job` VALUES ('29', '2018-05-27 16:10:38', '2018-05-27 16:10:38', '8', '39');
INSERT INTO `resume_job` VALUES ('30', '2018-05-27 16:10:38', '2018-05-27 16:10:38', '8', '43');
INSERT INTO `resume_job` VALUES ('31', '2018-05-27 16:10:38', '2018-05-27 16:10:38', '8', '44');
INSERT INTO `resume_job` VALUES ('32', '2018-05-27 16:10:38', '2018-05-27 16:10:38', '8', '85');
INSERT INTO `resume_job` VALUES ('33', '2018-05-27 16:10:38', '2018-05-27 16:10:38', '8', '86');
INSERT INTO `resume_job` VALUES ('34', '2018-05-27 16:13:10', '2018-05-27 16:13:10', '9', '47');
INSERT INTO `resume_job` VALUES ('35', '2018-05-27 16:13:10', '2018-05-27 16:13:10', '9', '48');
INSERT INTO `resume_job` VALUES ('36', '2018-05-27 16:13:10', '2018-05-27 16:13:10', '9', '52');
INSERT INTO `resume_job` VALUES ('37', '2018-05-27 16:15:08', '2018-05-27 16:15:08', '10', '12');
INSERT INTO `resume_job` VALUES ('38', '2018-05-27 16:15:08', '2018-05-27 16:15:08', '10', '13');
INSERT INTO `resume_job` VALUES ('39', '2018-05-27 16:15:08', '2018-05-27 16:15:08', '10', '15');
INSERT INTO `resume_job` VALUES ('40', '2018-05-27 16:15:08', '2018-05-27 16:15:08', '10', '19');
INSERT INTO `resume_job` VALUES ('41', '2018-05-27 16:15:08', '2018-05-27 16:15:08', '10', '22');
INSERT INTO `resume_job` VALUES ('42', '2018-05-27 16:18:04', '2018-05-27 16:18:04', '11', '37');
INSERT INTO `resume_job` VALUES ('43', '2018-05-27 16:18:04', '2018-05-27 16:18:04', '11', '46');
INSERT INTO `resume_job` VALUES ('44', '2018-05-27 16:18:04', '2018-05-27 16:18:04', '11', '44');
INSERT INTO `resume_job` VALUES ('45', '2018-05-27 19:47:12', '2018-05-27 19:47:12', '12', '39');
INSERT INTO `resume_job` VALUES ('46', '2018-05-27 19:47:12', '2018-05-27 19:47:12', '12', '43');
INSERT INTO `resume_job` VALUES ('47', '2018-05-27 19:47:12', '2018-05-27 19:47:12', '12', '46');
INSERT INTO `resume_job` VALUES ('48', '2018-05-27 19:47:12', '2018-05-27 19:47:12', '12', '45');
INSERT INTO `resume_job` VALUES ('49', '2018-05-27 19:57:02', '2018-05-27 19:57:02', '13', '35');
INSERT INTO `resume_job` VALUES ('50', '2018-05-27 19:57:02', '2018-05-27 19:57:02', '13', '24');
INSERT INTO `resume_job` VALUES ('51', '2018-05-27 19:57:02', '2018-05-27 19:57:02', '13', '27');
INSERT INTO `resume_job` VALUES ('52', '2018-05-27 19:57:02', '2018-05-27 19:57:02', '13', '31');
INSERT INTO `resume_job` VALUES ('53', '2018-05-27 19:59:06', '2018-05-27 19:59:06', '14', '75');
INSERT INTO `resume_job` VALUES ('54', '2018-05-27 19:59:06', '2018-05-27 19:59:06', '14', '74');

-- ----------------------------
-- Table structure for stu_resume_info
-- ----------------------------
DROP TABLE IF EXISTS `stu_resume_info`;
CREATE TABLE `stu_resume_info` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `create_time` DATETIME NOT NULL,
  `modify_time` DATETIME NOT NULL,
  `stu_id` BIGINT(20) NOT NULL,
  `resu_name` VARCHAR(50) NOT NULL COMMENT '简历名称 唯一',
  `real_name` VARCHAR(50) DEFAULT NULL,
  `id_photo` VARCHAR(255) DEFAULT NULL COMMENT '证件照',
  `gender` VARCHAR(20) DEFAULT NULL,
  `age` INT(11) DEFAULT NULL COMMENT '`',
  `province` VARCHAR(20) DEFAULT NULL,
  `city` VARCHAR(20) DEFAULT NULL,
  `school` VARCHAR(20) DEFAULT NULL COMMENT '学校名称',
  `major` VARCHAR(20) DEFAULT NULL COMMENT '专业',
  `education` VARCHAR(20) DEFAULT NULL COMMENT '学历',
  `phone` VARCHAR(20) DEFAULT NULL COMMENT '手机号',
  `qq` VARCHAR(20) DEFAULT NULL,
  `wechat` VARCHAR(20) DEFAULT NULL,
  `self_description` VARCHAR(255) DEFAULT NULL COMMENT '自我描述 工作经验',
  `annex_resume` VARCHAR(255) DEFAULT NULL COMMENT '附件文件',
  `status` INT(11) NOT NULL DEFAULT '0' COMMENT '状态0启用1非2删除',
  `resu_hot` INT(11) NOT NULL DEFAULT '0' COMMENT '热度',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_resume_info
-- ----------------------------
INSERT INTO `stu_resume_info` VALUES ('1', '2018-05-27 15:52:45', '2018-05-27 15:52:45', '2', '找传单派发兼职', '王丽思', '2\\b18ec0be9f5140c3b50348486d0d11be.jpg', '女', '22', '江苏省', '苏州市', '江苏科技大学', '软件工程', '本科', '18362202125', '121039156', '18362202125', '工作认真细致，富有责任心，具有多次兼职经验', '2\\大学生兼职信息分类网站的设计与实现.docx', '0', '4');
INSERT INTO `stu_resume_info` VALUES ('2', '2018-05-27 15:55:10', '2018-05-27 15:55:10', '2', '英语家教', '王丽思', '2\\66c28e052bfb44c1b47eeee2d20390f6.jpg', '女', '22', '江苏省', '苏州市', '江苏科技大学', '软件工程', '本科', '18362202125', '121028362', '18362202125', '1,谢绝中介,纯粹个人的一对一或一对多服务.\n\n2,教师网络授课，全球有效。\n\n3,只要是英语日语汉语的学习和运用方面都在服务范围.当然包括零起点的学员.\n\n4,前三节课是初步接触阶段,以后是正式合作阶段.\n\n5,学不会绝对不收费---是铁的承诺!\n\n6,无论你的根基如何,只要你有决心和信心,只要你谦虚刻苦,就没有不可能!', '2\\大学生兼职信息分类网站的设计与实现 -定稿 -完整-修.docx', '0', '7');
INSERT INTO `stu_resume_info` VALUES ('3', '2018-05-27 15:57:40', '2018-05-27 15:58:03', '3', '打字员游戏测试试', '刘同学', '3\\3f6b8d9b68cf4022894e8585454ea00c.jpg', '男', '23', '江苏省', '苏州市', '沙洲职业工程学院', '计算机技术', '专科', '18362202124', '123456', '18262125522', '工作时间宽裕，想在手机上找点兼职', '3\\timg (3).jpg', '0', '5');
INSERT INTO `stu_resume_info` VALUES ('4', '2018-05-27 16:00:19', '2018-05-27 16:00:19', '3', '客服', '刘同学', '3\\41aee53c2cdb4a53908cd1bca13b71c9.jpg', '男', '24', '江苏省', '苏州市', '科技大学', '物理管理', '本科', '18362552525', '123452155', '1822321525', '本人是在校大学生，希望能在寒假找一份兼职工作，在全家便利店有半年的工作经验。', '3\\timg (9).jpg', '0', '0');
INSERT INTO `stu_resume_info` VALUES ('5', '2018-05-27 16:02:46', '2018-05-27 16:02:46', '3', '销售', '任先生', '3\\628c5fa227864e4797877f5355b1d7e5.jpg', '男', '24', '江苏省', '苏州市', '艾利斯顿商学院', '金融经济', '博士及其以上', '185265522245', '123156215', '15646136813', '销售/业务员,促销员/导购员,商务公关/文秘销售/业务员,促销员/导购员,商务公关/文秘', '3\\u=1338514655,126346248&fm=27&gp=0.jpg', '0', '0');
INSERT INTO `stu_resume_info` VALUES ('6', '2018-05-27 16:05:59', '2018-05-27 16:05:59', '2', '美术助教', '胡同学', '2\\1f42a322246a4bb2a83be77c270c684c.jpg', '女', '25', '江苏省', '苏州市', '上海大学美术学院', '美术', '本科', '18522525652', '123165158', '123165135', '上海大学美术学院在校生，上海美院作为一个新成立的美院，实力不必多说，选择我，也不会让您失望。感谢您的浏览。', '2\\213ee9d108902ec382c0a2ab532fd138.jpg', '0', '9');
INSERT INTO `stu_resume_info` VALUES ('7', '2018-05-27 16:08:31', '2018-05-27 16:08:31', '2', '寻求7月8月暑期工职位', '韩', '2\\f391cc9e32384b6184557af93a72910d.jpg', '女', '26', '江苏省', '苏州市', '农业大学', '文字编辑', '专科', '182262255252', '12316545', '1351461686', '在茶餐厅 来伊份食品 餐饮都有过兼职', '2\\timg (4).jpg', '0', '0');
INSERT INTO `stu_resume_info` VALUES ('8', '2018-05-27 16:10:38', '2018-05-27 16:10:38', '2', '市场培训家教', '陈佳佳', '2\\1ab22264736f4dff8d663b9371e957c1.jpg', '女', '22', '江苏省', '苏州市', '职业技术学院', '食品安全', '专科', '182526562', '1122525', '1482625214', '目前工作经验并不丰富，但是做的种类比较多，并且能够适应很多岗位，积极向上', '2\\timg (5).jpg', '0', '6');
INSERT INTO `stu_resume_info` VALUES ('9', '2018-05-27 16:13:10', '2018-05-27 16:13:10', '3', '兼职', '陈明', '3\\72a36058f9204d08a5a0194ee58242db.jpg', '男', '22', '江苏省', '苏州市', '医科院大学', '临床医学', '博士及其以上', '1852622256', '1231654', '12314654165', '目前医学博士在读\n英语六级', '3\\timg (7).jpg', '0', '0');
INSERT INTO `stu_resume_info` VALUES ('10', '2018-05-27 16:15:08', '2018-05-27 16:15:08', '3', '数据处理程序设计', '朱雨杰', '3\\a50154fa95c74e02aca457579e2fcc31.jpg', '男', '22', '江苏省', '苏州市', '科技大学', '网络工程', '本科', '18252516845', '111156465', '16816815668', '会使用word,ppt的基本功能\n会运用matlab,spss做一些数据挖掘\n做过网页\n会使用sql来建立和管理数据库\n用mfc设计过和sql关联的程序\n会使用AE,PS的基本功能\n目前英语四级', '3\\timg (6).jpg', '0', '0');
INSERT INTO `stu_resume_info` VALUES ('11', '2018-05-27 16:18:04', '2018-05-27 16:18:04', '4', '求靠谱点兼职', '李思', '4\\1ec1783c5ed247f28ffb220c49be7298.jpg', '男', '25', '江苏省', '苏州市', '江科大', '商业管理', '本科', '18252652522', '1235114', '1141552252', '忠实诚信,讲原则，说到做到，决不推卸责任,有自制力,做事情始终坚持有始有终，从不半途而废；', '4\\timg (17).jpg', '0', '3');
INSERT INTO `stu_resume_info` VALUES ('12', '2018-05-27 19:47:12', '2018-05-27 19:47:12', '5', '周末想做兼职', '张文文', '5\\c8233ff8f265428584fac4a97c7e177d.jpg', '女', '23', '上海市', '上海市', '金融学校', '金融专业', '本科', '182526202556', '123213251', '15646815686', '闲暇时间经常做兼职，吃苦耐劳，踏实肯干！', NULL, '0', '0');
INSERT INTO `stu_resume_info` VALUES ('13', '2018-05-27 19:57:02', '2018-05-27 19:57:02', '5', '朱燕应聘职员工作', '朱燕', '5\\d9b7aa481d74465b9e21215a5b75b297.jpg', '女', '24', '上海市', '上海市', '医科药大学', '医科药大学', '本科', '1231231241', '123123123', '12312341244', '根据近期远期目标制定作息，按需听令理解做对事，踏实精业注意诚信。\n根据市场环境调整价格优惠活动，综合选货，服务顾客。\n积累财富，创造营业成绩，微商，本人稳重、细致、认真。具有较好的文字组织能力，有一定的听说读写唱能力，能熟练操作windows平台上的各类应用软件(如Microsoft office word2000、excel2000,photoshop、CorelDraw、Xnview、Outlook、HTML、Winrar、Adobe Reader。)具有独立思考作业能力，应变能力,操作能力。', '5\\附件1：使用指南——苏州理工.doc', '0', '0');
INSERT INTO `stu_resume_info` VALUES ('14', '2018-05-27 19:59:06', '2018-05-27 19:59:06', '5', '酒店餐饮', '闫伟强', '5\\12dda3e7168548c1b76a379de295a60d.jpg', '男', '22', '上海市', '上海市', '诚实守信', '诚实守信', '本科', '123123123', '12312321', '12312312', '开朗，执行力强，诚实守信', '5\\关于做好2018届本科毕业设计（论文）查重检测工作的通知.pdf', '0', '0');

-- ----------------------------
-- Table structure for stu_user_info
-- ----------------------------
DROP TABLE IF EXISTS `stu_user_info`;
CREATE TABLE `stu_user_info` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `create_time` DATETIME NOT NULL,
  `modify_time` DATETIME NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) DEFAULT NULL,
  `is_available` INT(11) DEFAULT '0' COMMENT '是否启用0启用1禁 ',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_user_info
-- ----------------------------
INSERT INTO `stu_user_info` VALUES ('2', '2018-05-27 15:36:30', '2018-05-27 15:36:30', '18362202125', 'f5b93b503bff2d892532592c460031d8', NULL, '0');
INSERT INTO `stu_user_info` VALUES ('3', '2018-05-27 15:39:32', '2018-05-27 15:39:32', '18362202124', '1f903443250436b28af215688aaeec15', NULL, '0');
INSERT INTO `stu_user_info` VALUES ('4', '2018-05-27 15:39:52', '2018-05-27 15:39:52', '18362202123', 'aa3cc076a5cab59b58e1a11f37600cb9', NULL, '0');
INSERT INTO `stu_user_info` VALUES ('5', '2018-05-27 15:40:06', '2018-05-27 15:40:06', '18362202122', '4ffec9f2b3e3e2233e707b89b3201279', NULL, '0');
INSERT INTO `stu_user_info` VALUES ('6', '2018-05-27 15:40:26', '2018-05-27 15:40:26', '18362202121', '9033317248fb52f5348b395793ec27e6', NULL, '0');
INSERT INTO `stu_user_info` VALUES ('7', '2018-05-27 15:40:38', '2018-05-27 15:40:38', '18362202120', '73989ef11e24807c5141a3882e581138', NULL, '0');
INSERT INTO `stu_user_info` VALUES ('8', '2018-05-27 15:41:50', '2018-05-27 15:41:50', '18362202119', '1b7371a1519d89fabac12360321b1067', NULL, '0');
INSERT INTO `stu_user_info` VALUES ('9', '2018-05-27 15:42:07', '2018-05-27 15:42:07', '18362202118', '93f1e50830b3a64e20c2fedd9b214c1c', NULL, '0');
INSERT INTO `stu_user_info` VALUES ('10', '2018-05-27 15:42:24', '2018-05-27 15:42:24', '18362202117', 'c495850ac5092aae88412abb22b7a61e', NULL, '0');
INSERT INTO `stu_user_info` VALUES ('11', '2018-05-27 15:42:37', '2018-05-27 15:42:37', '18362202116', 'b59bebe99e8755247441a39836d3a44e', NULL, '0');

-- ----------------------------
-- Table structure for website_news
-- ----------------------------
DROP TABLE IF EXISTS `website_news`;
CREATE TABLE `website_news` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `create_time` DATETIME NOT NULL,
  `modify_time` DATETIME NOT NULL,
  `news_title` VARCHAR(20) NOT NULL COMMENT '新闻标题',
  `news_content` VARCHAR(255) NOT NULL COMMENT '新闻内容',
  `status` INT(11) NOT NULL DEFAULT '0' COMMENT '状态0启用，1禁用',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of website_news
-- ----------------------------

-- ----------------------------
-- Table structure for work_experience
-- ----------------------------
DROP TABLE IF EXISTS `work_experience`;
CREATE TABLE `work_experience` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `create_time` DATETIME NOT NULL,
  `modify_time` DATETIME NOT NULL,
  `resu_id` BIGINT(20) DEFAULT NULL COMMENT '简历id',
  `work_title` VARCHAR(50) NOT NULL COMMENT '工作职位',
  `work_content` VARCHAR(255) NOT NULL COMMENT '工作内容',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work_experience
-- ----------------------------
INSERT INTO `work_experience` VALUES ('1', '2018-05-27 15:52:45', '2018-05-27 15:52:45', '1', '发传单', '在老街发过传单');
INSERT INTO `work_experience` VALUES ('2', '2018-05-27 15:55:10', '2018-05-27 15:55:10', '2', '家教', '英语家教日语家教');
INSERT INTO `work_experience` VALUES ('3', '2018-05-27 15:55:10', '2018-05-27 15:55:10', '2', '口语', '英语口语交际英语商务英语外贸英语雅思英语剑桥英语');
INSERT INTO `work_experience` VALUES ('4', '2018-05-27 16:02:46', '2018-05-27 16:02:46', '5', 'OTR 销售部门', '1.负责做好顾客接待，并进行商品讲解\n2.完成相应的销售指标');
INSERT INTO `work_experience` VALUES ('5', '2018-05-27 16:02:46', '2018-05-27 16:02:46', '5', '东亚银行 实习生', '1.文件及数据的汇总，分类与分析\n2.分行相关信息的统计\n3.协助上级完成分行管理的工作');
INSERT INTO `work_experience` VALUES ('6', '2018-05-27 16:08:31', '2018-05-27 16:08:31', '7', '排版', '排版文章大家啊所面临的');
INSERT INTO `work_experience` VALUES ('7', '2018-05-27 16:15:08', '2018-05-27 16:15:08', '10', '网页设计', '给其他公司设计过网页');
INSERT INTO `work_experience` VALUES ('8', '2018-05-27 16:18:04', '2018-05-27 16:18:04', '11', '超市收银', '给华润万家做过收银员');
INSERT INTO `work_experience` VALUES ('9', '2018-05-27 19:47:12', '2018-05-27 19:47:12', '12', '文员/客服', '文员/客服文员/客服文员/客服文员/客服文员/客服文员/客服文员/客服文员/客服文员/客服文员/客服文员/客服文员/客服');
INSERT INTO `work_experience` VALUES ('10', '2018-05-27 19:57:02', '2018-05-27 19:57:02', '13', '文员', '文员文员文员文员文员文员文员文员文员文员');
INSERT INTO `work_experience` VALUES ('11', '2018-05-27 19:59:06', '2018-05-27 19:59:06', '14', '开朗，执行力强，诚实守信', '开朗，执行力强，诚实守信 \n开朗，执行力强，诚实守信 \n开朗，执行力强，诚实守信');
-- ----------------------------
-- Table structure for stuinfo
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` VARCHAR(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` VARCHAR(64) NOT NULL DEFAULT 'NULL' COMMENT '昵称',
  `password` VARCHAR(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `type` VARCHAR(64) DEFAULT '0' COMMENT '用户类型：0代表普通用户，1代表企业联系人，2代表管理员',
  `status` CHAR(1) DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` VARCHAR(64) DEFAULT NULL COMMENT '邮箱',
  `phonenumber` VARCHAR(32) DEFAULT NULL COMMENT '手机号',
  `sex` CHAR(1) DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` VARCHAR(128) DEFAULT NULL COMMENT '头像',
  `create_by` VARCHAR(64) DEFAULT NULL COMMENT '创建人',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_by` VARCHAR(64) DEFAULT NULL COMMENT '更新人',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `del_flag` INT(11) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of stuinfo
-- ----------------------------

INSERT  INTO `user`(`user_name`,`nick_name`,`password`,`type`,`status`,`email`,`phonenumber`,`sex`,`avatar`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) VALUES ('tom','123','$2a$10$Jnq31rRkNV3RNzXe0REsEOSKaYK8UgVZZqlNlNXqn.JeVcj2NdeZy','1','0','23412332@qq.com','18888888888','1','https://inews.gtimg.com/newsapp_bt/0/13797387085/1000',NULL,'2022-01-05 09:01:56',1,'2022-01-30 15:37:03',0);