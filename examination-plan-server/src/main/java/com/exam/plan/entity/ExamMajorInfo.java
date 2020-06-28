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
@Table(name = "exam_major_info")
public class ExamMajorInfo {

    @Id
    @Column(name = "exam_id")
    private String examId;

    @Column(name = "major_id")
    private String majorId;

}
