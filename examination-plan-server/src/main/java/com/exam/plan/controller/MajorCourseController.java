package com.exam.plan.controller;

import com.exam.plan.entity.*;
import com.exam.plan.service.ICourseService;
import com.exam.plan.service.IMajorCourseService;
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
@RequestMapping(value = "/major-course")
public class MajorCourseController {

    @Resource
    private IMajorCourseService majorCourseService;

//    @GetMapping("/permission")
//    @ResponseBody
//    public Result listWithPermission(
//            @RequestParam(defaultValue = "0") final Integer page,
//            @RequestParam(defaultValue = "0") final Integer size) {
//        PageHelper.startPage(page, size);
//        final List<ProCourseInfo> list = this.majorCourseService.listAll();
//        final PageInfo<ProCourseInfo> pageInfo = new PageInfo<>(list);
//        return ResultGenerator.genOkResult(pageInfo);
//    }

    @GetMapping()
    @ResponseBody
    public Result list(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size,
            @RequestParam(defaultValue = "all") final String filter) {
        System.out.println("filter=" + filter);
        PageHelper.startPage(page, size);
        final List<ProCourseInfo> list;
        list = this.majorCourseService.listAll();
//        if ("all".equalsIgnoreCase(filter)) {
//            list = this.majorCourseService.listAll();
//        } else if ("enable".equalsIgnoreCase(filter)) {
//            final Condition condition = new Condition(ProCourseInfo.class);
//            condition.createCriteria().andCondition("course_status = ", "正常");
//            list = this.majorCourseService.listByCondition(condition);
//        } else if ("disable".equalsIgnoreCase(filter)) {
//            final Condition condition = new Condition(ProCourseInfo.class);
//            condition.createCriteria().andCondition("course_status = ", "注销");
//            list = this.majorCourseService.listByCondition(condition);
//        } else {
//            list = new ArrayList<>();
//        }
        final PageInfo<ProCourseInfo> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result deleteCourse(@PathVariable final String id, final Principal principal) {
        final ProCourseInfo dbRole = this.majorCourseService.getById(id);
        if (dbRole == null) {
            return ResultGenerator.genFailedResult("专业课程不存在");
        }
        this.majorCourseService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Result deleteCourse(@RequestBody final List<String> courseIdList) {
        System.out.println("deleteCourse courseIdList=" + courseIdList);
        majorCourseService.deleteCourse(courseIdList);
        return list(0, 9, "all");
    }

    @PutMapping()
    @ResponseBody
    public Result updateMajorCourse(@RequestBody final ProCourseInfo course) {
        majorCourseService.update(course);
        return ResultGenerator.genOkResult();
    }

    @PostMapping()
    @ResponseBody
    public Result addCourse(@RequestBody final ProCourseInfo course) {
        System.out.println("addCourse course=" + course);
        majorCourseService.save(course);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/disable")
    @ResponseBody
    public Result disableCourse(@RequestBody final List<String> courseIdList) {
        System.out.println("disableCourse courseIdList=" + courseIdList);
        majorCourseService.disableCourse(courseIdList);
        return list(0, 9, "all");
    }

//    @PostMapping(name = "/disable1")
//    @ResponseBody
//    public Result disableCourse() { // @RequestBody final String[] courseIds
////        System.out.println("disableCourse courseIdList=" + Arrays.toString(courseIds));
////        majorCourseService.disableCourse(courseIdList);
////        return list(0, 9, "all");
//        return ResultGenerator.genOkResult();
//    }

    @PostMapping("/enable")
    @ResponseBody
    public Result enableCourse(@RequestBody final List<String> courseIdList) {
        System.out.println("enableCourse courseIdList=" + courseIdList);
        majorCourseService.enableCourse(courseIdList);
        return list(0, 9, "all");
    }

    //--------------------------------------课程教材管理-----------------------------------

    @GetMapping("/textbook")
    @ResponseBody
    public Result listCourseTextbook(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final PageInfo<CourseTextBookInfo> pageInfo = new PageInfo<>(majorCourseService.listTextbook());
        return ResultGenerator.genOkResult(pageInfo);
    }

    @DeleteMapping("/textbook/{id}")
    @ResponseBody
    public Result deleteTextbook(@PathVariable final Long id) {
        final CourseTextBookInfo dbRole = this.majorCourseService.getTextbookById(id);
        if (dbRole == null) {
            return ResultGenerator.genFailedResult("教材不存在");
        }
        this.majorCourseService.deleteTextbookById(id);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/textbook/{id}")
    @ResponseBody
    public Result listTextbookByCourseId(@PathVariable final String id) {
        final CourseTextBookInfo info = this.majorCourseService.getTextbookById(id);
        return ResultGenerator.genOkResult(info);
    }

    @PutMapping("/textbook")
    @ResponseBody
    public Result updateTextbook(@RequestBody final CourseTextBookInfo info) {
        majorCourseService.updateTextbook(info);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/textbook")
    @ResponseBody
    public Result addTextbook(@RequestBody final CourseTextBookInfo info) {
        System.out.println("addTextbook info=" + info);
        majorCourseService.saveTextbook(info);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/charge")
    @ResponseBody
    public Result listCourseCharge(@RequestParam(defaultValue = "0") final Integer page,
                                   @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final PageInfo<CourseCharge> pageInfo = new PageInfo<>(majorCourseService.listCharge());
        return ResultGenerator.genOkResult(pageInfo);
    }

    @GetMapping("/charge/{id}")
    @ResponseBody
    public Result getCourseCharge(@PathVariable final String id) {
        return ResultGenerator.genOkResult(majorCourseService.getChargeById(id));
    }

    @DeleteMapping("/charge/{id}")
    @ResponseBody
    public Result deleteCourseCharge(@PathVariable final String id) {
        final CourseCharge dbRole = this.majorCourseService.getChargeById(id);
        if (dbRole == null) {
            return ResultGenerator.genFailedResult("课程费用不存在");
        }
        this.majorCourseService.deleteCourseCharge(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/charge")
    @ResponseBody
    public Result updateCourseCharge(@RequestBody final CourseCharge info) {
        majorCourseService.updateCourseCharge(info);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/charge")
    @ResponseBody
    public Result addCourseCharge(@RequestBody final CourseCharge info) {
        System.out.println("addTextbook info=" + info);
        majorCourseService.saveCourseCharge(info);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/national")
    @ResponseBody
    public Result listNationalCourse(@RequestParam(defaultValue = "0") final Integer page,
                                     @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final PageInfo<CourseNational> pageInfo = new PageInfo<>(majorCourseService.listNationalCourse());
        return ResultGenerator.genOkResult(pageInfo);
    }

    @GetMapping("/national/{id}")
    @ResponseBody
    public Result getNationalCourse(@PathVariable final String id) {
        return ResultGenerator.genOkResult(majorCourseService.getNationalCourseById(id));
    }

    @DeleteMapping("/national/{id}")
    @ResponseBody
    public Result deleteNationalCourse(@PathVariable final String id) {
        final CourseNational dbRole = this.majorCourseService.getNationalCourseById(id);
        if (dbRole == null) {
            return ResultGenerator.genFailedResult("国家课程不存在");
        }
        this.majorCourseService.deleteNationalCourse(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/national")
    @ResponseBody
    public Result updateNationalCourse(@RequestBody final CourseNational info) {
        majorCourseService.updateNationalCourse(info);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/national")
    @ResponseBody
    public Result addNationalCourse(@RequestBody final CourseNational info) {
        System.out.println("addTextbook info=" + info);
        majorCourseService.saveNationalCourse(info);
        return ResultGenerator.genOkResult();
    }

}
