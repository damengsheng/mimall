/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.5.54 : Database - db_shopping
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_shopping` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_shopping`;

/*Table structure for table `tb_address` */

DROP TABLE IF EXISTS `tb_address`;

CREATE TABLE `tb_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detail` text,
  `name` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_address_id` (`uid`),
  CONSTRAINT `fk_address_id` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `tb_address` */

insert  into `tb_address`(`id`,`detail`,`name`,`phone`,`uid`,`level`) values (2,'北京市:北京市市辖区:东城区:宝盛北里西区天丰利商城四层 千锋教育','李爽','1567890890822',4,0),(3,'北京市海淀区3','李爽1','136110803022',6,0),(4,'22223','张','112',6,0),(6,'辽宁省朝阳市凌源','张','15678908908',4,0),(7,'北京昌平','jiang','123456789',13,0),(8,'上海','jiang','123456',13,1),(15,'和GVv','小明明','4555665',13,0),(16,'哈哈哈吧VB','迷失','4555665',13,0),(17,'对的东风','小明明','4151665',13,0),(18,'对的东风','小明明','4151665',13,0);

/*Table structure for table `tb_cart_goods` */

DROP TABLE IF EXISTS `tb_cart_goods`;

CREATE TABLE `tb_cart_goods` (
  `cid` int(11) NOT NULL,
  `gid` int(11) NOT NULL,
  `num` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_cart_goods` */

insert  into `tb_cart_goods`(`cid`,`gid`,`num`) values (1,6,1);

/*Table structure for table `tb_cart_user` */

DROP TABLE IF EXISTS `tb_cart_user`;

CREATE TABLE `tb_cart_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_cart_user_uid_uindex` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_cart_user` */

insert  into `tb_cart_user`(`id`,`uid`) values (1,13);

/*Table structure for table `tb_date` */

DROP TABLE IF EXISTS `tb_date`;

CREATE TABLE `tb_date` (
  `id` varchar(50) NOT NULL,
  `date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_date` */

insert  into `tb_date`(`id`,`date`) values ('9055373b-d084-11e8-bd7d-507b9d483d3a','2018-12-03 00:00:00'),('dc85d385-d085-11e8-bd7d-507b9d483d3a',NULL),('0f1329a0-d086-11e8-bd7d-507b9d483d3a','2018-03-06 05:45:28'),('19ddc40e-d086-11e8-bd7d-507b9d483d3a',NULL),('25858592-d086-11e8-bd7d-507b9d483d3a',NULL),('34b01845-d086-11e8-bd7d-507b9d483d3a','2018-03-06 05:06:08'),('3c63b27e-d086-11e8-bd7d-507b9d483d3a',NULL),('539d521c-d086-11e8-bd7d-507b9d483d3a','2018-03-06 06:09:08');

/*Table structure for table `tb_goods` */

DROP TABLE IF EXISTS `tb_goods`;

CREATE TABLE `tb_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `pubdate` date DEFAULT NULL,
  `picture` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `star` tinyint(4) NOT NULL DEFAULT '0',
  `intro` text,
  `typeid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_typeid` (`typeid`),
  CONSTRAINT `fk_typeid` FOREIGN KEY (`typeid`) REFERENCES `tb_goods_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `tb_goods` */

insert  into `tb_goods`(`id`,`name`,`pubdate`,`picture`,`price`,`star`,`intro`,`typeid`) values (1,'java模式','2017-09-12','8\\7\\8bba858c-79da-4cdc-a974-eb4526d7408b_696673-1_w.jpg',1,5,'第一',1),(2,'java编程思想','2017-09-14','8\\7\\8bba858c-79da-4cdc-a974-eb4526d7408b_696673-1_w.jpg',23,5,'第二本',1),(3,'html5','2017-09-07','8\\7\\8b607da8e9-8421-47c2-bc61-424c22c50697_696673-1_w.jpg',78,4,'第一本html\r\n实例二\r\n\r\n     这次要介绍的就没那么简单了，这个悬停弹出框的效果比第一个实例要复杂很多。弹出框弹出的效果是一样的，不一样的是弹出框的内容，这次的弹出框中不仅要有提示，还要有相应的信息，链接等。小编所做的效果是在弹出框中添加了图片，然后有相应的链接，在点击之后可以跳转到不同的页面。',1),(4,'安卓','2017-09-02','b\\8\\46e944eb-3589-4dc3-aafa-24227ff0323c_',89,3,'安卓第一本书是从这里开始的',1),(5,'小米6','2018-01-24','d\\a\\3b673ed6-9dd9-4f34-8920-c4da39af94fd_liebiao_xiaomi6.jpg',2499,3,'变焦双摄，4 轴防抖 / 骁龙835 旗舰处理器，6GB 大内存，最大可选128GB 闪存 / 5.15\" 护眼屏 / 四曲面玻璃/陶瓷机身',2),(6,'小米7mini','2018-01-10','8\\5\\86c72bfa-67c2-4ab1-97ec-a82d6b2168d0_liebiao_xiaomi6.jpg',1,2,'',2),(7,'admin','2018-01-20','7\\1\\daf26636-d382-402c-9ac0-5e4f39aa5299_liebiao_xiaomi6.jpg',11,4,'',5),(8,'小米MIX2','2018-01-24','f\\0\\c5da94db-0d2e-42df-a4ab-2610e9b02df0_liebiao_xiaomimix.jpg',1588,3,'5.99\" 超大屏幕\r\n机身却比 5.5\" 传统手机还小\r\n\r\n5.99\"，你很难找到比它屏幕大，但是尺寸却比它小的手机。\r\n这源于不断进化的全面屏科技，带来更窄的底边、更小的相机、隐藏的距离传感器……\r\n最终将 5.99\" 大屏装进了比 5.5\" 传统手机还小的机身内，握感绝佳。',2),(9,'小米1','2018-09-22','f\\0\\c5da94db-0d2e-42df-a4ab-2610e9b02df0_liebiao_xiaomimix.jpg',123,2,NULL,1),(10,'小米2',NULL,'f\\0\\c5da94db-0d2e-42df-a4ab-2610e9b02df0_liebiao_xiaomimix.jpg',2222,0,NULL,1),(11,'请问1',NULL,'8\\7\\8bba858c-79da-4cdc-a974-eb4526d7408b_696673-1_w.jpg',2223,0,NULL,1),(12,'www',NULL,'7\\1\\daf26636-d382-402c-9ac0-5e4f39aa5299_liebiao_xiaomi6.jpg',566,0,NULL,1),(13,'err',NULL,'8\\7\\8bba858c-79da-4cdc-a974-eb4526d7408b_696673-1_w.jpg',577,0,NULL,1),(14,'小明明','2018-09-10','c\\5\\960c9d89-0161-46c0-b702-82041f595a65_322119_300.jpg',5000,4,'刚刚更换',2),(15,'小米11','2018-09-22','c\\9\\223fe088-7f98-4f95-be0a-c3b0d7f3db13_322233_300.jpg',120000,5,'哈佛的',2),(16,'迷失','2018-09-18','c\\5\\1de72d5e-997d-4194-a23a-251a78f80eda_322119_300.jpg',10000,4,'大大大丰富',4);

/*Table structure for table `tb_goods_type` */

DROP TABLE IF EXISTS `tb_goods_type`;

CREATE TABLE `tb_goods_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `level` int(11) DEFAULT NULL,
  `parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `tb_goods_type` */

insert  into `tb_goods_type`(`id`,`name`,`level`,`parent`) values (1,'计算机',1,0),(2,'小米手机',1,0),(3,'笔记本',1,0),(4,'电视盒子',1,0),(5,'智能家电',1,0),(6,'其他',2,1),(7,'平衡车',0,6),(8,'辅导室',3,6),(9,'辅导室222',4,8);

/*Table structure for table `tb_order` */

DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
  `id` varchar(100) NOT NULL,
  `uid` int(11) DEFAULT NULL,
  `money` decimal(11,0) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_uid` (`uid`),
  CONSTRAINT `fk_order_uid` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_order` */

insert  into `tb_order`(`id`,`uid`,`money`,`status`,`time`) values ('20181019051228277',13,'5001','1','2018-10-19 17:12:28'),('20181019051244546',13,'3245','1','2018-10-19 17:12:44'),('20181019051301099',13,'19444','1','2018-10-19 17:13:01'),('20181019051323874',13,'20577','1','2018-10-19 17:13:23');

/*Table structure for table `tb_order_address` */

DROP TABLE IF EXISTS `tb_order_address`;

CREATE TABLE `tb_order_address` (
  `oid` varchar(50) NOT NULL,
  `aid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_order_address` */

insert  into `tb_order_address`(`oid`,`aid`) values ('20181019051228277',7),('20181019051244546',7),('20181019051301099',7),('20181019051323874',8);

/*Table structure for table `tb_orderdetail` */

DROP TABLE IF EXISTS `tb_orderdetail`;

CREATE TABLE `tb_orderdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` varchar(100) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `money` decimal(11,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_pid` (`pid`),
  KEY `fk_order_id` (`oid`),
  CONSTRAINT `fk_order_id` FOREIGN KEY (`Oid`) REFERENCES `tb_order` (`id`),
  CONSTRAINT `fk_order_pid` FOREIGN KEY (`pid`) REFERENCES `tb_goods` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `tb_orderdetail` */

insert  into `tb_orderdetail`(`id`,`oid`,`pid`,`num`,`money`) values (18,'20181019051228277',5,2,'4998'),(19,'20181019051228277',6,3,'3'),(20,'20181019051244546',2,3,'69'),(21,'20181019051244546',8,2,'3176'),(22,'20181019051301099',10,2,'4444'),(23,'20181019051301099',14,3,'15000'),(24,'20181019051323874',13,1,'577'),(25,'20181019051323874',16,2,'20000');

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `flag` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`username`,`password`,`email`,`gender`,`flag`,`role`,`code`) values (3,'admin','e10adc3949ba59abbe56e057f20f883e','2407359193@qq.com','女',1,0,'20170912202221249354'),(4,'lee','e10adc3949ba59abbe56e057f20f883e','2407359193@qq.com','男',1,1,'2017091221312740330d'),(5,'andy','81dc9bdb52d04dc20036dbd8313ed055','2407359193@qq.com','女',2,1,'201709122331221743af'),(6,'amin','e10adc3949ba59abbe56e057f20f883e','2407359193@qq.com','女',1,1,'2018012218370268135d'),(8,'zhangsan','2a0d136ceacafe198ea64ac09daaf1b6','15216503505@163.com','女',2,0,'20180918073925123456'),(9,'Mr_25kjiang','12345678','15216503505@163.com','女',2,0,'20180918074510123456'),(10,'lisi','e10adc3949ba59abbe56e057f20f883e','15216503505@163.com','男',0,0,'2018092121191825732a'),(13,'jiang','e10adc3949ba59abbe56e057f20f883e','15216503505@163.com','男',1,0,'2018092210272707310e'),(15,'laowang1','124588','jjjj@qq.com','男',1,2,'sjdnnad'),(16,'xiaohei','22222','rrrr@qq.com','女',1,2,'5151515565656'),(17,'huang','1232','aaa@qq.com','男',1,2,'54566656565'),(19,'阿黄','1232','aaa@qq.com','男',1,2,'54566656565'),(32,'xiaohuang','123456','15216503505@163.com','男',1,1,'25656565'),(33,'xiaohuang1','123456','15216503505@163.com','女',1,1,'25656565'),(34,'leee','123456','15216503505@163.com','女',1,1,'25656565'),(42,'张迪','111111','15216503505@163.com','男',1,1,'25656565'),(43,'张三','e10adc3949ba59abbe56e057f20f883e','15216503505@163.com','男',1,1,'25656565'),(45,'李四','e10adc3949ba59abbe56e057f20f883e','15216503505@163.com','男',1,1,'25656565');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
