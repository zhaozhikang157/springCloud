package com.cloud.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cloud"})
@RestController
@EnableEurekaClient
public class ConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
		c.setIgnoreUnresolvablePlaceholders(true);
		return c;
	}
}

