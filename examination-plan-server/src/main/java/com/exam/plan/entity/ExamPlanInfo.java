package com.exam.plan.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.DateTimeException;
import java.util.Date;

@Data
@Getter
@Setter
@ToString
@Table(name = "exam_plan_info")
public class ExamPlanInfo {

    @Id
    @Column(name = "exam_id")
    private String examId;

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "major_id")
    private String majorId;

    @Column(name = "time_id")
    private String timeId;

    @Column(name = "exam_date")
    private Date examDate;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "exam_approve_status")
    private String examApproveStatus;

    @Column(name = "xuelichu_suggestion")
    private String xuelichuSuggestion;

    @Column(name = "leader_sign")
    private String leaderSign;



}
