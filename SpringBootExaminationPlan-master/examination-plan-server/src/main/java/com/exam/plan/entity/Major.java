package com.exam.plan.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Getter
@Setter
@ToString
@Table(name = "major_info")
public class Major {

    @Id
    @Column(name="major_id")
    private String major_id;
    @Column(name="national_major_code")
    private String national_major_code;
    @Column(name="major_name")
    private String major_name;
    @Column(name="major_brief_introduction")
    private String major_brief_introduction;
    @Column(name="major_type")
    private String major_type;
    @Column(name="education_level")
    private String education_level;
    @Column(name="exam_type")
    private String exam_type;
    @Column(name="main_target_school")
    private String main_target_school;
    @Column(name="first_exam_id")
    private String first_exam_id;
    @Column(name="approve_num")
    private String approve_num;
    @Column(name="stop_freshman_registration_exam_id")
    private String stop_freshman_registration_exam_id;
    @Column(name="stop_registration_num")
    private String stop_registration_num;
    @Column(name="stop_apply_exam_id")
    private String stop_apply_exam_id;
    @Column(name="stop_apply_num")
    private String stop_apply_num;
    @Column(name="stop_diploma_date")
    private String stop_diploma_date;
    @Column(name="stop_diploma_num")
    private String stop_diploma_num;
    @Column(name="major_status")
    private String major_status;
    @Column(name="total_credit")
    private Integer total_credit;
    @Column(name="graduation_credit")
    private Integer graduation_credit;
    @Column(name="total_course_number")
    private Integer total_course_number;
    @Column(name="whether_divide_direction")
    private Integer whether_divide_direction;
    @Column(name="major_category_code")
    private String major_category_code;
    @Column(name="apply_condition")
    private String apply_condition;
    @Column(name="graduation_condition")
    private String graduation_condition;
    @Column(name="notes")
    private String notes;

}
