package com.exam.plan.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Data
@Getter
@Setter
@ToString
@Table(name = "nation_major")
public class MajorNational {

    @Id
    @Column(name="national_major_code")
    private String national_major_code;
    @Column(name="major_name")
    private String major_name;
    @Column(name="major_abbr")
    private String major_abbr;
    @Column(name="education_level")
    private String education_level;
    @Column(name="credit")
    private String credit;
}
