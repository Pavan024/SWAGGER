package com.wavelabs.demo;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootSwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSwaggerApplication.class, args);
	}
	
	@Bean
	public Docket swaggerConfiguration() {
		//return a prepared Docket Instance
		return new Docket(DocumentationType.SWAGGER_2)
				.select() //ApiSelectorBuilder
				.paths(PathSelectors.ant("/api/*"))
				.apis(RequestHandlerSelectors.basePackage("com.wavelabs"))
				.build()
				.apiInfo(apiDetails());
		
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Address Book API", 
				"Sample Api For Swagger Tutorials",
				"1.0", 
				"Free to use", 
				new springfox.documentation.service.Contact("Pavan", "http://", "pavan@gmail.com"), 
				"API Licence", 
				"http://",
				Collections.emptyList());
		
	}
}
