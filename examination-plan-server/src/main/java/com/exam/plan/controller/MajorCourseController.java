package com.exam.plan.controller;

import com.exam.plan.entity.*;
import com.exam.plan.service.ICourseService;
import com.exam.plan.service.IMajorCourseService;
import com.exam.plan.service.IMajorDirectionService;
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
@RequestMapping(value = "/major-course")
public class MajorCourseController {

    @Resource
    private IMajorCourseService majorCourseService;
    @Resource
    private IMajorDirectionService majorDirectionService;

    @GetMapping()
    @ResponseBody
    public Result list(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size,
            @RequestParam(defaultValue = "all") final String filter) {
        System.out.println("filter=" + filter);
        PageHelper.startPage(page, size);
        final List<MajorCourseInfo> list;
        if ("all".equalsIgnoreCase(filter)) {
            list = this.majorCourseService.listAll();
        } else if ("enable".equalsIgnoreCase(filter)) {
            final Condition condition = new Condition(MajorCourseInfo.class);
            condition.createCriteria().andCondition("course_status = ", "正常");
            list = this.majorCourseService.listByCondition(condition);
        } else if ("disable".equalsIgnoreCase(filter)) {
            final Condition condition = new Condition(MajorCourseInfo.class);
            condition.createCriteria().andCondition("course_status = ", "注销");
            list = this.majorCourseService.listByCondition(condition);
        } else {
            list = new ArrayList<>();
        }
        final PageInfo<MajorCourseInfo> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result deleteCourse(@PathVariable final String id) {
        final MajorCourseInfo dbRole = this.majorCourseService.getById(id);
        if (dbRole == null) {
            return ResultGenerator.genFailedResult("专业课程不存在");
        }
        this.majorCourseService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

//    @PostMapping("/delete")
//    @ResponseBody
//    public Result deleteCourse(@RequestBody final List<String> courseIdList) {
//        System.out.println("deleteCourse courseIdList=" + courseIdList);
//        majorCourseService.deleteCourse(courseIdList);
//        return list(0, 9, "all");
//    }

    @PutMapping()
    @ResponseBody
    public Result updateCourse(@RequestBody final MajorCourseInfo course) {
        majorCourseService.update(course);
        return ResultGenerator.genOkResult();
    }

    @PostMapping()
    @ResponseBody
    public Result addCourse(@RequestBody final MajorCourseInfo course) {
        System.out.println("addCourse course=" + course);
        majorCourseService.save(course);
        return ResultGenerator.genOkResult();
    }

//    @PostMapping("/disable")
//    @ResponseBody
//    public Result disableCourse(@RequestBody final List<String> courseIdList) {
//        System.out.println("disableCourse courseIdList=" + courseIdList);
//        majorCourseService.disableCourse(courseIdList);
//        return list(0, 9, "all");
//    }
//
//    @PostMapping("/enable")
//    @ResponseBody
//    public Result enableCourse(@RequestBody final List<String> courseIdList) {
//        System.out.println("enableCourse courseIdList=" + courseIdList);
//        majorCourseService.enableCourse(courseIdList);
//        return list(0, 9, "all");
//    }

    @GetMapping("/direction")
    @ResponseBody
    public Result listPractice(@RequestParam(defaultValue = "0") final Integer page,
                               @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final PageInfo<MajorDirection> pageInfo = new PageInfo<>(majorDirectionService.listAll());
        return ResultGenerator.genOkResult(pageInfo);
    }

    @DeleteMapping("/direction/{id}")
    @ResponseBody
    public Result deletePractice(@PathVariable final String id) {
        final MajorDirection dbRole = this.majorDirectionService.getById(id);
        if (dbRole == null) {
            return ResultGenerator.genFailedResult("理论实践对应关系不存在");
        }
        this.majorDirectionService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/direction")
    @ResponseBody
    public Result updatePractice(@RequestBody final MajorDirection info) {
        majorDirectionService.update(info);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/direction")
    @ResponseBody
    public Result addPractice(@RequestBody final MajorDirection info) {
        System.out.println("addPractice info=" + info);
        majorDirectionService.save(info);
        return ResultGenerator.genOkResult();
    }

}
