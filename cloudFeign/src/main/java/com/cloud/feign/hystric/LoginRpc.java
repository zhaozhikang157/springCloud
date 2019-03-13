package com.cloud.feign.hystric;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "eureka-client",fallback = LoginServiceHystric.class)
@Component
public interface LoginRpc {
    @GetMapping(value = "/login/login")
    public String loginFeign();
}
