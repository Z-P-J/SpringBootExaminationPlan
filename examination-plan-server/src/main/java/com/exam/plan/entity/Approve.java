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
@Table(name = "approve")
public class Approve {

    @Id
    @Column(name = "approve_id")
    private String approve_id;

    @Column(name = "approve_name")
    private String approve_name;

    @Column(name = "school_id")
    private String school_id;

    @Column(name = "school_name")
    private String school_name;

    @Column(name = "apply_time")
    private String apply_time;

    @Column(name = "approve_comment")
    private String approve_comment;

    @Column(name = "approve_time")
    private String approve_time;

    @Column(name = "approver")
    private String approver;

    @Column(name = "approve_status")
    private String approve_status;

    @Column(name = "approve_num")
    private String approve_num;


    public String getApprove_id() {
        return approve_id;
    }

    public void setApprove_id(String approve_id) {
        this.approve_id = approve_id;
    }

    public String getApprove_name() {
        return approve_name;
    }

    public void setApprove_name(String approve_name) {
        this.approve_name = approve_name;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getApply_time() {
        return apply_time;
    }

    public void setApply_time(String apply_time) {
        this.apply_time = apply_time;
    }

    public String getApprove_comment() {
        return approve_comment;
    }

    public void setApprove_comment(String approve_comment) {
        this.approve_comment = approve_comment;
    }

    public String getApprove_time() {
        return approve_time;
    }

    public void setApprove_time(String approve_time) {
        this.approve_time = approve_time;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getApprove_status() {
        return approve_status;
    }

    public void setApprove_status(String approve_status) {
        this.approve_status = approve_status;
    }

    public String getApprove_num() {
        return approve_num;
    }

    public void setApprove_num(String approve_num) {
        this.approve_num = approve_num;
    }


    @Override
    public String toString() {
        return "Approve{" +
                "approve_id=" + approve_id +
                ", approve_name='" + approve_name + '\'' +
                ", school_id='" + school_id + '\'' +
                ", school_name='" + school_name + '\'' +
                ", apply_time=" + apply_time + '\'' +
                ", approver=" + approver + '\'' +
                ", approve_status=" + approve_status + '\'' +
                ", approve_num=" + approve_num + '\'' +
                ", approve_time=" + approve_time + '\'' +
                ", approve_comment=" + approve_comment +
                '}';
    }
}
