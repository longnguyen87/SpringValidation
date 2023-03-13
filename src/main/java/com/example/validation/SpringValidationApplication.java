package com.example.validation;

import com.example.validation.model.Patient;
import com.example.validation.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringValidationApplication implements CommandLineRunner {
	@Autowired
	private PatientRepository patientRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringValidationApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// Create a couple patient to save to database

		Patient patient1 = Patient.builder()
				.age(10).firstName("Long")
				.middleName("Huu").lastName("Nguyen")
				.email("abd@gmail.com").build();
		Patient patient2 = Patient.builder()
				.age(20).firstName("Ha")
				.middleName("Huu").lastName("Nguyen")
				.email("abd1@gmail.com").build();
		System.out.println("Saving patient to local h2 database");
		patientRepository.save(patient1);
		patientRepository.save(patient2);
	}
}
