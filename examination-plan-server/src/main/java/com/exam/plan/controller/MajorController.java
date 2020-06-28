package com.exam.plan.controller;

import com.exam.plan.entity.*;
import com.exam.plan.service.*;
import com.exam.plan.utils.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

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
    //主考院校
    @Resource
    private IMajorWithSchoolService majorWithSchoolService;
    //全国专业
    @Resource
    private IMajorNationalService majorNationalService;

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
        Condition condition=new Condition(MajorWithCategory.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andCondition("major_info.major_category_code=major_categories.category_code");
//        final List<MajorWithCategory> list = this.majorWithCategoryService.listAllWithCategory();
        final List<MajorWithCategory> list = this.majorWithCategoryService.listByCondition(condition);
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

    @DeleteMapping("/batch/{ids}")
    @ResponseBody
    public Result deleteByBatch(@PathVariable final String ids, final Principal principal) {
        System.out.println("delMajor majorids=" + ids);
        this.majorService.deleteByIds(ids);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/batch/{ids}")
    @ResponseBody
    public Result updateByBatch(
            @PathVariable final String ids, @RequestBody final Major major, final Principal principal) {
        System.out.println("updateMajor majorids=" + ids);
        if(ids==""||ids==null){
            return ResultGenerator.genOkResult();
        }
        Condition condition=new Condition(Major.class);
        condition.createCriteria().andCondition( "major_id in (" +ids+")");

        this.majorService.updateByConditionBatch(ids.split(",").length,major,condition);
        return ResultGenerator.genOkResult();
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable final String id, final Principal principal) {
        System.out.println("delMajor majorid=" + id);
        final Major major = this.majorService.getById(id);
        if (major == null) {
            return ResultGenerator.genFailedResult("选择的数据不存在，请检查");
        }
        this.majorService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Result update(
            @PathVariable final String id, @RequestBody final Major major, final Principal principal) {
        System.out.println("updateMajor majorid=" + id);
        final Major dbMajor = this.majorService.getById(id);
        if (dbMajor == null) {
            return ResultGenerator.genFailedResult("选择的数据不存在，请检查");
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
            return ResultGenerator.genFailedResult("选择的数据不存在，请检查");
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
            return ResultGenerator.genFailedResult("选择的数据不存在，请检查");
        }
        this.majorCategoryService.update(major);
        return ResultGenerator.genOkResult();
    }

    /*··········································全国专业···································*/

    @GetMapping("/nation")
    @ResponseBody
    public Result nationList(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<MajorNational> list = this.majorNationalService.listAll();
        final PageInfo<MajorNational> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);

    }

    @PostMapping("/nation/search")
    @ResponseBody
    public Result nationSearch(@RequestBody final Map<String, Object> param) {
        System.out.println("search param=" + param);
        PageHelper.startPage((Integer) param.get("page"), (Integer) param.get("size"));

        Condition condition=new Condition(MajorNational.class);
        condition.createCriteria().andCondition( param.get("fieldSelect")+" like '%" +param.get("fieldVal")+"%'");

        final List<MajorNational> list = this.majorNationalService.listByCondition(condition);
        final PageInfo<MajorNational> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PostMapping("/nation")
    @ResponseBody
    public Result nationAdd(@RequestBody final MajorNational major) {
        System.out.println("addMajorNational major=" + major);
        majorNationalService.save(major);
        return ResultGenerator.genOkResult();
    }
    @DeleteMapping("/nation/{id}")
    @ResponseBody
    public Result nationDelete(@PathVariable final String id, final Principal principal) {
        System.out.println("delMajorNational id=" + id);
        final MajorNational major = this.majorNationalService.getById(id);
        if (major == null) {
            return ResultGenerator.genFailedResult("选择的数据不存在，请检查");
        }
        this.majorNationalService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/nation/{id}")
    @ResponseBody
    public Result nationUpdate(
            @PathVariable final String id, @RequestBody final MajorNational major, final Principal principal) {
        final MajorNational dbMajor = this.majorNationalService.getById(id);
        if (dbMajor == null) {
            return ResultGenerator.genFailedResult("选择的数据不存在，请检查");
        }
        this.majorNationalService.update(major);
        return ResultGenerator.genOkResult();
    }


    /*··········································主考院校···································*/

    @GetMapping("/school/{id}")
    @ResponseBody
    public Result schoolList(
            @PathVariable final String id,
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        System.out.println("listMajorShcool major_id=" + id);
        PageHelper.startPage(page, size);

        Condition condition=new Condition(MajorWithSchool.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andCondition("major_id = '"+id+"'");
        criteria.andCondition("major_school.main_target_school=school_info.school_id");

        final List<MajorWithSchool> list = this.majorWithSchoolService.listByCondition(condition);
        final PageInfo<MajorWithSchool> pageInfo = new PageInfo<>(list);
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
        Condition condition=new Condition(MajorWithSchool.class);
        Example.Criteria criteria;

        criteria = condition.createCriteria();
        criteria.andCondition("major_id='"+major.major_id+"'");
        criteria.andCondition("main_target_school='"+major.main_target_school+"'");

        final List<MajorSchool> dbMajor = this.majorSchoolService.listByCondition(condition);
        if (dbMajor.isEmpty()) {
            this.majorSchoolService.save(major);
            return ResultGenerator.genOkResult("数据添加成功");
        }else{
            dbMajor.get(0).main_target_school_code=major.main_target_school_code;
            this.majorSchoolService.update(dbMajor.get(0));
            return ResultGenerator.genOkResult("数据已存在，默认进行修改");
        }

    }
    @DeleteMapping("/school/{id}")
    @ResponseBody
    public Result schoolDelete(@PathVariable final Integer id, final Principal principal) {
        System.out.println("delMajorShcool id=" + id);
        final MajorSchool major = this.majorSchoolService.getById(id);
        if (major == null) {
            return ResultGenerator.genFailedResult("选择的数据不存在，请检查");
        }
        this.majorSchoolService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/school/batch")
    @ResponseBody
    public Result schoolUpdateByBatch(
            @RequestBody final List<MajorSchool> major, final Principal principal) {
        System.out.println("schoolUpdateByBatch");
        Condition condition=new Condition(MajorWithSchool.class);
        Example.Criteria criteria;
        for(MajorSchool item:major){
            System.out.println("schoolUpdateByBatch: "+item);
            criteria = condition.createCriteria();
            criteria.andCondition("major_id='"+item.major_id+"'");
            criteria.andCondition("main_target_school='"+item.main_target_school+"'");

            final List<MajorSchool> dbMajor = this.majorSchoolService.listByCondition(condition);
            System.out.println("List<MajorSchool> dbMajor: "+dbMajor);
            if (dbMajor.isEmpty()) {
                this.majorSchoolService.save(item);
            }else{
                dbMajor.get(0).main_target_school_code=item.main_target_school_code;
                this.majorSchoolService.update(dbMajor.get(0));
            }
        }
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/school/{id}")
    @ResponseBody
    public Result schoolUpdate(
            @PathVariable final String id, @RequestBody final MajorSchool major, final Principal principal) {
        final MajorSchool dbMajor = this.majorSchoolService.getById(id);
        if (dbMajor == null) {
            return ResultGenerator.genFailedResult("选择的数据不存在，请检查");
        }
        this.majorSchoolService.update(major);
        return ResultGenerator.genOkResult();
    }
}


