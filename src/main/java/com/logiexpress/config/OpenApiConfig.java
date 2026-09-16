package com.logiexpress.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI logiExpressOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("LogiExpress API")
                .version("v1")
                .description("API REST para la gestión de envíos."));
    }
}
