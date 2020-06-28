package com.exam.plan.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="major_school")
public class MajorSchool {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(name="major_id")
    public String major_id;
    @Column(name="main_target_school")
    public String main_target_school;
    @Column(name="main_target_school_code")
    public String main_target_school_code;
}
