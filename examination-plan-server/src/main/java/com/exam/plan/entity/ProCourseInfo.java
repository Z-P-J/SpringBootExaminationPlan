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
@Table(name = "pro_course_info")
public class ProCourseInfo {

    @Id
    @Column(name = "major_id")
    private String majorId;

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "course_attr")
    private String courseAttr;

    @Column(name = "course_type")
    private String courseType;

    @Column(name = "exam_method")
    private String examMethod;

    @Column(name = "exam_type")
    private String examType;

    @Column(name = "direction_id")
    private String directionId;

    private String notes;

}