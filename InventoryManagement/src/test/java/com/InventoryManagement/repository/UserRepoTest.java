package com.InventoryManagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.InventoryManagement.entities.User;

@DataJpaTest
@AutoConfigureTestDatabase
public class UserRepoTest {
	
	@Autowired
	private UserRepo userRepo;
	
	User user;

	@BeforeEach
	void setUp()
	{
		user = new User();
		user.setId(1);
		user.setName("Himanshi Gupta");
		user.setEmail("himanshigupta898@gmail.com");
		user.setPassword("Hi*7y6t5");
		user.setPhone("9755115014");
		user.setAccountType("admin");
		user.setAddress("Rajpur");
		user.setVerificationToken("12345");
		
		userRepo.save(user);
	}
	
	@AfterEach
	void tearDown()
	{
		user = null;
		userRepo.deleteAll();
	}
	
	// Test case SUCCESS
//	@Test
//	void testFindById_found()
//	{
//		User newUser = new User();
//		newUser.setId(2);
//		newUser.setName("Himanshi Gupta");
//		newUser.setEmail("himanshigupta898@gmail.com");
//		newUser.setPassword("Hi*7y6t5");
//		newUser.setPhone("9755115014");
//		newUser.setAccountType("employee");
//		newUser.setAddress("Indore");
//		newUser.setVerificationToken("12345");//
//		
//		userRepo.save(newUser);
//        User myUser = userRepo.findById(2).get();
////		assertThat(myUser).isNotNull();
//		assertThat(newUser.getId()).isEqualTo(myUser.getId());
////		assertThat(user.getName()).isEqualTo(myUser.getName());
////		assertThat(user.getEmail()).isEqualTo(myUser.getEmail());
////		assertThat(user.getPassword()).isEqualTo(myUser.getPassword());
////		assertThat(user.getPhone()).isEqualTo(myUser.getPhone());
////		assertThat(user.getAccountType()).isEqualTo(myUser.getAccountType());
////		assertThat(user.getAddress()).isEqualTo(myUser.getAddress());
////		assertThat(user.getVerificationToken()).isEqualTo(myUser.getVerificationToken());
////		
//	}
	
	@Test
	void testFindByEmail_found()
	{
		// Perform the findByEmail operation
	    User foundUser = userRepo.findByEmail("himanshigupta898@gmail.com");
	    
	    // Assert that the foundUser is not null
	    assertThat(foundUser).isNotNull();
	    
	    // Assert that the email matches the expected value
	    assertThat(foundUser.getEmail()).isEqualTo("himanshigupta898@gmail.com");
	}
	
	@Test
	void testFindByEmailAndVerificationToken_found()
	{
		// Perform the findByEmailAndVerificationToken operation
	    User foundUser = userRepo.findByEmailAndVerificationToken("himanshigupta898@gmail.com", "12345");
	    
	    // Assert that the foundUser is not null
	    assertThat(foundUser).isNotNull();
	    // Assert that the email matches the expected value
	    assertThat(foundUser.getEmail()).isEqualTo("himanshigupta898@gmail.com");
	    // Assert that the verification token matches the expected value
	    assertThat(foundUser.getVerificationToken()).isEqualTo("12345");
	}
		
	// Test case Failure
	@Test
	void testFindById_notFound()
	{
		Optional<User> myUserOptional = userRepo.findById(25);
	    
	    // Assert that myUserOptional is empty (optional should not be present)
	    assertThat(myUserOptional).isEmpty();
		
	}
	
	@Test
	void testFindByEmail_notFound()
	{
	    // Perform the findByEmail operation for a non-existent email
	    User foundUser = userRepo.findByEmail("guptakrishna@gmail.com");
	    
	    // Assert that the foundUser is null
	    assertThat(foundUser).isNull();
	}
	
	@Test
	void testFindByEmailAndVerificationToken_notFound()
	{
	    // Perform the findByEmailAndVerificationToken operation with incorrect email and token
	    User foundUser = userRepo.findByEmailAndVerificationToken("snehabhate@gmail.com", "54321");
	    
	    // Assert that the foundUser is null
	    assertThat(foundUser).isNull();
	}
}
