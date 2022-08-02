package com.example.herokudemospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HerokuDemoSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HerokuDemoSpringBootApplication.class, args);
	}

}
