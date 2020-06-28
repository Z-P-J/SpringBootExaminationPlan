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
@Table(name = "course_charge")
public class CourseCharge {

    @Id
    @Column(name = "course_id")
    private String courseId;

    @Column(name = "exam_id")
    private String examId;

    @Column(name = "course_register_charge")
    private int courseRegisterCharge;

}
