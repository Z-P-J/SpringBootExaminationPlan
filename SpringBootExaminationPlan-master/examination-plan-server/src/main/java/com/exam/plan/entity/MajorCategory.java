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
@Table(name = "major_categories")
public class MajorCategory {

    @Id
    @Column(name="category_code")
    private String category_code;
    @Column(name="major_category_level")
    private String major_category_level;
    @Column(name="major_category_name")
    private String major_category_name;

}
