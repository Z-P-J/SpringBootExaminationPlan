package com.exam.plan.controller;

import com.exam.plan.entity.Result;
import com.exam.plan.entity.Role;
import com.exam.plan.entity.RoleWithPermission;
import com.exam.plan.entity.RoleWithResource;
import com.exam.plan.service.IRoleService;
import com.exam.plan.utils.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
  @Resource private IRoleService roleService;

//  @PreAuthorize("hasAuthority('role:add')")
  @PostMapping
  @ResponseBody
  public Result add(@RequestBody final RoleWithPermission role, final Principal principal) {
    this.roleService.save(role);
    return ResultGenerator.genOkResult();
  }

//  @PreAuthorize("hasAuthority('role:delete')")
  @DeleteMapping("/{id}")
  @ResponseBody
  public Result delete(@PathVariable final Long id, final Principal principal) {
    final Role dbRole = this.roleService.getById(id);
    if (dbRole == null) {
      return ResultGenerator.genFailedResult("角色不存在");
    }
    this.roleService.deleteById(id);
    return ResultGenerator.genOkResult();
  }

//  @PreAuthorize("hasAuthority('role:update')")
  @PutMapping
  @ResponseBody
  public Result update(@RequestBody final RoleWithPermission role, final Principal principal) {
    final Role dbRole = this.roleService.getById(role.getId());
    if (dbRole == null) {
      return ResultGenerator.genFailedResult("角色不存在");
    }
    this.roleService.update(role);
    return ResultGenerator.genOkResult();
  }

//  @PreAuthorize("hasAuthority('role:list')")
  @GetMapping("/permission")
  @ResponseBody
  public Result listWithPermission(
      @RequestParam(defaultValue = "0") final Integer page,
      @RequestParam(defaultValue = "0") final Integer size) {
    PageHelper.startPage(page, size);
    final List<RoleWithResource> list = this.roleService.listRoleWithPermission();
    final PageInfo<RoleWithResource> pageInfo = new PageInfo<>(list);
    return ResultGenerator.genOkResult(pageInfo);
  }

  @GetMapping
  @ResponseBody
  public Result list(
      @RequestParam(defaultValue = "0") final Integer page,
      @RequestParam(defaultValue = "0") final Integer size) {
    PageHelper.startPage(page, size);
    final List<Role> list = this.roleService.listAll();
    final PageInfo<Role> pageInfo = new PageInfo<>(list);
    return ResultGenerator.genOkResult(pageInfo);
  }
}
