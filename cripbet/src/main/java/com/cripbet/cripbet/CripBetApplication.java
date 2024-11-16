package com.cripbet.cripbet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CripBetApplication {

	public static void main(String[] args) {
		SpringApplication.run(CripBetApplication.class, args);
	}

}
