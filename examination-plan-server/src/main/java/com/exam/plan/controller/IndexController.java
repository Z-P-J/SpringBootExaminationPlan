package com.exam.plan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    /**
     * 默认启动页设置
     * @return
     */
    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }
}
