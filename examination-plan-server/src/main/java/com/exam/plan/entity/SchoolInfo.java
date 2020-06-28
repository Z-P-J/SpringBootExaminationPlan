package com.exam.plan.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="school_info")
public class SchoolInfo {
    @Id
    @Column(name="school_id")
    private String school_id;
    @Column(name="school_name")
    private String school_name;
    @Column(name="school_address")
    private String school_address;
    @Column(name="school_postcode")
    private String school_postcode;
    @Column(name="school_phone")
    private String school_phone;
    @Column(name="school_web")
    private String school_web;
    @Column(name="notes")
    private String notes;
}
