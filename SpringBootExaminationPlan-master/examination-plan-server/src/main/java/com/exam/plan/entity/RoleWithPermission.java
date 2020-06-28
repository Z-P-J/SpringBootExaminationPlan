package com.exam.plan.entity;

import java.util.List;

public class RoleWithPermission extends Role {
  /** 角色对应的权限Id列表 */
  private List<Integer> permissionIdList;

  public List<Integer> getPermissionIdList() {
    return this.permissionIdList;
  }

  public void setPermissionIdList(final List<Integer> permissionIdList) {
    this.permissionIdList = permissionIdList;
  }
}
