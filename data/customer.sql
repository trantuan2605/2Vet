/*
Navicat MySQL Data Transfer

Source Server         : IDMS
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : idms

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2020-10-26 17:11:48
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL COMMENT 'id khách hàng',
  `cus_code` varchar(10) NOT NULL COMMENT 'Mã khách hàng',
  `cus_name` varchar(50) NOT NULL COMMENT 'tên khách hàng',
  `address` varchar(100) DEFAULT NULL COMMENT 'địa chỉ khách hàng',
  `phone` varchar(15) NOT NULL COMMENT 'sđt khách hàng ',
  `email` varchar(100) DEFAULT NULL COMMENT 'email khách hàng',
  `identity_no` varchar(15) DEFAULT NULL COMMENT 'số cmt khách hàng',
  `cus_type` int(1) DEFAULT '0' COMMENT 'Loại khách hàng',
  `tax_code` varchar(50) DEFAULT NULL COMMENT 'Mã số thuế khách hàng',
  `remark` varchar(1000) CHARACTER SET utf8 DEFAULT NULL COMMENT 'Ghi chú',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('0', 'KH000007', 'Nguyễn Thị Minh Trang', null, '0366355435', null, null, '0', null, null);
INSERT INTO `customer` VALUES ('1', 'KH000001', 'Trần Tiến Đức', 'Số 1 Thái Hà', '0366355435', 'ductt@gmail.com', '013261933', '0', null, null);
INSERT INTO `customer` VALUES ('2', 'KH000002', 'Phạm Thùy Dương', 'Số 333 Kim Ngưu', '0366355435', 'duongpt@gmail.com', '013261933', '0', null, null);
INSERT INTO `customer` VALUES ('3', 'KH000003', 'Phùng Thanh Độ', '15/23 Trung Kính', '0366355435', 'dopt@gmail.com', '013261933', '0', null, null);
INSERT INTO `customer` VALUES ('4', 'KH000004', 'Nguyễn Tiến Hoàng', 'Số 5 ngõ 99/33 Lê Văn Lương', '0366355435', 'hoangnt@gmail.com', '013261933', '0', null, null);
INSERT INTO `customer` VALUES ('5', 'KH000005', 'Trần Đức Hiếu', null, '0366355435', null, null, '0', null, null);
INSERT INTO `customer` VALUES ('6', 'KH000006', 'Lưu Thanh Hương', null, '0366355435', null, null, '0', null, null);
INSERT INTO `customer` VALUES ('8', 'KH000008', 'Ngô Trần Linh', null, '0366355435', null, null, '0', null, null);
INSERT INTO `customer` VALUES ('9', 'KH000009', 'Lê Thu Trúc', null, '0366355435', null, null, '0', null, null);
INSERT INTO `customer` VALUES ('10', 'KH000010', 'Phạm Thu Thảo', null, '0366355435', null, null, '0', null, null);
