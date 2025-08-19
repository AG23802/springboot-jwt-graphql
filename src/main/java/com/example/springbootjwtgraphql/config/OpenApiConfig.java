package com.example.springbootjwtgraphql.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi v1Api() {
        return GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/api/v1/**")
                .addOpenApiCustomizer(v1Customizer())
                .build();
    }

    @Bean
    public GroupedOpenApi v2Api() {
        return GroupedOpenApi.builder()
                .group("v2")
                .pathsToMatch("/api/v2/**")
                .addOpenApiCustomizer(v2Customizer())
                .build();
    }

    private OpenApiCustomizer v1Customizer() {
        return openApi -> openApi.info(new Info()
                .title("Demo API")
                .version("v1")
                .description("Version 1 of the API"));
    }

    private OpenApiCustomizer v2Customizer() {
        return openApi -> openApi.info(new Info()
                .title("Demo API")
                .version("v2")
                .description("Version 2 of the API"));
    }
}