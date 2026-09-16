package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LogisticsManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogisticsManagementSystemApplication.class, args);
	}

}
