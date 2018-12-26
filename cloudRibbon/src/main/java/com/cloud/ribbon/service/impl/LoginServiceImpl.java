package com.cloud.ribbon.service.impl;

import com.cloud.ribbon.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String login() {
        return restTemplate.getForObject("http://eureka-client/login/login",String.class);
    }
}
