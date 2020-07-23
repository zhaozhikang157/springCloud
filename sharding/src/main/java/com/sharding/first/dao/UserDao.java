package com.sharding.first.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface UserDao {

    void insertUser(Map map);
    void updateUser(Map map);
    void delUser(Map map);
    void delAllUser(Map map);
    List<Map> joinUser();
    List<Map> queryUser();
    List<Map> queryUserLimit();
    List<Map> queryOrder();
    List<Map> queryOrder2();
}
