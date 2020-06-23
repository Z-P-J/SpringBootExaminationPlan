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
@Table(name = "course_textbook_info")
public class CourseTextBookInfo {

    @Id
    @Column(name = "course_id")
    private String courseId;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "textbook_code")
    private String textbookCode;

    @Column(name = "textbook_name")
    private String textbookName;

    @Column(name = "textbook_ISBN")
    private String textbookISBN;

    @Column(name = "textbook_type")
    private String textbookType;

    @Column(name = "chief_editor")
    private String chiefEditor;

    @Column(name = "author")
    private String author;

    @Column(name = "publishing_house")
    private String publishingHouse;

    @Column(name = "version")
    private String version;

    @Column(name = "publication_date")
    private Date publicationDate;

    @Column(name = "price")
    private int price;

    @Column(name = "textbook_select_type")
    private String textbookSelectType;

    @Column(name = "syllabus")
    private String syllabus;

    @Column(name = "textbook_use_status")
    private String textbookUseStatus;

    @Column(name = "textbook_start_exam_id")
    private String textbookStartExamId;

    @Column(name = "textbook_end_exam_id")
    private String textbookEndExamId;

}
