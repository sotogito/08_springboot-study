package com.sotogito.rest.section04.swagger.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ## Swagger UI
 * 1. API 문서를 자동으로 만들어주는 라이브러리
 * 2. 초기 Swagger는 API를 설계하고 빌드하고 문서화하는데만 사용
 * 3. 현재 Swagger는 API문서화, 테스트 기능 등 제공
 * 4. SpringDoc OpenAPI Starter 디펜던시 필요
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Spring Boot REST API")
                .description("REST API 기반의 회원서비스")
                .version("1.0.0");
    }

    /// http://localhost:8080/swagger-ui/index.html

}
