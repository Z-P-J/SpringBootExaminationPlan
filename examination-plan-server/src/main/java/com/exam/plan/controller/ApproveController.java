package com.exam.plan.controller;

import com.exam.plan.entity.*;
import com.exam.plan.service.IApproveService;
import com.exam.plan.service.ICourseService;
import com.exam.plan.utils.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

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
        System.out.println("approve list reached！");
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


    @PostMapping("/search")
    @ResponseBody
    public Result Search(@RequestBody final Map<String, Object> param) {
        System.out.println("search param=" + param);
        PageHelper.startPage((Integer) param.get("page"), (Integer) param.get("size"));

        Condition condition=new Condition(Approve.class);
        condition.createCriteria().andCondition( param.get("fieldSelect")+" like '%" +param.get("fieldVal")+"%'");

        final List<Approve> list = this.approveService.listByCondition(condition);
        final PageInfo<Approve> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PostMapping
    @ResponseBody
    public Result add(@RequestBody final Approve approve, final BindingResult bindingResult) {
        System.out.println("approve=" + approve);
        this.approveService.save(approve);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Result Update(
            @PathVariable final String id, @RequestBody final Approve approve, final Principal principal) {
        final Approve db = this.approveService.getById(id);
        System.out.println("updatecheck id=" + id);
        if (db == null) {
            return ResultGenerator.genFailedResult("选择的数据不存在，请检查");
        }
        this.approveService.update(approve);
        return ResultGenerator.genOkResult();
    }




}
