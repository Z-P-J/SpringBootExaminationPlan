package com.exam.plan.controller;

import com.exam.plan.entity.AccountWithRole;
import com.exam.plan.entity.Approve;
import com.exam.plan.entity.CourseInfo;
import com.exam.plan.entity.Result;
import com.exam.plan.service.IApproveService;
import com.exam.plan.service.ICourseService;
import com.exam.plan.utils.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/approve")
public class ApproveController {

    @Resource
    private IApproveService approveService;

    //list表
    @GetMapping()
    @ResponseBody
    public Result list(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        System.out.println("approve list reached");
        PageHelper.startPage(page, size);
        final List<Approve> list = this.approveService.listAll();
        final PageInfo<Approve> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    //删除
    @DeleteMapping("/{id}")
    @ResponseBody
    public Result deleteApprove(@PathVariable final Long id, final Principal principal) {
        final Approve dbRole = this.approveService.getById(id);
        System.out.println("approve delete reached");
        if (dbRole == null) {
            return ResultGenerator.genFailedResult("选择的申请表不存在！");
        }
        this.approveService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PostMapping
    @ResponseBody
    public Result add(@RequestBody final Approve approve, final BindingResult bindingResult) {
        System.out.println("approve=" + approve);
        this.approveService.save(approve);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/addCheck")
    @ResponseBody
    public Result addCheck(@RequestBody final Approve approve, final BindingResult bindingResult) {
        System.out.println("approve=" + approve);
        this.approveService.save(approve);
        return ResultGenerator.genOkResult();
    }




}
