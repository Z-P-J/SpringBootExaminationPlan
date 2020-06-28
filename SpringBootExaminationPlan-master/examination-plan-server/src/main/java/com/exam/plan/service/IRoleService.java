package com.exam.plan.service;

import com.exam.plan.entity.Role;
import com.exam.plan.entity.RoleWithPermission;
import com.exam.plan.entity.RoleWithResource;

import java.util.List;

/**
 * @author Zoctan
 * @date 2018/06/09
 */
public interface IRoleService extends Service<Role> {

  /**
   * 新建角色
   *
   * @param roleWithPermission 带权限列表的角色
   */
  void save(RoleWithPermission roleWithPermission);

  /**
   * 更新角色
   *
   * @param roleWithPermission 带权限列表的角色
   */
  void update(RoleWithPermission roleWithPermission);

  /**
   * 获取所有角色以及对应的权限
   *
   * @return 角色可控资源列表
   */
  List<RoleWithResource> listRoleWithPermission();
}
