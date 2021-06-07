package com.pruebatecnica.heroes.swagger;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer{
	
	 @Bean
	    public Docket api(){
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .securityContexts(Arrays.asList(securityContext()))
	                .securitySchemes(Arrays.asList(apiKey()))
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.pruebatecnica.heroes"))
	                .paths(PathSelectors.any())
	                .build();
	    }

	    private ApiKey apiKey(){
	        return new ApiKey("JWT", "Authorization", "header");
	    }

	    private SecurityContext securityContext(){
	        return SecurityContext.builder().securityReferences(defaultAuth()).build();
	    }

	    private List<SecurityReference> defaultAuth(){
	        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
	        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	        authorizationScopes[0] = authorizationScope;
	        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	    }

	    private ApiInfo apiInfo(){
	        return new ApiInfo(
	                "Tutorial JWT",
	                "Descripción",
	                "2.0",
	                "Términos y Condiciones",
	                "micorreo@gmail.com",
	                "Licencia",
	                "www.licencia.com"
	        );
	    }

}
