/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.19 : Database - shiro
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shiro` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shiro`;

/*Table structure for table `t_function` */

DROP TABLE IF EXISTS `t_function`;

CREATE TABLE `t_function` (
  `id` int(11) NOT NULL,
  `value` varchar(50) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_function_role` (`role_id`),
  KEY `fk_function_permission` (`permission_id`),
  CONSTRAINT `fk_function_permission` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`),
  CONSTRAINT `fk_function_role` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_function` */

insert  into `t_function`(`id`,`value`,`permission_id`,`role_id`,`type`) values (2,'/login.html',NULL,NULL,'anon'),(3,'/user.html',4,NULL,'perms'),(4,'/user/add.html',1,NULL,'perms'),(5,'/user/del.html',2,NULL,'perms'),(6,'/user/edit.html',3,NULL,'perms'),(7,'/test.html',NULL,4,'roles'),(8,'/verifyCode.html',NULL,NULL,'anon'),(9,'/user/userList.html',NULL,4,'roles');

/*Table structure for table `t_permission` */

DROP TABLE IF EXISTS `t_permission`;

CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL,
  `permissionname` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_permission` */

insert  into `t_permission`(`id`,`permissionname`) values (1,'user:add'),(2,'user:del'),(3,'user:update'),(4,'user:query');

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL,
  `rolename` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`rolename`,`description`) values (1,'admin','超级管理员'),(2,'manager','系统管理员'),(3,'normal','普通管理员'),(4,'user','普通用户');

/*Table structure for table `t_role_permission` */

DROP TABLE IF EXISTS `t_role_permission`;

CREATE TABLE `t_role_permission` (
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_permission` */

insert  into `t_role_permission`(`role_id`,`permission_id`) values (1,1),(1,2),(1,3),(1,4),(2,1),(2,2),(2,3),(3,4),(4,4),(4,3);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`password`) values (1,'admin1','670b14728ad9902aecba32e22fa4f6bd'),(2,'admin2','670b14728ad9902aecba32e22fa4f6bd'),(3,'admin3','670b14728ad9902aecba32e22fa4f6bd'),(4,'admin4','670b14728ad9902aecba32e22fa4f6bd');

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  KEY `FK_role_user` (`role_id`),
  KEY `FK_user_role` (`user_id`),
  CONSTRAINT `FK_role_user` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK_user_role` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`user_id`,`role_id`) values (1,1),(1,3),(2,2),(2,3),(3,3),(4,4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
