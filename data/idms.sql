/*
Navicat MySQL Data Transfer

Source Server         : IDMS
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : idms

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2020-12-13 15:35:15
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
  `icon` varchar(50) NOT NULL DEFAULT 'fas fa-tachometer-alt' COMMENT 'icon menu',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app_menu
-- ----------------------------
INSERT INTO `app_menu` VALUES ('1', 'Cấu hình', '/config', null, '2', '0', 'fas fa-tachometer-alt');
INSERT INTO `app_menu` VALUES ('2', 'Cấu hình tài khoản', '/config/user', '1', '3', '0', 'fas fa-tachometer-alt');
INSERT INTO `app_menu` VALUES ('3', 'Cấu hình vai trò', '/config/function', '1', '4', '0', 'fas fa-tachometer-alt');
INSERT INTO `app_menu` VALUES ('4', 'Trang chủ', '/home', null, '1', '1', 'fas fa-home');
INSERT INTO `app_menu` VALUES ('5', 'Cấu hình thông báo', '/config/notif', '1', '5', '0', 'fas fa-tachometer-alt');
INSERT INTO `app_menu` VALUES ('6', 'Quản lý Danh mục', '/catalog', null, '6', '1', 'fas fa-tv');
INSERT INTO `app_menu` VALUES ('7', 'Danh mục Khách hàng(Chủ)', '/catalog/customer', '6', '7', '1', 'fas fa-users');
INSERT INTO `app_menu` VALUES ('8', 'Danh mục Thú', '/catalog/pet', '6', '8', '1', 'fas fa-paw');
INSERT INTO `app_menu` VALUES ('9', 'Danh mục Thuốc', '/catalog/medicine', '6', '9', '1', 'fas fa-capsules');
INSERT INTO `app_menu` VALUES ('10', 'Quản lý Khám chữa', '/examination', null, '10', '1', 'fas fa-book-medical');
INSERT INTO `app_menu` VALUES ('11', 'Quản lý Đặt lịch Khám ', '/booking', null, '11', '1', 'fas fa-calendar-plus');
INSERT INTO `app_menu` VALUES ('12', 'Báo cáo', '/report', null, '12', '1', 'fas fa-list-alt');
INSERT INTO `app_menu` VALUES ('13', 'Danh mục Loài', '/catalog/breed', '6', '13', '1', 'fas fa-paw');
INSERT INTO `app_menu` VALUES ('14', 'Danh mục Giống', '/catalog/spec', '6', '14', '1', 'fas fa-paw');
INSERT INTO `app_menu` VALUES ('15', 'Danh mục Siêu âm', '/catalog/sonic', '6', '15', '1', 'fas fa-digital-tachograph');
INSERT INTO `app_menu` VALUES ('16', 'Danh mục XQ', '/catalog/xray', '6', '16', '1', 'fas fa-x-ray');
INSERT INTO `app_menu` VALUES ('17', 'Danh mục Chi nhánh', '/catalog/branch', '6', '17', '1', 'fas fa-x-ray');
INSERT INTO `app_menu` VALUES ('18', 'Danh mục bác sĩ', '/catalog/doctor', '6', '18', '1', 'fas fa-user-nurse');
INSERT INTO `app_menu` VALUES ('19', 'Danh mục tiêm phòng', '/catalog/vacination', '6', '19', '1', 'fas fa-syringe');
INSERT INTO `app_menu` VALUES ('20', 'Danh mục test nhanh', '/catalog/quicktest', '6', '20', '1', 'fas fa-fighter-jet');
INSERT INTO `app_menu` VALUES ('21', 'Danh mục dịch vụ', '/catalog/service', '6', '21', '1', 'fas fa-menorah');

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
INSERT INTO `app_role` VALUES ('2', 'ROLE_USER');

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
INSERT INTO `app_user` VALUES ('3', 'namnv', '123456', '1', '0');
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
-- Table structure for `branch`
-- ----------------------------
DROP TABLE IF EXISTS `branch`;
CREATE TABLE `branch` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT COMMENT 'id branch',
  `branch_code` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT 'Mã Branch',
  `branch_name` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT 'Tên chi nhánh',
  `address` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT 'Địa chỉ',
  `phone` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT 'Số điện thoại',
  `descript` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT 'Ghi chú',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of branch
-- ----------------------------
INSERT INTO `branch` VALUES ('1', '2VET000001', 'Kim Ngưu', 'Số 335 Kim Ngưu, Hai Bà Trưng, Hà Nội', '024 3826 3366', 'nnnm');
INSERT INTO `branch` VALUES ('2', '2VET000002', 'Kim Mã', 'Số 236 Kim Mã, Ba Đình, Hà Nội', '096 165 8126', '');
INSERT INTO `branch` VALUES ('3', '2VET000003', 'Cầu Giấy', 'Số 236 Cầu Giấy, Cầu Giấy, Hà Nội', '096 165 8126', 'có  đấy nhé');
INSERT INTO `branch` VALUES ('4', '2VET000004', 'Hai Bà Trưng', 'Số 236 Kim Mã, Hai Bà Trưng, Hà Nội', '096 165 8126', '');
INSERT INTO `branch` VALUES ('5', '2VET000005', 'Kim Mã', 'Số 236 Kim Mã, Ba Đình, Hà Nội', '096 165 8126', '');
INSERT INTO `branch` VALUES ('6', '2VET000006', 'Kim Mã', 'Số 236 Kim Mã, Ba Đình, Hà Nội', '096 165 8126', null);
INSERT INTO `branch` VALUES ('7', '2VET000007', 'Kim Mã', 'Số 236 Kim Mã, Ba Đình, Hà Nội', '096 165 8126', null);
INSERT INTO `branch` VALUES ('8', '2VET000008', 'Kim Mã', 'Số 236 Kim Mã, Ba Đình, Hà Nội', '096 165 8126', null);
INSERT INTO `branch` VALUES ('9', '2VET000009', 'Kim Mã', 'Số 236 Kim Mã, Ba Đình, Hà Nội', '096 165 8126', '5620');
INSERT INTO `branch` VALUES ('10', '2VET000010', 'Kim Mã', 'Số 236 Kim Mã, Ba Đình, Hà Nội', '096 165 8126', 'có ghi chú thì note vào đâyccc213');
INSERT INTO `branch` VALUES ('11', '2VET000011', '2Vet Ngọc Khánh', 'hiui', '0123457890', 'mnbb');
INSERT INTO `branch` VALUES ('12', '2VET000013', '2Vet Xuân Thủy', '123 Xuân Thủy - Cầu Giấy - Hà Nội', '79513214', 'không có ghi chú mmm');
INSERT INTO `branch` VALUES ('13', '2VET000014', 'Hà Đông', 'Số 88 - Mỗ Lao - Hà Đông', '0245614520', '');
INSERT INTO `branch` VALUES ('14', '2VET000015', 'Hoàn Kiếm', 'Số 99 Hai Bà Trưng - Hoàn Kiếm - Hà Nội', '024 256 1235', 'thêm mới nhỉ ahiho');
INSERT INTO `branch` VALUES ('15', '2VET000017', '2Vet Bạch Mai', 'Số 121 Bạch Mai - Hai Bà Trưng - Hà Nội', '023 256 1012', '2 vét bạch mai');
INSERT INTO `branch` VALUES ('16', '2VET000050', 'Ngọc Khánh', '256 Ngọc Khánh - Ba Đình - Hà Nội', '0125478587', 'Chi nhánh ngọc khánh');

-- ----------------------------
-- Table structure for `breed`
-- ----------------------------
DROP TABLE IF EXISTS `breed`;
CREATE TABLE `breed` (
  `ID` smallint(6) NOT NULL AUTO_INCREMENT,
  `BREED_CODE` varchar(100) NOT NULL,
  `BREED_NAME` varchar(200) NOT NULL,
  `DESCRIPT` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of breed
-- ----------------------------
INSERT INTO `breed` VALUES ('1', 'L001', 'Chó', null);
INSERT INTO `breed` VALUES ('2', 'L002', 'Mèo', '1');
INSERT INTO `breed` VALUES ('3', 'L003', 'Lợn', null);
INSERT INTO `breed` VALUES ('4', 'L004', 'Gà', null);
INSERT INTO `breed` VALUES ('5', 'L005', 'Chuột', null);
INSERT INTO `breed` VALUES ('6', 'L006', 'Tê tê', null);
INSERT INTO `breed` VALUES ('7', 'L0007', 'Test11111', 'Test11111222');
INSERT INTO `breed` VALUES ('8', 'L0008', 'Test1111111', '545454');
INSERT INTO `breed` VALUES ('10', 'L0009', 'Test', 'Test');
INSERT INTO `breed` VALUES ('11', 'L0010', 'Test', 'Test');

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id khách hàng',
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', 'KH000001', 'Trần Tiến Đức', 'Số 1 Thái Hà', '0366355435', 'ductt@gmail.com', '013261933', '0', null, null);
INSERT INTO `customer` VALUES ('2', 'KH000002', 'Phạm Thùy Dương', 'Số 333 Kim Ngưu - HBT', '0366355435', 'duongpt@gmail.com', '013261933', '4', '', '');
INSERT INTO `customer` VALUES ('3', 'KH000003', 'Phùng Thanh Độ', '15/23 Trung Kính', '0366355435', 'dopt@gmail.com', '013261933', '1', '', '');
INSERT INTO `customer` VALUES ('4', 'KH000004', 'Nguyễn Tiến Hoàng', 'Số 5 ngõ 99/33 Lê Văn Lương', '0366355435', 'hoangnt@gmail.com', '013261933', '0', null, null);
INSERT INTO `customer` VALUES ('5', 'KH000005', 'Trần Đức Hiếu', null, '0366355435', null, null, '0', null, null);
INSERT INTO `customer` VALUES ('6', 'KH000006', 'Lưu Thanh Hương', null, '0366355435', null, null, '0', null, null);
INSERT INTO `customer` VALUES ('7', 'KH000007', 'Nguyễn Thị Minh Trang', null, '0366355435', null, null, '0', null, null);
INSERT INTO `customer` VALUES ('8', 'KH000008', 'Ngô Trần Linh', null, '0366355435', null, null, '0', null, null);
INSERT INTO `customer` VALUES ('9', 'KH000009', 'Lê Thu Trúc', 'Tân Mai - Hoàng Mai - Hà Nội', '0366355435', 'namnv@rikkeisoft.com', '', '1', '', '');
INSERT INTO `customer` VALUES ('10', 'KH000010', 'Phạm Thu Thảo', null, '0366355435', null, null, '0', null, null);
INSERT INTO `customer` VALUES ('11', 'KH000012', 'Trần Văn Tuấn', 'Từ Liêm - Hà Nội', '0124568412', 'tuantv@gmail.com', '012450123', '1', '12340120', 'thử test tí');
INSERT INTO `customer` VALUES ('12', 'KH000011', 'Nguyễn Văn Nam', 'Tây Hồ - Hà Nội', '0124501222', 'namnv@gmail.com', '8745120', '1', '01200', 'không có thú cưng nào');
INSERT INTO `customer` VALUES ('13', 'Testa', 'Test Server', 'Hà Tây', '0123567809', 'namnv@rikkeisoft.com', '', '4', '', 'không có ghi chú');
INSERT INTO `customer` VALUES ('14', 'nanvs', 'sdfghh', 'Tân Mai - Hoàng Mai - Hà Nội', '1234560', 'tuantv@gmail.com', '', '1', '', '');
INSERT INTO `customer` VALUES ('15', 'bbbb', 'đâsw', 'Hà Nội 2', '1234560', 'tuantv@gmail.com', '', '3', '', '');
INSERT INTO `customer` VALUES ('16', 'bbb', 'qưq', 'Tân Mai - Hoàng Mai - Hà Nội', '1234560', 'namnv@rikkeisoft.com', '', '2', '', '');
INSERT INTO `customer` VALUES ('17', 'TYT', 'Nguyễn Thị Tịt', 'Tân Mai - Hoàng Mai - Hà Nội', '34543543', 'hieunv@rikkeisoft.com', '', '2', '', '');
INSERT INTO `customer` VALUES ('18', 'vvv', 'Trần Tvvvvv', 'Hà Nội 2', '1234560', 'tuantv@gmail.com', '', '2', '', '');

-- ----------------------------
-- Table structure for `doctor`
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT COMMENT ' Code khách hàng',
  `doctor_code` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT ' Mã bác sỹ',
  `doctor_name` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT ' Tên bác sỹ',
  `gender` int(1) DEFAULT NULL COMMENT ' Giới tính',
  `cmnd` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT ' Chứng minh nhân dân',
  `address` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT ' Địa chỉ',
  `dob` datetime NOT NULL COMMENT ' Ngày thánh năm sinh',
  `phone` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT ' Số điện thoại',
  `email` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT ' Email',
  `majors` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT ' Chuyên môn',
  `experience` text CHARACTER SET utf8 NOT NULL COMMENT ' Kinh nghiệm',
  `descript` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT ' Ghi chú',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES ('1', 'BS000001', 'Trần Tiến Đức', '1', '013261933', 'Số 1 Thái Hà', '1971-11-29 11:12:10', '0399355435', 'ductt@gmail.com', 'Tiêu hóa', '02 năm làm việc, nghiên cứu tại Khoa Tiêu Hóa của Viện Thú Y Hà Nội01 năm làm việc tại Thú Y 123', 'mmmmm');
INSERT INTO `doctor` VALUES ('2', 'BS000002', 'Phạm Thị Thùy Dương', '0', '013261933', 'Số 1 Thái Hà', '1971-11-29 11:12:10', '0399355435', 'duongtt@gmail.com', 'Xương khớp', '02 năm làm việc, nghiên cứu tại Khoa Tiêu Hóa của Viện Thú Y Hà Nội01 năm làm việc tại Thú Y 123', '');
INSERT INTO `doctor` VALUES ('3', 'BS000003', 'Phùng Thanh Độ', '1', '013261933', 'Số 1 Thái Hà', '1971-11-29 11:12:10', '0399355435', 'domixi@gmail.com', 'Đa khoa', '02 năm làm việc, nghiên cứu tại Khoa Tiêu Hóa của Viện Thú Y Hà Nội\r\n01 năm làm việc tại Thú Y 123', null);
INSERT INTO `doctor` VALUES ('4', 'BS000004', 'Nguyễn Tiến Hoàng', '1', '013261933', 'Số 1 Thái Hà', '1971-11-29 11:12:10', '0399355435', 'hoangnt@gmail.com', 'Phụ sản', '02 năm làm việc, nghiên cứu tại Khoa Tiêu Hóa của Viện Thú Y Hà Nội01 năm làm việc tại Thú Y 123', 'bbbbnnnnn');
INSERT INTO `doctor` VALUES ('5', 'BS000005', 'Trần Đức Hiếu', '1', '013261933', 'Số 1 Thái Hà', '1971-11-29 11:12:10', '0399355435', 'hieutd@gmail.com', 'Xương khớp', '02 năm làm việc, nghiên cứu tại Khoa Tiêu Hóa của Viện Thú Y Hà Nội\r\n01 năm làm việc tại Thú Y 123', null);
INSERT INTO `doctor` VALUES ('6', 'BS000006', 'Trần Văn Tuấn', '1', '013261933', 'Số 1 Thái Hà', '1971-11-29 11:12:10', '0399355435', 'tuantv@gmail.com', 'Xương khớp', '02 năm làm việc, nghiên cứu tại Khoa Tiêu Hóa của Viện Thú Y Hà Nội\r\n01 năm làm việc tại Thú Y 123', null);
INSERT INTO `doctor` VALUES ('7', 'BS000007', 'Nguyễn Ngọc Viện', '1', '013261933', 'Số 1 phủ Sóc', '1971-11-29 11:12:10', '0399355435', 'viennn@gmail.com', 'Xương khớp', '02 năm làm việc, nghiên cứu tại Khoa Tiêu Hóa của Viện Thú Y Hà Nội01 năm làm việc tại Thú Y 123', '');
INSERT INTO `doctor` VALUES ('8', 'BS000008', 'Vũ Trần Tiến', '1', '013261933', 'Đông Anh', '1971-11-29 11:12:10', '0399355435', 'tienvt@gmail.com', 'Xương khớp', '02 năm làm việc, nghiên cứu tại Khoa Tiêu Hóa của Viện Thú Y Hà Nội\r\n01 năm làm việc tại Thú Y 123', null);
INSERT INTO `doctor` VALUES ('9', 'BS000009', 'Nguyễn Văn Quí', '1', '013261933', 'Số 1 Vin smart', '1971-11-29 11:12:10', '0399355435', 'quinv@gmail.com', 'Xương khớp', '02 năm làm việc, nghiên cứu tại Khoa Tiêu Hóa của Viện Thú Y Hà Nội\r\n01 năm làm việc tại Thú Y 123', null);
INSERT INTO `doctor` VALUES ('10', 'BS000010', 'Trần Danh Hưng', '1', '013261933', 'Tân Mai - Hoàng Mai - Hà Nội', '1971-11-29 11:12:10', '0399355435', 'hungtd@gmail.com', 'Xương khớp háng', '02 năm làm việc, nghiên cứu tại Khoa Tiêu Hóa của Viện Thú Y Hà Nội01 năm làm việc tại Thú Y 12', '');
INSERT INTO `doctor` VALUES ('11', 'BS000011', 'Nguyễn Văn Tích', '1', '013261933', 'Số 1 Tây Nguyên Lâm Đồng', '1980-05-14 00:00:00', '0399355435', 'tichnv@gmail.com', 'Xương khớp', '<p>02 năm l&agrave;m việc, nghi&ecirc;n cứu tại Khoa Ti&ecirc;u H&oacute;a của Viện Th&uacute; Y H&agrave; Nội01 năm l&agrave;m việc tại Th&uacute; Y 123</p>', '');
INSERT INTO `doctor` VALUES ('12', 'BS000012', 'Bùi Quang Huy', '0', '013261933', 'Số 1 Mỹ Đức Hà Nội 2', '1991-11-29 11:12:10', '1654612', 'huybq@gmail.com', 'Xương khớp háng', '02 năm làm việc, nghiên cứu tại Khoa Tiêu Hóa của Viện Thú Y Hà Nội01 năm làm việc tại Thú Y 123', 'có ghi chú thì note vào đây');
INSERT INTO `doctor` VALUES ('13', 'BS000015', 'Nguyễn Văn Nam', '1', '168425410', 'Tây Hồ Hà Nội', '1991-11-29 11:12:10', '2323232', 'tuantv@gmail.com', 'Xương khớp', '5 năm trong nghề', 'Có ghi chú gì không');
INSERT INTO `doctor` VALUES ('14', 'BS000016', 'Trương Lan Anh', '0', '', 'Hà Nội 2', '1991-11-29 11:12:10', '1235464', 'tuantv@gmail.com', 'Xương khớp', '5 năm trong nghề này nhé', 'không có ghi chú bbb');
INSERT INTO `doctor` VALUES ('15', 'BS000019', 'Bác sĩ test', '0', '', 'Tân Mai - Hoàng Mai - Hà Nội', '1991-11-05 00:00:00', '1234560', 'namnv@rikkeisoft.com', '', '', '');
INSERT INTO `doctor` VALUES ('16', 'BS000020', 'Bác sĩ test 2', '0', '', 'Tân Mai - Hoàng Mai - Hà Nội', '1991-11-29 11:12:10', '1234560', 'tuantv@gmail.com', '', '', '');
INSERT INTO `doctor` VALUES ('17', 'BS000022', 'Bác sĩ thứ hai mươi hai', '1', '', 'Hà Tây 2', '1963-12-02 00:00:00', '1234560', 'namnv.2901@gmail.com', 'Xương khớp', '', '');
INSERT INTO `doctor` VALUES ('18', 'BS000025', 'Bác sĩ test ckEditor', '1', '', 'Tân Mai - Hoàng Mai - Hà Nội', '1963-08-01 00:00:00', '1234560', 'hieunv@rikkeisoft.com', 'Xương khớp háng', '<p><strong>C&oacute; chuy&ecirc;n m&ocirc;n trong lĩnh vực th&uacute; &yacute; 3 năm</strong></p><ol><li><em><strong>4 năm c&ocirc;ng t&aacute;c trong ngh&agrave;nh&nbsp;</strong></em></li><li><em><strong>6 năm l&agrave;m b&aacute;c sỹ th&uacute;y u</strong></em></li><li><em><strong>C&ograve;n g&igrave; nữa đ&acirc;u m&agrave; kh&oacute;c với sầu</strong></em></li></ol>', '');
INSERT INTO `doctor` VALUES ('19', 'BS000026', 'test bs 26', '1', '', 'Tân Mai - Hoàng Mai - Hà Nội', '1984-01-05 00:00:00', '1234560', 'namnv@rikkeisoft.com', '', '', '');
INSERT INTO `doctor` VALUES ('20', 'BS000027', 'test bs 27', '0', '', 'Tân Mai - Hoàng Mai - Hà Nội', '2020-12-09 14:00:19', '1234560', 'namnv@rikkeisoft.com', '', '', '');
INSERT INTO `doctor` VALUES ('21', 'BS000028', 'Bác sĩ test 28', '1', '', 'Hà Tây', '1993-12-01 00:00:00', '1234560', 'tuantv@gmail.com', '', '<p><strong>Ghi lyric một b&agrave;i h&aacute;t v&agrave;o đ&acirc;y</strong></p>', '');

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
-- Table structure for `pet`
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id thú nuôi',
  `pet_code` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT 'mã thú nuôi',
  `cus_code` varchar(10) NOT NULL COMMENT 'mã khách hàng',
  `pet_type` int(1) NOT NULL DEFAULT '0' COMMENT 'loại thú nuôi',
  `pet_name` varchar(100) NOT NULL COMMENT 'tên thú nuôi',
  `sex` int(1) NOT NULL COMMENT 'giới tính',
  `pet_breed` varchar(100) DEFAULT NULL COMMENT 'giống vật nuôi',
  `certificate_birth` varchar(15) DEFAULT NULL COMMENT 'Giấy khai sinh',
  `remark` varchar(1000) CHARACTER SET utf8 DEFAULT NULL COMMENT 'Ghi chú',
  `is_deleted` int(1) DEFAULT '0' COMMENT '0: hoạt động, 1: deleted',
  `SPECIES_CODE` varchar(20) NOT NULL COMMENT 'giống thú cưng',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of pet
-- ----------------------------
INSERT INTO `pet` VALUES ('1', 'TN000001', 'KH000001', '0', 'Mic', '0', 'Chó Phú Quốc', '', null, '0', '0');
INSERT INTO `pet` VALUES ('2', 'TN000002', 'KH000001', '0', 'Bon', '0', null, null, '', '0', 'G00002');
INSERT INTO `pet` VALUES ('3', 'TN000003', 'KH000002', '0', 'Lu', '1', 'Chó Corgi', 'C010203', null, '0', '0');
INSERT INTO `pet` VALUES ('4', 'PET000004', 'KH000012', '0', 'NHOI', '1', null, 'kgs-875456', null, '0', '0');
INSERT INTO `pet` VALUES ('5', 'PET000005', 'KH000012', '0', 'huskey', '0', null, 'kgs-8754562', null, '1', '0');
INSERT INTO `pet` VALUES ('6', 'PET0006', 'KH000013', '0', 'huskey', '1', null, 'kgs-875456', null, '0', '0');
INSERT INTO `pet` VALUES ('7', 'PET0007', 'KH000013', '0', 'NHOI 2', '0', null, 'kgs-8754563612', null, '0', '0');
INSERT INTO `pet` VALUES ('10', 'PET00008', 'KH000014', '0', 'ahhihi', '1', null, '1oid', '', '0', 'G00004');
INSERT INTO `pet` VALUES ('11', 'PET00009', 'KH000014', '0', 'chó con', '1', null, 'kgs', '', '0', 'G00002');
INSERT INTO `pet` VALUES ('12', 'PET0010', 'KH000015', '0', 'thú cưng 1', '1', null, '14521', null, '0', '0');
INSERT INTO `pet` VALUES ('13', 'PET0011', 'KH000015', '0', 'thú cưng 2', '1', null, '140', null, '0', '0');
INSERT INTO `pet` VALUES ('14', 'PET000012', 'KH000016', '0', 'con thú 1', '1', null, '1450', '', '0', 'G00006');
INSERT INTO `pet` VALUES ('15', 'PET00011', 'KH000017', '0', 'Cún Nhật', '1', null, 'kdahaaa', null, '1', '0');
INSERT INTO `pet` VALUES ('16', 'PET00012', 'KH000017', '0', 'Cún Tây Âu', '1', null, 'ơussssss', null, '1', '0');
INSERT INTO `pet` VALUES ('17', 'PET000134', 'KH000017', '0', 'Cún Tây Mỹ', '1', null, '4342342', null, '0', '0');
INSERT INTO `pet` VALUES ('18', 'PET00020', 'KH000018', '0', 'cún', '1', null, 'khp-45120-1', null, '0', '0');
INSERT INTO `pet` VALUES ('19', 'CU012', 'TK12015', '0', 'sseesbvccc', '1', null, '1245-0ndhs', '', '0', 'G00002');
INSERT INTO `pet` VALUES ('20', 'tet', 'tet', '0', 'tet', '1', null, 'tet', '', '0', 'G00002');
INSERT INTO `pet` VALUES ('21', 'test', 'test', '0', 'test', '1', null, 'test123', null, '0', '0');
INSERT INTO `pet` VALUES ('22', 'bbbb', 'bbbb', '0', 'bbbb', '0', null, 'bbbb', null, '1', 'G00005');
INSERT INTO `pet` VALUES ('23', 'mmmm', 'mmmm', '0', 'mmmm', '1', null, 'mmmm', '', '0', 'G00003');
INSERT INTO `pet` VALUES ('24', 'qqqq', 'qqqq', '0', 'qqqq', '1', null, 'qqqq', null, '0', '0');
INSERT INTO `pet` VALUES ('25', 'cccc', 'cccc', '0', 'cccc', '1', null, 'cccc', null, '0', '0');
INSERT INTO `pet` VALUES ('27', 'sdsda', 'sdsda', '0', 'sdsda', '1', null, 'sdsda', null, '0', '0');
INSERT INTO `pet` VALUES ('31', 'T0125', 'PT001', '0', 'test T0125', '0', null, '12', 'mnvv', '0', '0');
INSERT INTO `pet` VALUES ('32', 'T0126', 'PT001', '0', 'test T0125', '0', null, '13', null, '1', '0');
INSERT INTO `pet` VALUES ('33', 'mmmm', 'PT001', '0', 'mmmm', '1', null, '34d', '', '1', 'G00003');
INSERT INTO `pet` VALUES ('34', 'nmnnnn', 'PT001', '0', 'bbbb', '0', null, 'ádasdas', null, '1', '0');
INSERT INTO `pet` VALUES ('35', 'đâs', 'PT001', '0', 'adasdas', '0', null, 'mmmm', null, '1', '0');
INSERT INTO `pet` VALUES ('36', 'sdsda', 'PT001', '0', 'bbbb', '1', null, 'sdsda', null, '1', '0');
INSERT INTO `pet` VALUES ('37', 'sdsda', 'PT001', '0', 'bbbb', '1', null, 'sdsda', null, '1', '0');
INSERT INTO `pet` VALUES ('38', 'sdsda', 'PT001', '0', 'tessrt 12', '0', null, '', null, '1', '0');
INSERT INTO `pet` VALUES ('39', 'PET000141', 'PT001', '0', 'ôrr4444', '0', null, '', null, '1', '0');
INSERT INTO `pet` VALUES ('40', '', 'KH000010', '0', 'Thú cưng của Thảo', '0', null, '', '', '0', 'G00004');
INSERT INTO `pet` VALUES ('41', 'kml12', 'nanvs', '0', 'sdsadas', '0', null, 'kgs-875456', null, '0', 'G0012');
INSERT INTO `pet` VALUES ('42', 'kml13', 'nanvs', '0', 'mkji', '0', null, null, null, '1', 'G00006');
INSERT INTO `pet` VALUES ('43', 'kml14', 'nanvs', '0', 'nnn', '0', null, null, null, '1', 'G00005');
INSERT INTO `pet` VALUES ('44', 'qe', 'bbbb', '0', 'thú cưng 1', '0', null, 'aa', null, '1', 'G00003');
INSERT INTO `pet` VALUES ('45', 'bg', 'bbbb', '0', 'huskey', '1', null, '', null, '1', 'G00001');
INSERT INTO `pet` VALUES ('46', 'gb', 'bbbb', '0', 'qd', '0', null, '', null, '1', 'G00006');
INSERT INTO `pet` VALUES ('47', 'bn', 'bbbb', '0', 'sdd', '0', null, '', null, '1', 'G00001');
INSERT INTO `pet` VALUES ('48', 'tg', 'bbbb', '0', 'ưd', '0', null, '', '', '0', 'G00004');
INSERT INTO `pet` VALUES ('49', 'nn', 'bbbb', '0', 'đ', '1', null, 'nm', null, '1', 'G00004');
INSERT INTO `pet` VALUES ('50', 'mn', 'bbbb', '0', 'mn', '0', null, '', null, '0', 'G0012');
INSERT INTO `pet` VALUES ('51', 'qa', 'bbbb', '0', 'bn', '1', null, 'mn', null, '1', 'G00006');
INSERT INTO `pet` VALUES ('52', 'qqq', 'bbbb', '0', 'vc', '0', null, 'fd', null, '1', 'G0012');
INSERT INTO `pet` VALUES ('53', 'cv', 'bbbb', '0', 'cv', '0', null, 'sc', null, '0', 'G00002');
INSERT INTO `pet` VALUES ('54', 'P1', 'bbb', '0', 'ax', '0', null, '', null, '1', 'G00001');
INSERT INTO `pet` VALUES ('55', 'P2', 'bbb', '0', 'xd', '1', null, 'nj', null, '1', 'G00006');
INSERT INTO `pet` VALUES ('56', 'M2', 'bbb', '0', 'mờ 2 ahah', '1', null, 'kh-ffff', null, '1', 'G0012');
INSERT INTO `pet` VALUES ('57', 'M3', 'bbb', '0', 'mờ 3 ahia', '0', null, 'hatyqya', null, '0', 'G00006');
INSERT INTO `pet` VALUES ('58', 'tr', 'TYT', '0', 'Tên thú là Tê Rờ', '1', null, '', '', '0', 'G00006');
INSERT INTO `pet` VALUES ('59', 'qqqq', 'KH000009', '0', 'bv', '1', null, '', null, '0', 'G00002');
INSERT INTO `pet` VALUES ('60', 'bvv', 'vvv', '0', 'huskey', '0', null, '', '', '0', 'G00001');
INSERT INTO `pet` VALUES ('61', 'bnb', 'vvv', '0', 'sdsdassss', '1', null, 'mnnn', '', '0', 'G00006');
INSERT INTO `pet` VALUES ('62', '', '0', '0', 'Thú cưng của Thảo', '0', null, '', '', '0', 'G00004');
INSERT INTO `pet` VALUES ('63', 'PET000020', 'KH000011', '0', 'Thú cưng 20', '0', null, '', '', '0', 'G00001');
INSERT INTO `pet` VALUES ('64', 'PET000021', 'KH000011', '0', 'Thú cưng thứ hai mươi mốt', '0', null, '', '', '0', 'G00001');
INSERT INTO `pet` VALUES ('65', 'PET000021', 'KH000011', '0', 'Thú cưng thứ hai mươi mốt', '0', null, '', '', '0', 'G00001');

-- ----------------------------
-- Table structure for `quick_test`
-- ----------------------------
DROP TABLE IF EXISTS `quick_test`;
CREATE TABLE `quick_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id test nhanh',
  `quick_code` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT ' Mã test nhanh',
  `quick_name` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT ' Tên test nhanh',
  `descript` varchar(2000) CHARACTER SET utf8 DEFAULT NULL COMMENT ' Ghi chú',
  `is_deleted` int(11) DEFAULT '0' COMMENT '0: hoạt động, 1: đã xóa',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of quick_test
-- ----------------------------
INSERT INTO `quick_test` VALUES ('1', 'TN00001', 'Test nhanh 1', 'Mô tả test nhanh 1', '0');
INSERT INTO `quick_test` VALUES ('2', 'TN00002', 'Test nhanh 2', 'Mô tả test nhanh 2', '0');
INSERT INTO `quick_test` VALUES ('3', 'TN00003', 'Test nhanh 3', 'Mô tả test nhanh 3', '0');
INSERT INTO `quick_test` VALUES ('4', 'TN00004', 'Test nhanh 4', 'Mô tả test nhanh 4', '0');
INSERT INTO `quick_test` VALUES ('5', 'TN00005', 'Test nhanh 5', 'Mô tả test nhanh 5', '0');
INSERT INTO `quick_test` VALUES ('6', 'TN00006', 'Test nhanh 6', 'Mô tả test nhanh 6', '0');
INSERT INTO `quick_test` VALUES ('7', 'TN00007', 'Test nhanh 7', 'Mô tả test nhanh 7', '0');
INSERT INTO `quick_test` VALUES ('8', 'TN00008', 'Test nhanh 8', 'Mô tả test nhanh 8', '0');
INSERT INTO `quick_test` VALUES ('9', 'TN00009', 'Test nhanh 9', 'Mô tả test nhanh 9', '0');
INSERT INTO `quick_test` VALUES ('10', 'TN00010', 'Test nhanh 10', 'Mô tả test nhanh 10', '0');
INSERT INTO `quick_test` VALUES ('11', 'TN00011', 'Test nhanh 11 nhé các bạn ahihi', 'Mô tả test nhanh 11 nhé ahihi', '0');
INSERT INTO `quick_test` VALUES ('12', 'TN00012', 'Test nhanh mười hai  cập nhật sau thêm mới', 'không có ghi chú cập nhật sau thêm mới', '0');
INSERT INTO `quick_test` VALUES ('13', 'TN00013', 'Test nhanh đấy ', 'ahihi', '0');

-- ----------------------------
-- Table structure for `services`
-- ----------------------------
DROP TABLE IF EXISTS `services`;
CREATE TABLE `services` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `service_code` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT 'Mã dịch vụ',
  `service_name` varchar(200) CHARACTER SET utf8 NOT NULL COMMENT 'Tên dịch vụ',
  `descript` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT 'Ghi chú',
  `is_deleted` int(1) DEFAULT '0' COMMENT '0: hoạt động, 1: deleted',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of services
-- ----------------------------
INSERT INTO `services` VALUES ('1', 'DV00001', 'Kiểm tra sức khỏe sơ bộ', '', '0');
INSERT INTO `services` VALUES ('2', 'DV00002', 'Kiểm tra tim phổi', '', '0');
INSERT INTO `services` VALUES ('3', 'DV00003', 'Kiểm tra hệ tiêu hóa', '', '0');
INSERT INTO `services` VALUES ('4', 'DV00004', 'Kiểm tra hệ tuần hoàn', '', '0');
INSERT INTO `services` VALUES ('5', 'DV00005', 'Kiểm tra xương khớp', '', '0');
INSERT INTO `services` VALUES ('6', 'DV00006', 'Kiểm tra sức khỏe sinh sản', '', '0');
INSERT INTO `services` VALUES ('7', 'DV00007', 'Chụp CT cắt lớp, cộng hưởng', '', '0');
INSERT INTO `services` VALUES ('8', 'DV00008', 'Kiểm tra định kỳ', '', '0');
INSERT INTO `services` VALUES ('9', 'DV00009', 'Kiểm tra thị lực', '', '0');
INSERT INTO `services` VALUES ('10', 'DV000010', 'Kiểm tra thính lực', '', '0');
INSERT INTO `services` VALUES ('11', 'DV000011', 'Siêu âm ổ bụng', '', '0');
INSERT INTO `services` VALUES ('12', 'DV000012', 'Kiểm tra tổng quát', '', '0');
INSERT INTO `services` VALUES ('13', 'DV000012', 'Kiểm tra tổng quát', '', '1');
INSERT INTO `services` VALUES ('14', 'DV000012', 'Kiểm tra tổng quát', '', '1');
INSERT INTO `services` VALUES ('15', 'DV000013', 'Dịch vụ Xét nghiệm lâm sàng', '', '0');
INSERT INTO `services` VALUES ('16', 'DVTEST', 'Dịch vụ test tổng hợp hô hấp', 'Test dịch vụ mới', '0');

-- ----------------------------
-- Table structure for `services_branch`
-- ----------------------------
DROP TABLE IF EXISTS `services_branch`;
CREATE TABLE `services_branch` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `service_code` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT 'Mã dịch vụ',
  `branch_code` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT 'Mã chi nhánh',
  `price` double(11,0) NOT NULL COMMENT 'Giá tiền',
  `is_deleted` int(1) DEFAULT '0' COMMENT '0: hoạt động, 1: xóa',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of services_branch
-- ----------------------------
INSERT INTO `services_branch` VALUES ('1', 'DV00001', '2VET000017', '715000', '1');
INSERT INTO `services_branch` VALUES ('2', 'DV00002', '2VET000017', '136000', '1');
INSERT INTO `services_branch` VALUES ('3', 'DV000012', '2VET000015', '547000', '1');
INSERT INTO `services_branch` VALUES ('4', 'DV000013', '2VET000015', '813000', '1');
INSERT INTO `services_branch` VALUES ('5', 'DV000011', '2VET000013', '100000', '0');
INSERT INTO `services_branch` VALUES ('6', 'DV000012', '2VET000013', '252000', '0');
INSERT INTO `services_branch` VALUES ('7', 'DV00001', '2VET000011', '252000', '0');
INSERT INTO `services_branch` VALUES ('8', 'DV00002', '2VET000011', '100000', '0');
INSERT INTO `services_branch` VALUES ('9', 'DV000012', '2VET000015', '100000', '1');
INSERT INTO `services_branch` VALUES ('10', 'DV000013', '2VET000015', '996000', '1');
INSERT INTO `services_branch` VALUES ('11', 'DV000012', '2VET000017', '547000', '1');
INSERT INTO `services_branch` VALUES ('12', 'DV000013', '2VET000017', '50000', '1');
INSERT INTO `services_branch` VALUES ('13', 'DV000012', '2VET000017', '459000', '1');
INSERT INTO `services_branch` VALUES ('14', 'DV00001', '2VET000017', '316000', '1');
INSERT INTO `services_branch` VALUES ('15', 'DV00002', '2VET000017', '50000', '1');
INSERT INTO `services_branch` VALUES ('16', 'DV00001', '2VET000017', '554000', '1');
INSERT INTO `services_branch` VALUES ('17', 'DV00002', '2VET000017', '50000', '1');
INSERT INTO `services_branch` VALUES ('18', 'DV00001', '2VET000017', '535000', '1');
INSERT INTO `services_branch` VALUES ('19', 'DV00002', '2VET000017', '316000', '1');
INSERT INTO `services_branch` VALUES ('20', 'DV000013', '2VET000017', '50000', '1');
INSERT INTO `services_branch` VALUES ('21', 'DV000013', '2VET000017', '100000', '1');
INSERT INTO `services_branch` VALUES ('22', 'DV00001', '2VET000017', '782000', '1');
INSERT INTO `services_branch` VALUES ('23', 'DV000012', '2VET000017', '100000', '1');
INSERT INTO `services_branch` VALUES ('24', 'DV000013', '2VET000017', '734000', '1');
INSERT INTO `services_branch` VALUES ('25', 'DV000012', '2VET000017', '107000', '1');
INSERT INTO `services_branch` VALUES ('26', 'DV000010', '2VET000017', '100000', '1');
INSERT INTO `services_branch` VALUES ('27', 'DV00001', '2VET000017', '753000', '1');
INSERT INTO `services_branch` VALUES ('28', 'DV00002', '2VET000017', '50000', '1');
INSERT INTO `services_branch` VALUES ('29', 'DV00001', '2VET000017', '2805000', '1');
INSERT INTO `services_branch` VALUES ('30', 'DV00002', '2VET000017', '100000', '1');
INSERT INTO `services_branch` VALUES ('31', 'DV000013', '2VET000017', '2131000', '1');
INSERT INTO `services_branch` VALUES ('32', 'DV000012', '2VET000017', '222000', '1');
INSERT INTO `services_branch` VALUES ('33', 'DV000013', '2VET000017', '1048000', '1');
INSERT INTO `services_branch` VALUES ('34', 'DV000013', '2VET000008', '100000', '0');
INSERT INTO `services_branch` VALUES ('35', 'DV00002', '2VET000008', '1464000', '0');
INSERT INTO `services_branch` VALUES ('36', 'DV00003', '2VET000008', '480000', '0');
INSERT INTO `services_branch` VALUES ('37', 'DV000013', '2VET000017', '100000', '1');
INSERT INTO `services_branch` VALUES ('38', 'DV00002', '2VET000017', '100000', '1');
INSERT INTO `services_branch` VALUES ('39', 'DV00003', '2VET000017', '100000', '1');
INSERT INTO `services_branch` VALUES ('40', 'DV00004', '2VET000017', '100000', '1');
INSERT INTO `services_branch` VALUES ('41', 'DV00005', '2VET000017', '100000', '1');
INSERT INTO `services_branch` VALUES ('42', 'DV000012', '2VET000017', '1808000', '1');
INSERT INTO `services_branch` VALUES ('43', 'DV00002', '2VET000017', '100000', '1');
INSERT INTO `services_branch` VALUES ('44', 'DV000012', '2VET000015', '100000', '0');
INSERT INTO `services_branch` VALUES ('45', 'DV00001', '2VET000017', '604000', '1');
INSERT INTO `services_branch` VALUES ('46', 'DV000013', '2VET000050', '100000', '0');
INSERT INTO `services_branch` VALUES ('47', 'DV000012', '2VET000050', '681000', '0');
INSERT INTO `services_branch` VALUES ('48', 'DVTEST', '2VET000050', '537000', '0');

-- ----------------------------
-- Table structure for `species`
-- ----------------------------
DROP TABLE IF EXISTS `species`;
CREATE TABLE `species` (
  `ID` smallint(6) NOT NULL AUTO_INCREMENT,
  `SPECIES_CODE` varchar(254) NOT NULL,
  `SPECIES_NAME` varchar(100) NOT NULL,
  `BREED_CODE` varchar(500) NOT NULL,
  `DESCRIPT` varchar(200) DEFAULT NULL,
  `IS_DELETE` smallint(1) DEFAULT '0' COMMENT '0: hoạt động, 1: deleted',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of species
-- ----------------------------
INSERT INTO `species` VALUES ('1', 'G00001', 'Chó Phú Quốc', 'L001', 'Mô tả 1', '0');
INSERT INTO `species` VALUES ('2', 'G00002', 'Chó Corgi', 'L001', 'Mô tả 2', '0');
INSERT INTO `species` VALUES ('3', 'G00003', 'Mèo Anh', 'L002', 'Mô tả mèo anh', '0');
INSERT INTO `species` VALUES ('4', 'G00004', 'Mèo Nga', 'L002', 'Mô tả mèo nga', '0');
INSERT INTO `species` VALUES ('5', 'G00005', 'Mèo Pháp', 'L002', 'Mô tả mèo pháp', '0');
INSERT INTO `species` VALUES ('6', 'G00006', 'Mèo Mướp', 'L002', 'Mô tả mèo Mướp', '0');
INSERT INTO `species` VALUES ('7', 'G0008', 'Test Add', 'L0010', 'Test Add', '0');
INSERT INTO `species` VALUES ('8', 'G0009', 'Test Add', 'L0010', 'Test Add', '0');
INSERT INTO `species` VALUES ('9', 'G0010', 'Test Add', 'L0010', 'Test Add', '0');
INSERT INTO `species` VALUES ('10', 'G0011', 'Test Add', 'L0008', 'Test Add', '0');
INSERT INTO `species` VALUES ('11', 'G0012', 'Test Add111122223', 'L001', 'Test Add2222', '0');

-- ----------------------------
-- Table structure for `supersonic`
-- ----------------------------
DROP TABLE IF EXISTS `supersonic`;
CREATE TABLE `supersonic` (
  `ID` smallint(6) NOT NULL AUTO_INCREMENT,
  `SUPERSONIC_CODE` varchar(20) CHARACTER SET utf8 NOT NULL,
  `SUPERSONIC_NAME` varchar(100) CHARACTER SET utf8 NOT NULL,
  `DESCRIPT` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `IS_DELETE` int(1) DEFAULT '0',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of supersonic
-- ----------------------------
INSERT INTO `supersonic` VALUES ('1', 'S0001', 'Siêu âm ổ bụng', 'Siêu âm ổ bụng', '0');
INSERT INTO `supersonic` VALUES ('2', 'S0002', 'Siêu âm cổ', 'Test Edit1', '0');
INSERT INTO `supersonic` VALUES ('3', 'S0003', 'Test', 'Test12', '0');
INSERT INTO `supersonic` VALUES ('4', 'S0004', 'Siêu âm chân', '1', '0');
INSERT INTO `supersonic` VALUES ('5', 'S0005', 'Siêu âm tay', '1', '0');
INSERT INTO `supersonic` VALUES ('6', 'S0006', 'Siêu âm lồng ngực', '', '0');

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

-- ----------------------------
-- Table structure for `vacination`
-- ----------------------------
DROP TABLE IF EXISTS `vacination`;
CREATE TABLE `vacination` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `vacine_code` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT 'Mã tiêm phòng',
  `vacine_name` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT 'Tên tiêm phòng',
  `descript` varchar(2000) CHARACTER SET utf8 DEFAULT NULL COMMENT 'Ghi chú',
  `is_deleted` int(1) DEFAULT '0' COMMENT '0: hoạt động, 1: xóa',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of vacination
-- ----------------------------
INSERT INTO `vacination` VALUES ('1', 'TP0001', 'Tiêm phòng 1 test', 'Vắc xin tiêm phòng 1 test', '0');
INSERT INTO `vacination` VALUES ('2', 'TP0001', 'Tiêm phòng 1', 'Vắc xin tiêm phòng 1', '0');
INSERT INTO `vacination` VALUES ('3', 'TP0002', 'Tiêm phòng 2', 'Vắc xin tiêm phòng 2', '0');
INSERT INTO `vacination` VALUES ('4', 'TP0003', 'Tiêm phòng 3', 'Vắc xin tiêm phòng 3', '0');
INSERT INTO `vacination` VALUES ('5', 'TP0004', 'Tiêm phòng 4', 'Vắc xin tiêm phòng 4', '0');
INSERT INTO `vacination` VALUES ('6', 'TP0005', 'Tiêm phòng 5', 'Vắc xin tiêm phòng 5', '0');
INSERT INTO `vacination` VALUES ('7', 'TP0006', 'Tiêm phòng 6', 'Vắc xin tiêm phòng 6', '0');
INSERT INTO `vacination` VALUES ('8', 'TP0007', 'Tiêm phòng 7', 'Vắc xin tiêm phòng 7', '0');
INSERT INTO `vacination` VALUES ('9', 'TP0008', 'Tiêm phòng 8', 'Vắc xin tiêm phòng 8', '0');
INSERT INTO `vacination` VALUES ('10', 'TP0009', 'Tiêm phòng 9', 'Vắc xin tiêm phòng 9', '0');
INSERT INTO `vacination` VALUES ('11', 'TP0010', 'Tiêm phòng 10', 'Vắc xin tiêm phòng 10', '0');
INSERT INTO `vacination` VALUES ('12', 'TP0011', 'Tiêm phòng 11', 'Vắc xin tiêm phòng 11', '0');
INSERT INTO `vacination` VALUES ('13', 'TP00013', 'Tên tiêm phòng mười ba', 'Ghi chú tiêm phòng mười ba test test', '0');
INSERT INTO `vacination` VALUES ('14', 'TP00014', 'Tiêm phòng 14', 'test cho tiêm phòng số mười bốn cập nhật sau thêm mới bản ghi', '0');
INSERT INTO `vacination` VALUES ('15', 'TP00015', 'Tiêm phòng 15', 'Ghi chú cho tiêm phòng 15 nhé', '0');
INSERT INTO `vacination` VALUES ('16', 'TP000016', 'Test lại tiêm phòng 16 cập nhật sau thêm mới', 'có ghi chú nào đây cập nhật sau thêm', '0');

-- ----------------------------
-- Table structure for `xq`
-- ----------------------------
DROP TABLE IF EXISTS `xq`;
CREATE TABLE `xq` (
  `ID` smallint(6) NOT NULL AUTO_INCREMENT,
  `XQ_CODE` varchar(20) CHARACTER SET utf8 NOT NULL,
  `XQ_NAME` varchar(100) CHARACTER SET utf8 NOT NULL,
  `DESCRIPT` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `IS_DELETE` smallint(6) DEFAULT '0',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of xq
-- ----------------------------
INSERT INTO `xq` VALUES ('1', 'XQ001', 'Chụp XQ chân', null, '0');
INSERT INTO `xq` VALUES ('2', 'XQ002', 'Chụp XQ tay', null, '0');
INSERT INTO `xq` VALUES ('3', 'XQ003', 'Chụp XQ phổi', '1', '0');
INSERT INTO `xq` VALUES ('4', 'XQ004', 'Test', '1', '0');
INSERT INTO `xq` VALUES ('5', 'XQ005', 'Test', '', '0');
INSERT INTO `xq` VALUES ('6', 'XQ006', 'Test', '', '0');
