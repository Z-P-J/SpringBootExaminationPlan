package com.exam.plan.mapper;

import com.exam.plan.entity.AccountRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountRoleMapper extends MyMapper<AccountRole> {

  /**
   * 更新用户角色
   *
   * @param accountRole 用户角色
   */
  @Update(
      "UPDATE account_role SET role_id = #{accountRole.roleId} WHERE account_id = #{accountRole.accountId}")
  void updateRoleIdByAccountId(@Param("accountRole") AccountRole accountRole);
}
