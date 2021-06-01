/*
Navicat MySQL Data Transfer

Source Server         : IDMS
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : idms

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2020-10-28 09:21:53
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
  `is_active` int(1) NOT NULL DEFAULT '1' COMMENT 'trang thai hoat dong: 0: không hoạt động, 1: hoạt động',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_menu
-- ----------------------------
INSERT INTO `app_menu` VALUES ('1', 'Cấu hình', '/config', null, '2', '0');
INSERT INTO `app_menu` VALUES ('2', 'Cấu hình tài khoản', '/config/user', '1', '3', '0');
INSERT INTO `app_menu` VALUES ('3', 'Cấu hình vai trò', '/config/function', '1', '4', '0');
INSERT INTO `app_menu` VALUES ('4', 'Trang chủ', '/home', null, '1', '1');
INSERT INTO `app_menu` VALUES ('5', 'Cấu hình thông báo', '/config/notif', '1', '5', '0');
INSERT INTO `app_menu` VALUES ('6', 'Quản lý Danh mục', '/catalog', null, '6', '1');
INSERT INTO `app_menu` VALUES ('7', 'Danh mục Khách hàng(Chủ)', '/catalog/customer', '6', '7', '1');
INSERT INTO `app_menu` VALUES ('8', 'Danh mục Thú', '/catalog/pet', '6', '8', '1');
INSERT INTO `app_menu` VALUES ('9', 'Danh mục Thuốc', '/catalog/medicine', '6', '9', '1');
INSERT INTO `app_menu` VALUES ('10', 'Quản lý Khám chữa', '/examination', null, '10', '1');
INSERT INTO `app_menu` VALUES ('11', 'Quản lý Đặt lịch Khám ', '/booking', null, '11', '1');
INSERT INTO `app_menu` VALUES ('12', 'Báo cáo', '/report', null, '12', '1');
