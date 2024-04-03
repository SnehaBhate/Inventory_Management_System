//package com.InventoryManagement.config;
//
//public class MailConfig {
//
//}
package com.InventoryManagement.config;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {


    @Bean
    JavaMailSender javaMailSender() {
    	
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("aradhyadimri2000@gmail.com");
        mailSender.setPassword("xlmqxgdwdhyouogh");
        
        Properties props = mailSender.getJavaMailProperties();
        
        //protocol to be used for email transport
        props.put("mail.transport.protocol", "smtp");
        
        //SMTP authentication is required
        props.put("mail.smtp.auth", "true");
        
        //Secure connection is used
        //Enable the STARTTLS protocol for secure communication
        props.put("mail.smtp.starttls.enable", "true");
        
        //Debugging is enabled for the email sending process
        props.put("mail.debug", "true");

        return mailSender;
    }
}