/*
Navicat MySQL Data Transfer

Source Server         : mine
Source Server Version : 80020
Source Host           : localhost:3306
Source Database       : exam

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2020-06-29 18:03:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户Id',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `register_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `login_time` datetime DEFAULT NULL COMMENT '上一次登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ix_account_name` (`name`) USING BTREE,
  UNIQUE KEY `ix_account_email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', '123456@qq.com', 'admin', 'admin123', '2019-07-01 00:00:00', '2020-06-29 15:35:53');
INSERT INTO `account` VALUES ('2', 'editor@qq.com', 'editor', '$2a$10$/m4SgZ.ZFVZ7rcbQpJW2tezmuhf/UzQtpAtXb0WZiAE3TeHxq2DYu', '2019-07-02 00:00:00', '2019-07-02 00:00:00');
INSERT INTO `account` VALUES ('3', 'test@qq.com', 'test', '$2a$10$.0gBYBHAtdkxUUQNg3kII.fqGOngF4BLe8JavthZFalt2QIXHlrhm', '2019-07-03 00:00:00', '2019-07-03 00:00:00');
INSERT INTO `account` VALUES ('6', '1@qq.com', '4321', '123456', '2020-06-20 17:46:24', '2020-06-20 18:34:21');
INSERT INTO `account` VALUES ('7', '1111@qq.com', '123', '123456', '2020-06-21 22:51:40', null);

-- ----------------------------
-- Table structure for account_role
-- ----------------------------
DROP TABLE IF EXISTS `account_role`;
CREATE TABLE `account_role` (
  `account_id` bigint unsigned NOT NULL COMMENT '用户Id',
  `role_id` bigint unsigned NOT NULL COMMENT '角色Id',
  PRIMARY KEY (`account_id`,`role_id`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE,
  CONSTRAINT `account_role_fk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `account_role_fk_2` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- ----------------------------
-- Records of account_role
-- ----------------------------
INSERT INTO `account_role` VALUES ('1', '1');
INSERT INTO `account_role` VALUES ('2', '2');
INSERT INTO `account_role` VALUES ('6', '2');
INSERT INTO `account_role` VALUES ('7', '2');
INSERT INTO `account_role` VALUES ('3', '3');

-- ----------------------------
-- Table structure for approve
-- ----------------------------
DROP TABLE IF EXISTS `approve`;
CREATE TABLE `approve` (
  `approve_id` int NOT NULL AUTO_INCREMENT,
  `approve_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `approve_school_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `apply_time` datetime DEFAULT NULL,
  `approve_comment` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `approve_time` datetime DEFAULT NULL,
  `approver` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `approve_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `approve_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`approve_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1170 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of approve
-- ----------------------------
INSERT INTO `approve` VALUES ('1134', '续办专业', '00004', '2020-06-28 09:00:00', '准许通过', '2020-06-29 02:16:05', '于老师', '已审批', null);
INSERT INTO `approve` VALUES ('1140', '新专业', '00001', '2020-06-28 10:23:57', null, null, null, '已申请', null);
INSERT INTO `approve` VALUES ('1143', '扩办专业', '00001', '2020-06-29 02:19:27', null, '2020-06-29 02:19:39', null, '已审批', null);
INSERT INTO `approve` VALUES ('1146', '扩办专业', '00001', '2020-06-29 03:33:42', null, null, null, '已申请', null);
INSERT INTO `approve` VALUES ('1159', '专业调整', '00001', '2020-06-29 12:16:40', null, null, null, '已申请', null);
INSERT INTO `approve` VALUES ('1161', '专业调整', '00001', '2020-06-29 04:53:45', 't通过', '2020-06-29 04:54:16', null, '已审批', null);
INSERT INTO `approve` VALUES ('1168', '续办专业', '00002', '2020-06-29 04:55:27', null, null, null, '已申请', null);
INSERT INTO `approve` VALUES ('1169', '新专业', '00001', '2020-06-29 04:56:07', null, null, null, '已申请', null);

-- ----------------------------
-- Table structure for approve_course_info
-- ----------------------------
DROP TABLE IF EXISTS `approve_course_info`;
CREATE TABLE `approve_course_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `major_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `course_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `new_course_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `new_course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `approve_id` int DEFAULT NULL,
  `course_property` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `school_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of approve_course_info
-- ----------------------------
INSERT INTO `approve_course_info` VALUES ('1', 'A000001', 'B0001', '线性代数', '11111', '11111', '1161', '理论', null);
INSERT INTO `approve_course_info` VALUES ('2', 'A000001', 'C0005', '古代历史', 'B1292', '物联网', '1153', '理论', null);
INSERT INTO `approve_course_info` VALUES ('3', 'A000003', 'C0003', '实训课', 'C0002', '西方经济学', '1153', '实践', null);

-- ----------------------------
-- Table structure for approve_major_info
-- ----------------------------
DROP TABLE IF EXISTS `approve_major_info`;
CREATE TABLE `approve_major_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `major_id` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `national_major_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `major_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `major_brief_introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `major_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `education_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `exam_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `main_target_school` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `first_exam_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `approve_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `stop_freshman_registration_exam_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `stop_registration_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `stop_apply_exam_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `stop_apply_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `stop_diploma_date` date DEFAULT NULL,
  `stop_diploma_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `major_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `total_credit` int DEFAULT NULL,
  `graduation_credit` int DEFAULT NULL,
  `total_course_number` int DEFAULT NULL,
  `whether_divide_direction` tinyint(1) DEFAULT NULL,
  `major_category_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `apply_condition` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `graduation_condition` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `notes` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `school_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `approve_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='专业基本信息';

-- ----------------------------
-- Records of approve_major_info
-- ----------------------------
INSERT INTO `approve_major_info` VALUES ('18', 'A000002', null, '法学', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '00001', '1133');
INSERT INTO `approve_major_info` VALUES ('19', 'A000001', null, '汉语言', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '00001', '1133');
INSERT INTO `approve_major_info` VALUES ('20', 'A000001', null, '汉语言', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '00004', '1134');
INSERT INTO `approve_major_info` VALUES ('23', 'A100000', 'A100000', '测试专业', '后续补充介绍', '本科段', '本科', '面向高校', '四川大学', '001', null, '000', '000', '000', '000', '2022-12-31', '000', '正常', '200', '110', '55', '1', 'A100000', '暂无', '暂无', '暂无', '00001', '1139');
INSERT INTO `approve_major_info` VALUES ('24', 'A100000', 'A100000', '测试专业', '后续补充介绍', '本科段', '本科', '面向高校', '四川大学', '001', null, '000', '000', '000', '000', '2022-12-31', '000', '正常', '200', '110', '55', '1', 'A100000', '暂无', '暂无', '暂无', '00001', '1140');
INSERT INTO `approve_major_info` VALUES ('25', 'A100000', 'A100000', '测试专业', '后续补充介绍', '本科段', '本科', '面向高校', '四川大学', '001', null, '000', '000', '000', '000', '2022-12-31', '000', '正常', '200', '110', '55', '1', 'A100000', '暂无', '暂无', '暂无', '00001', '1141');
INSERT INTO `approve_major_info` VALUES ('26', 'A100001', null, '测试专业', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '00001', '1143');
INSERT INTO `approve_major_info` VALUES ('27', 'A100002', null, '测试专业', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '00001', '1146');
INSERT INTO `approve_major_info` VALUES ('28', 'A000001', null, '汉语言', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '00002', '1168');
INSERT INTO `approve_major_info` VALUES ('29', 'A111000', 'C000004', '测试专业', '后续补充介绍', '本科段', '本科', '面向高校', '四川大学', '001', null, '000', '000', '000', '000', '2022-12-31', '000', '正常', '200', '110', '55', '1', 'B000003', '暂无', '暂无', '暂无', '00001', '1169');

-- ----------------------------
-- Table structure for certificate_replace_relationship
-- ----------------------------
DROP TABLE IF EXISTS `certificate_replace_relationship`;
CREATE TABLE `certificate_replace_relationship` (
  `non-degree_certificate_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `non-degree_certificate_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `exam-free_self-study_exam_course` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `course_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `conversion_score` int DEFAULT NULL,
  PRIMARY KEY (`non-degree_certificate_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of certificate_replace_relationship
-- ----------------------------

-- ----------------------------
-- Table structure for course_charge
-- ----------------------------
DROP TABLE IF EXISTS `course_charge`;
CREATE TABLE `course_charge` (
  `course_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `exam_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `course_register_charge` int DEFAULT NULL,
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of course_charge
-- ----------------------------
INSERT INTO `course_charge` VALUES ('00001', '1', '100');
INSERT INTO `course_charge` VALUES ('00002', '2', '1000');

-- ----------------------------
-- Table structure for course_info
-- ----------------------------
DROP TABLE IF EXISTS `course_info`;
CREATE TABLE `course_info` (
  `course_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `national_course_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `course_specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `test_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `course_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `credit` int DEFAULT NULL,
  `qualified_score` int DEFAULT NULL,
  `score_scale` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `subjective_score` int DEFAULT NULL,
  `objective_score` int DEFAULT NULL,
  `total_score` int DEFAULT NULL,
  `exam_duration` int DEFAULT NULL,
  `is_procedural` tinyint(1) DEFAULT NULL,
  `course_property` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of course_info
-- ----------------------------
INSERT INTO `course_info` VALUES ('00004', '00004', '数学', '数学课程', '全国命题', '注销', '3', '60', '100分制', '40', '60', '100', '120', '0', '理论', '无');
INSERT INTO `course_info` VALUES ('00007', '00007', '数据结构', '数据结构', '全国命题', '注销', '3', '60', '100分制', '40', '60', '100', '120', '0', '理论', '无');
INSERT INTO `course_info` VALUES ('11111', '11111', '11111', '无', '全国命题', '注销', '3', '60', '100分制', '40', '60', '100', '120', '0', '理论', '无');
INSERT INTO `course_info` VALUES ('11112', '', '11111', '无', '全国命题', '注销', '3', '60', '100分制', '40', '60', '100', '120', '0', '理论', '无');

-- ----------------------------
-- Table structure for course_textbook_info
-- ----------------------------
DROP TABLE IF EXISTS `course_textbook_info`;
CREATE TABLE `course_textbook_info` (
  `course_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_czech_ci NOT NULL,
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `textbook_code` varchar(9) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `textbook_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `textbook_ISBN` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `textbook_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `chief_editor` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `publishing_house` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `publication_date` date DEFAULT NULL,
  `price` int DEFAULT NULL,
  `textbook_select_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `syllabus` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `textbook_use_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `textbook_start_time` date DEFAULT NULL,
  `textbook_end_time` date DEFAULT NULL,
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of course_textbook_info
-- ----------------------------
INSERT INTO `course_textbook_info` VALUES ('00001', '英语', '000000001', '英语', '12345678', '教材', '张三', '张三', '出版社', '1.0', '2020-06-23', '100', '国家统编', '教材大纲', '作废', '2020-01-02', '2020-12-01');
INSERT INTO `course_textbook_info` VALUES ('00002', '数学', '000000002', '数学', '12345', '教材', '未知', '未知', '未知', '1.0', '2020-01-01', '50', '国家统编', 'sedrfhgtjghnvk', '作废', '2020-01-01', '2020-12-01');
INSERT INTO `course_textbook_info` VALUES ('00003', '数据结构', '000000003', '数据结构', 'xxxxxxxx', '教材', '未知', '未知', '未知', '1.0', '2020-01-01', '50', '国家统编', '', '未启用', '2020-01-01', '2020-12-01');

-- ----------------------------
-- Table structure for detail_of_student_service
-- ----------------------------
DROP TABLE IF EXISTS `detail_of_student_service`;
CREATE TABLE `detail_of_student_service` (
  `main_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `school` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '所属院校',
  `student_service_org` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '助学组织名称',
  `student_permit_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '助学许可证编码\r\n',
  `issuing_unit_of_school_license` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '办学许可证发放单位',
  `issuing_date_of_school_license` date DEFAULT NULL COMMENT '办学许可证发放日期',
  `types_of_student_services` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '助学主体类型',
  `name_of_legal_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '法人姓名',
  `position_of_legal_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '法人职务',
  `number_of_main_students` int DEFAULT NULL COMMENT '集中学员数',
  `number_of_part_time_teachers` int DEFAULT NULL COMMENT '兼职教学人数',
  `
number_of_full_time_teachers` int DEFAULT NULL COMMENT '专职教学人数',
  `number_of_amateur_students` int DEFAULT NULL COMMENT '业余学员人数',
  `new_students_of_year` int DEFAULT NULL COMMENT '当年新招升学员数',
  `number_of_managers` int DEFAULT NULL COMMENT '管理人员总数',
  `number_of_managers_and_teachers` int DEFAULT NULL COMMENT '教学和管理人员数',
  `person_in_charge` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '负责人',
  `contact_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '联系地址',
  `postal_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '邮政编码',
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话',
  `fax` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '传真',
  `major_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '专业代码',
  `major_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '专业名称',
  `ways_of_student_services` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '助学方式',
  `means_of_student_services` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '助学手段',
  `number_of_students` int DEFAULT NULL COMMENT '学员数',
  PRIMARY KEY (`main_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of detail_of_student_service
-- ----------------------------
INSERT INTO `detail_of_student_service` VALUES ('1', '四川大学', '四川大学助学组织', 'k123456', '四川大学办事处', '2020-06-03', '公办', '李校长', '校长', '100', '200', '20', '200', '100', '20', '100', '李校长', '川大路21段', '611200', '123456789', '465', 'A000001', '汉语言', '线上线下', '发助学金', '500');
INSERT INTO `detail_of_student_service` VALUES ('2', '电子科大', '电子科大助学组织', 'k123456', '电子科大办事处', '2020-06-10', '公办', '李校长', '校长', '100', '200', '20', '200', '100', '20', '100', '李校长', '川大路21段', '611200', '123456789', '465', 'A000001', '汉语言', '线上线下', '发助学金', '500');
INSERT INTO `detail_of_student_service` VALUES ('3', '西南财大', '西南财大助学组织', 'k123456', '西南财大办事处', '2020-06-17', '公办', '李校长', '校长', '100', '200', '20', '200', '100', '20', '100', '李校长', '川大路21段', '611200', '123456789', '465', 'A000001', '汉语言', '线上线下', '发助学金', '500');

-- ----------------------------
-- Table structure for elective_course_relationship_info
-- ----------------------------
DROP TABLE IF EXISTS `elective_course_relationship_info`;
CREATE TABLE `elective_course_relationship_info` (
  `major_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `direction_id` varchar(9) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `course_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `ec_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `ec_number` int DEFAULT NULL,
  `group_credit` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of elective_course_relationship_info
-- ----------------------------

-- ----------------------------
-- Table structure for exam_course
-- ----------------------------
DROP TABLE IF EXISTS `exam_course`;
CREATE TABLE `exam_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `course_id` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `time_id` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='统考课表';

-- ----------------------------
-- Records of exam_course
-- ----------------------------
INSERT INTO `exam_course` VALUES ('1', '202', '00001', '7A');
INSERT INTO `exam_course` VALUES ('4', '201', '00002', '7A');
INSERT INTO `exam_course` VALUES ('5', '202', '00001', '7A');
INSERT INTO `exam_course` VALUES ('63', '201', '00001', '7B');
INSERT INTO `exam_course` VALUES ('65', '202', '2', '2A');
INSERT INTO `exam_course` VALUES ('66', '203', '1', '3A');

-- ----------------------------
-- Table structure for exam_course_info
-- ----------------------------
DROP TABLE IF EXISTS `exam_course_info`;
CREATE TABLE `exam_course_info` (
  `exam_id` varchar(3) CHARACTER SET utf8 COLLATE utf8_czech_ci NOT NULL,
  `course_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `time_id` varchar(1) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `exam_date` date DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`exam_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of exam_course_info
-- ----------------------------
INSERT INTO `exam_course_info` VALUES ('001', '00001', '1', '2020-01-01', '2020-02-01 10:00:00', '2020-02-01 12:00:00');

-- ----------------------------
-- Table structure for exam_info
-- ----------------------------
DROP TABLE IF EXISTS `exam_info`;
CREATE TABLE `exam_info` (
  `exams_id` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `time_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `exams_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `jihua_exams_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `kaowu_exams_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `kaoji_exams_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `exam_date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `default_charge` int DEFAULT '26',
  `photo_charge` int(255) unsigned zerofill DEFAULT '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010',
  `notes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`exams_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='考次信息';

-- ----------------------------
-- Records of exam_info
-- ----------------------------
INSERT INTO `exam_info` VALUES ('201', '4B', '统考', '未用', '已用', '已用', '2020-06-24', '18:32:10', '08:09:29', '26', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010', null);
INSERT INTO `exam_info` VALUES ('202', '3C', '统考', '未用', '冻结', '冻结', '2020-06-24', '18:32:10', '20:07:29', '26', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010', '');
INSERT INTO `exam_info` VALUES ('203', '7A', '省考', '未用', '冻结', '冻结', '2020-07-01', '18:32:10', '12:00:00', '26', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010', '后续添加备注');
INSERT INTO `exam_info` VALUES ('204', '7A', '省考', '未用', '冻结', '冻结', '2020-07-01', '18:32:10', '12:00:00', '26', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010', '后续添加备注');

-- ----------------------------
-- Table structure for exam_major_info
-- ----------------------------
DROP TABLE IF EXISTS `exam_major_info`;
CREATE TABLE `exam_major_info` (
  `exam_id` varchar(3) CHARACTER SET utf8 COLLATE utf8_czech_ci NOT NULL,
  `major_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  PRIMARY KEY (`exam_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of exam_major_info
-- ----------------------------
INSERT INTO `exam_major_info` VALUES ('001', '00002');

-- ----------------------------
-- Table structure for exam_plan_approve_info
-- ----------------------------
DROP TABLE IF EXISTS `exam_plan_approve_info`;
CREATE TABLE `exam_plan_approve_info` (
  `exam_id` varchar(3) CHARACTER SET utf8 COLLATE utf8_czech_ci NOT NULL,
  `exam_approve_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `xuelichu_suggestion` text CHARACTER SET utf8 COLLATE utf8_czech_ci,
  `leader_sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  PRIMARY KEY (`exam_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of exam_plan_approve_info
-- ----------------------------
INSERT INTO `exam_plan_approve_info` VALUES ('001', '计划科提交', '无', 'zpj');
INSERT INTO `exam_plan_approve_info` VALUES ('002', '计划科提交', '无', '');

-- ----------------------------
-- Table structure for exam_time
-- ----------------------------
DROP TABLE IF EXISTS `exam_time`;
CREATE TABLE `exam_time` (
  `exam_id` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `time_id` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `exam_date` date DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`exam_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of exam_time
-- ----------------------------

-- ----------------------------
-- Table structure for expend_major_apply
-- ----------------------------
DROP TABLE IF EXISTS `expend_major_apply`;
CREATE TABLE `expend_major_apply` (
  `school_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NOT NULL,
  `major_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `apply_date` date DEFAULT NULL,
  `approve_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `approve_comment` date DEFAULT NULL,
  `approve_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `approver` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  PRIMARY KEY (`school_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of expend_major_apply
-- ----------------------------

-- ----------------------------
-- Table structure for information_of_service_center
-- ----------------------------
DROP TABLE IF EXISTS `information_of_service_center`;
CREATE TABLE `information_of_service_center` (
  `main_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `service_center_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '服务中心名称',
  `service_center_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '服务中心类型，0：国家级，1：省级，2市级',
  `school_license_nomber` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '办学许可证号',
  `license_issuing_unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '许可证发放单位',
  `license_issue_date` date DEFAULT NULL COMMENT '许可证发放日期',
  `service_center_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '服务中心地址',
  `head_of_service_center` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '服务中心负责人',
  `nomber_of_the_leader` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '负责人身份证号',
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话',
  `approval_date` date DEFAULT NULL COMMENT '审批日期',
  `approval_nomber` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '审批文号',
  `service_center_introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '服务中心简介',
  PRIMARY KEY (`main_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of information_of_service_center
-- ----------------------------

-- ----------------------------
-- Table structure for major_categories
-- ----------------------------
DROP TABLE IF EXISTS `major_categories`;
CREATE TABLE `major_categories` (
  `category_code` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `major_category_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `major_category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`category_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC COMMENT='大类专业';

-- ----------------------------
-- Records of major_categories
-- ----------------------------
INSERT INTO `major_categories` VALUES ('B000001', '二级学科', '工学');
INSERT INTO `major_categories` VALUES ('B000002', '二级学科', '理学');
INSERT INTO `major_categories` VALUES ('B000003', '二级学科', '社会学');
INSERT INTO `major_categories` VALUES ('B000007', '一级学科', '社会学');
INSERT INTO `major_categories` VALUES ('B200002', '一级学科', '社会学');

-- ----------------------------
-- Table structure for major_direction
-- ----------------------------
DROP TABLE IF EXISTS `major_direction`;
CREATE TABLE `major_direction` (
  `major_id` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `direction_id` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `direction_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `total_credit` int DEFAULT NULL,
  PRIMARY KEY (`major_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of major_direction
-- ----------------------------
INSERT INTO `major_direction` VALUES ('B000001', 'B00000101', '测试方向', '9');

-- ----------------------------
-- Table structure for major_info
-- ----------------------------
DROP TABLE IF EXISTS `major_info`;
CREATE TABLE `major_info` (
  `major_id` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `national_major_code` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `major_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `major_brief_introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `major_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `education_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `exam_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `main_target_school` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `first_exam_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `approve_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `stop_freshman_registration_exam_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `stop_registration_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `stop_apply_exam_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `stop_apply_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `stop_diploma_date` date DEFAULT NULL,
  `stop_diploma_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `major_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `total_credit` int DEFAULT NULL,
  `graduation_credit` int DEFAULT NULL,
  `total_course_number` int DEFAULT NULL,
  `whether_divide_direction` tinyint(1) DEFAULT NULL,
  `major_category_code` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `apply_condition` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `graduation_condition` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `notes` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  PRIMARY KEY (`major_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='专业基本信息';

-- ----------------------------
-- Records of major_info
-- ----------------------------
INSERT INTO `major_info` VALUES ('A000001', 'C000002', '汉语言', '暂无介绍', '本科段', '本科', '面向高校', '四川大学', '003', '000', '000', '000', '000', '000', '2020-06-23', '000', '正常', '200', '170', '50', '0', 'B000003', '000', '毕业学分达到要求', '000');
INSERT INTO `major_info` VALUES ('A000002', 'C000001', '法学', '暂无介绍', '本科段', '本科', '面向社会', '四川大学', '001', '000', '000', '000', '000', '000', '2020-06-23', '000', '正常', '200', '170', '50', '1', 'B000001', '000', '毕业学分达到要求', '000');
INSERT INTO `major_info` VALUES ('A100001', 'A100000', '测试专业', '后续补充介绍', '本科段', '本科', '面向高校', '四川大学', '001', 'BS001', '000', '000', '000', '000', '2022-12-31', '000', '正常', '200', '110', '55', '1', 'A100000', '暂无', '暂无', '暂无');
INSERT INTO `major_info` VALUES ('A110001', 'A100000', '测试专业', '后续补充介绍', '本科段', '本科', '面向高校', '四川大学', '001', 'BS001', '000', '000', '000', '000', '2022-12-31', '000', '正常', '200', '110', '55', '1', 'B000007', '暂无', '暂无', '暂无');

-- ----------------------------
-- Table structure for major_plan_adjust_apply
-- ----------------------------
DROP TABLE IF EXISTS `major_plan_adjust_apply`;
CREATE TABLE `major_plan_adjust_apply` (
  `approve_table_id` int NOT NULL,
  `approve_table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `school_id` int DEFAULT NULL,
  `school_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `apply_time` date DEFAULT NULL,
  `approve_comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `approve_time` date DEFAULT NULL,
  `approver` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `approve_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`approve_table_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of major_plan_adjust_apply
-- ----------------------------

-- ----------------------------
-- Table structure for major_plan_adjust_course
-- ----------------------------
DROP TABLE IF EXISTS `major_plan_adjust_course`;
CREATE TABLE `major_plan_adjust_course` (
  `major_id` int NOT NULL,
  `old_course_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `new_course_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`major_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of major_plan_adjust_course
-- ----------------------------

-- ----------------------------
-- Table structure for major_plan_version
-- ----------------------------
DROP TABLE IF EXISTS `major_plan_version`;
CREATE TABLE `major_plan_version` (
  `plan_version_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `plan_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `approve_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `xuelichu_suggestion` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `leader_sign` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `major_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `course_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`plan_version_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of major_plan_version
-- ----------------------------
INSERT INTO `major_plan_version` VALUES ('B00001', '计算机普通', '已启用', '2020-06-28', '计划发布启用', 0xE8BF99E698AFE5ADA6E58E86E5A484E79A84E6848FE8A781, 0xE5908CE6848FE58F91E5B88332E38082, 'A123122', '12312');
INSERT INTO `major_plan_version` VALUES ('B00002', '物联网普通', '已审批', '2020-06-28', '领导审核', 0xE697A0, 0xE697A0E6848FE8A781, 'A123122', '12312');
INSERT INTO `major_plan_version` VALUES ('B00003', '公共管理普通', '已启用', '2020-06-28', '计划发布启用', 0xE697A0, 0xE697A0, 'A123122', '12312');
INSERT INTO `major_plan_version` VALUES ('P0000', '计算机科学专业计划', '新建', '2020-06-29', '计划科提交', 0xE697A0, 0xE697A0, 'A000000', '00000');
INSERT INTO `major_plan_version` VALUES ('P001', '化学专业计划', '已审批', '2020-06-28', '领导审核', 0xE697A0, 0xE695B4E68CBAE5A5BD, 'G000001', '12323');
INSERT INTO `major_plan_version` VALUES ('P002', '口腔专业计划', '已编制', '2020-06-28', '学历处审核', 0xE697A0, 0xE697A0, 'G000001', '12323');
INSERT INTO `major_plan_version` VALUES ('P003', '临床专业计划', '已审批', '2020-06-28', '领导审核', 0xE697A0, 0xE697A0, 'C000001', '00001');
INSERT INTO `major_plan_version` VALUES ('P004', '网络安全专业计划', '已审批', '2020-06-29', '领导审核', 0xE697A0, 0xE697A0, 'A000000', '00000');

-- ----------------------------
-- Table structure for major_school
-- ----------------------------
DROP TABLE IF EXISTS `major_school`;
CREATE TABLE `major_school` (
  `id` int NOT NULL AUTO_INCREMENT,
  `major_id` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `main_target_school` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `main_target_school_code` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC COMMENT='专业主考院校';

-- ----------------------------
-- Records of major_school
-- ----------------------------
INSERT INTO `major_school` VALUES ('1', 'A000001', '00001', '1');
INSERT INTO `major_school` VALUES ('2', 'A000001', '00002', '2');
INSERT INTO `major_school` VALUES ('6', 'A000002', '00001', '1');
INSERT INTO `major_school` VALUES ('11', 'A000001', '00004', '4');
INSERT INTO `major_school` VALUES ('17', 'A000002', '00001', '3');
INSERT INTO `major_school` VALUES ('18', 'A000002', '00001', '3');
INSERT INTO `major_school` VALUES ('26', 'A000001', '00003', '3');
INSERT INTO `major_school` VALUES ('27', 'A000001', '00005', '5');

-- ----------------------------
-- Table structure for nation_course
-- ----------------------------
DROP TABLE IF EXISTS `nation_course`;
CREATE TABLE `nation_course` (
  `nation_major_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `credit` int DEFAULT NULL,
  PRIMARY KEY (`nation_major_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of nation_course
-- ----------------------------
INSERT INTO `nation_course` VALUES ('00001', '数学', '3');
INSERT INTO `nation_course` VALUES ('00002', '语文', '3');
INSERT INTO `nation_course` VALUES ('00003', '英语', '3');
INSERT INTO `nation_course` VALUES ('00004', '历史', '3');

-- ----------------------------
-- Table structure for nation_major
-- ----------------------------
DROP TABLE IF EXISTS `nation_major`;
CREATE TABLE `nation_major` (
  `national_major_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `major_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `major_abbr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `education_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `credit` int DEFAULT NULL,
  PRIMARY KEY (`national_major_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='全国专业';

-- ----------------------------
-- Records of nation_major
-- ----------------------------
INSERT INTO `nation_major` VALUES ('C000001', '汉语言', null, '本科', '170');
INSERT INTO `nation_major` VALUES ('C000002', '计算机科学与技术', null, '本科', '213');
INSERT INTO `nation_major` VALUES ('C000003', '法学', null, '专科', '165');
INSERT INTO `nation_major` VALUES ('C000004', '艺术', null, '专科', '143');
INSERT INTO `nation_major` VALUES ('C100000', '社会学', '社会学', '本科', '170');

-- ----------------------------
-- Table structure for new_major_apply
-- ----------------------------
DROP TABLE IF EXISTS `new_major_apply`;
CREATE TABLE `new_major_apply` (
  `school_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NOT NULL,
  `major_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `apply_date` date DEFAULT NULL,
  `approve_comment` date DEFAULT NULL,
  `approve_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `approver` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  PRIMARY KEY (`school_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of new_major_apply
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '权限Id',
  `resource` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限对应的资源',
  `code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限的代码/通配符,对应代码中@hasAuthority(xx)',
  `handle` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '对应的资源操作',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '账号', 'account:list', '列表');
INSERT INTO `permission` VALUES ('2', '账号', 'account:detail', '详情');
INSERT INTO `permission` VALUES ('3', '账号', 'account:add', '添加');
INSERT INTO `permission` VALUES ('4', '账号', 'account:update', '更新');
INSERT INTO `permission` VALUES ('5', '账号', 'account:delete', '删除');
INSERT INTO `permission` VALUES ('6', '账号', 'account:search', '搜索');
INSERT INTO `permission` VALUES ('7', '角色', 'role:list', '列表');
INSERT INTO `permission` VALUES ('8', '角色', 'role:detail', '详情');
INSERT INTO `permission` VALUES ('9', '角色', 'role:add', '添加');
INSERT INTO `permission` VALUES ('10', '角色', 'role:update', '更新');
INSERT INTO `permission` VALUES ('11', '角色', 'role:delete', '删除');
INSERT INTO `permission` VALUES ('12', '角色', 'role:search', '搜索');

-- ----------------------------
-- Table structure for pro_course_info
-- ----------------------------
DROP TABLE IF EXISTS `pro_course_info`;
CREATE TABLE `pro_course_info` (
  `major_id` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `course_id` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `course_attr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `course_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `exam_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `exam_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `direction_id` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `notes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`major_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of pro_course_info
-- ----------------------------
INSERT INTO `pro_course_info` VALUES ('', '11111', '专业核心', '必考', '笔试', '统考', '', '无');
INSERT INTO `pro_course_info` VALUES ('0000001', '00001', '专业核心', '必考', '笔试', '统考', '000000001', '无');
INSERT INTO `pro_course_info` VALUES ('B000001', '12345', '公共基础', '必考', '非笔试', '省考', 'B00000101', '无');
INSERT INTO `pro_course_info` VALUES ('C000002', '11223', '专业选考', '选考', '机考', '省考', 'C00000203', '无');

-- ----------------------------
-- Table structure for pro_course_replace_relationship
-- ----------------------------
DROP TABLE IF EXISTS `pro_course_replace_relationship`;
CREATE TABLE `pro_course_replace_relationship` (
  `major_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_czech_ci NOT NULL,
  `old_course_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `old_course_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `old_course_number` int DEFAULT NULL,
  `new_course_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `new_course_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `new_course_number` int DEFAULT NULL,
  `replace_relationship` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `is_automatic` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  PRIMARY KEY (`major_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of pro_course_replace_relationship
-- ----------------------------

-- ----------------------------
-- Table structure for renew_major_apply
-- ----------------------------
DROP TABLE IF EXISTS `renew_major_apply`;
CREATE TABLE `renew_major_apply` (
  `school_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci NOT NULL,
  `major_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `apply_date` date DEFAULT NULL,
  `approve_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `approve_comment` date DEFAULT NULL,
  `approve_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `approver` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_czech_ci DEFAULT NULL,
  PRIMARY KEY (`school_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of renew_major_apply
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '角色Id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '2019-07-01 00:00:00', '2019-07-01 00:00:00');
INSERT INTO `role` VALUES ('2', '普通用户', '2019-07-01 00:00:00', '2019-07-01 00:00:00');
INSERT INTO `role` VALUES ('3', '测试', '2019-07-01 00:00:00', '2019-07-01 00:00:00');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` bigint unsigned NOT NULL COMMENT '角色Id',
  `permission_id` bigint unsigned NOT NULL COMMENT '权限Id',
  PRIMARY KEY (`role_id`,`permission_id`) USING BTREE,
  KEY `permission_id` (`permission_id`) USING BTREE,
  CONSTRAINT `role_permission_fk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_permission_fk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色权限表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('3', '1');
INSERT INTO `role_permission` VALUES ('3', '5');

-- ----------------------------
-- Table structure for school_info
-- ----------------------------
DROP TABLE IF EXISTS `school_info`;
CREATE TABLE `school_info` (
  `school_id` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `school_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `school_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `school_postcode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `school_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `school_web` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `notes` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='院校信息(后加';

-- ----------------------------
-- Records of school_info
-- ----------------------------
INSERT INTO `school_info` VALUES ('00001', '四川大学', '', '612500', '12580', '', 0xE69A82E697B6E999A2E6A0A1E4BFA1E681AF);
INSERT INTO `school_info` VALUES ('00002', '电子科技大学', '', '612500', '12581', '', '');
INSERT INTO `school_info` VALUES ('00003', '西南财经大学', '', '612500', '12582', '', '');
INSERT INTO `school_info` VALUES ('00004', '西南交通大学', '', '612500', '12583', '', '');
INSERT INTO `school_info` VALUES ('00005', '四川师范大学', '', '612500', '12580', '', 0xE69A82E697B6E999A2E6A0A1E4BFA1E681AF);
INSERT INTO `school_info` VALUES ('00006', '四川农业大学', '', '612500', '12581', '', '');
INSERT INTO `school_info` VALUES ('00007', '成都理工大学', '', '612500', '12582', '', '');
INSERT INTO `school_info` VALUES ('00008', '西华大学', '', '612500', '12583', '', '');
INSERT INTO `school_info` VALUES ('00009', '西南民族大学', '', '612500', '12580', '', 0xE69A82E697B6E999A2E6A0A1E4BFA1E681AF);
INSERT INTO `school_info` VALUES ('00010', '西南科技大学', '', '612500', '12581', '', '');
INSERT INTO `school_info` VALUES ('00011', '成都中医药大学', '', '612500', '12582', '', '');
INSERT INTO `school_info` VALUES ('00012', '成都医学院', '', '612500', '12583', '', '');

-- ----------------------------
-- Table structure for theory_practice
-- ----------------------------
DROP TABLE IF EXISTS `theory_practice`;
CREATE TABLE `theory_practice` (
  `theory_course_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `practice_course_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`theory_course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of theory_practice
-- ----------------------------
INSERT INTO `theory_practice` VALUES ('00001', '00003');
