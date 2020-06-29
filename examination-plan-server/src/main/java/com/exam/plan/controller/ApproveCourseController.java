package com.exam.plan.controller;

import com.exam.plan.entity.*;
import com.exam.plan.service.IApproveCourseService;
import com.exam.plan.service.IApproveMajorService;
import com.exam.plan.service.IApproveService;
import com.exam.plan.service.IMajorService;
import com.exam.plan.utils.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping(value = "/approve_course")
public class ApproveCourseController {

    //自动装配需要 @Resource!!
    //专业基本信息
    @Resource
    private IMajorService majorService;
    @Resource
    private IApproveCourseService approveCourseService;
    @Resource
    private IApproveService approveService;


    //list表
    @GetMapping("/courselist/{id}")
    @ResponseBody
    public Result getApproveCourseDataList(
            @PathVariable final String id) {
        System.out.println("approve_course list id"+ id);
        Condition condition=new Condition(ApproveCourse.class);
        Example.Criteria criteria=condition.createCriteria();
        criteria.andCondition("major_id='"+id+"'");

//        PageHelper.startPage(1, 100);
        final List<ApproveCourse> list = this.approveCourseService.listByCondition(condition);
        final PageInfo<ApproveCourse> pageInfo = new PageInfo<>(list);

        System.out.println("前端将会受到数据pageInfo"+pageInfo);

        return ResultGenerator.genOkResult(pageInfo);
    }
    @PutMapping("/course/addApproveCourseSet/{school_id}")
    @ResponseBody
    public Result courseUpdate(@PathVariable final String school_id,@RequestBody final List<ApproveCourse> approveCourse, final Principal principal) {
//        System.out.println("approveCourse =" + approveCourse);
        Approve approve = new Approve();
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        approve.setApply_time(ft.format(dNow));

        approve.setApprove_name("专业调整");
        approve.setSchool_id(school_id);
        approve.setApprove_status("已申请");
        this.approveService.save(approve);
        System.out.println("school_id="+school_id);
        Condition condition=new Condition(ApproveCourse.class);
        condition.setOrderByClause("`approve_id` desc");
        final List<Approve> list = this.approveService.listByCondition(condition);

        System.out.println("List<Approve> list="+list);
        Integer approve_id=list.get(0).getApprove_id();
        for(ApproveCourse item:approveCourse){

            item.setApprove_id(approve_id);
            System.out.println("approveCourse =" + item);
            this.approveCourseService.update(item);
        }
        return ResultGenerator.genOkResult();
    }

//    //删除
//    @DeleteMapping("/{id}")
//    @ResponseBody
//    public Result removeData(@PathVariable final Long id, final Principal principal) {
//        final ApproveCourse approveCourse = this.approveCourseService.getById(id);
//        System.out.println("approveCourse delete reached");
//        if (approveCourse == null) {
//            return ResultGenerator.genFailedResult("选择的申请表不存在！");
//        }
//        System.out.println("major_id = '"+id+"'");
//        Condition condition=new Condition(ApproveMajor.class);
//        Example.Criteria criteria=condition.createCriteria();
//        criteria.andCondition("major_id='"+id+"'");
//        final List<ApproveCourse> approveCourse = this.approveCourseService.listByCondition(condition);
//        this.approveService.deleteById(id);
//        return ResultGenerator.genOkResult();
//    }

//    @PostMapping("/newapprove/{approve_id}")
//    @ResponseBody
//    public Result AddApproveCourseData(
//            @PathVariable final String approve_id, @RequestBody final Map<String, Object> param) {
//        System.out.println("approve_id reached ,ids="+approve_id);
//
//        String school_id=(String)param.get("school_id");
//
//        Approve approve = new Approve();
//        Date dNow = new Date( );
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        approve.setApply_time(ft.format(dNow));
//
//        approve.setApprove_name((String)(param.get("operation_status")));
//        approve.setSchool_id(school_id);
//        approve.setApprove_status("已申请");
//        this.approveService.save(approve);
//
//        Condition condition=new Condition(ApproveCourse.class);
//        condition.setOrderByClause("`approve_id` desc");
//        final List<ApproveCourse> list = this.approveCourseService.listByCondition(condition);
//
//        System.out.println("List<Approve> list="+list);
//        Integer approve_id=list.get(0).getApprove_id();
//        List<String> idList= Arrays.asList(ids.split(","));
//
//        for(String item:idList){
//            ApproveMajor approveMajor=new ApproveMajor();
//            System.out.println("major_id="+item);
//
//            final Major major = this.majorService.getById(item);
//
//            approveMajor.setApprove_id(approve_id);
//            approveMajor.setMajor_id(item);
//            approveMajor.setMajor_name(major.getMajor_name());
//            approveMajor.setSchool_id(school_id);
//
//            condition=new Condition(MajorWithSchool.class);
//            Example.Criteria criteria;
//            System.out.println("approveMajor="+approveMajor);
//            criteria = condition.createCriteria();
//            criteria.andCondition("major_id='"+item+"'");
//            criteria.andCondition("school_id='"+school_id+"'");
//
//            final List<ApproveMajor> dbMajor = this.approveMajorService.listByCondition(condition);
//            System.out.println("List<MajorSchool> dbMajor: "+dbMajor);
//            if (dbMajor.isEmpty()) {
//                this.approveMajorService.save(approveMajor);
//            }else {
//                this.approveService.deleteById(approve_id);
//                return ResultGenerator.genFailedResult("存在专业编码为"+item+"的专业已经申请，请检查");
//            }
//        }
//        return ResultGenerator.genOkResult();
//    }

}


