package com.vtuber.wafuseph_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

// @SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WafusephBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WafusephBackendApplication.class, args);
	}

}
