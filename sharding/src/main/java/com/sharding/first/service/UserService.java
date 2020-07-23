package com.sharding.first.service;


public interface UserService {
    void insertUser() throws Exception;
    void updateUser();
    void delUser();
    void delAllUser();
    void joinUser();
    void queryUser();
    void queryUserLimit();
    void queryOrder();
}
