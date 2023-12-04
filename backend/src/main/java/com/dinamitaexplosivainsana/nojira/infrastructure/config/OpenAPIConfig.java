package com.dinamitaexplosivainsana.nojira.infrastructure.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.BEARER_AUTHENTICATION_SCHEME_NAME;
/**
 * This class provides configuration for the OpenAPI documentation of the application.
 * It uses the @Configuration annotation to indicate that it is a source of bean definitions.
 * The @SecurityScheme annotation is used to define the security scheme for the API.
 */
@Configuration
@SecurityScheme(name = BEARER_AUTHENTICATION_SCHEME_NAME, type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class OpenAPIConfig {
/**
     * Configures the OpenAPI bean.
     *
     * @return An instance of OpenAPI with the custom information.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Nojira")
                        .version("v0.0.1")
                        .description("Nojira is an application that includes the functionalities of Jira, but without being Jira.")
                        .license(new License()
                                .name("Github Repository")
                                .url("https://github.com/AbrahamXTS/Nojira")
                        )
                );
    }
}