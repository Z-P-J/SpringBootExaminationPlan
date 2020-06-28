package com.exam.plan.entity;

public class AccountWithRole extends Account {
  /** 用户的角色Id */
  private Long roleId;

  /** 用户的角色名 */
  private String roleName;

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getRoleName() {
    return roleName;
  }
}
