package com.cloud.feign.hystric;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "eureka-client-1",fallback = LoginServiceHystric_1.class)
@Component
public interface LoginRpc_1 {
    @GetMapping(value = "/login_1/login_1")
    public String loginFeign();
}
