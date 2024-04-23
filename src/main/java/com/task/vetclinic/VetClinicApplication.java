package com.task.vetclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VetClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetClinicApplication.class, args);
	}


}
