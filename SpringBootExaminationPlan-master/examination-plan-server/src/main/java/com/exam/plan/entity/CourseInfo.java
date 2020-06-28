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
@Table(name = "course_info")
public class CourseInfo {

    @Id
    @Column(name = "course_id")
    private String courseId;

    @Column(name = "national_course_id")
    private String nationalCourseId;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_specification")
    private String courseSpecification;

    @Column(name = "test_source")
    private String testSource;

    @Column(name = "course_status")
    private String courseStatus;

    @Column(name = "credit")
    private int credit;

    @Column(name = "qualified_score")
    private int qualifiedScore;

    @Column(name = "score_scale")
    private String scoreScale;

    @Column(name = "subjective_score")
    private int subjectiveScore;

    @Column(name = "objective_score")
    private int objectiveScore;

    @Column(name = "total_score")
    private int totalScore;

    @Column(name = "exam_duration")
    private int examDuration;

    @Column(name = "is_procedural")
    private int isProcedural;

    @Column(name = "course_property")
    private String courseProperty;

    private String notes;

}
