package com.example.Hospital_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class HospitalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalProjectApplication.class, args);
	}

}
