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
@Table(name = "exam_course_info")
public class ExamCourseInfo {

    @Id
    @Column(name = "exam_id")
    private String examId;

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "time_id")
    private String timeId;

    @Column(name = "exam_date")
    private Date examDate;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

}
