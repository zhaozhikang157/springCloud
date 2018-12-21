package com.cloud.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "/login")
public interface LoginService {
    @GetMapping(value = "/login")
    public String login();
}
