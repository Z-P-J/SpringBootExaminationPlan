package com.exam.plan.controller;

import com.exam.plan.entity.*;
import com.exam.plan.service.ICourseService;
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
        final List<CourseInfo> list = this.courseService.listAll();
        final PageInfo<CourseInfo> pageInfo = new PageInfo<>(list);
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
        final List<CourseInfo> list;
        if ("all".equalsIgnoreCase(filter)) {
            list = this.courseService.listAll();
        } else if ("enable".equalsIgnoreCase(filter)) {
            final Condition condition = new Condition(CourseInfo.class);
            condition.createCriteria().andCondition("course_status = ", "正常");
            list = this.courseService.listByCondition(condition);
        } else if ("disable".equalsIgnoreCase(filter)) {
            final Condition condition = new Condition(CourseInfo.class);
            condition.createCriteria().andCondition("course_status = ", "注销");
            list = this.courseService.listByCondition(condition);
        } else {
            list = new ArrayList<>();
        }
        final PageInfo<CourseInfo> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result deleteCourse(@PathVariable final Long id, final Principal principal) {
        final CourseInfo dbRole = this.courseService.getById(id);
        if (dbRole == null) {
            return ResultGenerator.genFailedResult("课程不存在");
        }
        this.courseService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping()
    @ResponseBody
    public Result updateCourse(@RequestBody final CourseInfo course) {
        courseService.update(course);
        return ResultGenerator.genOkResult();
    }

    @PostMapping()
    @ResponseBody
    public Result addCourse(@RequestBody final CourseInfo course) {
        System.out.println("addCourse course=" + course);
        courseService.save(course);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/disable")
    @ResponseBody
    public Result disableCourse(@RequestBody final List<String> courseIdList) {
        System.out.println("disableCourse courseIdList=" + courseIdList);
        courseService.disableCourse(courseIdList);
        return list(0, 9, "all");
    }

//    @PostMapping(name = "/disable1")
//    @ResponseBody
//    public Result disableCourse() { // @RequestBody final String[] courseIds
////        System.out.println("disableCourse courseIdList=" + Arrays.toString(courseIds));
////        courseService.disableCourse(courseIdList);
////        return list(0, 9, "all");
//        return ResultGenerator.genOkResult();
//    }

    @PostMapping("/enable")
    @ResponseBody
    public Result enableCourse(@RequestBody final List<String> courseIdList) {
        System.out.println("enableCourse courseIdList=" + courseIdList);
        courseService.enableCourse(courseIdList);
        return list(0, 9, "all");
    }

    //--------------------------------------课程教材管理-----------------------------------

    @GetMapping("/textbook")
    @ResponseBody
    public Result listCourseTextbook(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final PageInfo<CourseTextBookInfo> pageInfo = new PageInfo<>(courseService.listTextbook());
        return ResultGenerator.genOkResult(pageInfo);
    }

    @DeleteMapping("/textbook/{id}")
    @ResponseBody
    public Result deleteTextbook(@PathVariable final Long id, final Principal principal) {
        final CourseTextBookInfo dbRole = this.courseService.getTextbookById(id);
        if (dbRole == null) {
            return ResultGenerator.genFailedResult("教材不存在");
        }
        this.courseService.deleteTextbookById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/textbook")
    @ResponseBody
    public Result updateTextbook(@RequestBody final CourseTextBookInfo info) {
        courseService.updateTextbook(info);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/textbook")
    @ResponseBody
    public Result addTextbook(@RequestBody final CourseTextBookInfo info) {
        System.out.println("addTextbook info=" + info);
        courseService.saveTextbook(info);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/charge")
    @ResponseBody
    public Result listCourseCharge(@RequestParam(defaultValue = "0") final Integer page,
                                   @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final PageInfo<CourseCharge> pageInfo = new PageInfo<>(courseService.listCharge());
        return ResultGenerator.genOkResult(pageInfo);
    }

    @GetMapping("/charge/{id}")
    @ResponseBody
    public Result getCourseCharge(@PathVariable final String id) {
        return ResultGenerator.genOkResult(courseService.getChargeById(id));
    }

    @GetMapping("/national")
    @ResponseBody
    public Result listNationalCourse(@RequestParam(defaultValue = "0") final Integer page,
                                   @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final PageInfo<CourseNational> pageInfo = new PageInfo<>(courseService.listNationalCourse());
        return ResultGenerator.genOkResult(pageInfo);
    }

    @GetMapping("/national/{id}")
    @ResponseBody
    public Result getNationalCourse(@PathVariable final String id) {
        return ResultGenerator.genOkResult(courseService.getNationalCourseById(id));
    }

    @DeleteMapping("/national/{id}")
    @ResponseBody
    public Result deleteNationalCourse(@PathVariable final String id) {
        final CourseNational dbRole = this.courseService.getNationalCourseById(id);
        if (dbRole == null) {
            return ResultGenerator.genFailedResult("国家课程不存在");
        }
        this.courseService.deleteNationalCourse(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/national")
    @ResponseBody
    public Result updateNationalCourse(@RequestBody final CourseNational info) {
        courseService.updateNationalCourse(info);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/national")
    @ResponseBody
    public Result addNationalCourse(@RequestBody final CourseNational info) {
        System.out.println("addTextbook info=" + info);
        courseService.saveNationalCourse(info);
        return ResultGenerator.genOkResult();
    }

}
