package com.akshansh.urlshorter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class UrlShorter {

	public static void main(String[] args) {
		SpringApplication.run(UrlShorter.class, args);
	}

}
