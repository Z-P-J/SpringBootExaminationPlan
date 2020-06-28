package com.exam.plan.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="exam_course")
public class ExamCourse {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="exam_id")
    private String exam_id;
    @Column(name="course_id")
    private String course_id;
    @Column(name="time_id")
    private String time_id;
}
