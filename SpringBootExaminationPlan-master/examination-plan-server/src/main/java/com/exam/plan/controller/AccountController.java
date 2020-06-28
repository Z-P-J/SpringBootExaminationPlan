package com.exam.plan.controller;

import com.exam.plan.entity.Account;
import com.exam.plan.entity.AccountWithRole;
import com.exam.plan.entity.AccountWithRolePermission;
import com.exam.plan.entity.Result;
import com.exam.plan.service.IAccountService;
import com.exam.plan.utils.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Controller
@RestController
@RequestMapping(value = "/account")
//@CrossOrigin("http://127.0.0.1:9999/#/login")
public class AccountController {

    @Resource
    private IAccountService accountService;

    @PostMapping
    @ResponseBody
    public Result register(
            @RequestBody final AccountWithRole account, final BindingResult bindingResult) {
        // {"name":"123456", "password":"123456", "email": "123456@qq.com"}
        System.out.println("account=" + account);
        if (bindingResult.hasErrors()) {
            //noinspection ConstantConditions
            final String msg = bindingResult.getFieldError().getDefaultMessage();
            return ResultGenerator.genFailedResult(msg);
        } else {
            this.accountService.save(account);
            return this.getToken(account.getName());
        }
    }

    @PostMapping(value = "/token")
    @ResponseBody
    public Result login(@RequestBody final Account account) { // @RequestBody final Account account
        System.out.println("account=" + account);
//        Result<String> result = new Result<String>();
//        result.setMessage("login success");
//        result.setCode(HttpStatus.OK.value());
//        result.setData("11111111111111111111111111111111111111111111");
//        return result;

        if (account.getName() == null && account.getEmail() == null) {
            return ResultGenerator.genFailedResult("用户名或邮箱为空");
        }
        if (account.getPassword() == null) {
            return ResultGenerator.genFailedResult("密码为空");
        }
        // 用户名登录
        Account dbAccount = null;
        if (account.getName() != null) {
            dbAccount = this.accountService.getBy("name", account.getName());
            if (dbAccount == null) {
                return ResultGenerator.genFailedResult("用户名错误");
            }
        }
        // 邮箱登录
        if (account.getEmail() != null) {
            dbAccount = this.accountService.getBy("email", account.getEmail());
            if (dbAccount == null) {
                return ResultGenerator.genFailedResult("邮箱错误");
            }
            account.setName(dbAccount.getName());
        }
        // 验证密码
        //noinspection ConstantConditions
        if (!this.accountService.verifyPassword(account.getPassword(), dbAccount.getPassword())) {
            return ResultGenerator.genFailedResult("密码错误");
        }
        // 更新登录时间
        this.accountService.updateLoginTimeByName(account.getName());
        return this.getToken(account.getName());
    }

    @DeleteMapping(value = "/token")
    @ResponseBody
    public Result logout() {
        System.out.println("logout");
//        this.jwtUtil.invalidRedisToken(principal.getName());
        return ResultGenerator.genOkResult();
    }

    /**
     * 自己的资料
     */
    @GetMapping("/detail")
    @ResponseBody
    public Result<Account> detail(final Principal principal) { // final Principal principal
//        System.out.println("principal.name=" + principal.getName());
        AccountWithRolePermission account = new AccountWithRolePermission();
        account.setId(1L);
        account.setName("admin");
        account.setPassword("admin123");
        account.setEmail("123456@qq.com");
        account.setLoginTime(Timestamp.valueOf("2020-05-10 00:00:00"));
        account.setRegisterTime(Timestamp.valueOf("2010-05-10 00:00:00"));
        List<String> permissionCodeList = new ArrayList<String>();
        permissionCodeList.add("account:list");
        permissionCodeList.add("account:detail");
        permissionCodeList.add("account:add");
        permissionCodeList.add("account:update");
        permissionCodeList.add("account:delete");
        permissionCodeList.add("account:search");
        permissionCodeList.add("role:list");
        permissionCodeList.add("role:detail");
        permissionCodeList.add("role:add");
        permissionCodeList.add("role:update");
        permissionCodeList.add("role:delete");
        permissionCodeList.add("role:search");
        account.setPermissionCodeList(permissionCodeList);
        account.setRoleId(1L);
        account.setRoleName("超级管理员");
        Result<Account> result = new Result<Account>();
        result.setCode(HttpStatus.OK.value());
        result.setMessage("123456");
        result.setData(account);
        return result;
//        final Account dbAccount = this.accountService.findDetailByName(principal.getName());
//        return ResultGenerator.genOkResult(dbAccount);
    }

    /** 其他账户的资料 */
//    @PreAuthorize("hasAuthority('account:detail')")
    @GetMapping("/{id}")
    @ResponseBody
    public Result detail(@PathVariable final Long id) {
        final Account dbAccount = this.accountService.getById(id);
        return ResultGenerator.genOkResult(dbAccount);
    }

//    @PreAuthorize("hasAuthority('account:list')")
    @GetMapping
    @ResponseBody
    public Result list(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        System.out.println("AccountController page=" + page + " size=" + size);
        PageHelper.startPage(page, size);
        final List<AccountWithRole> list = this.accountService.listAllWithRole();
        final PageInfo<AccountWithRole> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

//    @PreAuthorize("hasAuthority('account:search')")
    @PostMapping("/search")
    @ResponseBody
    public Result search(@RequestBody final Map<String, Object> param) {
        PageHelper.startPage((Integer) param.get("page"), (Integer) param.get("size"));
        final List<AccountWithRole> list = this.accountService.findWithRoleBy(param);
        final PageInfo<AccountWithRole> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

//    @PreAuthorize("hasAuthority('account:delete')")
    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable final Long id, final Principal principal) {
        final Account dbAccount = this.accountService.getById(id);
        if (dbAccount == null) {
            return ResultGenerator.genFailedResult("用户不存在");
        }
        this.accountService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/password")
    @ResponseBody
    public Result validatePassword(@RequestBody final Account account) {
        final Account dbAccount = this.accountService.getById(account.getId());
        final boolean isValidate =
                this.accountService.verifyPassword(account.getPassword(), dbAccount.getPassword());
        return ResultGenerator.genOkResult(isValidate);
    }

    /** 更新其他账户的资料 */
//    @PreAuthorize("hasAuthority('account:update')")
    @PutMapping("/{id}")
    @ResponseBody
    public Result updateOthers(
            @PathVariable final Long id, @RequestBody final Account account, final Principal principal) {
        final Account dbAccount = this.accountService.getById(id);
        if (dbAccount == null) {
            return ResultGenerator.genFailedResult("用户不存在");
        }
        this.accountService.update(account);
        return ResultGenerator.genOkResult();
    }

    /** 更新自己的资料 */
    @PutMapping("/detail")
    @ResponseBody
    public Result updateMe(@RequestBody final Account account) {
        this.accountService.update(account);
        final Account dbAccount = this.accountService.getById(account.getId());
        return this.getToken(dbAccount.getName());
    }


    /** 获得 token */
    private Result getToken(final String name) {
//        final UserDetails accountDetails = this.userDetailsService.loadUserByUsername(name);
//        final String token = this.jwtUtil.sign(name, accountDetails.getAuthorities(), true);
//        return ResultGenerator.genOkResult(token);
        Result<String> result = new Result<String>();
        result.setMessage("login success");
        result.setCode(HttpStatus.OK.value());
        result.setData("11111111111111111111111111111111111111111111");
        return result;
    }


}
