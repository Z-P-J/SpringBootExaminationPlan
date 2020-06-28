package com.exam.plan.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Getter
@Setter
@ToString
@Table(name = "exam_plan_approve_info")
public class ExamPlanApproveInfo {

    @Id
    @Column(name = "exam_id")
    private String examId;

    @Column(name = "exam_approve_status")
    private String examApproveStatus;

    @Column(name = "xuelichu_suggestion")
    private String xuelichuSuggestion;

    @Column(name = "leader_sign")
    private String leaderSign;

}
