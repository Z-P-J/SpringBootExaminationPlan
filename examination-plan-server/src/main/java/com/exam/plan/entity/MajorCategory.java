package com.exam.plan.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
public class MajorCategory {
    @Id
    private String major_id;
    private String national_major_code;
    private String major_name;
    private String major_brief_introduction;
    private String major_type;
    private String education_level;
    private String exam_type;
    private String main_target_school;
    private String first_exam_id;
    private String approve_num;
    private String stop_freshman_registration_exam_id;
    private String stop_registration_num;
    private String stop_apply_exam_id;
    private String stop_apply_num;
    private String stop_diploma_date;
    private String stop_diploma_num;
    private String major_status;
    private Integer total_credit;
    private Integer graduation_credit;
    private Integer total_course_number;
    private Integer whether_divide_direction;
    private String major_category_code;
    private String apply_condition;
    private String graduation_condition;
    private String notes;
//    private String major_category_code;
    private String major_category_level;
    private String major_category_name;
//    private Major major;

}
