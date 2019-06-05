/*
Navicat MySQL Data Transfer

Source Server         : bxy
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : library_system

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2019-06-05 13:45:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `allbooks`
-- ----------------------------
DROP TABLE IF EXISTS `allbooks`;
CREATE TABLE `allbooks` (
  `book_id` int(30) NOT NULL AUTO_INCREMENT,
  `book_type` varchar(30) NOT NULL,
  `book_name` varchar(30) NOT NULL,
  `writer_name` varchar(30) NOT NULL,
  `press` varchar(30) NOT NULL,
  `counts` int(30) NOT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of allbooks
-- ----------------------------
INSERT INTO `allbooks` VALUES ('1', '玄幻', '斗罗大陆', '天蚕土豆', '人民出版社', '20');
INSERT INTO `allbooks` VALUES ('2', '文学', '老人与海', '欧内斯特·海明威', '人民出版社', '17');
INSERT INTO `allbooks` VALUES ('3', '文学', '偷影子的人', '马克·李维', '人民出版社', '10');
INSERT INTO `allbooks` VALUES ('4', '文学', '钢铁是怎样练成的', '奥斯特洛夫斯基', '人民出版社', '20');
INSERT INTO `allbooks` VALUES ('5', '玄幻', '斗破苍穹', '天蚕土豆', '人民出版社', '10');
INSERT INTO `allbooks` VALUES ('6', '爱情', '爱的多重奏', '爱的多重奏', '人民出版社', '20');

-- ----------------------------
-- Table structure for `borrow`
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow` (
  `book_id` int(10) NOT NULL,
  `book_name` varchar(30) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `borrow_datetime` date NOT NULL,
  `borrow_endtime` date NOT NULL,
  `borrow_status` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES ('2', '老人与海', '小强', '2019-06-05', '2019-08-05', '1');
INSERT INTO `borrow` VALUES ('2', '老人与海', '张三', '2019-06-05', '2019-08-05', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `user_pwd` varchar(50) NOT NULL,
  `user_email` varchar(30) NOT NULL,
  `user_phone` varchar(30) NOT NULL,
  `user_type` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('12', '张三', 'f18eb415bf259a7459a4a9926075fb7e', '2694645542@qq.com', '18255944522', 'user');
INSERT INTO `user` VALUES ('9', 'admin', 'c64140e86b79fbea17f84e53264ea239', '2694645542@qq.com', '13157118620', 'admin');
