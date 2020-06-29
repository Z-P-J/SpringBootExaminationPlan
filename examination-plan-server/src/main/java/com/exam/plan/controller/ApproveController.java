package com.exam.plan.controller;

import com.exam.plan.entity.*;
import com.exam.plan.service.IApproveService;
import com.exam.plan.service.IApproveViewService;
import com.exam.plan.service.ISchoolWithMajorService;
import com.exam.plan.utils.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/approve")
public class ApproveController {

    @Resource
    private IApproveService approveService;
    @Resource
    private IApproveViewService approveViewService;
    @Resource
    private ISchoolWithMajorService schoolWithMajorService;

    @GetMapping("/continue/{id}")
    @ResponseBody
    public Result continuelist(
            @PathVariable final String id,
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        System.out.println("listAllWithMajor list reached！,id="+id);
        PageHelper.startPage(page, size);
        final List<SchoolWithMajor> list = this.schoolWithMajorService.listAllWithMajor(id);
        final PageInfo<SchoolWithMajor> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @GetMapping("/extend/{id}")
    @ResponseBody
    public Result extendlist(
            @PathVariable final String id,
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        System.out.println("listAllWithExtendMajor list reached！,id="+id);
        PageHelper.startPage(page, size);
        final List<SchoolWithMajor> list = this.schoolWithMajorService.listAllWithExtendMajor(id);
        final PageInfo<SchoolWithMajor> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }



    //list表
    @GetMapping()
    @ResponseBody
    public Result list(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "0") final Integer size) {
        System.out.println("approve list reached！");
        PageHelper.startPage(page, size);
        Condition condition=new Condition(ApproveView.class);
        Example.Criteria criteria;
        criteria = condition.createCriteria();
        criteria.andCondition("approve.approve_school_id=school_info.school_id");
        final List<ApproveView> list = this.approveViewService.listByCondition(condition);
        final PageInfo<ApproveView> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    //删除
    @DeleteMapping("/{id}")
    @ResponseBody
    public Result deleteApprove(@PathVariable final Long id, final Principal principal) {
        final Approve dbRole = this.approveService.getById(id);
        System.out.println("approve delete reached");
        if (dbRole == null) {
            return ResultGenerator.genFailedResult("选择的申请表不存在！");
        }
        this.approveService.deleteById(id);
        return ResultGenerator.genOkResult();
    }


    @PostMapping("/search")
    @ResponseBody
    public Result Search(@RequestBody final Map<String, Object> param) {
        System.out.println("search param=" + param);
        PageHelper.startPage((Integer) param.get("page"), (Integer) param.get("size"));

        Condition condition=new Condition(Approve.class);
        condition.createCriteria().andCondition( param.get("fieldSelect")+" like '%" +param.get("fieldVal")+"%'");

        final List<Approve> list = this.approveService.listByCondition(condition);
        final PageInfo<Approve> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @PostMapping
    @ResponseBody
    public Result add(@RequestBody final Approve approve, final BindingResult bindingResult) {
        System.out.println("approve=" + approve);
        this.approveService.save(approve);
        return ResultGenerator.genOkResult();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Result Update(
            @PathVariable final String id, @RequestBody final Approve approve, final Principal principal) {
        final Approve db = this.approveService.getById(id);
        System.out.println("updatecheck id=" + id);
        if (db == null) {
            return ResultGenerator.genFailedResult("选择的数据不存在，请检查");
        }
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        approve.setApprove_time(ft.format(dNow));
        approve.setApprove_status("已审批");
        this.approveService.update(approve);
        return ResultGenerator.genOkResult();
    }

    //continue续办
    //list表


}
