package com.sharding.second;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.sharding.second.dao")
@ComponentScan(basePackages = {"com.sharding.*"})
public class ShardApplication2 {

	public static void main(String[] args) {
		SpringApplication.run(ShardApplication2.class, args);
	}

}

