package com.exam.plan.controller;

import com.exam.plan.entity.AccountRole;
import com.exam.plan.entity.Result;
import com.exam.plan.service.IAccountRoleService;
import com.exam.plan.utils.ResultGenerator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * @author Zoctan
 * @date 2018/06/09
 */
@RestController
@RequestMapping("/account/role")
public class AccountRoleController {
  @Resource private IAccountRoleService accountRoleService;

//  @PreAuthorize("hasAuthority('role:update')")
  @PutMapping
  @ResponseBody
  public Result updateAccountRole(
          @RequestBody final AccountRole accountRole, final Principal principal) {
    final AccountRole dbAccountRole =
        this.accountRoleService.getBy("accountId", accountRole.getAccountId());
    this.accountRoleService.updateRoleIdByAccountId(accountRole);
    return ResultGenerator.genOkResult();
  }
}
