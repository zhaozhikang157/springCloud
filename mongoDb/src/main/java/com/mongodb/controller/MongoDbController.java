package com.mongodb.controller;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.dto.Comments;
import com.mongodb.service.MongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/mongoDb")
public class MongoDbController {

    @Autowired
    private MongoDbService mongoDbService;

    @RequestMapping("/insert")
    public void insert(){
        Comments comments = new Comments();
        comments.setLikeNum(0);
        comments.setContent("这是一个评论");
        comments.setNickName("卢本伟");
        comments.setCreateTime(new Date());
        comments.setParentid("0");
        comments.setReplyNum(0);
        comments.setStatus("1");
        comments.setUserId("10");
        comments.setActicleid("1");
        mongoDbService.insert(comments);
    }

    @RequestMapping("/queryAll")
    public void queryAll(){
        System.out.println(JSONObject.toJSON(mongoDbService.queryAll()).toString());
    }

    @RequestMapping("/queryById")
    public void queryById(){
        mongoDbService.queryById("");
    }

    @RequestMapping("/deleteById")
    public void deleteById(){
        mongoDbService.deleteById("5f227f999cb94f30343e3d8b");
    }
}
