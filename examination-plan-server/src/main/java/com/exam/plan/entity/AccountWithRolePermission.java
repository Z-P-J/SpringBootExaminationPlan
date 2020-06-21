package com.exam.plan.entity;

import java.util.List;


public class AccountWithRolePermission extends AccountWithRole {
  /** 用户的角色对应的权限code */
  private List<String> permissionCodeList;


  public void setPermissionCodeList(List<String> permissionCodeList) {
    this.permissionCodeList = permissionCodeList;
  }

  public List<String> getPermissionCodeList() {
    return permissionCodeList;
  }
}
