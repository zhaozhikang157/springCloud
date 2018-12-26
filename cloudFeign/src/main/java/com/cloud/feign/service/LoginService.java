package com.cloud.feign.service;

import com.cloud.feign.hystric.LoginServiceHystric;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "eureka-client",fallback = LoginServiceHystric.class)
public interface LoginService {
    @GetMapping(value = "/login/login")
    public String loginFeign();
}
