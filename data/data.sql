/*
Navicat MySQL Data Transfer

Source Server         : IDMS
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : idms

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2020-08-31 09:56:44
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `app_menu`
-- ----------------------------
DROP TABLE IF EXISTS `app_menu`;
CREATE TABLE `app_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `url` varchar(20) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `ord` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_menu
-- ----------------------------
INSERT INTO `app_menu` VALUES ('1', 'Cấu hình', '/config', null, '2');
INSERT INTO `app_menu` VALUES ('2', 'Cấu hình tài khoản', '/config/user', '1', '3');
INSERT INTO `app_menu` VALUES ('3', 'Cấu hình vai trò', '/config/function', '1', '4');
INSERT INTO `app_menu` VALUES ('4', 'Trang chủ', '/home', null, '1');
INSERT INTO `app_menu` VALUES ('5', 'Cấu hình thông báo', '/config/notif', '1', '5');

-- ----------------------------
-- Table structure for `app_role`
-- ----------------------------
DROP TABLE IF EXISTS `app_role`;
CREATE TABLE `app_role` (
  `ROLE_ID` int(19) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_role
-- ----------------------------
INSERT INTO `app_role` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `app_role` VALUES ('2', 'ROLE_MEMBER');

-- ----------------------------
-- Table structure for `app_user`
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `USER_ID` int(19) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(36) NOT NULL,
  `encryted_password` varchar(128) NOT NULL,
  `ENABLED` int(1) NOT NULL,
  `DELETE_FLG` int(1) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_user
-- ----------------------------
INSERT INTO `app_user` VALUES ('1', 'dbadmin1', 'PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', '0', '1');
INSERT INTO `app_user` VALUES ('2', 'dbuser1', 'PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', '0', '1');
INSERT INTO `app_user` VALUES ('3', 'namnv', '123456', '1', '1');
INSERT INTO `app_user` VALUES ('4', 'member', '123456', '1', '0');

-- ----------------------------
-- Table structure for `articles`
-- ----------------------------
DROP TABLE IF EXISTS `articles`;
CREATE TABLE `articles` (
  `ARTICLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(200) DEFAULT NULL,
  `CATEGORY` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ARTICLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of articles
-- ----------------------------
INSERT INTO `articles` VALUES ('1', 'Java Concurency', 'Java');
INSERT INTO `articles` VALUES ('2', 'Spring MVC With Hibernate', 'Spring');
INSERT INTO `articles` VALUES ('3', 'Hibernate HQL', 'Hibernate');

-- ----------------------------
-- Table structure for `persistent_logins`
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `USERNAME` varchar(64) NOT NULL,
  `SERIES` varchar(64) NOT NULL,
  `TOKEN` varchar(64) NOT NULL,
  `LAST_USED` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `ID` int(19) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(19) NOT NULL,
  `ROLE_ID` int(19) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '1', '2');
INSERT INTO `user_role` VALUES ('3', '2', '2');
INSERT INTO `user_role` VALUES ('4', '3', '1');
INSERT INTO `user_role` VALUES ('5', '4', '2');
