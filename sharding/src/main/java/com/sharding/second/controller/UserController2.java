package com.sharding.second.controller;

import com.sharding.second.service.UserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user2")
public class UserController2 {

    @Autowired
    UserService2 userService;

    @GetMapping("/insertUser")
    public void insertUser() throws Exception {
         userService.insertUser();
    }

    @GetMapping("/updateUser")
    public void updateUser(){
        userService.updateUser();
    }

    @GetMapping("/delUser")
    public void delUser(){
        userService.delUser();
    }

    @GetMapping("/delAllUser")
    public void delAllUser(){
        userService.delAllUser();
    }

    @GetMapping("queryUser")
    public void queryUser(){
        userService.queryUser();
    }

    @GetMapping("queryUserLimit")
    public void queryUserLimit(){
        userService.queryUserLimit();
    }

    @GetMapping("joinUser")
    public void joinUser(){
        userService.joinUser();
    }

    @GetMapping("queryOrder")
    public void queryOrder(){
        userService.queryOrder();
    }
}
