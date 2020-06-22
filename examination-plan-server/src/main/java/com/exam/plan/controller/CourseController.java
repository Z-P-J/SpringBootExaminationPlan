package com.exam.plan.controller;

import com.exam.plan.entity.Course;
import com.exam.plan.entity.Result;
import com.exam.plan.entity.Role;
import com.exam.plan.entity.RoleWithResource;
import com.exam.plan.service.ICourseService;
import com.exam.plan.service.IRoleService;
import com.exam.plan.utils.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/course")
public class CourseController {

    @Resource
    private ICourseService courseService;

    @GetMapping("/permission")
    @ResponseBody
    public Result listWithPermission(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<Course> list = this.courseService.listAll();
        final PageInfo<Course> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @GetMapping()
    @ResponseBody
    public Result list(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<Course> list = this.courseService.listAll();
        final PageInfo<Course> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable final Long id, final Principal principal) {
        final Course dbRole = this.courseService.getById(id);
        if (dbRole == null) {
            return ResultGenerator.genFailedResult("角色不存在");
        }
        this.courseService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

}
