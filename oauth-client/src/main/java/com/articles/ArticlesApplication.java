package com.articles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@EnableGlobalMethodSecurity(securedEnabled = true)
 public class ArticlesApplication  {
	    
	public static void main(String[] args) {
		SpringApplication.run(ArticlesApplication.class, args);
	}

}
