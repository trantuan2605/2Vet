/*
Navicat MySQL Data Transfer

Source Server         : IDMS
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : idms

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2020-10-26 17:11:55
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `pet`
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet` (
  `id` int(11) NOT NULL COMMENT 'id thú nuôi',
  `pet_code` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT 'mã thú nuôi',
  `cus_code` varchar(10) NOT NULL COMMENT 'mã khách hàng',
  `pet_type` int(1) NOT NULL DEFAULT '0' COMMENT 'loại thú nuôi',
  `pet_name` varchar(100) NOT NULL COMMENT 'tên thú nuôi',
  `sex` int(1) NOT NULL COMMENT 'giới tính',
  `pet_breed` varchar(100) DEFAULT NULL COMMENT 'giống vật nuôi',
  `certificate_birth` varchar(15) DEFAULT NULL COMMENT 'Giấy khai sinh',
  `remark` varchar(1000) CHARACTER SET utf8 DEFAULT NULL COMMENT 'Ghi chú',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of pet
-- ----------------------------
INSERT INTO `pet` VALUES ('0', 'TN000002', 'KH000001', '0', 'Bon', '0', null, null, null);
INSERT INTO `pet` VALUES ('1', 'TN000001', 'KH000001', '0', 'Mic', '0', 'Chó Phú Quốc', '', null);
INSERT INTO `pet` VALUES ('3', 'TN000003', 'KH000002', '0', 'Lu', '1', 'Chó Corgi', 'C010203', null);
