package com.exam.plan.mapper;

import com.exam.plan.entity.Permission;
import com.exam.plan.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper extends MyMapper<Permission> {
  /**
   * 找到所有权限可控资源
   *
   * @return 资源列表
   */
  List<Resource> listResourceWithHandle();

  /**
   * 找到所有权限可控资源
   *
   * @param roleId 角色id
   * @return 资源列表
   */
  List<Resource> listRoleWithResourceByRoleId(@Param("roleId") Long roleId);

  /**
   * 获取所有权限代码
   *
   * @return 代码列表
   */
  @Select("SELECT p.code FROM `permission` p")
  List<String> listAllCode();
}
