package com.b3backoffice.infra.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        // Swagger 자동 문서화 관련 Bean 재정의
        // - title, description, version 설정
        // - Authorize 사용할 수 있도록 수정
        .addSecurityItem(
            SecurityRequirement()
                .addList("Bearer Authentication")
                .addList("Refresh Token") // 참고: https://velog.io/@seulpace/Swagger-Springdoc-access-token-refresh-token-설정
        )
        .components(
            Components()
                .addSecuritySchemes(
                    "Bearer Authentication",
                    SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("Bearer")
                        .bearerFormat("JWT")
                        .`in`(SecurityScheme.In.HEADER)
                        .name("Authorization")
                ).addSecuritySchemes(
                    "Refresh Token",
                    SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .`in`(SecurityScheme.In.HEADER)
                        .name("Refresh Token")
                )
        )
        .info(
            Info()
                .title("b3backoffice project")
                .description("B-3 Back Office Project API schema")
                .version("1.0.0")
        )
}
