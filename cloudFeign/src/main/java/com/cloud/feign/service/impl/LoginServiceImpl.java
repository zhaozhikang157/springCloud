package com.cloud.feign.service.impl;

import com.cloud.feign.dao.LoginDao;
import com.cloud.feign.hystric.LoginRpc;
import com.cloud.feign.hystric.LoginRpc_1;
import com.cloud.feign.service.LoginService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;
    @Autowired
    private LoginRpc loginRpc;
    @Autowired
    private LoginRpc_1 loginRpc_1;

    @Override
    @Transactional
    @LcnTransaction
    public String login() {
        //loginDao.login();
        loginRpc.loginFeign();
        loginRpc_1.loginFeign();
        throw new RuntimeException();
    }
}
