package com.library.application.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Component
public class SwaggerConfig {
	
	  @Bean
	  public GroupedOpenApi publicApi() {
	      return GroupedOpenApi.builder()
	              .group("public-apis")
	              .pathsToExclude("/book/add", "/book/update") //excluding post and put APIs
	              .pathsToMatch("/book/**")
	              .build();
	  }
	
	 @Bean
	  public OpenAPI springShopOpenAPI() {
	      return new OpenAPI()
	              .info(new Info().title("Library Service API")
	              .description("Sample Library Application using Spring Boot")
	              .version("v1")
	              .license(new License().name("booklib1.0").url("http://springdoc.org")))
	              .externalDocs(new ExternalDocumentation()
	              .description("SpringShop Wiki Documentation")
	              .url("https://springshop.wiki.github.org/docs"));
	  }
	
	
}
