package com.sharding.second.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface UserDao2 {

    void insertUser(Map map);
    void updateUser(Map map);
    void delUser(Map map);
    void delAllUser(Map map);
    List<Map> queryUser();
    List<Map> queryUserLimit();
    List<Map> joinUser();
    List<Map> queryOrder();
    List<Map> queryOrder2();
}
