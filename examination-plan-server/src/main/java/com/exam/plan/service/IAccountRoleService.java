package com.exam.plan.service;

import com.exam.plan.entity.AccountRole;

/**
 * @author Zoctan
 * @date 2018/06/09
 */
public interface IAccountRoleService extends Service<AccountRole> {
  /**
   * 更新用户角色
   *
   * @param accountRole 用户角色
   */
  void updateRoleIdByAccountId(AccountRole accountRole);
}
