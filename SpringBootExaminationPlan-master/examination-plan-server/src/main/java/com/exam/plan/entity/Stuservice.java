package com.exam.plan.entity;

/**
 * XX
 * 2020/6/28
 * 更改了数据库中的部分数据名
 */

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * `main_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
 *   `school` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属院校',
 *   `student_service_org` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '助学组织名称',
 *   `student_permit_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '助学许可证编码\r\n',
 *   `issuing_unit_of_school_license` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '办学许可证发放单位',
 *   `issuing_date_of_school_license` date NULL DEFAULT NULL COMMENT '办学许可证发放日期',
 *   `types_of_student_services` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '助学主体类型',
 *   `name_of_legal_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '法人姓名',
 *   `position_of_legal_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '法人职务',
 *   `number_of_main_students` int(0) NULL DEFAULT NULL COMMENT '集中学员数',
 *   `number_of_part_time_teachers` int(0) NULL DEFAULT NULL COMMENT '兼职教学人数',
 *   `number_of_full_time_teachers` int(0) NULL DEFAULT NULL COMMENT '转职教学人数',
 *   `number_of_amateur_students` int(0) NULL DEFAULT NULL COMMENT '业余学员人数',
 *   `new_students_of_year` int(0) NULL DEFAULT NULL COMMENT '当年新招升学员数',
 *   `number_of_managers` int(0) NULL DEFAULT NULL COMMENT '管理人员总数',
 *   `number_of_managers_and_teachers` int(0) NULL DEFAULT NULL COMMENT '教学和管理人员数',
 *   `person_in_charge` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '负责人',
 *   `contact_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '联系地址',
 *   `postal_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮政编码',
 *   `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '联系电话',
 *   `fax` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '传真',
 *   `major_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '专业代码',
 *   `major_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '专业名称',
 *   `ways_of_student_services` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '助学方式',
 *   `means_of_student_services` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '助学手段',
 *   `number_of_students` int(0) NULL DEFAULT NULL COMMENT '学员数',
 */
@Data
public class Stuservice {
    @Id
    private String main_key;

    private String school;
    private String student_service_org;
    private String student_permit_code;
    private String issuing_unit_of_school_license;
    private Date issuing_date_of_school_license;
    private String types_of_student_services;
    private String name_of_legal_person;
    private String position_of_legal_person;
    private int number_of_main_students;
    private int number_of_part_time_teachers;
    private int number_of_full_time_teachers;
    private int number_of_amateur_students;
    private Date new_students_of_year;
    private int number_of_managers;
    private int number_of_managers_and_teachers;
    private String person_in_charge;
    private String contact_address;
    private String postal_code;
    private String telephone;
    private String fax;
    private String major_id;
    private String major_name;
    private String ways_of_student_services;
    private String means_of_student_services;
    private int number_of_students;


    public String getMain_key() {
        return main_key;
    }

    public void setMain_key(String main_key) {
        this.main_key = main_key;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getStudent_service_org() {
        return student_service_org;
    }

    public void setStudent_service_org(String student_service_org) {
        this.student_service_org = student_service_org;
    }

    public String getStudent_permit_code() {
        return student_permit_code;
    }

    public void setStudent_permit_code(String student_permit_code) {
        this.student_permit_code = student_permit_code;
    }

    public String getIssuing_unit_of_school_license() {
        return issuing_unit_of_school_license;
    }

    public void setIssuing_unit_of_school_license(String issuing_unit_of_school_license) {
        this.issuing_unit_of_school_license = issuing_unit_of_school_license;
    }

    public Date getIssuing_date_of_school_license() {
        return issuing_date_of_school_license;
    }

    public void setIssuing_date_of_school_license(Date issuing_date_of_school_license) {
        this.issuing_date_of_school_license = issuing_date_of_school_license;
    }

    public String getTypes_of_student_services() {
        return types_of_student_services;
    }

    public void setTypes_of_student_services(String types_of_student_services) {
        this.types_of_student_services = types_of_student_services;
    }

    public String getName_of_legal_person() {
        return name_of_legal_person;
    }

    public void setName_of_legal_person(String name_of_legal_person) {
        this.name_of_legal_person = name_of_legal_person;
    }

    public String getPosition_of_legal_person() {
        return position_of_legal_person;
    }

    public void setPosition_of_legal_person(String position_of_legal_person) {
        this.position_of_legal_person = position_of_legal_person;
    }

    public int getNumber_of_main_students() {
        return number_of_main_students;
    }

    public void setNumber_of_main_students(int number_of_main_students) {
        this.number_of_main_students = number_of_main_students;
    }

    public int getNumber_of_part_time_teachers() {
        return number_of_part_time_teachers;
    }

    public void setNumber_of_part_time_teachers(int number_of_part_time_teachers) {
        this.number_of_part_time_teachers = number_of_part_time_teachers;
    }

    public int getNumber_of_full_time_teachers() {
        return number_of_full_time_teachers;
    }

    public void setNumber_of_full_time_teachers(int number_of_full_time_teachers) {
        this.number_of_full_time_teachers = number_of_full_time_teachers;
    }

    public int getNumber_of_amateur_students() {
        return number_of_amateur_students;
    }

    public void setNumber_of_amateur_students(int number_of_amateur_students) {
        this.number_of_amateur_students = number_of_amateur_students;
    }

    public Date getNew_students_of_year() {
        return new_students_of_year;
    }

    public void setNew_students_of_year(Date new_students_of_year) {
        this.new_students_of_year = new_students_of_year;
    }

    public int getNumber_of_managers() {
        return number_of_managers;
    }

    public void setNumber_of_managers(int number_of_managers) {
        this.number_of_managers = number_of_managers;
    }

    public int getNumber_of_managers_and_teachers() {
        return number_of_managers_and_teachers;
    }

    public void setNumber_of_managers_and_teachers(int number_of_managers_and_teachers) {
        this.number_of_managers_and_teachers = number_of_managers_and_teachers;
    }

    public String getPerson_in_charge() {
        return person_in_charge;
    }

    public void setPerson_in_charge(String person_in_charge) {
        this.person_in_charge = person_in_charge;
    }

    public String getContact_address() {
        return contact_address;
    }

    public void setContact_address(String contact_address) {
        this.contact_address = contact_address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMajor_id() {
        return major_id;
    }

    public void setMajor_id(String major_id) {
        this.major_id = major_id;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public String getWays_of_student_services() {
        return ways_of_student_services;
    }

    public void setWays_of_student_services(String ways_of_student_services) {
        this.ways_of_student_services = ways_of_student_services;
    }

    public String getMeans_of_student_services() {
        return means_of_student_services;
    }

    public void setMeans_of_student_services(String means_of_student_services) {
        this.means_of_student_services = means_of_student_services;
    }

    public int getNumber_of_students() {
        return number_of_students;
    }

    public void setNumber_of_students(int number_of_students) {
        this.number_of_students = number_of_students;
    }




    @Override
    public String toString() {
        return "Stuservice{" +
                "school='" + school + '\'' +
                ", student_service_org='" + student_service_org + '\'' +
                ", student_permit_code='" + student_permit_code + '\'' +
                ", issuing_unit_of_school_license='" + issuing_unit_of_school_license + '\'' +
                ", issuing_date_of_school_license=" + issuing_date_of_school_license +
                ", types_of_student_services='" + types_of_student_services + '\'' +
                ", name_of_legal_person='" + name_of_legal_person + '\'' +
                ", position_of_legal_person='" + position_of_legal_person + '\'' +
                ", number_of_main_students=" + number_of_main_students +
                ", number_of_part_time_teachers=" + number_of_part_time_teachers +
                ", number_of_full_time_teachers=" + number_of_full_time_teachers +
                ", number_of_amateur_students=" + number_of_amateur_students +
                ", new_students_of_year=" + new_students_of_year +
                ", number_of_managers=" + number_of_managers +
                ", number_of_managers_and_teachers=" + number_of_managers_and_teachers +
                ", person_in_charge='" + person_in_charge + '\'' +
                ", contact_address='" + contact_address + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", telephone='" + telephone + '\'' +
                ", fax='" + fax + '\'' +
                ", major_id='" + major_id + '\'' +
                ", major_name='" + major_name + '\'' +
                ", ways_of_student_services='" + ways_of_student_services + '\'' +
                ", means_of_student_services='" + means_of_student_services + '\'' +
                ", number_of_students='" + number_of_students + '\'' +
                '}';
    }
}
