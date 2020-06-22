package com.exam.plan.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class Course {

    @Id
    @Column(name = "course_id")
    private String courseId;
    @Column(name = "course_code")
    private String courseCode;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "course_desc")
    private String courseDesc;
    @Column(name = "paper_source")
    private String paperSource;
    @Column(name = "course_state")
    private String courseState;
    @Column(name = "marking_system")
    private String markingSystem;
    @Column(name = "study_score")
    private int studyScore;
    @Column(name = "pass_line")
    private int passLine;
    @Column(name = "total_score")
    private int totalScore;
    @Column(name = "test_time")
    private int testTime;
    private String curriculum;
    private String notes;
    @Column(name = "subjective_score")
    private int subjectiveScore;
    @Column(name = "objective_score")
    private int objectiveScore;

}
