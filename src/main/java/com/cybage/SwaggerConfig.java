package com.cybage;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	//Initializing swagger related configurations with a method 
//	@Bean
//	public Docket swaggerApiConfig() {
//	 	return new Docket(DocumentationType.SWAGGER_2)
//	 			.select()
//	 			.paths(PathSelectors.any())
// 			    .apis(RequestHandlerSelectors.basePackage("com.cybage"))
//	 			.build();
//	}
	
	 @Bean
	    public Docket api() {
	        return 
	                new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.any())    //selecting handler
	                .paths(PathSelectors.any())                //selecting request mapping
	                .build();        
	    }

}
