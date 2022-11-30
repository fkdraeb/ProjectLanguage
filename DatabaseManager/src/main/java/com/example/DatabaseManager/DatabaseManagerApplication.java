package com.example.DatabaseManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class DatabaseManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseManagerApplication.class, args);
	}

}
