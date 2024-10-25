package com.cst.asynchronous.openapiconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
@OpenAPIDefinition(
	    servers = {
	        @Server(url = "http://localhost:9002", description = "Local server"),
	        @Server(url = "https://cts.com", description = "Production server")
	    }
	)
@Configuration
//@EnableOpenApi
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Management API")
                        .version("1.0.0")
                        .description("This API allows you to manage users, including creating, retrieving, updating, and deleting user records.")
                        .contact(new Contact()
                                .name("Durgeshkumar Gupta")
                                .url("https://www.cognizant.com/us/en")
                                .email("dk@gmail.com")));
    }
}
