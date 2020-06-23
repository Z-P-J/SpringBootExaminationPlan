package com.exam.plan.mapper;

import com.exam.plan.entity.CourseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseMapper extends MyMapper<CourseInfo> {
//  /**
//   * 获取所有角色以及对应的权限
//   *
//   * @return 角色可控资源列表
//   */
//  List<RoleWithResource> listRoles();

  /**
   * 按角色Id更新修改时间
   *
   * @param id 角色Id
   */
  void updateTimeById(@Param("id") Long id);
}
