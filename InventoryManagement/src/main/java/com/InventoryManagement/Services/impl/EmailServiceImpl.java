//package com.InventoryManagement.Services.impl;
//
//public class EmailServiceImpl {
//
//}
package com.InventoryManagement.Services.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.InventoryManagement.Services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	
    private final JavaMailSender mailSender;
    
    //constructor injection
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    @Override
    public void sendVerificationEmail(String to, String verificationToken) {
    	
    	// Create a new instance of SimpleMailMessage
        SimpleMailMessage message = new SimpleMailMessage();
        
        // Set the recipient's email address
        message.setTo(to);
        
        // Set the subject of the email
        message.setSubject("Email Verification");
        
        // Set the text content of the email(Verification Token = Verification Link)
        message.setText("Please click the link to verify your email: " + verificationToken);
        
        mailSender.send(message);
    }
}