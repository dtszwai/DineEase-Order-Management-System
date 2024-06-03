package org.order.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This configuration class is responsible for setting up the Swagger UI and OpenAPI documentation.
 * It provides methods to create a new GroupedOpenApi and a custom OpenAPI instance.
 */
@Configuration
public class SwaggerConfig {

  /**
   * Creates a new GroupedOpenApi instance that includes all API paths.
   *
   * @return the newly created GroupedOpenApi instance.
   */
  @Bean
  GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder()
        .group("public-api")
        .pathsToMatch("/**")
        .build();
  }

  /**
   * Creates a new OpenAPI instance with custom information and security scheme. The security scheme
   * is set to use the HTTP bearer format for authorization.
   *
   * @return the newly created OpenAPI instance.
   */
  @Bean
  OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("Self-service Ordering API").version("1.0"))
        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
        .components(
            new Components()
                .addSecuritySchemes("bearerAuth",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .in(SecurityScheme.In.HEADER)
                        .name("Authorization")
                )
        );
  }
}