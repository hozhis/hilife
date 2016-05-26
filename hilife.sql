/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : hilife

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-05-26 22:57:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for AUNT_INFO
-- ----------------------------
DROP TABLE IF EXISTS `AUNT_INFO`;
CREATE TABLE `AUNT_INFO` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOGIN_ID` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TOKEN` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `AUNT_IMAGE` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `AUNT_NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SEX` varchar(2) COLLATE utf8_unicode_ci DEFAULT '0',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for BANK_CARD_INFO
-- ----------------------------
DROP TABLE IF EXISTS `BANK_CARD_INFO`;
CREATE TABLE `BANK_CARD_INFO` (
  `BANK_ID` int(10) NOT NULL AUTO_INCREMENT,
  `BANK_NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BANK_IMAGE` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CARD_TYPE` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CARD_NO` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BIND_PHONE` bigint(11) DEFAULT NULL,
  `SERVICE_TEL` int(11) DEFAULT NULL,
  PRIMARY KEY (`BANK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for BASIC_PARA
-- ----------------------------
DROP TABLE IF EXISTS `BASIC_PARA`;
CREATE TABLE `BASIC_PARA` (
  `PARA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TYPE_ID` int(11) NOT NULL,
  `PARA_NAME` varchar(255) DEFAULT NULL,
  `PARA_VALUE1` varchar(255) DEFAULT NULL,
  `PARA_VALUE2` varchar(255) DEFAULT NULL,
  `PARA_VALUE3` varchar(255) DEFAULT NULL,
  `PARA_VALUE4` varchar(255) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PARA_ID`),
  KEY `TYPE_ID` (`TYPE_ID`),
  CONSTRAINT `TYPE_ID` FOREIGN KEY (`TYPE_ID`) REFERENCES `basic_type` (`TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for BASIC_TYPE
-- ----------------------------
DROP TABLE IF EXISTS `BASIC_TYPE`;
CREATE TABLE `BASIC_TYPE` (
  `TYPE_ID` int(11) NOT NULL,
  `TYPE_NAME` varchar(255) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for COUPON
-- ----------------------------
DROP TABLE IF EXISTS `COUPON`;
CREATE TABLE `COUPON` (
  `COUPON_ID` int(10) NOT NULL AUTO_INCREMENT,
  `COUPON_TITLE` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MONEY` int(10) NOT NULL DEFAULT '1',
  `USE_MONEY_LIMIT` int(10) NOT NULL DEFAULT '1',
  `USE_COND_LIMIT` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `CREATE_DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `STATUS` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '1',
  `TYPE_ID` int(10) NOT NULL,
  PRIMARY KEY (`COUPON_ID`),
  KEY `FK_COUPON_COUPON_TYPE` (`TYPE_ID`),
  CONSTRAINT `FK_COUPON_COUPON_TYPE` FOREIGN KEY (`TYPE_ID`) REFERENCES `coupon_type` (`TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for COUPON_TYPE
-- ----------------------------
DROP TABLE IF EXISTS `COUPON_TYPE`;
CREATE TABLE `COUPON_TYPE` (
  `TYPE_ID` int(10) NOT NULL AUTO_INCREMENT,
  `TYPE_NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` varchar(2) COLLATE utf8_unicode_ci DEFAULT '1',
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for CUST_ADDRESS
-- ----------------------------
DROP TABLE IF EXISTS `CUST_ADDRESS`;
CREATE TABLE `CUST_ADDRESS` (
  `ADDRESS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `ADDRESS_NAME` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `PHONE` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `CONSIGNEE` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `DEF` int(2) DEFAULT '0' COMMENT '默认不选中',
  `STATUS` varchar(2) CHARACTER SET utf8 DEFAULT '1',
  PRIMARY KEY (`ADDRESS_ID`),
  KEY `FK_CUST_ADDRESS_CUST_INFO` (`USER_ID`),
  CONSTRAINT `FK_CUST_ADDRESS_CUST_INFO` FOREIGN KEY (`USER_ID`) REFERENCES `cust_info` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for CUST_BALANCE
-- ----------------------------
DROP TABLE IF EXISTS `CUST_BALANCE`;
CREATE TABLE `CUST_BALANCE` (
  `BALANCE_ID` int(10) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) DEFAULT NULL,
  `BALANCE` int(10) DEFAULT NULL,
  PRIMARY KEY (`BALANCE_ID`),
  KEY `FK_CUST_BALANCE_CUST_INFO` (`USER_ID`),
  CONSTRAINT `FK_CUST_BALANCE_CUST_INFO` FOREIGN KEY (`USER_ID`) REFERENCES `cust_info` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for CUST_BANK
-- ----------------------------
DROP TABLE IF EXISTS `CUST_BANK`;
CREATE TABLE `CUST_BANK` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) DEFAULT '0',
  `BANK_ID` int(10) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `FK_CUST_BANK_CUST_INFO` (`USER_ID`),
  KEY `FK_CUST_BANK_BANK_CARD_INFO` (`BANK_ID`),
  CONSTRAINT `FK_CUST_BANK_BANK_CARD_INFO` FOREIGN KEY (`BANK_ID`) REFERENCES `bank_card_info` (`BANK_ID`),
  CONSTRAINT `FK_CUST_BANK_CUST_INFO` FOREIGN KEY (`USER_ID`) REFERENCES `cust_info` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for CUST_COUPON
-- ----------------------------
DROP TABLE IF EXISTS `CUST_COUPON`;
CREATE TABLE `CUST_COUPON` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) DEFAULT '0',
  `COUPON_ID` int(10) DEFAULT '0',
  `EFFECTIVE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `EXPIRE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `FK_CUST_COUPON_CUST_INFO` (`USER_ID`),
  KEY `FK_CUST_COUPON_COUPON` (`COUPON_ID`),
  CONSTRAINT `FK_CUST_COUPON_COUPON` FOREIGN KEY (`COUPON_ID`) REFERENCES `coupon` (`COUPON_ID`),
  CONSTRAINT `FK_CUST_COUPON_CUST_INFO` FOREIGN KEY (`USER_ID`) REFERENCES `cust_info` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for CUST_INFO
-- ----------------------------
DROP TABLE IF EXISTS `CUST_INFO`;
CREATE TABLE `CUST_INFO` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `LOGIN_ID` varchar(11) NOT NULL COMMENT '登录ID',
  `TOKEN` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL COMMENT '用户名',
  `CUST_IMAGE` varchar(255) DEFAULT NULL,
  `CUST_NAME` varchar(255) DEFAULT NULL,
  `SEX` varchar(2) DEFAULT '0' COMMENT '男--0  女--1',
  `REGION_ID` int(11) DEFAULT NULL,
  `INVITE_CODE` varchar(6) DEFAULT NULL,
  `ADDRESS_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for CUST_INVITE_USER
-- ----------------------------
DROP TABLE IF EXISTS `CUST_INVITE_USER`;
CREATE TABLE `CUST_INVITE_USER` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) DEFAULT '0',
  `INVITE_USER_ID` int(10) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `FK__CUST_INFO` (`USER_ID`),
  KEY `FK_INVITE_USER_CUST_INFO` (`INVITE_USER_ID`),
  CONSTRAINT `FK_INVITE_USER_CUST_INFO` FOREIGN KEY (`INVITE_USER_ID`) REFERENCES `cust_info` (`USER_ID`),
  CONSTRAINT `FK__CUST_INFO` FOREIGN KEY (`USER_ID`) REFERENCES `cust_info` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for CUST_ORDER
-- ----------------------------
DROP TABLE IF EXISTS `CUST_ORDER`;
CREATE TABLE `CUST_ORDER` (
  `ORDER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `ORDER_TYPE` int(11) DEFAULT '1' COMMENT '1 - 服务订单， 0 - 商品订单',
  `TOTAL_AMOUNT` int(11) DEFAULT NULL COMMENT '总金额',
  `AUNT_ID` int(11) DEFAULT NULL,
  `ORDER_STATUS` int(11) DEFAULT NULL COMMENT '101 提交订单待处理',
  `SERVICE_ADDRESS` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PHONE` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CUST_NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EVALUATE_STAR` int(2) DEFAULT '0',
  `EVALUATE_CONTENT` varchar(255) COLLATE utf8_unicode_ci DEFAULT '0',
  `RECEIVE_TIME` datetime DEFAULT NULL,
  `DISPATCH_TIME` datetime DEFAULT NULL,
  `FINISH_TIME` datetime DEFAULT NULL,
  `PAY_TIME` datetime DEFAULT NULL,
  `EVALUATE_TIME` datetime DEFAULT NULL,
  `CLOSE_TIME` datetime DEFAULT NULL,
  `CANCEL_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(2) COLLATE utf8_unicode_ci DEFAULT '1',
  PRIMARY KEY (`ORDER_ID`),
  KEY `FK_CUST_ORDER_CUST_INFO` (`USER_ID`),
  KEY `FK_CUST_ORDER_AUNT_INFO` (`AUNT_ID`),
  CONSTRAINT `FK_CUST_ORDER_AUNT_INFO` FOREIGN KEY (`AUNT_ID`) REFERENCES `aunt_info` (`USER_ID`),
  CONSTRAINT `FK_CUST_ORDER_CUST_INFO` FOREIGN KEY (`USER_ID`) REFERENCES `cust_info` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000046 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for CUST_ORDER_DETAIL
-- ----------------------------
DROP TABLE IF EXISTS `CUST_ORDER_DETAIL`;
CREATE TABLE `CUST_ORDER_DETAIL` (
  `ORDER_DETAIL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDER_ID` int(11) DEFAULT '0',
  `PRODUCT_ID` int(11) DEFAULT '0',
  `PRICE` int(11) DEFAULT '0',
  `AMOUNT` int(11) DEFAULT '0',
  `COUPON` int(11) DEFAULT '0',
  `NUM` int(11) DEFAULT '0',
  PRIMARY KEY (`ORDER_DETAIL_ID`),
  KEY `FK__CUST_ORDER` (`ORDER_ID`),
  KEY `FK__PRODUCT` (`PRODUCT_ID`),
  CONSTRAINT `FK__CUST_ORDER` FOREIGN KEY (`ORDER_ID`) REFERENCES `cust_order` (`ORDER_ID`),
  CONSTRAINT `FK__PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`PRODUCT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for CUST_ORDER_SERVICE
-- ----------------------------
DROP TABLE IF EXISTS `CUST_ORDER_SERVICE`;
CREATE TABLE `CUST_ORDER_SERVICE` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `ORDER_ID` int(10) DEFAULT NULL,
  `ORDER_PARA_VALUE1` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORDER_PARA_VALUE2` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORDER_PARA_VALUE3` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORDER_PARA_VALUE4` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORDER_PARA_VALUE5` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORDER_PARA_VALUE6` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REMARK` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CUST_ORDER_SERVICE_CUST_ORDER` (`ORDER_ID`),
  CONSTRAINT `FK_CUST_ORDER_SERVICE_CUST_ORDER` FOREIGN KEY (`ORDER_ID`) REFERENCES `cust_order` (`ORDER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for CUST_SHOPCART
-- ----------------------------
DROP TABLE IF EXISTS `CUST_SHOPCART`;
CREATE TABLE `CUST_SHOPCART` (
  `CART_ID` int(10) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) DEFAULT '0',
  `PRODUCT_ID` int(10) DEFAULT '0',
  `NUM` int(10) DEFAULT '0',
  PRIMARY KEY (`CART_ID`),
  KEY `FK_CUST_SHOPCART_CUST_INFO` (`USER_ID`),
  KEY `FK_CUST_SHOPCART_PRODUCT` (`PRODUCT_ID`),
  CONSTRAINT `FK_CUST_SHOPCART_CUST_INFO` FOREIGN KEY (`USER_ID`) REFERENCES `cust_info` (`USER_ID`),
  CONSTRAINT `FK_CUST_SHOPCART_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for CUST_TRANSACTION
-- ----------------------------
DROP TABLE IF EXISTS `CUST_TRANSACTION`;
CREATE TABLE `CUST_TRANSACTION` (
  `TRANS_ID` int(10) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) NOT NULL DEFAULT '0',
  `ORDER_ID` int(10) DEFAULT NULL,
  `TRANS_NAME` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TRANS_TIME` datetime NOT NULL,
  `TRANS_MONEY` int(11) NOT NULL COMMENT '交易金额，精确到分',
  `BALANCE` int(11) NOT NULL,
  `CODE` int(5) DEFAULT NULL,
  `STATUS` varchar(2) COLLATE utf8_unicode_ci DEFAULT '1',
  PRIMARY KEY (`TRANS_ID`),
  KEY `FK_CUST_TRANSACTION_CUST_INFO` (`USER_ID`),
  CONSTRAINT `FK_CUST_TRANSACTION_CUST_INFO` FOREIGN KEY (`USER_ID`) REFERENCES `cust_info` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for FEEDBACK
-- ----------------------------
DROP TABLE IF EXISTS `FEEDBACK`;
CREATE TABLE `FEEDBACK` (
  `FB_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `FB_TITLE` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `FB_CONTENT` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `PHONE` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REVIEWS` int(10) DEFAULT NULL,
  PRIMARY KEY (`FB_ID`),
  KEY `FK_FEEDBACK_CUST_INFO` (`USER_ID`),
  CONSTRAINT `FK_FEEDBACK_CUST_INFO` FOREIGN KEY (`USER_ID`) REFERENCES `cust_info` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for PLATFORM_FUNCTION_INFO
-- ----------------------------
DROP TABLE IF EXISTS `PLATFORM_FUNCTION_INFO`;
CREATE TABLE `PLATFORM_FUNCTION_INFO` (
  `FUNCTION_ID` int(10) NOT NULL DEFAULT '0',
  `FUNCTION_NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TYPE` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `PARENT_ID` int(11) DEFAULT NULL,
  `PARENT_IDS` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PERMISSION` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `URL` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LEVEL` int(11) DEFAULT NULL,
  PRIMARY KEY (`FUNCTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for PLATFORM_ROLE_FUNCTION
-- ----------------------------
DROP TABLE IF EXISTS `PLATFORM_ROLE_FUNCTION`;
CREATE TABLE `PLATFORM_ROLE_FUNCTION` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` int(10) DEFAULT NULL,
  `FUNCTION_ID` int(10) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `FK_PLATFORM_ROLE_FUNCTION_PLATFORM_FUNCTION_INFO` (`FUNCTION_ID`),
  KEY `FK_PLATFORM_ROLE_FUNCTION_PLATFORM_ROLE_INFO` (`ROLE_ID`),
  CONSTRAINT `FK_PLATFORM_ROLE_FUNCTION_PLATFORM_FUNCTION_INFO` FOREIGN KEY (`FUNCTION_ID`) REFERENCES `platform_function_info` (`FUNCTION_ID`),
  CONSTRAINT `FK_PLATFORM_ROLE_FUNCTION_PLATFORM_ROLE_INFO` FOREIGN KEY (`ROLE_ID`) REFERENCES `platform_role_info` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for PLATFORM_ROLE_INFO
-- ----------------------------
DROP TABLE IF EXISTS `PLATFORM_ROLE_INFO`;
CREATE TABLE `PLATFORM_ROLE_INFO` (
  `ROLE_ID` int(10) NOT NULL AUTO_INCREMENT,
  `ROLE` varchar(255) COLLATE utf8_unicode_ci DEFAULT '0',
  `DESCRIPTION` varchar(255) COLLATE utf8_unicode_ci DEFAULT '0',
  `CREATE_DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `STATUS` varchar(2) COLLATE utf8_unicode_ci NOT NULL DEFAULT '1',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for PLATFORM_USER_INFO
-- ----------------------------
DROP TABLE IF EXISTS `PLATFORM_USER_INFO`;
CREATE TABLE `PLATFORM_USER_INFO` (
  `USER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `LOGIN_ID` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `PASSWORD` varchar(255) COLLATE utf8_unicode_ci DEFAULT '0',
  `TOKEN` varchar(255) COLLATE utf8_unicode_ci DEFAULT '0',
  `USER_NAME` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `EMAIL` varchar(255) COLLATE utf8_unicode_ci DEFAULT '0',
  `PHONE` bigint(11) NOT NULL DEFAULT '0',
  `CREATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `REMARK` varchar(255) COLLATE utf8_unicode_ci DEFAULT '0',
  `STATUS` varchar(255) COLLATE utf8_unicode_ci DEFAULT '1',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for PLATFORM_USER_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `PLATFORM_USER_ROLE`;
CREATE TABLE `PLATFORM_USER_ROLE` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` int(10) DEFAULT '0',
  `USER_ID` int(10) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `FK_PLATFORM_USER_ROLE_PLATFORM_ROLE_INFO` (`ROLE_ID`),
  KEY `FK_PLATFORM_USER_ROLE_PLATFORM_USER_INFO` (`USER_ID`),
  CONSTRAINT `FK_PLATFORM_USER_ROLE_PLATFORM_ROLE_INFO` FOREIGN KEY (`ROLE_ID`) REFERENCES `platform_role_info` (`ROLE_ID`),
  CONSTRAINT `FK_PLATFORM_USER_ROLE_PLATFORM_USER_INFO` FOREIGN KEY (`USER_ID`) REFERENCES `platform_user_info` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for PRODUCT
-- ----------------------------
DROP TABLE IF EXISTS `PRODUCT`;
CREATE TABLE `PRODUCT` (
  `PRODUCT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRODUCT_NAME` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `BRAND_ID` int(10) DEFAULT NULL,
  `IMAGE` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `TYPE_ID` int(11) NOT NULL,
  `INTRODUCTION` text CHARACTER SET utf8,
  `PRICE` int(11) DEFAULT '0',
  `AMOUNT` int(11) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `MODIFY_DATE` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `REMARK` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品 规格  属性',
  `STATUS` varchar(2) CHARACTER SET utf8 DEFAULT '1',
  `FLAG_ID` int(10) DEFAULT NULL COMMENT '服务商品专用',
  `SALE_AMOUNT` int(10) DEFAULT NULL COMMENT '销量',
  `RIVIEWS` int(10) DEFAULT NULL COMMENT '人气',
  PRIMARY KEY (`PRODUCT_ID`),
  KEY `TYPE_ID` (`TYPE_ID`),
  KEY `product_ibfk_1` (`FLAG_ID`),
  KEY `FK_PRODUCT_PRODUCT_BRAND` (`BRAND_ID`),
  CONSTRAINT `FK_PRODUCT_PRODUCT_BRAND` FOREIGN KEY (`BRAND_ID`) REFERENCES `product_brand` (`BRAND_ID`),
  CONSTRAINT `FK_PRODUCT_PRODUCT_TYPE` FOREIGN KEY (`TYPE_ID`) REFERENCES `product_type` (`TYPE_ID`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`FLAG_ID`) REFERENCES `product_flag` (`FLAG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for PRODUCT_BRAND
-- ----------------------------
DROP TABLE IF EXISTS `PRODUCT_BRAND`;
CREATE TABLE `PRODUCT_BRAND` (
  `BRAND_ID` int(10) NOT NULL AUTO_INCREMENT,
  `BRAND_NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` varchar(2) COLLATE utf8_unicode_ci DEFAULT '1',
  PRIMARY KEY (`BRAND_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for PRODUCT_FLAG
-- ----------------------------
DROP TABLE IF EXISTS `PRODUCT_FLAG`;
CREATE TABLE `PRODUCT_FLAG` (
  `FLAG_ID` int(10) DEFAULT NULL,
  `FLAG_NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  KEY `FLAG_ID` (`FLAG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for PRODUCT_PROMOTE
-- ----------------------------
DROP TABLE IF EXISTS `PRODUCT_PROMOTE`;
CREATE TABLE `PRODUCT_PROMOTE` (
  `PROMOTE_ID` int(10) NOT NULL AUTO_INCREMENT,
  `PRODUCT_ID` int(10) DEFAULT NULL,
  `PROMOTE_TYPE_ID` int(10) DEFAULT NULL,
  `PROMOTE_TYPE_NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`PROMOTE_ID`),
  KEY `FK_PRODUCT_PROMOTE_PRODUCT` (`PRODUCT_ID`),
  CONSTRAINT `FK_PRODUCT_PROMOTE_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`PRODUCT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for PRODUCT_TYPE
-- ----------------------------
DROP TABLE IF EXISTS `PRODUCT_TYPE`;
CREATE TABLE `PRODUCT_TYPE` (
  `TYPE_ID` int(11) NOT NULL,
  `TYPE_NAME` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `STATUS` varchar(2) CHARACTER SET utf8 DEFAULT '1',
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for REGION
-- ----------------------------
DROP TABLE IF EXISTS `REGION`;
CREATE TABLE `REGION` (
  `REGION_ID` int(10) DEFAULT NULL,
  `AREANAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PARENT_ID` int(10) DEFAULT NULL,
  `SHORT_NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LONGITUDE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LATITUDE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LEVEL` int(10) DEFAULT NULL,
  `CITY_PY` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for SMS_MESSAGE
-- ----------------------------
DROP TABLE IF EXISTS `SMS_MESSAGE`;
CREATE TABLE `SMS_MESSAGE` (
  `SMS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PHONE` bigint(11) NOT NULL,
  `MESSAGE` varchar(255) DEFAULT NULL,
  `CREATE_DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `STATUS` varchar(2) DEFAULT '1',
  PRIMARY KEY (`SMS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
