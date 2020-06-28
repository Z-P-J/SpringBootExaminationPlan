package com.exam.plan.controller;

import com.exam.plan.entity.*;
//import com.exam.plan.service.IExamPlanService;
import com.exam.plan.service.IExamCourseInfoService;
import com.exam.plan.service.IExamMajorService;
import com.exam.plan.service.IExamPlanApproveInfoService;
import com.exam.plan.service.IExamPlanService;
import com.exam.plan.utils.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/exam-plan")
public class ExamPlanController {

//    @Resource
//    private IExamPlanService examPlanService;
    @Resource
    private IExamMajorService examMajorService;
    @Resource
    private IExamCourseInfoService examCourseInfoService;
    @Resource
    private IExamPlanApproveInfoService examPlanApproveInfoService;

    @GetMapping()
    @ResponseBody
    public Result list(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<ExamMajorInfo> list = this.examMajorService.listAll();
        final PageInfo<ExamMajorInfo> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result deleteExam(@PathVariable final String id) {
        final ExamMajorInfo dbRole = this.examMajorService.getById(id);
        if (dbRole == null) {
            return ResultGenerator.genFailedResult("开考专业信息不存在");
        }
        this.examMajorService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping()
    @ResponseBody
    public Result updateExam(@RequestBody final ExamMajorInfo examplan) {
        examMajorService.update(examplan);
        return ResultGenerator.genOkResult();
    }

    @PostMapping()
    @ResponseBody
    public Result addExam(@RequestBody final ExamMajorInfo examplan) {
        System.out.println("addExam examplan=" + examplan);
        examMajorService.save(examplan);
        return ResultGenerator.genOkResult();
    }




    @GetMapping("/course")
    @ResponseBody
    public Result listPractice(@RequestParam(defaultValue = "0") final Integer page,
                               @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final PageInfo<ExamCourseInfo> pageInfo = new PageInfo<>(examCourseInfoService.listAll());
        return ResultGenerator.genOkResult(pageInfo);
    }

    @DeleteMapping("/course/{id}")
    @ResponseBody
    public Result deletePractice(@PathVariable final String id) {
        final ExamCourseInfo dbRole = this.examCourseInfoService.getById(id);
        if (dbRole == null) {
            return ResultGenerator.genFailedResult("开考课程信息不存在");
        }
        this.examCourseInfoService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/course")
    @ResponseBody
    public Result updatePractice(@RequestBody final ExamCourseInfo info) {
        examCourseInfoService.update(info);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/course")
    @ResponseBody
    public Result addPractice(@RequestBody final ExamCourseInfo info) {
        System.out.println("addPractice info=" + info);
        examCourseInfoService.save(info);
        return ResultGenerator.genOkResult();
    }



    @GetMapping("/approve")
    @ResponseBody
    public Result listApprove(@RequestParam(defaultValue = "0") final Integer page,
                               @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final PageInfo<ExamPlanApproveInfo> pageInfo = new PageInfo<>(examPlanApproveInfoService.listAll());
        return ResultGenerator.genOkResult(pageInfo);
    }

    @DeleteMapping("/approve/{id}")
    @ResponseBody
    public Result deleteApprove(@PathVariable final String id) {
        final ExamPlanApproveInfo dbRole = this.examPlanApproveInfoService.getById(id);
        if (dbRole == null) {
            return ResultGenerator.genFailedResult("开考课程信息不存在");
        }
        this.examPlanApproveInfoService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/approve")
    @ResponseBody
    public Result updateApprove(@RequestBody final ExamPlanApproveInfo info) {
        examPlanApproveInfoService.update(info);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/approve")
    @ResponseBody
    public Result addApprove(@RequestBody final ExamPlanApproveInfo info) {
        System.out.println("addPractice info=" + info);
        examPlanApproveInfoService.save(info);
        return ResultGenerator.genOkResult();
    }

}
