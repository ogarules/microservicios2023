package com.example.demo;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.demo.controllers"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(new ApiInfo("Greeting API", "API para adminsitrar saludos", "1.0.0", "https://spring.io", new Contact("OGA", "https://spring.io", "oscar.garcia@mariachi.io"), "Apache 2.0", "https://spring.io", Collections.emptyList()));
    }
}
