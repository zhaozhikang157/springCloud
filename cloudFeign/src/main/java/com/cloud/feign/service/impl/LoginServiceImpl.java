package com.cloud.feign.service.impl;

import com.cloud.feign.dao.LoginDao;
import com.cloud.feign.hystric.LoginRpc;
import com.cloud.feign.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;
    @Autowired
    private LoginRpc loginRpc;

    @Override
    @Transactional
    public String login() {
        loginDao.login();
        loginRpc.loginFeign();
        return "";
    }
}
