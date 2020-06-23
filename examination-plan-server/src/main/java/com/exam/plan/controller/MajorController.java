package com.exam.plan.controller;

import com.exam.plan.entity.CourseInfo;
import com.exam.plan.entity.Major;
import com.exam.plan.entity.MajorCategory;
import com.exam.plan.entity.Result;
import com.exam.plan.service.ICourseService;
import com.exam.plan.service.IMajorCategoryService;
import com.exam.plan.service.IMajorService;
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
@RequestMapping(value = "/major")
public class MajorController {

    @Resource
    //自动装配需要!!
    private IMajorService majorService;
    @Resource
    private IMajorCategoryService majorCategoryService;

    @GetMapping()
    @ResponseBody
    public Result list(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
//        final List<Major> list = this.majorService.listAll();
//        final PageInfo<Major> pageInfo = new PageInfo<>(list);
//        return ResultGenerator.genOkResult(pageInfo);
        final List<MajorCategory> list = this.majorCategoryService.listAllWithCategory();
        final PageInfo<MajorCategory> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);

    }

    @PostMapping("/search")
    @ResponseBody
    public Result search(@RequestBody final Map<String, Object> param) {
        System.out.println("search param=" + param);
        PageHelper.startPage((Integer) param.get("page"), (Integer) param.get("size"));

        Condition condition=new Condition(Major.class);
        condition.createCriteria().andCondition( param.get("fieldSelect")+" like '%" +param.get("fieldVal")+"%'");

        final List<Major> list = this.majorService.listByCondition(condition);
        final PageInfo<Major> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PostMapping
    @ResponseBody
    public Result add(@RequestBody final Major major) {
        System.out.println("addMajor major=" + major);
        majorService.save(major);
        return ResultGenerator.genOkResult();
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable final String id, final Principal principal) {
        System.out.println("delMajor majorid=" + id);
        final Major major = this.majorService.getById(id);
        if (major == null) {
            return ResultGenerator.genFailedResult("数据不存在");
        }
        this.majorService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Result update(
            @PathVariable final String id, @RequestBody final Major major, final Principal principal) {
        final Major dbMajor = this.majorService.getById(id);
        if (dbMajor == null) {
            return ResultGenerator.genFailedResult("不存在");
        }
        this.majorService.update(major);
        return ResultGenerator.genOkResult();
    }
}
