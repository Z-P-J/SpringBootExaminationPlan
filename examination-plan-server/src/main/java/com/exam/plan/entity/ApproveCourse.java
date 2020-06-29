package com.exam.plan.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Data
@Getter
@Setter
@ToString
@Table(name = "approve_course_info")
public class ApproveCourse {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "major_id")
    private String major_id;

    @Column(name = "approve_id")
    private Integer approve_id;

    @Column(name = "school_id")
    private String school_id;

    @Column(name = "course_id")
    private String course_id;

    @Column(name = "course_name")
    private String course_name;

    @Column(name = "course_property")
    private String course_property;

    @Column(name = "new_course_id")
    private String new_course_id;

    @Column(name = "new_course_name")
    private String new_course_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajor_id() {
        return major_id;
    }

    public void setMajor_id(String major_id) {
        this.major_id = major_id;
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

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_property() {
        return course_property;
    }

    public void setCourse_property(String course_property) {
        this.course_property = course_property;
    }

    public String getNew_course_id() {
        return new_course_id;
    }

    public void setNew_course_id(String new_course_id) {
        this.new_course_id = new_course_id;
    }

    public String getNew_course_name() {
        return new_course_name;
    }

    public void setNew_course_name(String new_course_name) {
        this.new_course_name = new_course_name;
    }

}
