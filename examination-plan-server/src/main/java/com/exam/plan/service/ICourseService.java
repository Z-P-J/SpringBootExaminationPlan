package com.exam.plan.service;

import com.exam.plan.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICourseService extends Service<CourseInfo> {

//  /**
//   * 新建角色
//   *
//   * @param roleWithPermission 带权限列表的角色
//   */
//  void save(RoleWithPermission roleWithPermission);

  /**
   * 更新角色
   *
   * @param roleWithPermission 带权限列表的角色
   */
  void update(RoleWithPermission roleWithPermission);

//  /**
//   * 获取所有角色以及对应的权限
//   *
//   * @return 角色可控资源列表
//   */
//  List<RoleWithResource> listRoleWithPermission();

  List<CourseTextBookInfo> listTextbook();

  CourseTextBookInfo getTextbookById(Object id);

  void deleteTextbookById(Object id);

  void updateTextbook(CourseTextBookInfo info);

  void saveTextbook(CourseTextBookInfo info);


  List<CourseCharge> listCharge();

  CourseCharge getChargeById(Object id);

  void deleteCourseCharge(Object id);

  void updateCourseCharge(CourseCharge info);

  void saveCourseCharge(CourseCharge info);

  List<CourseNational> listNationalCourse();

  CourseNational getNationalCourseById(Object id);

  void deleteNationalCourse(Object id);

  void updateNationalCourse(CourseNational info);

  void saveNationalCourse(CourseNational info);

  void disableCourse(List<String> courseIdList);

  void enableCourse(List<String> courseIdList);

  void deleteCourse(List<String> courseIdList);

  List<CourseTextBookInfo> listTextbookByCourseId(String courseId);

  List<TheoryPractice> listTheoryPractice();

  TheoryPractice getTheoryPracticeById(Object id);

  void deleteTheoryPractice(Object id);

  void updateTheoryPractice(TheoryPractice info);

  void saveTheoryPractice(TheoryPractice info);

}
