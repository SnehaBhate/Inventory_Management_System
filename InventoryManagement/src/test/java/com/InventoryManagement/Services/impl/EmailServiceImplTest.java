package com.InventoryManagement.Services.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@ExtendWith(MockitoExtension.class)
public class EmailServiceImplTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Test
    void testSendVerificationEmail() {
        String to = "himanshigupta@gmail.com";
        String verificationToken = "12345";

        emailService.sendVerificationEmail(to, verificationToken);

        verify(mailSender).send(createExpectedMessage(to, verificationToken));
    }

    private SimpleMailMessage createExpectedMessage(String to, String verificationToken) {
        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setTo(to);
        expectedMessage.setSubject("Email Verification");
        expectedMessage.setText("Please click the link to verify your email: " + verificationToken);
        return expectedMessage;
    }
}
