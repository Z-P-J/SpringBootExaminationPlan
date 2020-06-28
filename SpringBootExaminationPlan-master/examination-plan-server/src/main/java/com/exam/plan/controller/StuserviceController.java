package com.exam.plan.controller;

import com.exam.plan.entity.Result;
import com.exam.plan.entity.Stuservice;
import com.exam.plan.service.IPermissionService;
import com.exam.plan.service.IStuserviceService;
import com.exam.plan.utils.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.objects.XObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("stuservice")
public class StuserviceController {
    @RequestMapping("test")
    public String test(){
        return "这是一个测试";
    }
    @Autowired
    IStuserviceService iStuserviceService;

    @RequestMapping("getstuservice")
    public List<Stuservice> getstuservice(Map map){
        return iStuserviceService.getstuservice(map);
    }

}
