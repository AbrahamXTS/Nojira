package com.dinamitaexplosivainsana.nojira.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.*;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Nojira")
                .version("v0.0.1")
                .description("Nojira is an MVC application that includes the functionalities of Jira, but without being Jira.")
                .license(new License()
                        .name("Github Repository")
                        .url("https://github.com/AbrahamXTS/Nojira")
                )
        );
    }
}