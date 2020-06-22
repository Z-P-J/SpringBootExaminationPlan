/*
 Navicat Premium Data Transfer

 Source Server         : ExaminationPlan
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : examination_plan_system

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 23/06/2020 00:47:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
-- Records of certificate_replace_relationship
-- ----------------------------

-- ----------------------------
-- Table structure for course_charge
-- ----------------------------
DROP TABLE IF EXISTS `course_charge`;
CREATE TABLE `course_charge`  (
  `course_id` varchar(5) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `exam_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `course_register_charge` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_charge
-- ----------------------------

-- ----------------------------
-- Table structure for course_info
-- ----------------------------
DROP TABLE IF EXISTS `course_info`;
CREATE TABLE `course_info`  (
  `course_id` varchar(5) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `national_course_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `course_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `course_specification` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `test_source` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `course_status` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `credit` int(255) NULL DEFAULT NULL,
  `qualified_score` int(255) NULL DEFAULT NULL,
  `score_scale` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `subjective_score` int(255) NULL DEFAULT NULL,
  `objective_score` int(255) NULL DEFAULT NULL,
  `total_score` int(255) NULL DEFAULT NULL,
  `exam_duration` int(255) NULL DEFAULT NULL,
  `is_procedural` tinyint(1) NULL DEFAULT NULL,
  `course_property` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_info
-- ----------------------------

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
-- Records of course_textbook_info
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of detail_of_student_service
-- ----------------------------

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
-- Records of elective_course_relationship_info
-- ----------------------------

-- ----------------------------
-- Table structure for exam_course
-- ----------------------------
DROP TABLE IF EXISTS `exam_course`;
CREATE TABLE `exam_course`  (
  `exam_id` varchar(3) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `course_id` varchar(5) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `time_id` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`exam_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of exam_course
-- ----------------------------

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
-- Records of exam_course_info
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of exam_info
-- ----------------------------

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
-- Records of exam_major_info
-- ----------------------------

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
-- Records of exam_plan_approve_info
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of exam_time
-- ----------------------------

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
-- Records of expend_major_apply
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of information_of_service_center
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of major_direction
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of major_info
-- ----------------------------

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
-- Records of major_plan_adjust_apply
-- ----------------------------

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
-- Records of major_plan_adjust_course
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of major_plan_version
-- ----------------------------

-- ----------------------------
-- Table structure for major_school
-- ----------------------------
DROP TABLE IF EXISTS `major_school`;
CREATE TABLE `major_school`  (
  `major_id` varchar(7) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `main_target_school` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `main_target_school_code` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`major_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of major_school
-- ----------------------------

-- ----------------------------
-- Table structure for nation_course
-- ----------------------------
DROP TABLE IF EXISTS `nation_course`;
CREATE TABLE `nation_course`  (
  `nation_major_code` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `course_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `credit` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`nation_major_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of nation_course
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of nation_major
-- ----------------------------

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
-- Records of new_major_apply
-- ----------------------------

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
-- Records of pro_course_info
-- ----------------------------

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
-- Records of pro_course_replace_relationship
-- ----------------------------

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
-- Records of renew_major_apply
-- ----------------------------

-- ----------------------------
-- Table structure for theory_practice
-- ----------------------------
DROP TABLE IF EXISTS `theory_practice`;
CREATE TABLE `theory_practice`  (
  `theory_course_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `practice_course_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`theory_course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of theory_practice
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
