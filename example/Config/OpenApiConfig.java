package com.example.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
public class OpenApiConfig {

    @Bean
    public OpenAPI logisticsOpenAPI() {

        return new OpenAPI()
                .components(new Components())
                .info(
                    new Info()
                        .title("Logistics Management System API")
                        .description("REST API for Logistics Management System")
                        .version("1.0")
                );
    }
}