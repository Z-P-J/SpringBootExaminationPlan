package com.exam.plan.service.impl;

import com.exam.plan.entity.AccountRole;
import com.exam.plan.mapper.AccountRoleMapper;
import com.exam.plan.service.IAccountRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Zoctan
 * @date 2018/06/09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountRoleServiceImpl extends AbstractService<AccountRole>
    implements IAccountRoleService {
  @Resource
  private AccountRoleMapper accountRoleMapper;

  @Override
  public void updateRoleIdByAccountId(final AccountRole accountRole) {
    this.accountRoleMapper.updateRoleIdByAccountId(accountRole);
  }
}
