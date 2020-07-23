package com.sharding.first;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.sharding.first.dao")
@ComponentScan(basePackages = {"com.sharding.*"})
public class ShardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardApplication.class, args);
	}

}

