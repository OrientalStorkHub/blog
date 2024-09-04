package com.orientalstorkhub.blog.common.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Service Name API")
                .description("API documentation for Service Name")
                .version("v1.0"))
                .components(new Components()
                        .addSecuritySchemes("access-token", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .name("access-token")
                                .in(SecurityScheme.In.HEADER)))
                .security(List.of(new SecurityRequirement().addList("access-token")));    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/**")
                .build();
    }



}
