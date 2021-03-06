package com.exam.plan.controller;

import com.exam.plan.entity.*;
import com.exam.plan.service.IMajorplanService;
import com.exam.plan.service.IRoleService;
import com.exam.plan.utils.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/majorplan")
public class MajorplanController {

    @Resource
    private IMajorplanService majorplanService;
    @GetMapping("/permission")
    @ResponseBody
    public Result listWithPermission(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<MajorPlanVersion> list = this.majorplanService.listAll();
        final PageInfo<MajorPlanVersion> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @GetMapping()
    @ResponseBody
    public Result list(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size,
            @RequestParam(defaultValue = "all") final String filter) {
        System.out.println("filter=" + filter);
        PageHelper.startPage(page, size);
        final List<MajorPlanVersion> list;
//        list = this.majorplanService.listAll();
        if ("all".equalsIgnoreCase(filter)) {
            list = this.majorplanService.listAll();
        } else if ("enable".equalsIgnoreCase(filter)) {
            final Condition condition = new Condition(MajorPlanVersion.class);
            condition.createCriteria().andCondition("state = ", "已启用");
            list = this.majorplanService.listByCondition(condition);
        } else if ("newable".equalsIgnoreCase(filter)) {
            final Condition condition = new Condition(MajorPlanVersion.class);
            condition.createCriteria().andCondition("state = ", "新建");
            list = this.majorplanService.listByCondition(condition);
        } else if ("compable".equalsIgnoreCase(filter)) {
            final Condition condition = new Condition(MajorPlanVersion.class);
            condition.createCriteria().andCondition("state = ", "已编制");
            list = this.majorplanService.listByCondition(condition);
        } else if ("approvable".equalsIgnoreCase(filter)) {
            final Condition condition = new Condition(MajorPlanVersion.class);
            condition.createCriteria().andCondition("state = ", "已审批");
            list = this.majorplanService.listByCondition(condition);
        } else {
            list = new ArrayList<>();
        }
        final PageInfo<MajorPlanVersion> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result deleteMajorplan(@PathVariable final String id, final Principal principal) {
        final MajorPlanVersion dbRole = this.majorplanService.getById(id);
        if (dbRole == null) {
            return ResultGenerator.genFailedResult("计划不存在");
        }
        this.majorplanService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Result deleteMajorplan(@RequestBody final List<String> planVersionIdList) {
        System.out.println("deleteMajorplan planVersionIdList=" + planVersionIdList);
        majorplanService.deleteMajorplan(planVersionIdList);
        return list(0, 9, "all");
    }

    @PutMapping()
    @ResponseBody
    public Result updateCourse(@RequestBody final MajorPlanVersion majorPlan) {
        majorplanService.update(majorPlan);
        return ResultGenerator.genOkResult();
    }

    @PostMapping()
    @ResponseBody
    public Result addMajorplan(@RequestBody final MajorPlanVersion majorPlan) {
        System.out.println("addMajorplan =" + majorPlan);
        majorplanService.save(majorPlan);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/disable")
    @ResponseBody
    public Result disableMajorplan(@RequestBody final List<String> planVersionIdList) {
        System.out.println("disableMajorplan planVersionIdList=" + planVersionIdList);
        majorplanService.disableMajorplan(planVersionIdList);
        return list(0, 9, "all");
    }

//    @PostMapping(name = "/disable1")
//    @ResponseBody
//    public Result disableMajorplan() { // @RequestBody final String[] courseIds
////        System.out.println("disableMajorplan planVersionIdList=" + Arrays.toString(courseIds));
////        majorplanService.disableMajorplan(planVersionIdList);
////        return list(0, 9, "all");
//        return ResultGenerator.genOkResult();
//    }

    @PostMapping("/enable")
    @ResponseBody
    public Result enableMajorplan(@RequestBody final List<String> planVersionIdList) {
        System.out.println("enableMajorplan planVersionIdList=" + planVersionIdList);
        majorplanService.enableMajorplan(planVersionIdList);
        return list(0, 9, "all");
    }

    //--------------------------------------课程教材管理-----------------------------------

    @GetMapping("/textbook")
    @ResponseBody
    public Result listCourseTextbook(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final PageInfo<CourseTextBookInfo> pageInfo = new PageInfo<>(majorplanService.listTextbook());
        return ResultGenerator.genOkResult(pageInfo);
    }

    @DeleteMapping("/textbook/{id}")
    @ResponseBody
    public Result deleteTextbook(@PathVariable final Long id, final Principal principal) {
        final CourseTextBookInfo dbRole = this.majorplanService.getTextbookById(id);
        if (dbRole == null) {
            return ResultGenerator.genFailedResult("教材不存在");
        }
        this.majorplanService.deleteTextbookById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/textbook")
    @ResponseBody
    public Result updateTextbook(@RequestBody final CourseTextBookInfo info) {
        majorplanService.updateTextbook(info);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/textbook")
    @ResponseBody
    public Result addTextbook(@RequestBody final CourseTextBookInfo info) {
        System.out.println("addTextbook info=" + info);
        majorplanService.saveTextbook(info);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/charge")
    @ResponseBody
    public Result listCourseCharge(@RequestParam(defaultValue = "0") final Integer page,
                                   @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final PageInfo<CourseCharge> pageInfo = new PageInfo<>(majorplanService.listCharge());
        return ResultGenerator.genOkResult(pageInfo);
    }

    @GetMapping("/charge/{id}")
    @ResponseBody
    public Result getCourseCharge(@PathVariable final String id) {
        return ResultGenerator.genOkResult(majorplanService.getChargeById(id));
    }

    @GetMapping("/national")
    @ResponseBody
    public Result listNationalCourse(@RequestParam(defaultValue = "0") final Integer page,
                                     @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final PageInfo<CourseNational> pageInfo = new PageInfo<>(majorplanService.listNationalCourse());
        return ResultGenerator.genOkResult(pageInfo);
    }

    @GetMapping("/national/{id}")
    @ResponseBody
    public Result getNationalCourse(@PathVariable final String id) {
        return ResultGenerator.genOkResult(majorplanService.getNationalCourseById(id));
    }

    @DeleteMapping("/national/{id}")
    @ResponseBody
    public Result deleteNationalCourse(@PathVariable final String id) {
        final CourseNational dbRole = this.majorplanService.getNationalCourseById(id);
        if (dbRole == null) {
            return ResultGenerator.genFailedResult("国家课程不存在");
        }
        this.majorplanService.deleteNationalCourse(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/national")
    @ResponseBody
    public Result updateNationalCourse(@RequestBody final CourseNational info) {
        majorplanService.updateNationalCourse(info);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/national")
    @ResponseBody
    public Result addNationalCourse(@RequestBody final CourseNational info) {
        System.out.println("addTextbook info=" + info);
        majorplanService.saveNationalCourse(info);
        return ResultGenerator.genOkResult();
    }

}
