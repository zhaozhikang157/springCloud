package com.cloud.client.service;

import com.cloud.client.dao.LoginDao;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl {

    @Autowired
    private LoginDao loginDao;

    //如果其他bean调用这个方法,在其他bean中声明事务,那就用事务.如果其他bean没有声明事务,那就不用事务
//    @TxcTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional
    @LcnTransaction
    public void login() {
        loginDao.login();
    }
}
