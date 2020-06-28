package com.exam.plan.mapper;

import com.exam.plan.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolePermissionMapper extends MyMapper<RolePermission> {
  /**
   * 保存角色以及对应的权限ID
   *
   * @param roleId 角色ID
   * @param permissionIdList 权限ID列表
   */
  void saveRolePermission(
          @Param("roleId") Long roleId, @Param("permissionIdList") List<Integer> permissionIdList);
}
