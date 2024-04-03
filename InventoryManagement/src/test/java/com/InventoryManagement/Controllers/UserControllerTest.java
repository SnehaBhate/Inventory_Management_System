package com.InventoryManagement.Controllers;


import com.InventoryManagement.Payloads.Userdatatransfer;
import com.InventoryManagement.Services.EmailService;
import com.InventoryManagement.Services.UserService;
import com.InventoryManagement.entities.User;
import com.InventoryManagement.repository.UserRepo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepo userRepo;
    
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private EmailService emailService;
      
    @InjectMocks
    private UserController userController;
    
    private User dummyUser;
    private User user = new User(3, "Krishna Gupta", "krishna2213@gmail.com", "65746", "Fi*76t5r", "Indore", "8956478758", "employee");
    private Userdatatransfer expectedUser = new Userdatatransfer(2, "Krishna Gupta", "krishna2213@gmail.com", "Kr*isg654", "9644737388", "Indore", "employee");
    private Userdatatransfer expectedUserData;

	List<Userdatatransfer> userList = new ArrayList<>();
	
	@BeforeEach
	void setUp() throws Exception {
		dummyUser = new User();
        dummyUser.setId(1);
        dummyUser.setName("Himanshi Gupta");
        dummyUser.setEmail("himanshigupta898@gmail.com");
        dummyUser.setPassword("Hi*y7t6s2");
        dummyUser.setPhone("9755115014");
        dummyUser.setAddress("Rajpur");
        dummyUser.setAccountType("employee");
        dummyUser.setVerificationToken("12345");
        
        assertThat(dummyUser.getVerificationToken()).isEqualTo("12345");
        
        expectedUserData = new Userdatatransfer();
        expectedUserData.setId(dummyUser.getId());
        expectedUserData.setName(dummyUser.getName());
        expectedUserData.setEmail(dummyUser.getEmail());
        expectedUserData.setPhone(dummyUser.getPhone());
        expectedUserData.setPassword(dummyUser.getPassword());
        expectedUserData.setAddress(dummyUser.getAddress());
        expectedUserData.setAccountType(dummyUser.getAccountType());
        
        
        expectedUser.setId(user.getId());
        expectedUser.setName(user.getName());
        expectedUser.setEmail(user.getEmail());
        expectedUser.setPhone(user.getPhone());
        expectedUser.setPassword(user.getPassword());
        expectedUser.setAddress(user.getAddress());
        expectedUser.setAccountType(user.getAccountType());
        
        
        userList.add(expectedUserData);
        
        when(userService.getUserById(dummyUser.getId())).thenReturn(expectedUserData);
        MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
		dummyUser = null;
        expectedUserData = null;
        user = null;
        expectedUser = null;
	}

	@Test
    void testGetUser() throws Exception {
        
		// Perform the GET request to the getUser end point
		
		when(userService.getUserById(1)).thenReturn(expectedUserData);
		
		mockMvc.perform(get("/api/1"))
		       .andDo(print()) 
		       .andExpect(status().isOk());
		
		when(userService.getUserById(2)).thenReturn(expectedUser);
		
		mockMvc.perform(get("/api/2"))
               .andDo(print())
               .andExpect(status().isOk());
	}
	
	@Test
	void testGetAllUsers() throws Exception {
		
		when(userService.getAllUsers()).thenReturn(userList);
		
		mockMvc.perform(get("/api/"))
               .andDo(print())
               .andExpect(status().isOk());
	}
	
	@Test
	void testDeleteUser() throws Exception {
		
		int userId = 1;
	    
	    doNothing().when(userService).deleteUser(userId);
	    
	    mockMvc.perform(delete("/api/deleteuser/{id}", userId))
	           .andDo(print())
	           .andExpect(status().isOk());
	        
	    verify(userService, times(1)).deleteUser(userId);
	}


    @Test
    void testLog_Credentials() throws IOException {
        // Mock input data
        Map<String, String> request = new HashMap<>();
        request.put("email", "john@gmail.com");
        request.put("password", "password");
        request.put("accountType", "employee");

        // Mock user data
        User user = new User();
        user.setName("John");
        user.setAccountType("employee");
        user.setAddress("123 Street");
        user.setEmail("john@gmail.com");
        user.setId(1);
        user.setPassword("hashedPassword");
        user.setPhone("9234567890");

        // Mock user retrieval from userRepo
        when(userRepo.findByEmail("john@gmail.com")).thenReturn(user);
        when(userRepo.findByEmail("ash@gmail.com")).thenReturn(null);

        // Mock password comparison result
        when(passwordEncoder.matches("password", "hashedPassword")).thenReturn(true);
        when(passwordEncoder.matches("password", "wrongHashedPassword")).thenReturn(false);

        // Perform the log operation with valid credentials
        ResponseEntity<Userdatatransfer> responseEntity = userController.log(request, null);

        // Verify the response for valid credentials
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Userdatatransfer responseDto = responseEntity.getBody();
    
        assertEquals("employee", responseDto.getAccountType());
        assertEquals("123 Street", responseDto.getAddress());
        assertEquals("john@gmail.com", responseDto.getEmail());
        assertEquals(1, responseDto.getId());
        assertEquals("John", responseDto.getName());
        assertEquals("hashedPassword", responseDto.getPassword());
        assertEquals("9234567890", responseDto.getPhone());
        
        // verify that the findByEmail method of the userRepo mock was called with the argument "john@gmail.com"
        verify(userRepo).findByEmail("john@gmail.com");
        verify(passwordEncoder).matches("password", "hashedPassword");

        // Perform the log operation with invalid credentials (invalid email)
        request.put("email", "test@example.com");
        responseEntity = userController.log(request, null);

        // Verify the response for invalid email
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        responseDto = responseEntity.getBody();
        verify(userRepo).findByEmail("test@example.com");

        // Update email to the valid one and perform the log operation with invalid credentials (invalid password)
        request.put("email", "john@gmail.com");
        request.put("password", "wrongPassword");
        responseEntity = userController.log(request, null);

        // Verify the response for invalid password
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        responseDto = responseEntity.getBody();
        
//        This verification is performed to ensure that the password comparison was performed with the expected values during the test.
        verify(passwordEncoder).matches("wrongPassword", "hashedPassword");
      }
}