package com.cloud.feign.hystric;

import com.cloud.feign.service.LoginService;
import org.springframework.stereotype.Component;

@Component
public class LoginServiceHystric implements LoginRpc{

    @Override
    public String loginFeign() {
        return "断了断了";
    }
}
