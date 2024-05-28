package com.bhuvnesh.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice RES API Documentation!!",
				description = "Bhuvi Bank Account Microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Bhuvnesh",
						email = "bhuvnesh@gamil.com"
						),
				license = @License(
						name = "Apache 2.0",
						url = "http://www.bhuvnesh.com"
						)
				),
		externalDocs = @ExternalDocumentation(
				description = "bhuvi bank microservice REST API Documentation",
				url = "http://www.bhuvnesh.com"
				)
		)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
		
	}

}
