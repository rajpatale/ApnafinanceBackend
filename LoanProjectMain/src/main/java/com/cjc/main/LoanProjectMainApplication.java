package com.cjc.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LoanProjectMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanProjectMainApplication.class, args);
	}
	
	@Bean
	public RestTemplate rs()
	{
		RestTemplate rt= new RestTemplate();
		return rt;
	}

}
