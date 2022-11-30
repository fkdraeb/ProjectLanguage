package com.upskill.TaskManager;

import org.apache.tika.exception.TikaException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class TaskManagerApplication {

	public static void main(String[] args) throws IOException, TikaException {
		SpringApplication.run(TaskManagerApplication.class, args);
	}
}
