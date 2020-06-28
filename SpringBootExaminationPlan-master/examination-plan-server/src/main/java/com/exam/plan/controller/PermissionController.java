package com.exam.plan.controller;

import com.exam.plan.entity.Result;
import com.exam.plan.service.IPermissionService;
import com.exam.plan.utils.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//@RestController
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Resource
    private IPermissionService permissionService;

//    @PreAuthorize("hasAuthority('role:list')")
    @GetMapping
    @ResponseBody
    public Result listResourcePermission(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<com.exam.plan.entity.Resource> list =
                this.permissionService.listResourceWithHandle();
        final PageInfo<com.exam.plan.entity.Resource> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
