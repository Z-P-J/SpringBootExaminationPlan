package com.exam.plan.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="exam_info")
public class ExamInfo {
    @Id
    @Column(name="exams_id")
    private String exams_id;
    @Column(name="time_id")
    private String time_id;
    @Column(name="exams_type")
    private String exams_type;
    @Column(name="jihua_exams_status")
    private String jihua_exams_status;
    @Column(name="kaowu_exams_status")
    private String kaowu_exams_status;
    @Column(name="kaoji_exams_status")
    private String kaoji_exams_status;
    @Column(name="exam_date")
    private String exam_date;
    @Column(name="start_time")
    private String start_time;
    @Column(name="end_time")
    private String end_time;
    @Column(name="default_charge")
    private Integer default_charge;
    @Column(name="photo_charge")
    private Integer photo_charge;
    @Column(name="notes")
    private String notes;
}
