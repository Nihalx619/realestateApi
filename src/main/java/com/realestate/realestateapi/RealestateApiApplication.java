package com.realestate.realestateapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RealestateApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealestateApiApplication.class, args);
	}

}
