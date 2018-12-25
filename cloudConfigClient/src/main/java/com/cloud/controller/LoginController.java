package com.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Value("${name}")
    private String name;

    @GetMapping("/login")
    public String login(){
        System.out.println("----name:"+name+"，登陆成功------");
        return "name:"+name+",登陆成功成功";
    }
}
