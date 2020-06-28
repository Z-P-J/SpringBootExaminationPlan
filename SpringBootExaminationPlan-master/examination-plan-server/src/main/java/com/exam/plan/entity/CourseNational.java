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
@Table(name = "nation_course")
public class CourseNational {

    @Id
    @Column(name = "nation_major_code")
    private String nationMajorCode;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "credit")
    private int credit;

}
