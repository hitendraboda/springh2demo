package com.boda.springh2demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {
	// Swagger config
		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.boda.springh2demo.controller"))
					.paths(PathSelectors.regex("/.*"))
					.build()
					.apiInfo(apiEndPointsInfo());
		}

		private ApiInfo apiEndPointsInfo() {
			return new ApiInfoBuilder()
					.title("SpringH2Demo")
					.description("SpringH2Demo REST API")					
					.version("1.0.0")
					.build();
		}
}
