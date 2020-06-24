package com.exam.plan.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="major_school")
public class MajorSchool {
    @Id
    @Column(name="id")
    private Integer id;
    @Column(name="major_id")
    private String major_id;
    @Column(name="main_target_school")
    private String main_target_school;
    @Column(name="main_target_school_code")
    private String main_target_school_code;
}
