package com.exam.plan.controller;

import com.exam.plan.entity.*;
import com.exam.plan.mapper.ExamInfoMapper;
import com.exam.plan.mapper.MajorMapper;
import com.exam.plan.service.*;
import com.exam.plan.service.impl.ExamInfoServiceImpl;
import com.exam.plan.utils.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/exam-set")
public class ExamInfoController {

    //自动装配需要 @Resource!!
    //专业基本信息
    @Resource
    private IExamInfoService examInfoService;
    /*··········································考次信息···································*/
    @GetMapping()
    @ResponseBody
    public Result List(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<ExamInfo> list = this.examInfoService.listAll();
        final PageInfo<ExamInfo> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PostMapping("/search")
    @ResponseBody
    public Result Search(@RequestBody final Map<String, Object> param) {
        System.out.println("search param=" + param);
        PageHelper.startPage((Integer) param.get("page"), (Integer) param.get("size"));

        Condition condition=new Condition(ExamInfo.class);
        condition.createCriteria().andCondition( param.get("fieldSelect")+" like '%" +param.get("fieldVal")+"%'");

        final List<ExamInfo> list = this.examInfoService.listByCondition(condition);
        final PageInfo<ExamInfo> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PostMapping()
    @ResponseBody
    public Result Add(@RequestBody final ExamInfo examInfo) {
        System.out.println("addExamInfo examInfo=" + examInfo);
        examInfoService.save(examInfo);
        return ResultGenerator.genOkResult();
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public Result categoryDelete(@PathVariable final String id, final Principal principal) {
        System.out.println("delExamInfo id=" + id);
        final ExamInfo examInfo = this.examInfoService.getById(id);
        if (examInfo == null) {
            return ResultGenerator.genFailedResult("数据不存在");
        }
        this.examInfoService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("{id}")
    @ResponseBody
    public Result categoryUpdate(
            @PathVariable final String id, @RequestBody final ExamInfo examInfo, final Principal principal) {
        final ExamInfo db = this.examInfoService.getById(id);
        if (db == null) {
            return ResultGenerator.genFailedResult("不存在");
        }
        this.examInfoService.update(examInfo);
        return ResultGenerator.genOkResult();
    }
}


