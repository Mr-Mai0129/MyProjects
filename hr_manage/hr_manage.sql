/*
 Navicat Premium Data Transfer

 Source Server         : Test
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : hr_manage

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 27/10/2020 19:47:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department_table
-- ----------------------------
DROP TABLE IF EXISTS `department_table`;
CREATE TABLE `department_table`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门名称',
  `POSITION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门职务',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department_table
-- ----------------------------
INSERT INTO `department_table` VALUES (1, '秘书处', '负责统计');
INSERT INTO `department_table` VALUES (2, '宣传部', '负责宣传工作');
INSERT INTO `department_table` VALUES (3, '组织部', '负责组织');
INSERT INTO `department_table` VALUES (4, '策划部', '负责策划');
INSERT INTO `department_table` VALUES (5, '市场部', '负责产品的市场调研');
INSERT INTO `department_table` VALUES (6, '技术部', '负责科技产品的开发');

-- ----------------------------
-- Table structure for employee_table
-- ----------------------------
DROP TABLE IF EXISTS `employee_table`;
CREATE TABLE `employee_table`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '员工编号（自增）',
  `NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工姓名',
  `SEX` int(0) NULL DEFAULT NULL COMMENT '员工性别(0未知，1男，2女)',
  `STATUS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工状态（1在职、0不在职）',
  `PID` int(0) NULL DEFAULT NULL COMMENT '岗位ID',
  `DID` int(0) NULL DEFAULT NULL COMMENT '部门ID',
  `PHONE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工电话',
  `AGE` int(0) NULL DEFAULT NULL COMMENT '员工年龄',
  `ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '家庭住址',
  `ENTRY_TIME` date NULL DEFAULT NULL COMMENT '入职时间',
  `FID` int(0) NULL DEFAULT NULL COMMENT '文件ID',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `employee_foreignkey1`(`PID`) USING BTREE,
  INDEX `employee_foreignkey2`(`DID`) USING BTREE,
  INDEX `employee_foreignkey3`(`FID`) USING BTREE,
  CONSTRAINT `employee_foreignkey1` FOREIGN KEY (`PID`) REFERENCES `post_table` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `employee_foreignkey2` FOREIGN KEY (`DID`) REFERENCES `department_table` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `employee_foreignkey3` FOREIGN KEY (`FID`) REFERENCES `file_table` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_table
-- ----------------------------
INSERT INTO `employee_table` VALUES (1, '张三', 1, '1', 1, 6, '123456', 22, '佛山', '2020-10-14', NULL);
INSERT INTO `employee_table` VALUES (2, '李四', 2, '1', 6, 1, '456789', 20, '广州', '2020-10-06', NULL);
INSERT INTO `employee_table` VALUES (3, '王五', 0, '0', 5, 6, '123789', 25, '深圳', '2020-10-09', NULL);

-- ----------------------------
-- Table structure for file_table
-- ----------------------------
DROP TABLE IF EXISTS `file_table`;
CREATE TABLE `file_table`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名',
  `PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件路径',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for post_table
-- ----------------------------
DROP TABLE IF EXISTS `post_table`;
CREATE TABLE `post_table`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '岗位id',
  `NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '岗位名称',
  `CONTENT` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '岗位内容',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post_table
-- ----------------------------
INSERT INTO `post_table` VALUES (1, 'Java高级工程师', '负责Java程序的编写');
INSERT INTO `post_table` VALUES (2, '程序设计师', '负责程序的整体设计');
INSERT INTO `post_table` VALUES (3, '软件架构师', '负责软件的结构框架');
INSERT INTO `post_table` VALUES (4, '技术总监', '负责所有技术问题');
INSERT INTO `post_table` VALUES (5, '产品分析师', '负责产品的功能特点');
INSERT INTO `post_table` VALUES (6, '秘书', '负责协助经理');

-- ----------------------------
-- Table structure for salary_table
-- ----------------------------
DROP TABLE IF EXISTS `salary_table`;
CREATE TABLE `salary_table`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `EID` int(0) NOT NULL COMMENT '员工ID',
  `MONEY` double(10, 2) NULL DEFAULT NULL COMMENT '工资',
  `DATE` date NULL DEFAULT NULL COMMENT '时间',
  `STATUS` int(0) NULL DEFAULT NULL COMMENT '是否发放（0未发放，1已发放）',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `salary_foreignkey1`(`EID`) USING BTREE,
  CONSTRAINT `salary_foreignkey1` FOREIGN KEY (`EID`) REFERENCES `employee_table` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_table
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户ID（自增）',
  `USERNAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_table
-- ----------------------------
INSERT INTO `user_table` VALUES (1, 'admin', '123');
INSERT INTO `user_table` VALUES (3, 'mtg', '123');
INSERT INTO `user_table` VALUES (4, 'cjt', '123');
INSERT INTO `user_table` VALUES (10, 'tg', '123');
INSERT INTO `user_table` VALUES (11, 'mt', '202cb962ac59075b964b07152d234b70');
INSERT INTO `user_table` VALUES (12, 'admin111', '698d51a19d8a121ce581499d7b701668');

SET FOREIGN_KEY_CHECKS = 1;
