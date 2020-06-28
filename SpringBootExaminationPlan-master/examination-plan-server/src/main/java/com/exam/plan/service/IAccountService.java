package com.exam.plan.service;

import com.exam.plan.entity.Account;
import com.exam.plan.entity.AccountWithRole;
import com.exam.plan.entity.AccountWithRolePermission;

import java.util.List;
import java.util.Map;

public interface IAccountService extends Service<Account> {
    /**
     * 保存用户
     *
     * @param accountDto 用户
     */
    void save(AccountWithRole accountDto);

    /**
     * 获取所有用户以及对应角色
     *
     * @return 用户列表
     */
    List<AccountWithRole> listAllWithRole();

    /**
     * 按条件查询用户
     *
     * @param params 参数
     * @return 用户列表
     */
    List<AccountWithRole> findWithRoleBy(final Map<String, Object> params);

    /**
     * 按条件查询用户信息
     *
     * @param column 列名
     * @param params 参数
     * @return 用户
     */
    AccountWithRolePermission findDetailBy(String column, Object params);

    /**
     * 按用户名查询用户信息
     *
     * @param name 用户名
     * @return 用户
     */
    AccountWithRolePermission findDetailByName(String name);

    /**
     * 按用户名更新最后一次登录时间
     *
     * @param name 用户名
     */
    void updateLoginTimeByName(String name);

    /**
     * 验证用户密码
     *
     * @param rawPassword 原密码
     * @param encodedPassword 加密后的密码
     * @return boolean
     */
    boolean verifyPassword(String rawPassword, String encodedPassword);

}
