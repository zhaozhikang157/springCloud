package com.cloud.client.service;

import com.cloud.client.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl {

    @Autowired
    private LoginDao loginDao;

    @Transactional
    public void login() {
        loginDao.login();
        throw new RuntimeException();
    }
}
