package com.cloud.feign.hystric;

import org.springframework.stereotype.Component;

@Component
public class LoginServiceHystric_1 implements LoginRpc_1{

    @Override
    public String loginFeign() {
        throw new RuntimeException();
//        return "断了断了";
    }
}
