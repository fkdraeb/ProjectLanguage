package com.upskill.LanguageIdentifier;

import com.upskill.LanguageIdentifier.entity.TaskDTO;
import com.upskill.LanguageIdentifier.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Base64;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableFeignClients
@Profile("!test")
public class LanguageIdentifier implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(LanguageIdentifier.class, args);
	}

	@Value("${spring.application.name}")
	String nomemicroservico;
	@Value("${server.port}")
	Integer porta;

	@Value("${taskid}")
	Integer taskId;
	@Value("${url}")
	String url;

	@Autowired
	@Qualifier("bean.porta")
	ServletWebServerApplicationContext socket;

	@Autowired
	TaskService taskService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println( taskId +"   " + url);
		byte[] decodedBytes = Base64.getUrlDecoder().decode(url);
		String decodedURL = new String(decodedBytes);
		System.out.println("Conseguiu decodificar url: " + url + " — url descodificado: " + decodedURL);
		taskService.runTask(new TaskDTO(taskId, decodedURL));
		suicideMS();
	}

	public void suicideMS() throws UnknownHostException {
			String uribase = InetAddress.getLocalHost().getHostAddress();
			int porta = socket.getWebServer().getPort();
			String uri = "http://" + uribase + ":" + porta + "/actuator/shutdown";
			System.out.println("Task URI: http://" + uribase + ":" + porta);
			System.out.println("Task URI Shutdown: " + uri);
			System.out.println("Shutting down");

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>(null, headers);
			ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
			System.out.println("Shutdown Status Code: " + result.getStatusCode());

	}
}
