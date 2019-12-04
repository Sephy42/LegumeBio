package com.formation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;


@SpringBootApplication
@Profile(value = "dev")
public class MonApplication {

	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MonApplication.class, args);
	}

}