package com.exam.plan.controller;

import com.exam.plan.entity.*;
import com.exam.plan.mapper.ApproveMapper;
import com.exam.plan.service.*;
import com.exam.plan.utils.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/approve_major")
public class ApproveMajorController {

    //自动装配需要 @Resource!!
    //专业基本信息
    @Resource
    private IMajorService majorService;
    @Resource
    private IApproveMajorService approveMajorService;
    @Resource
    private IApproveCourseService approveCourseService;
    @Resource
    private IApproveService approveService;


    //list表
    @GetMapping("/majorlist/{id}")
    @ResponseBody
    public Result list(
            @PathVariable final String id) {
        System.out.println("approve_major list id"+id);
        Condition condition=new Condition(ApproveMajor.class);
        Example.Criteria criteria=condition.createCriteria();
        criteria.andCondition("approve_id='"+id+"'");

//        return ResultGenerator.genOkResult(list);
        PageHelper.startPage(1, 100);
        final List<ApproveMajor> list = this.approveMajorService.listByCondition(condition);
        final PageInfo<ApproveMajor> pageInfo = new PageInfo<>(list);
        System.out.println("pageInfo"+pageInfo);

        return ResultGenerator.genOkResult(pageInfo);
    }

    //getApproveNewData显示新专业信息
    @GetMapping("/newmajor/{approve_id}")
    @ResponseBody
    public Result getApproveNewData(
            @PathVariable final String approve_id) {
        System.out.println("approve_id = '"+approve_id+"'");


        Condition condition=new Condition(ApproveMajor.class);
        Example.Criteria criteria=condition.createCriteria();
        criteria.andCondition("approve_id='"+approve_id+"'");

        final List<ApproveMajor> approveMajor = this.approveMajorService.listByCondition(condition);

        System.out.println("approveMajor"+approveMajor.get(0));

        return ResultGenerator.genOkResult(approveMajor.get(0));
    }

    //显示专业调整信息
    @GetMapping("/courselist/{id}")
    @ResponseBody
    public Result ApproveCourselist(
            @PathVariable final String id) {
        System.out.println("ApproveCourselist list approve_id"+id);
        Condition condition=new Condition(ApproveCourse.class);
        Example.Criteria criteria=condition.createCriteria();
        criteria.andCondition("approve_id="+id+"");

//        return ResultGenerator.genOkResult(list);
        PageHelper.startPage(1, 100);
        final List<ApproveCourse> list = this.approveCourseService.listByCondition(condition);
        final PageInfo<ApproveCourse> pageInfo = new PageInfo<>(list);
        System.out.println("pageInfo"+pageInfo);

        return ResultGenerator.genOkResult(pageInfo);
    }

    @PostMapping("/single/{id}")
    @ResponseBody
    public Result addApproveMajor(
            @PathVariable final String id, @RequestBody final ApproveMajor approveMajor) {
        System.out.println("id ="+id+"approveMajor="+approveMajor);

        String school_id=id;

        Approve approve = new Approve();
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        approve.setApply_time(ft.format(dNow));

        approve.setApprove_name("新专业");
        approve.setSchool_id(school_id);
        approve.setApprove_status("已申请");
        this.approveService.save(approve);

        Condition condition=new Condition(Approve.class);
        condition.setOrderByClause("`approve_id` desc");
        final List<Approve> list = this.approveService.listByCondition(condition);

        System.out.println("List<Approve> list="+list);
        Integer approve_id=list.get(0).getApprove_id();

        approveMajor.setApprove_id(approve_id);
        approveMajor.setSchool_id(school_id);


        this.approveMajorService.save(approveMajor);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/batch/{ids}")
    @ResponseBody
    public Result addApproveMajorByBatch(
            @PathVariable final String ids, @RequestBody final Map<String, Object> param) {
        System.out.println("addApproveMajorByBatch reached ,ids="+ids+"params="+param);

        String school_id=(String)param.get("school_id");

        Approve approve = new Approve();
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        approve.setApply_time(ft.format(dNow));

        approve.setApprove_name((String)(param.get("operation_status")));
        approve.setSchool_id(school_id);
        approve.setApprove_status("已申请");
        this.approveService.save(approve);

        Condition condition=new Condition(Approve.class);
        condition.setOrderByClause("`approve_id` desc");
        final List<Approve> list = this.approveService.listByCondition(condition);

        System.out.println("List<Approve> list="+list);
        Integer approve_id=list.get(0).getApprove_id();
        List<String> idList= Arrays.asList(ids.split(","));

        for(String item:idList){
            ApproveMajor approveMajor=new ApproveMajor();
            System.out.println("major_id="+item);

            final Major major = this.majorService.getById(item);

            approveMajor.setApprove_id(approve_id);
            approveMajor.setMajor_id(item);
            approveMajor.setMajor_name(major.getMajor_name());
            approveMajor.setSchool_id(school_id);

            condition=new Condition(MajorWithSchool.class);
            Example.Criteria criteria;
            System.out.println("approveMajor="+approveMajor);
            criteria = condition.createCriteria();
            criteria.andCondition("major_id='"+item+"'");
            criteria.andCondition("school_id='"+school_id+"'");

            final List<ApproveMajor> dbMajor = this.approveMajorService.listByCondition(condition);
            System.out.println("List<MajorSchool> dbMajor: "+dbMajor);
            if (dbMajor.isEmpty()) {
                this.approveMajorService.save(approveMajor);
            }else {
                this.approveService.deleteById(approve_id);
                return ResultGenerator.genFailedResult("存在专业编码为"+item+"的专业已经申请，请检查");
            }
        }
        return ResultGenerator.genOkResult();
    }

}


