package com.InventoryManagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.InventoryManagement.Services.EmailService;
import com.InventoryManagement.repository.UserRepo;

@SpringBootTest
class InventoryManagementApplicationTests {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EmailService emailService;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void repoTest() {
		//Name of class which object resides in userRepo
		String className = this.userRepo.getClass().getName();
		String packageName = this.userRepo.getClass().getPackageName();
		System.out.println(className);
		System.out.println(packageName);
	}
	
	@Test
	public void emailTest() {
		//Name of class which object resides in userRepo
		String className = this.emailService.getClass().getName();
		String packageName = this.emailService.getClass().getPackageName();
		System.out.println(className);
		System.out.println(packageName);
	}
}
