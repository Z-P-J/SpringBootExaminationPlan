package com.exam.plan.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import javax.persistence.*;

@Data
@Getter
@Setter
@ToString
@Table(name = "major_plan_version")
public class MajorPlanVersion {
    @Id
    @Column(name = "plan_version_id")
    private String planVersionId;

    @Column(name = "plan_name")
    private String planName;

    private String state;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "approve_status")
    private String approveStatus;

    @Column(name = "leader_sign")
    private String leaderSign;

    @Column(name = "xuelichu_suggestion")
    private String xuelichuSuggestion;

    @Column(name = "major_id")
    private String majorId;

    @Column(name = "course_id")
    private String courseId;
}