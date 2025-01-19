package com.junghwan.springbootdeveloper.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@OpenAPIDefinition(info = @Info(title = "wa-r-r.com", version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi chatOpenApi(){
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("wa-r-r OPEN API v1")
                .pathsToMatch(paths)
                .build();
    }

}
