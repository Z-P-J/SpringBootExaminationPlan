/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : exam

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 21/06/2020 22:47:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户Id',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `register_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '注册时间',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '上一次登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ix_account_name`(`name`) USING BTREE,
  UNIQUE INDEX `ix_account_email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'admin@qq.com', 'admin', 'admin123', '2019-07-01 00:00:00', '2020-06-21 22:46:47');
INSERT INTO `account` VALUES (2, 'editor@qq.com', 'editor', '$2a$10$/m4SgZ.ZFVZ7rcbQpJW2tezmuhf/UzQtpAtXb0WZiAE3TeHxq2DYu', '2019-07-02 00:00:00', '2019-07-02 00:00:00');
INSERT INTO `account` VALUES (3, 'test@qq.com', 'test', '$2a$10$.0gBYBHAtdkxUUQNg3kII.fqGOngF4BLe8JavthZFalt2QIXHlrhm', '2019-07-03 00:00:00', '2019-07-03 00:00:00');
INSERT INTO `account` VALUES (6, '1@qq.com', '4321', '123456', '2020-06-20 17:46:24', '2020-06-20 18:34:21');

-- ----------------------------
-- Table structure for account_role
-- ----------------------------
DROP TABLE IF EXISTS `account_role`;
CREATE TABLE `account_role`  (
  `account_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户Id',
  `role_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色Id',
  PRIMARY KEY (`account_id`, `role_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  CONSTRAINT `account_role_fk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `account_role_fk_2` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_role
-- ----------------------------
INSERT INTO `account_role` VALUES (1, 1);
INSERT INTO `account_role` VALUES (2, 2);
INSERT INTO `account_role` VALUES (6, 2);
INSERT INTO `account_role` VALUES (3, 3);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '权限Id',
  `resource` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限对应的资源',
  `code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限的代码/通配符,对应代码中@hasAuthority(xx)',
  `handle` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '对应的资源操作',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '账号', 'account:list', '列表');
INSERT INTO `permission` VALUES (2, '账号', 'account:detail', '详情');
INSERT INTO `permission` VALUES (3, '账号', 'account:add', '添加');
INSERT INTO `permission` VALUES (4, '账号', 'account:update', '更新');
INSERT INTO `permission` VALUES (5, '账号', 'account:delete', '删除');
INSERT INTO `permission` VALUES (6, '账号', 'account:search', '搜索');
INSERT INTO `permission` VALUES (7, '角色', 'role:list', '列表');
INSERT INTO `permission` VALUES (8, '角色', 'role:detail', '详情');
INSERT INTO `permission` VALUES (9, '角色', 'role:add', '添加');
INSERT INTO `permission` VALUES (10, '角色', 'role:update', '更新');
INSERT INTO `permission` VALUES (11, '角色', 'role:delete', '删除');
INSERT INTO `permission` VALUES (12, '角色', 'role:search', '搜索');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色Id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', '2019-07-01 00:00:00', '2019-07-01 00:00:00');
INSERT INTO `role` VALUES (2, '普通用户', '2019-07-01 00:00:00', '2019-07-01 00:00:00');
INSERT INTO `role` VALUES (3, '测试', '2019-07-01 00:00:00', '2019-07-01 00:00:00');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `role_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色Id',
  `permission_id` bigint(20) UNSIGNED NOT NULL COMMENT '权限Id',
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
  INDEX `permission_id`(`permission_id`) USING BTREE,
  CONSTRAINT `role_permission_fk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_permission_fk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (3, 1);
INSERT INTO `role_permission` VALUES (3, 5);

SET FOREIGN_KEY_CHECKS = 1;
