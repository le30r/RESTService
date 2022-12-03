package rsreu.microchad.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfiguration {
    private ApiInfo apiInfo() {
        return new ApiInfo("Управление персоналом",
                "API для управления персоналом.",
                "v1.0",
                "/#",
                new Contact("Microchad", "https://rsreu.ru", "le30r@ya.ru"),
                "Apache 2.0",
                "/#",
                Collections.emptyList());
    }

    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api-v1.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage("rsreu.microchad.service.contollers"))
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket apiV2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api-v2.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage("rsreu.microchad.service.contollers"))
                .paths(PathSelectors.ant("/api/v2/**"))
                .build()
                .apiInfo(apiInfo());
    }
}

