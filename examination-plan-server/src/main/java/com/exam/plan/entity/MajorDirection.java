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
@Table(name = "major_direction")
public class MajorDirection {

    @Id
    @Column(name = "major_id")
    private String majorId;

    @Column(name = "direction_id")
    private String directionId;

    @Column(name = "direction_name")
    private String directionName;

    @Column(name = "total_credit")
    private int totalCredit;

}
