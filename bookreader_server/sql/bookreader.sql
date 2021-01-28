/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
DROP TABLE IF EXISTS `book`;/*书*/
create table `book`(
                       `id` int(11) NOT NULL AUTO_INCREMENT,
                       `bookImgUrl` text default null,/*图片地址*/
                       `bookName` varchar(255) default null,/*书名*/
                       `bookAuthor` varchar(255) default null,/*作者*/
                       `bookLength` double default null,/*总字数*/
                       `bookDescription` varchar(255) default null,/*出版社*/
                       `bookUrl` varchar(255) default null,/*书地址*/
                       `label` varchar(255) default null,/*标签*/
                       PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `audio`;/*音频*/
create table `audio`(
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `audioNo` int(11)default null,/*录音编号*/
                        `audioName` varchar(255)default null,/*录音名称*/
                        `radioUrl` varchar(255) default null,/*录像地址*/
                        `bookId` int(11) default null,/*书的id*/
                        `audioDescription` varchar(255)default null,/*录音简介*/
                        PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `chapter`;/*章节*/
create table `chapter`(
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `bookId` int(11) default null,/*所属书Id*/
                          `chapterNo` int(11) default null,/*标识章号*/
                          `chapterName` varchar(255) default null,
                          `beginLocation` double default null,/*起始位置*/
                          `endLocation` double default null,/*结束位置*/
                          PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `mark`;/*书签*/
create table `mark`(
                       `id` int(11) NOT NULL AUTO_INCREMENT,
                       `bookId` int(11) default null,/*对应书*/
                       `openId` char(255) default null,/*对应用户*/
                       `chapterId` int(11) default null,/*对应章号*/
                       `offset` double default null,/*书签位置*/
                       PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `user`;/*用户信息*/
create table `user`(
                       `id` int(11) NOT NULL AUTO_INCREMENT,
                       `openId` varchar(255) DEFAULT NULL,/*用户唯一标识*/
					   `userName` varchar(255) DEFAULT NULL,/*用户名*/
					   `phoneNumber` varchar(255) DEFAULT NULL,/*电话号码*/
					   `registerDate` varchar(255) DEFAULT NULL,/*注册时间*/
					   `profilePhotoUrl` varchar(255) DEFAULT NULL,/*头像url*/
					   `signature` varchar(255) DEFAULT NULL,/*个性签名*/
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `message`;/*消息*/
create table `message`(
					`id` int(11) NOT NULL AUTO_INCREMENT,
					`openId` varchar(255) DEFAULT NULL,/*所属用户*/
					`action`varchar(255) DEFAULT null,
					`bookId` int(11) DEFAULT NULL,/*消息类型*/
					`chapter` int(11) DEFAULT NULL,
					`page` int(11) DEFAULT NULL,
					`date` varchar(255) DEFAULT NULL,
					PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `note`;
create table `note`(
                       `id` int(11) NOT NULL AUTO_INCREMENT,
                       `openId` varchar(255) DEFAULT NULL,/*所属用户*/
                       `title` varchar(255) DEFAULT NULL,/*标题*/
                       `noteContent` text DEFAULT NULL,/*内容*/
                       `createDate` varchar(255) DEFAULT NULL,/*创建时间*/
                       PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS  `shelf`;/*收藏*/
create table `shelf`(
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `openId` char(255) DEFAULT NULL,/*对应用户*/
                        `bookId` int(11) DEFAULT NULL,/*对应书*/
                        PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
