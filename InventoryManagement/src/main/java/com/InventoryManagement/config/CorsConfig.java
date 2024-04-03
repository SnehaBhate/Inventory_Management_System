//This Configuration is used to control that which domains are allowed to access our APIs.
package com.InventoryManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
   
	 @Bean
	 public WebMvcConfigurer corsConfigurer() {
	     
		 return new WebMvcConfigurer() {
	         
	    	 @Override
	         public void addCorsMappings(CorsRegistry registry) {
	             registry.addMapping("/api/**")
	             .allowedOrigins("http://localhost:3000") 
	             .allowedMethods("GET", "POST", "PUT","OPTIONS")
	             .allowedHeaders("*")
	             .allowCredentials(true);
	         }
	      };
	 }
}