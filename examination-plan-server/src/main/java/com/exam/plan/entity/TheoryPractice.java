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
@Table(name = "theory_practice")
public class TheoryPractice {

    @Id
    @Column(name = "theory_course_id")
    private String theoryCourseId;

    @Column(name = "practice_course_id")
    private String practiceCourseId;

}
