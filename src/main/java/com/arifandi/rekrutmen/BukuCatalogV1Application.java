package com.arifandi.rekrutmen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

@EnableAspectJAutoProxy
@SpringBootApplication
public class BukuCatalogV1Application {

	public static void main(String[] args) {
		SpringApplication.run(BukuCatalogV1Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
