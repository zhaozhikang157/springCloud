package com.sharding.second.service;


public interface UserService2 {
    void insertUser() throws Exception;
    void updateUser();
    void delUser();
    void delAllUser();
    void queryUser();
    void queryUserLimit();
    void joinUser();
    void queryOrder();
}
