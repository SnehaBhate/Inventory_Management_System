package com.InventoryManagement.Services;

public interface EmailService {
	
    void sendVerificationEmail(String to, String verificationToken);
}