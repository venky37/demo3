package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@SpringBootApplication
@RestController
@RequestMapping("/assignments")
public class ProjectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectServiceApplication.class, args);
	}

	// This client calls Service 1
	private final RestClient restClient = RestClient.create();

	@GetMapping("/allocate/{empId}/to/{projectName}")
	public String allocate(@PathVariable Long empId, @PathVariable String projectName) {
		// Calling the other microservice!
		Object employee = restClient.get().uri("http://localhost:8081/employees/" + empId).retrieve()
				.body(Object.class);

		return "Successfully allocated Project [" + projectName + "] to Employee: " + employee;
	}

	@GetMapping("/")
	public String project2() {
		return "project2";
	}
}