package com.authorized.client_authorized.config_openapi;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("user")
                .pathsToMatch("/api/user/**")  // Ajuste este caminho conforme necessário
                .pathsToExclude("/error/**")  // Exclui caminhos de erro
                .build();
    }
}
