package com.exam.plan.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="approve_major_info")
public class ApproveMajor{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "approve_id")
    private Integer approve_id;

    @Column(name = "school_id")
    private String school_id;

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

    public ApproveMajor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApprove_id() {
        return approve_id;
    }

    public void setApprove_id(Integer approve_id) {
        this.approve_id = approve_id;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getMajor_id() {
        return major_id;
    }

    public void setMajor_id(String major_id) {
        this.major_id = major_id;
    }

    public String getNational_major_code() {
        return national_major_code;
    }

    public void setNational_major_code(String national_major_code) {
        this.national_major_code = national_major_code;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public String getMajor_brief_introduction() {
        return major_brief_introduction;
    }

    public void setMajor_brief_introduction(String major_brief_introduction) {
        this.major_brief_introduction = major_brief_introduction;
    }

    public String getMajor_type() {
        return major_type;
    }

    public void setMajor_type(String major_type) {
        this.major_type = major_type;
    }

    public String getEducation_level() {
        return education_level;
    }

    public void setEducation_level(String education_level) {
        this.education_level = education_level;
    }

    public String getExam_type() {
        return exam_type;
    }

    public void setExam_type(String exam_type) {
        this.exam_type = exam_type;
    }

    public String getMain_target_school() {
        return main_target_school;
    }

    public void setMain_target_school(String main_target_school) {
        this.main_target_school = main_target_school;
    }

    public String getFirst_exam_id() {
        return first_exam_id;
    }

    public void setFirst_exam_id(String first_exam_id) {
        this.first_exam_id = first_exam_id;
    }

    public String getStop_freshman_registration_exam_id() {
        return stop_freshman_registration_exam_id;
    }

    public void setStop_freshman_registration_exam_id(String stop_freshman_registration_exam_id) {
        this.stop_freshman_registration_exam_id = stop_freshman_registration_exam_id;
    }

    public String getStop_registration_num() {
        return stop_registration_num;
    }

    public void setStop_registration_num(String stop_registration_num) {
        this.stop_registration_num = stop_registration_num;
    }

    public String getStop_apply_exam_id() {
        return stop_apply_exam_id;
    }

    public void setStop_apply_exam_id(String stop_apply_exam_id) {
        this.stop_apply_exam_id = stop_apply_exam_id;
    }

    public String getStop_apply_num() {
        return stop_apply_num;
    }

    public void setStop_apply_num(String stop_apply_num) {
        this.stop_apply_num = stop_apply_num;
    }

    public String getStop_diploma_date() {
        return stop_diploma_date;
    }

    public void setStop_diploma_date(String stop_diploma_date) {
        this.stop_diploma_date = stop_diploma_date;
    }

    public String getStop_diploma_num() {
        return stop_diploma_num;
    }

    public void setStop_diploma_num(String stop_diploma_num) {
        this.stop_diploma_num = stop_diploma_num;
    }

    public String getMajor_status() {
        return major_status;
    }

    public void setMajor_status(String major_status) {
        this.major_status = major_status;
    }

    public Integer getTotal_credit() {
        return total_credit;
    }

    public void setTotal_credit(Integer total_credit) {
        this.total_credit = total_credit;
    }

    public Integer getGraduation_credit() {
        return graduation_credit;
    }

    public void setGraduation_credit(Integer graduation_credit) {
        this.graduation_credit = graduation_credit;
    }

    public Integer getTotal_course_number() {
        return total_course_number;
    }

    public void setTotal_course_number(Integer total_course_number) {
        this.total_course_number = total_course_number;
    }

    public Integer getWhether_divide_direction() {
        return whether_divide_direction;
    }

    public void setWhether_divide_direction(Integer whether_divide_direction) {
        this.whether_divide_direction = whether_divide_direction;
    }

    public String getMajor_category_code() {
        return major_category_code;
    }

    public void setMajor_category_code(String major_category_code) {
        this.major_category_code = major_category_code;
    }

    public String getApply_condition() {
        return apply_condition;
    }

    public void setApply_condition(String apply_condition) {
        this.apply_condition = apply_condition;
    }

    public String getGraduation_condition() {
        return graduation_condition;
    }

    public void setGraduation_condition(String graduation_condition) {
        this.graduation_condition = graduation_condition;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
