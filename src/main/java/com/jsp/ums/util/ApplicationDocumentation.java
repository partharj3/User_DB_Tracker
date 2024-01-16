package com.jsp.ums.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class ApplicationDocumentation {
	Info info() {
		return new Info()
				.title("User Management System API")
				.version("1.0v")
				.description("User Management System API is RESTful API build using with Spring-Boot and MYSQL")
				.contact(contact());
	}
	
	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().info(info());
	}
	
	Contact contact() {
		return new Contact()
			        .name("Parthasarathy Ramamoorthy")
			        .email("ramnagsarathy@gamil.com")
			        .url("https://www.google.com");
	}
	
//	@Bean
//	ModelMapper getModelMapper() {
//		return new ModelMapper();
//	}
}
