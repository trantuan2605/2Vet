/*
Navicat MySQL Data Transfer

Source Server         : IDMS
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : idms

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2020-12-18 18:14:31
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `registration_examination`
-- ----------------------------
DROP TABLE IF EXISTS `registration_examination`;
CREATE TABLE `registration_examination` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `pet_code` varchar(20) DEFAULT NULL,
  `start_time` date DEFAULT NULL,
  `branch_code` varchar(100) DEFAULT NULL,
  `doctor_code` varchar(100) DEFAULT NULL,
  `symptom_descript` varchar(256) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  `status` smallint(1) DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of registration_examination
-- ----------------------------
INSERT INTO `registration_examination` VALUES ('1', 'TN000001', '2020-12-16', '2VET000001', 'BS000001', 'abc', 'test', null, '2020-12-17');
INSERT INTO `registration_examination` VALUES ('8', 'PET00009', '2020-12-17', '2VET000011', '2VET000011', null, null, null, '2020-12-17');
INSERT INTO `registration_examination` VALUES ('9', 'NAO01', '2020-12-18', '2VET000011', '2VET000011', null, null, null, '2020-12-18');
INSERT INTO `registration_examination` VALUES ('10', 'PET000021', '2020-12-18', '2VET000011', '2VET000011', null, null, null, '2020-12-18');
