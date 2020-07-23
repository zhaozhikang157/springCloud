package com.sharding.second.service.impl;

import com.alibaba.fastjson.JSON;
import com.sharding.second.dao.UserDao2;
import com.sharding.second.service.UserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService2Impl implements UserService2 {

    @Autowired
    private UserDao2 userDao2;

    @Override
    @Transactional
    public void insertUser() throws Exception {
        for(int i = 0 ; i<20 ; i++){
            Map map = new HashMap();
            map.put("id",i);
            map.put("name","tom");
            map.put("city_id",1);
            map.put("sex",i%2);
            map.put("phone",i);
            map.put("email",i + "@qq.com");
            map.put("create_time",new Date());
            map.put("password","cffbjkdsnakjf");
            userDao2.insertUser(map);
            if(i == 15){
                //事务一致性  用分布式框架   lcn可以
                throw new Exception();
            }
        }
    }

    @Override
    @Transactional
    public void updateUser(){
        Map map = new HashMap();
        map.put("id",5);
        userDao2.updateUser(map);
    }

    @Override
    @Transactional
    public void delUser(){
        Map map = new HashMap();
        map.put("id",5);
        userDao2.delUser(map);
    }

    @Override
    @Transactional
    public void delAllUser(){
        Map map = new HashMap();
        userDao2.delAllUser(map);
    }

    @Override
    @Transactional
    public void queryUser(){
        List<Map> list = userDao2.queryUser();
        System.out.println(JSON.toJSON(list).toString());
    }

    /**
     * 分页
     */
    @Override
    @Transactional
    public void queryUserLimit(){
        List<Map> list = userDao2.queryUserLimit();
        System.out.println(JSON.toJSON(list).toString());
    }

    /**
     * join 表
     */
    @Override
    @Transactional
    public void joinUser(){
        List<Map> list = userDao2.joinUser();
        System.out.println(JSON.toJSON(list).toString());
    }

    /**
     * 查询一个不分表的数据    只会查配置的第一个库
     */
    @Override
    @Transactional
    public void queryOrder(){
        List<Map> list = userDao2.queryOrder();
        System.out.println(JSON.toJSON(list).toString());

        /*List<Map> list2 = userDao.queryOrder2();
        System.out.println(JSON.toJSON(list2).toString());*/
    }
}
