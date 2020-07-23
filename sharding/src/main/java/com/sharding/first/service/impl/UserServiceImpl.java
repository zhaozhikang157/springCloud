package com.sharding.first.service.impl;

import com.alibaba.fastjson.JSON;
import com.sharding.first.dao.UserDao;
import com.sharding.first.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

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
            userDao.insertUser(map);
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
        userDao.updateUser(map);
    }

    @Override
    @Transactional
    public void delUser(){
        Map map = new HashMap();
        map.put("id",5);
        userDao.delUser(map);
    }

    @Override
    @Transactional
    public void delAllUser(){
        Map map = new HashMap();
        userDao.delAllUser(map);
    }


    /**
     * 全局表
     *
     * 所谓全局表，就是有可能系统中所有模块都可能会依赖到的一些表。比较类似我们理解的“数据字典”。为了避免跨库join查询，我们可以将这类表在其他每个数据库中均保存一份。同时，这类数据通常也很少发生修改（甚至几乎不会），所以也不用太担心“一致性”问题。
     *
     * 字段冗余
     *
     * 这是一种典型的反范式设计，在互联网行业中比较常见，通常是为了性能来避免join查询。
     *
     * 举个电商业务中很简单的场景：
     *
     * “订单表”中保存“卖家Id”的同时，将卖家的“Name”字段也冗余，这样查询订单详情的时候就不需要再去查询“卖家用户表”。
     *
     * 字段冗余能带来便利，是一种“空间换时间”的体现。但其适用场景也比较有限，比较适合依赖字段较少的情况。最复杂的还是数据一致性问题，这点很难保证，可以借助数据库中的触发器或者在业务代码层面去保证。当然，也需要结合实际业务场景来看一致性的要求。就像上面例子，如果卖家修改了Name之后，是否需要在订单信息中同步更新呢？
     *
     * 数据同步
     *
     * 定时A库中的tab_a表和B库中tbl_b有关联，可以定时将指定的表做同步。当然，同步本来会对数据库带来一定的影响，需要性能影响和数据时效性中取得一个平衡。这样来避免复杂的跨库查询。笔者曾经在项目中是通过ETL工具来实施的。
     *
     * 系统层组装
     *
     * 在系统层面，通过调用不同模块的组件或者服务，获取到数据并进行字段拼装。说起来很容易，但实践起来可真没有这么简单，尤其是数据库设计上存在问题但又无法轻易调整的时候。
     *
     * 具体情况通常会比较复杂。下面笔者结合以往实际经验，并通过伪代码方式来描述。
     *
     * 简单的列表查询的情况
     */
    @Override
    @Transactional
    public void joinUser(){
        List<Map> list = userDao.joinUser();
        System.out.println(JSON.toJSON(list).toString());
    }

    @Override
    @Transactional
    public void queryUser(){
        List<Map> list = userDao.queryUser();
        System.out.println(JSON.toJSON(list).toString());
    }

    /**
     * 分页
     */
    @Override
    @Transactional
    public void queryUserLimit(){
        List<Map> list = userDao.queryUserLimit();
        System.out.println(JSON.toJSON(list).toString());
    }

    /**
     * 查询一个不分表的数据    只会查配置的第一个库   需要多实验
     */
    @Override
    @Transactional
    public void queryOrder(){
        List<Map> list = userDao.queryOrder();
        System.out.println(JSON.toJSON(list).toString());

        /*List<Map> list2 = userDao.queryOrder2();
        System.out.println(JSON.toJSON(list2).toString());*/
    }
}
