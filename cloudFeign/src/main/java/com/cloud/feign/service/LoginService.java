package com.cloud.feign.service;

import com.cloud.feign.hystric.LoginServiceHystric;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


public interface LoginService {
    public String login();
}
