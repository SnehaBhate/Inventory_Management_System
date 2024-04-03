//Main Class
package com.InventoryManagement;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryManagementApplication {

//	private static final Logger logger = (Logger) LoggerFactory.getLogger(InventoryManagementApplication.class);
	
	public static void main(String[] args) {
//			
//		logger.info("This is a info message");
//		logger.warn("This is a warn message");
//		logger.error("This is a debug message");
		SpringApplication.run(InventoryManagementApplication.class, args);
	}
}
