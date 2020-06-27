package com.exam.plan.controller;

import com.exam.plan.entity.ExamCourse;
import com.exam.plan.entity.ExamInfo;
import com.exam.plan.entity.Result;
import com.exam.plan.entity.SchoolInfo;
import com.exam.plan.service.IExamCourseService;
import com.exam.plan.service.IExamInfoService;
import com.exam.plan.service.ISchoolInfoService;
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
@RequestMapping(value = "/school")
public class SchoolInfoController {

    //自动装配需要 @Resource!!
    //专业基本信息
    @Resource
    private ISchoolInfoService schoolInfoService;
    /*··········································考次信息···································*/
    @GetMapping()
    @ResponseBody
    public Result List(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<SchoolInfo> list = this.schoolInfoService.listAll();
        final PageInfo<SchoolInfo> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PostMapping("/search")
    @ResponseBody
    public Result Search(@RequestBody final Map<String, Object> param) {
        System.out.println("search param=" + param);
        PageHelper.startPage((Integer) param.get("page"), (Integer) param.get("size"));

        Condition condition=new Condition(SchoolInfo.class);
        condition.createCriteria().andCondition( param.get("fieldSelect")+" like '%" +param.get("fieldVal")+"%'");

        final List<SchoolInfo> list = this.schoolInfoService.listByCondition(condition);
        final PageInfo<SchoolInfo> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PostMapping()
    @ResponseBody
    public Result Add(@RequestBody final SchoolInfo schoolInfo) {
        System.out.println("addSchoolInfo schoolInfo=" + schoolInfo);
        schoolInfoService.save(schoolInfo);
        return ResultGenerator.genOkResult();
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public Result Delete(@PathVariable final String id, final Principal principal) {
        System.out.println("delExamInfo id=" + id);
        final SchoolInfo schoolInfo = this.schoolInfoService.getById(id);
        if (schoolInfo == null) {
            return ResultGenerator.genFailedResult("选择的数据不存在，请检查");
        }
        this.schoolInfoService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("{id}")
    @ResponseBody
    public Result Update(
            @PathVariable final String id, @RequestBody final SchoolInfo schoolInfo, final Principal principal) {
        final SchoolInfo db = this.schoolInfoService.getById(id);
        System.out.println("updateExamInfo id=" + id);
        if (db == null) {
            return ResultGenerator.genFailedResult("选择的数据不存在，请检查");
        }
        this.schoolInfoService.update(schoolInfo);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/batch/{ids}")
    @ResponseBody
    public Result deleteByBatch(@PathVariable final String ids, final Principal principal) {
        System.out.println("delexamInfo examinfoids=" + ids);
        this.schoolInfoService.deleteByIds(ids);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/batch/{ids}")
    @ResponseBody
    public Result updateByBatch(
            @PathVariable final String ids, @RequestBody final SchoolInfo schoolInfo, final Principal principal) {
        System.out.println("updateExamInfo id=" + ids);
        if(ids==""||ids==null){
            return ResultGenerator.genOkResult();
        }
        Condition condition=new Condition(ExamInfo.class);
        condition.createCriteria().andCondition( "exams_id in (" +ids+")");

        this.schoolInfoService.updateByConditionBatch(ids.split(",").length,schoolInfo,condition);
        return ResultGenerator.genOkResult();
    }


}


