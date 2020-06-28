//package com.exam.plan.controller;
//
//import com.exam.plan.entity.User;
//import com.exam.plan.service.IUserService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//
//@Controller
//@RequestMapping(value = "/user")
//public class UserController {
//
////    @Autowired
//    @Resource
//    private IUserService userService;
//
//    @RequestMapping(value = "/saveUser")
//    @ResponseBody
//    public String saveUser(){
//        User user = new User();
//        user.setAge(18);
//        user.setName("boot2");
//        user.setAddress("org.spring.io");
//        userService.insert(user);
//        return "save user success";
//    }
//
//}
