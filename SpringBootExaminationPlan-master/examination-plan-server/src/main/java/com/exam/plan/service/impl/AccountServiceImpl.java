package com.exam.plan.service.impl;

import com.exam.plan.entity.Account;
import com.exam.plan.entity.AccountRole;
import com.exam.plan.entity.AccountWithRole;
import com.exam.plan.entity.AccountWithRolePermission;
import com.exam.plan.exception.ServiceException;
import com.exam.plan.mapper.AccountMapper;
import com.exam.plan.mapper.AccountRoleMapper;
import com.exam.plan.mapper.PermissionMapper;
import com.exam.plan.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl extends AbstractService<Account> implements IAccountService {


    @Resource private AccountMapper accountMapper;
    @Resource private AccountRoleMapper accountRoleMapper;
    @Resource private PermissionMapper permissionMapper;
//    @Resource private PasswordEncoder passwordEncoder;

    // 普通用户角色Id
    private final Long defaultRoleId = 2L;

    @Override
    public List<AccountWithRole> listAllWithRole() {
        return this.accountMapper.listAllWithRole();
    }

    /** 重写save方法，密码加密后再存 */
    @Override
    public void save(final AccountWithRole accountDto) {
        Account a = this.getBy("name", accountDto.getName());
        if (a != null) {
            throw new ServiceException("用户名已存在");
        } else {
            a = this.getBy("email", accountDto.getEmail());
            if (a != null) {
                throw new ServiceException("邮箱已存在");
            } else {
                // log.info("before password : {}", account.getPassword().trim());
//                accountDto.setPassword(this.passwordEncoder.encode(accountDto.getPassword().trim()));
                accountDto.setPassword(accountDto.getPassword().trim());
                // log.info("after password : {}", account.getPassword());
                final Account account = new Account();
                BeanUtils.copyProperties(accountDto, account);
                this.accountMapper.insertSelective(account);
                // log.info("Account<{}> id : {}", account.getName(), account.getId());
                System.out.println("acccount=" + account);
                this.saveRole(account.getId(), accountDto.getRoleId());
            }
        }
    }

    private void saveRole(final Long accountId, Long roleId) {
        // 如果没有指定角色Id，以默认普通用户roleId保存
        if (roleId == null) {
            roleId = this.defaultRoleId;
        }
        final AccountRole accountRole = new AccountRole();
        accountRole.setAccountId(accountId);
        accountRole.setRoleId(roleId);
        this.accountRoleMapper.insertSelective(accountRole);
    }

    /** 重写update方法 */
    @Override
    public void update(final Account account) {
        // 如果修改了密码
        if (account.getPassword() != null && account.getPassword().length() >= 6) {
            // 密码修改后需要加密
//            account.setPassword(this.passwordEncoder.encode(account.getPassword().trim()));
            account.setPassword(account.getPassword().trim());
        }
        this.accountMapper.updateByPrimaryKeySelective(account);
    }

    @Override
    public List<AccountWithRole> findWithRoleBy(final Map<String, Object> params) {
        return this.accountMapper.findWithRoleBy(params);
    }

    @Override
    public AccountWithRolePermission findDetailBy(final String column, final Object params) {
        final Map<String, Object> map = new HashMap<>(1);
        map.put(column, params);
        return this.accountMapper.findDetailBy(map);
    }

    @Override
    public AccountWithRolePermission findDetailByName(final String name) {
        final AccountWithRolePermission account = this.findDetailBy("name", name);
        if (account == null) {
            throw new ServiceException("没有找到该用户名");
        }
        if ("超级管理员".equals(account.getRoleName())) {
            // 超级管理员所有权限都有
            account.setPermissionCodeList(this.permissionMapper.listAllCode());
        }
        return account;
    }

    @Override
    public boolean verifyPassword(final String rawPassword, final String encodedPassword) {
        return rawPassword.equals(encodedPassword);
//        return this.passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public void updateLoginTimeByName(final String name) {
        this.accountMapper.updateLoginTimeByName(name);
    }

}
