/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50634
Source Host           : localhost:3306
Source Database       : hualian

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2018-05-10 00:06:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account_chain_wallet
-- ----------------------------
DROP TABLE IF EXISTS `account_chain_wallet`;
CREATE TABLE `account_chain_wallet` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `addr` varchar(80) NOT NULL,
  `chain_code` varchar(50) NOT NULL,
  `account_wallet_id` bigint(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_chain_wallet
-- ----------------------------
INSERT INTO `account_chain_wallet` VALUES ('1', '0xd39dd63d4f29c4a5842342eb8344a745b2865868', 'moac', '1');
INSERT INTO `account_chain_wallet` VALUES ('2', 'jHwTDv5ZCxFVNcXnSMF5L8boyELjuSbmHY', 'jingtum', '1');

-- ----------------------------
-- Table structure for account_wallet
-- ----------------------------
DROP TABLE IF EXISTS `account_wallet`;
CREATE TABLE `account_wallet` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `wallet_addr` varchar(80) NOT NULL,
  `user_id` varchar(80) NOT NULL,
  `appid` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_wallet
-- ----------------------------
INSERT INTO `account_wallet` VALUES ('1', 'f7ca929c-9b47-495f-883a-8460c1eb9db4', 'ceshiyonghu2', '993814533992611840');

-- ----------------------------
-- Table structure for chain_wallet
-- ----------------------------
DROP TABLE IF EXISTS `chain_wallet`;
CREATE TABLE `chain_wallet` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `addr` varchar(50) NOT NULL,
  `chain_code` varchar(30) NOT NULL,
  `wallet_id` bigint(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `wallet` (`wallet_id`),
  CONSTRAINT `wallet` FOREIGN KEY (`wallet_id`) REFERENCES `wallet` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chain_wallet
-- ----------------------------
INSERT INTO `chain_wallet` VALUES ('1', '0xbe7ba3121040d652ba16354f1c966f540ed6e127', 'moac', '2');
INSERT INTO `chain_wallet` VALUES ('2', 'j4MzniEh87qu2HsYQKJsPdbBYJD6QDiQC6', 'jingtum', '2');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `table_id` bigint(20) DEFAULT NULL,
  `flag` varchar(100) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `fileurl` varchar(255) DEFAULT NULL,
  `create_name` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES ('4', '7', 'article', '104212qvrkkqwllkra2k87.png', '04/4516-1511762592743.png', '超级管理员', '2017-11-27 14:03:14');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '资源名称',
  `icon` varchar(64) DEFAULT NULL COMMENT '图标',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级id',
  `type` char(1) DEFAULT '0' COMMENT '类型(0.菜单 1.按钮)',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `del_flag` smallint(1) NOT NULL DEFAULT '0' COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '系统管理', 'cogs', '0', '0', '', '0');
INSERT INTO `sys_resource` VALUES ('2', '用户管理', 'users', '0', '0', '', '0');
INSERT INTO `sys_resource` VALUES ('3', '菜单配置', 'gear', '1', '0', 'menu', '0');
INSERT INTO `sys_resource` VALUES ('4', '账号管理', 'street-view', '2', '0', 'userCenter', '0');
INSERT INTO `sys_resource` VALUES ('6', '角色管理', null, '2', '0', 'userRole', '0');
INSERT INTO `sys_resource` VALUES ('7', '钱包管理', 'gear', '0', '0', '', '0');
INSERT INTO `sys_resource` VALUES ('8', '企业钱包', '', '7', '0', 'wallet', '0');
INSERT INTO `sys_resource` VALUES ('9', '用户钱包', null, '7', '0', 'accountWallet', '0');
INSERT INTO `sys_resource` VALUES ('10', '用户公链钱包', null, '7', '0', 'accountChainWallet', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '访客', '访客权限');
INSERT INTO `sys_role` VALUES ('2', '普通用户', '普通权限');

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `resource_id` (`resource_id`),
  CONSTRAINT `sys_role_resource_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_role_resource_ibfk_2` FOREIGN KEY (`resource_id`) REFERENCES `sys_resource` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `appcode` varchar(50) NOT NULL,
  `appname` varchar(100) NOT NULL,
  `cburl` varchar(40) DEFAULT NULL,
  `appid` varchar(40) NOT NULL,
  `appsecret` varchar(50) NOT NULL,
  `del_flag` smallint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'testhualiansrj', '火花系统测试', '', '992562277334908928', '3e69bdc4-d605-4108-bd66-a2b57a89f803', '0');
INSERT INTO `sys_user` VALUES ('2', 'newhuohualink', '新测试srj', '', '993645780306755584', '4a1fc636-e411-49f7-ab5d-c72330e732e5', '0');
INSERT INTO `sys_user` VALUES ('3', 'newhuohualink2', '新火花测试2', '', '993648659088277504', 'e3b5b616-248e-49d6-8471-485e83284471', '0');
INSERT INTO `sys_user` VALUES ('4', 'huohuatest3', '火花测试3', '', '993810746548682752', '2f504a44-91d2-4122-a6f0-b9b0c93c6a9e', '0');
INSERT INTO `sys_user` VALUES ('5', 'huohuaceshi4', '火花测试4', '', '993814533992611840', '080c5b83-3988-4a7b-8685-9645df6258d1', '0');
INSERT INTO `sys_user` VALUES ('6', 'hualianceshi5', '火花测试5', '', '994241218256830464', '4242d640-c16a-49c4-91e3-d022cc37dd8d', '0');
INSERT INTO `sys_user` VALUES ('7', 'huohuaceshi6', '火花测试6', '', '994242373624004608', '8adb227a-e79f-4bf3-af9c-17068e374653', '0');
INSERT INTO `sys_user` VALUES ('8', 'huohuaceshi7', '火花测试7', '', '994243083598036992', '3bc14f54-004a-422b-86f6-eee4fd4a50b9', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for wallet
-- ----------------------------
DROP TABLE IF EXISTS `wallet`;
CREATE TABLE `wallet` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `addr` varchar(50) NOT NULL,
  `query_pwd` varchar(50) DEFAULT NULL,
  `user_code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wallet
-- ----------------------------
INSERT INTO `wallet` VALUES ('2', '7e9777ca-3eb3-463b-a34e-df7b83940f7f', '993814533992611840', '993814533992611840');
