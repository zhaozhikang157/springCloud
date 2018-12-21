package com.cloud.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "eurka-client")
public interface LoginService {
    @GetMapping(value = "/login/login")
    public String loginFeign();
}
