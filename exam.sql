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

 Date: 23/06/2020 02:22:31
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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, '123456@qq.com', 'admin', 'admin123', '2019-07-01 00:00:00', '2020-06-21 22:46:47');
INSERT INTO `account` VALUES (2, 'editor@qq.com', 'editor', '$2a$10$/m4SgZ.ZFVZ7rcbQpJW2tezmuhf/UzQtpAtXb0WZiAE3TeHxq2DYu', '2019-07-02 00:00:00', '2019-07-02 00:00:00');
INSERT INTO `account` VALUES (3, 'test@qq.com', 'test', '$2a$10$.0gBYBHAtdkxUUQNg3kII.fqGOngF4BLe8JavthZFalt2QIXHlrhm', '2019-07-03 00:00:00', '2019-07-03 00:00:00');
INSERT INTO `account` VALUES (6, '1@qq.com', '4321', '123456', '2020-06-20 17:46:24', '2020-06-20 18:34:21');
INSERT INTO `account` VALUES (7, '1111@qq.com', '123', '123456', '2020-06-21 22:51:40', NULL);

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
INSERT INTO `account_role` VALUES (7, 2);
INSERT INTO `account_role` VALUES (3, 3);

-- ----------------------------
-- Table structure for certificate_replace_relationship
-- ----------------------------
DROP TABLE IF EXISTS `certificate_replace_relationship`;
CREATE TABLE `certificate_replace_relationship`  (
  `non-degree_certificate_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NOT NULL,
  `non-degree_certificate_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `exam-free_self-study_exam_course` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `course_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `conversion_score` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`non-degree_certificate_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_czech_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_charge
-- ----------------------------
DROP TABLE IF EXISTS `course_charge`;
CREATE TABLE `course_charge`  (
  `course_id` varchar(5) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `exam_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `course_register_charge` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_info
-- ----------------------------
DROP TABLE IF EXISTS `course_info`;
CREATE TABLE `course_info`  (
  `course_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `national_course_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `course_specification` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `test_source` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `course_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `credit` int(255) NULL DEFAULT NULL,
  `qualified_score` int(255) NULL DEFAULT NULL,
  `score_scale` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `subjective_score` int(255) NULL DEFAULT NULL,
  `objective_score` int(255) NULL DEFAULT NULL,
  `total_score` int(255) NULL DEFAULT NULL,
  `exam_duration` int(255) NULL DEFAULT NULL,
  `is_procedural` tinyint(1) NULL DEFAULT NULL,
  `course_property` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_info
-- ----------------------------
INSERT INTO `course_info` VALUES ('00001', '00001', '英语', '英语课程', '全国命题', '0', NULL, 60, '100分制', 40, 60, 100, 120, 0, '理论', '无');
INSERT INTO `course_info` VALUES ('00002', '00002', '英语2', '英语课程2', '全国命题', '0', NULL, 60, '100分制', 40, 60, 100, 120, 0, '理论', '无');
INSERT INTO `course_info` VALUES ('00003', '00003', '英语3', '英语课程3', '全国命题', '0', NULL, 60, '100分制', 40, 60, 100, 120, 0, '理论', '无');
INSERT INTO `course_info` VALUES ('00004', '00004', '数学', '数学课程', '全国命题', '0', NULL, 60, '100分制', 40, 60, 100, 120, 0, '理论', '无');

-- ----------------------------
-- Table structure for course_textbook_info
-- ----------------------------
DROP TABLE IF EXISTS `course_textbook_info`;
CREATE TABLE `course_textbook_info`  (
  `course_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_czech_ci NOT NULL,
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `textbook_code` varchar(9) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `textbook_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `textbook_ISBN` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `textbook_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `chief_editor` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `publishing_house` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `publication_date` date NULL DEFAULT NULL,
  `price` int(10) NULL DEFAULT NULL,
  `textbook_select_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `syllabus` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `textbook_use_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `textbook_start_exam_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `textbook_end_exam_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_czech_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for detail_of_student_service
-- ----------------------------
DROP TABLE IF EXISTS `detail_of_student_service`;
CREATE TABLE `detail_of_student_service`  (
  `main_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `school` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属院校',
  `student_servicie_org` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '助学组织名称',
  `student_permit_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '助学许可证编码\r\n',
  `issuing_unit_of_school_license` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '办学许可证发放单位',
  `issuing_date_of_school_license` date NULL DEFAULT NULL COMMENT '办学许可证发放日期',
  `types_of_student_servicies` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '助学主体类型',
  `name_of_legal_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '法人姓名',
  `position_of_legal_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '法人职务',
  `number_of_main_students` int(255) NULL DEFAULT NULL COMMENT '集中学员数',
  `number_of_part_time_teachers` int(255) NULL DEFAULT NULL COMMENT '兼职教学人数',
  `
number_of_full_time_teachers` int(255) NULL DEFAULT NULL COMMENT '转职教学人数',
  `number_of_amateur_students` int(255) NULL DEFAULT NULL COMMENT '业余学员人数',
  `new_students_of_year` int(255) NULL DEFAULT NULL COMMENT '当年新招升学员数',
  `number_of_managers` int(255) NULL DEFAULT NULL COMMENT '管理人员总数',
  `number_of_managers_and_teachers` int(255) NULL DEFAULT NULL COMMENT '教学和管理人员数',
  `person_in_charge` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '负责人',
  `contact_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '联系地址',
  `postal_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮政编码',
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '联系电话',
  `fax` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '传真',
  `major_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '专业代码',
  `major_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '专业名称',
  `ways_of_student_servicies` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '助学方式',
  `means_of_student_servicies` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '助学手段',
  `number_of_students` int(255) NULL DEFAULT NULL COMMENT '学员数',
  PRIMARY KEY (`main_key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for elective_course_relationship_info
-- ----------------------------
DROP TABLE IF EXISTS `elective_course_relationship_info`;
CREATE TABLE `elective_course_relationship_info`  (
  `major_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `direction_id` varchar(9) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `course_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `ec_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `ec_number` int(255) NULL DEFAULT NULL,
  `group_credit` int(255) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_czech_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam_course
-- ----------------------------
DROP TABLE IF EXISTS `exam_course`;
CREATE TABLE `exam_course`  (
  `exam_id` varchar(3) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `course_id` varchar(5) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `time_id` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`exam_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam_course_info
-- ----------------------------
DROP TABLE IF EXISTS `exam_course_info`;
CREATE TABLE `exam_course_info`  (
  `exam_id` varchar(3) CHARACTER SET utf8 COLLATE utf8_czech_ci NOT NULL,
  `course_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `time_id` varchar(1) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `exam_date` date NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`exam_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_czech_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam_info
-- ----------------------------
DROP TABLE IF EXISTS `exam_info`;
CREATE TABLE `exam_info`  (
  `exams_id` varchar(3) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `exams_type` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `jihua_exams_status` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `kaowu_exams_status` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `kaoji_exams_status` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `start_date` date NULL DEFAULT NULL,
  `end_date` date NULL DEFAULT NULL,
  `default_charge` int(255) NULL DEFAULT 26,
  `photo_charge` int(255) UNSIGNED ZEROFILL NULL DEFAULT 000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010,
  `notes` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`exams_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam_major_info
-- ----------------------------
DROP TABLE IF EXISTS `exam_major_info`;
CREATE TABLE `exam_major_info`  (
  `exam_id` varchar(3) CHARACTER SET utf8 COLLATE utf8_czech_ci NOT NULL,
  `major_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  PRIMARY KEY (`exam_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_czech_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam_plan_approve_info
-- ----------------------------
DROP TABLE IF EXISTS `exam_plan_approve_info`;
CREATE TABLE `exam_plan_approve_info`  (
  `exam_id` varchar(3) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `exam_approve_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `xuelichu_suggestion` text CHARACTER SET utf8 COLLATE utf8_czech_ci NULL,
  `leader_sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_czech_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam_time
-- ----------------------------
DROP TABLE IF EXISTS `exam_time`;
CREATE TABLE `exam_time`  (
  `exam_id` varchar(3) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `time_id` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `exam_date` date NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`exam_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for expend_major_apply
-- ----------------------------
DROP TABLE IF EXISTS `expend_major_apply`;
CREATE TABLE `expend_major_apply`  (
  `school_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NOT NULL,
  `major_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `apply_date` date NULL DEFAULT NULL,
  `approve_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `approve_comment` date NULL DEFAULT NULL,
  `approve_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `approver` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  PRIMARY KEY (`school_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_czech_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for information_of_service_center
-- ----------------------------
DROP TABLE IF EXISTS `information_of_service_center`;
CREATE TABLE `information_of_service_center`  (
  `main_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `service_center_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '服务中心名称',
  `service_center_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '服务中心类型，0：国家级，1：省级，2市级',
  `school_license_nomber` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '办学许可证号',
  `license_issuing_unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '许可证发放单位',
  `license_issue_date` date NULL DEFAULT NULL COMMENT '许可证发放日期',
  `service_center_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '服务中心地址',
  `head_of_service_center` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '服务中心负责人',
  `nomber_of_the_leader` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '负责人身份证号',
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '联系电话',
  `approval_date` date NULL DEFAULT NULL COMMENT '审批日期',
  `approval_nomber` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '审批文号',
  `service_center_introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '服务中心简介',
  PRIMARY KEY (`main_key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for major_direction
-- ----------------------------
DROP TABLE IF EXISTS `major_direction`;
CREATE TABLE `major_direction`  (
  `major_id` varchar(7) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `direction_id` varchar(9) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `direction_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `total_credit` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`major_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for major_info
-- ----------------------------
DROP TABLE IF EXISTS `major_info`;
CREATE TABLE `major_info`  (
  `major_id` varchar(7) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `national_major_code` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `major_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `major_brief_introduction` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL,
  `major_type` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `education_level` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `exam_type` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `main_target_school` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `first_exam_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `approve_num` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `stop_freshman_registration_exam_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `stop_registration_num` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `stop_apply_exam_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `stop_apply_num` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `stop_diploma_date` date NULL DEFAULT NULL,
  `stop_diploma_num` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `major_status` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `total_credit` int(255) NULL DEFAULT NULL,
  `graduation_credit` int(255) NULL DEFAULT NULL,
  `total_course_number` int(255) NULL DEFAULT NULL,
  `whether_divide_direction` tinyint(1) NULL DEFAULT NULL,
  `major_category_level` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `major_category_code` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `major_category_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `apply_condition` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL,
  `graduation_condition` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL,
  `notes` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL,
  PRIMARY KEY (`major_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for major_plan_adjust_apply
-- ----------------------------
DROP TABLE IF EXISTS `major_plan_adjust_apply`;
CREATE TABLE `major_plan_adjust_apply`  (
  `approve_table_id` int(255) NOT NULL,
  `approve_table_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `school_id` int(255) NULL DEFAULT NULL,
  `school_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `apply_time` date NULL DEFAULT NULL,
  `approve_comment` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL,
  `approve_time` date NULL DEFAULT NULL,
  `approver` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `approve_num` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`approve_table_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for major_plan_adjust_course
-- ----------------------------
DROP TABLE IF EXISTS `major_plan_adjust_course`;
CREATE TABLE `major_plan_adjust_course`  (
  `major_id` int(10) NOT NULL,
  `old_course_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `new_course_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`major_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for major_plan_version
-- ----------------------------
DROP TABLE IF EXISTS `major_plan_version`;
CREATE TABLE `major_plan_version`  (
  `plan_version_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `plan_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `approve_status` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `xuelichu_suggestion` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL,
  `leader_sign` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `leader_sign_file` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`plan_version_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for major_school
-- ----------------------------
DROP TABLE IF EXISTS `major_school`;
CREATE TABLE `major_school`  (
  `major_id` varchar(7) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `main_target_school` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `main_target_school_code` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`major_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for nation_course
-- ----------------------------
DROP TABLE IF EXISTS `nation_course`;
CREATE TABLE `nation_course`  (
  `nation_major_code` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `course_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `credit` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`nation_major_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for nation_major
-- ----------------------------
DROP TABLE IF EXISTS `nation_major`;
CREATE TABLE `nation_major`  (
  `national_major_code` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `major_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `major_abbr` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `education_level` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `credit` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`national_major_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for new_major_apply
-- ----------------------------
DROP TABLE IF EXISTS `new_major_apply`;
CREATE TABLE `new_major_apply`  (
  `school_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NOT NULL,
  `major_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `apply_date` date NULL DEFAULT NULL,
  `approve_comment` date NULL DEFAULT NULL,
  `approve_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `approver` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  PRIMARY KEY (`school_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_czech_ci ROW_FORMAT = Dynamic;

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
-- Table structure for pro_course_info
-- ----------------------------
DROP TABLE IF EXISTS `pro_course_info`;
CREATE TABLE `pro_course_info`  (
  `major_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_czech_ci NOT NULL,
  `course_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `course_attr` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `course_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `exam_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `exam_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `direction_id` varchar(9) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  PRIMARY KEY (`major_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_czech_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pro_course_replace_relationship
-- ----------------------------
DROP TABLE IF EXISTS `pro_course_replace_relationship`;
CREATE TABLE `pro_course_replace_relationship`  (
  `major_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_czech_ci NOT NULL,
  `old_course_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `old_course_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `old_course_number` int(255) NULL DEFAULT NULL,
  `new_course_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `new_course_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `new_course_number` int(255) NULL DEFAULT NULL,
  `replace_relationship` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `is_automatic` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  PRIMARY KEY (`major_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_czech_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for renew_major_apply
-- ----------------------------
DROP TABLE IF EXISTS `renew_major_apply`;
CREATE TABLE `renew_major_apply`  (
  `school_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NOT NULL,
  `major_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `apply_date` date NULL DEFAULT NULL,
  `approve_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `approve_comment` date NULL DEFAULT NULL,
  `approve_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `approver` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NULL DEFAULT NULL,
  PRIMARY KEY (`school_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_czech_ci ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- Table structure for theory_practice
-- ----------------------------
DROP TABLE IF EXISTS `theory_practice`;
CREATE TABLE `theory_practice`  (
  `theory_course_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `practice_course_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`theory_course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
