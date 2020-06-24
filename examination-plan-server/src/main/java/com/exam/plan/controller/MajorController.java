package com.exam.plan.controller;

import com.exam.plan.entity.*;
import com.exam.plan.service.*;
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

    //自动装配需要 @Resource!!
    //专业基本信息
    @Resource
    private IMajorService majorService;
    //专业信息+专业大类信息+首所主考院校信息
    @Resource
    private IMajorWithCategoryService majorWithCategoryService;
    //专业大类
    @Resource
    private IMajorCategoryService majorCategoryService;
    //主考院校
    @Resource
    private IMajorSchoolService majorSchoolService;

    /*··········································专业信息···································*/
    @GetMapping()
    @ResponseBody
    public Result list(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
//        final List<Major> list = this.majorService.listAll();
//        final PageInfo<Major> pageInfo = new PageInfo<>(list);
//        return ResultGenerator.genOkResult(pageInfo);
        final List<MajorWithCategory> list = this.majorWithCategoryService.listAllWithCategory();
        final PageInfo<MajorWithCategory> pageInfo = new PageInfo<>(list);
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


    /*··········································专业大类···································*/


    @GetMapping("/category")
    @ResponseBody
    public Result categoryList(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<MajorCategory> list = this.majorCategoryService.listAll();
        final PageInfo<MajorCategory> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);

    }

    @PostMapping("/category/search")
    @ResponseBody
    public Result categorySearch(@RequestBody final Map<String, Object> param) {
        System.out.println("search param=" + param);
        PageHelper.startPage((Integer) param.get("page"), (Integer) param.get("size"));

        Condition condition=new Condition(MajorCategory.class);
        condition.createCriteria().andCondition( param.get("fieldSelect")+" like '%" +param.get("fieldVal")+"%'");

        final List<MajorCategory> list = this.majorCategoryService.listByCondition(condition);
        final PageInfo<MajorCategory> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PostMapping("/category")
    @ResponseBody
    public Result categoryAdd(@RequestBody final MajorCategory major) {
        System.out.println("addMajor major=" + major);
        majorCategoryService.save(major);
        return ResultGenerator.genOkResult();
    }
    @DeleteMapping("/category/{id}")
    @ResponseBody
    public Result categoryDelete(@PathVariable final String id, final Principal principal) {
        System.out.println("delMajorCategory id=" + id);
        final MajorCategory major = this.majorCategoryService.getById(id);
        if (major == null) {
            return ResultGenerator.genFailedResult("数据不存在");
        }
        this.majorCategoryService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/category/{id}")
    @ResponseBody
    public Result categoryUpdate(
            @PathVariable final String id, @RequestBody final MajorCategory major, final Principal principal) {
        final MajorCategory dbMajor = this.majorCategoryService.getById(id);
        if (dbMajor == null) {
            return ResultGenerator.genFailedResult("不存在");
        }
        this.majorCategoryService.update(major);
        return ResultGenerator.genOkResult();
    }

    /*··········································全国专业···································*/
    /*··········································主考院校···································*/

    @GetMapping("/school/{id}")
    @ResponseBody
    public Result schoolList(
            @PathVariable final String id,
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        System.out.println("listMajorShcool major_id=" + id);
        PageHelper.startPage(page, size);

        Condition condition=new Condition(MajorSchool.class);
        condition.createCriteria().andCondition("major_id = '"+id+"'");

        final List<MajorSchool> list = this.majorSchoolService.listByCondition(condition);
        final PageInfo<MajorSchool> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);

    }

    @PostMapping("/school/search/{id}")
    @ResponseBody
    public Result schoolSearch(@PathVariable final String id, @RequestBody final Map<String, Object> param) {
        System.out.println("search param=" + param +" id="+id);
        PageHelper.startPage((Integer) param.get("page"), (Integer) param.get("size"));

        Condition condition=new Condition(MajorSchool.class);
        condition.createCriteria().andCondition( param.get("fieldSelect")+" like '%" +param.get("fieldVal")+"%'" +"and major_id = '"+id+"'");
        condition.setOrderByClause("main_target_school_code");

        final List<MajorSchool> list = this.majorSchoolService.listByCondition(condition);
        final PageInfo<MajorSchool> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PostMapping("/school")
    @ResponseBody
    public Result schoolAdd(@RequestBody final MajorSchool major) {
        System.out.println("addMajorShcool MajorShcool=" + major);
        majorSchoolService.save(major);
        return ResultGenerator.genOkResult();
    }
    @DeleteMapping("/school/{id}")
    @ResponseBody
    public Result schoolDelete(@PathVariable final Integer id, final Principal principal) {
        System.out.println("delMajorShcool id=" + id);
        final MajorSchool major = this.majorSchoolService.getById(id);
        if (major == null) {
            return ResultGenerator.genFailedResult("数据不存在");
        }
        this.majorSchoolService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/school/{id}")
    @ResponseBody
    public Result schoolUpdate(
            @PathVariable final String id, @RequestBody final MajorSchool major, final Principal principal) {
        final MajorSchool dbMajor = this.majorSchoolService.getById(id);
        if (dbMajor == null) {
            return ResultGenerator.genFailedResult("不存在");
        }
        this.majorSchoolService.update(major);
        return ResultGenerator.genOkResult();
    }

}


