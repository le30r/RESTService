package rsreu.microchad.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfiguration {
    private ApiInfo apiInfo() {
        return new ApiInfo("HR App",
                "API для управления персоналом.",
                "v1.0",
                "terms",
                new Contact("Microchad", "rsreu.ru", "le30r@ya.ru"),
                "License of API",
                "API license URL",
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
}

