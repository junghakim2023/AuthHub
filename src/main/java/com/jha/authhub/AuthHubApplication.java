package com.jha.authhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:/device.properties")
@SpringBootApplication
public class AuthHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthHubApplication.class, args);
	}

}
