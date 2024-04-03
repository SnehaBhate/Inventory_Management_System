package com.InventoryManagement.Services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.InventoryManagement.Exceptions.ResourceNotFoundException;
import com.InventoryManagement.Payloads.Userdatatransfer;
import com.InventoryManagement.entities.User;
import com.InventoryManagement.repository.UserRepo;


public class UserServiceImplTest {
	private UserServiceImpl userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(passwordEncoder, userRepo);
    }
    
    @Test
    public void testCreateUser() {
        // Arrange
        Userdatatransfer userDto = new Userdatatransfer();
        
        userDto.setName("Himanshi Gupta");
	    userDto.setEmail("himanshigupta898@gmail.com");
		userDto.setPassword("Hi*y7t6r4");
		userDto.setPhone("9755115014");
		userDto.setAddress("Indore");
		userDto.setAccountType("employee");

        User user = new User();
        user.setEmail("himanshigupta898@gmail.com");
        user.setPassword("Hi*y7t6r4");
        
        when(userRepo.findByEmail("himanshigupta898@gmail.com")).thenReturn(null);
       
        when(passwordEncoder.encode("Hi*y7t6r4")).thenReturn("encodedPassword");
        when(userRepo.save(any(User.class))).thenReturn(user);

        // Act
        Userdatatransfer result = userService.CreateUser(userDto);

        // Assert
        verify(userRepo, times(1)).findByEmail("himanshigupta898@gmail.com");
        verify(passwordEncoder, times(1)).encode("Hi*y7t6r4");
        verify(userRepo, times(1)).save(any(User.class));
        
        when(userRepo.findByEmail("himanshigupta898@gmail.com")).thenThrow(new IllegalArgumentException("Email already exists!"));

        // Add additional assertions as needed to verify the result
        // Assertion for the email
        assertEquals("himanshigupta898@gmail.com", result.getEmail());

    }
    
    
    @Test 
    void testgetAllUsers() {
    	
    	 // Arrange
        User user1 = new User();
        user1.setId(1);
        user1.setName("Sneha Bhate");
	    user1.setEmail("snehabhate001@gmail.com");
		user1.setPassword("Aa@shi01");
		user1.setPhone("7049729232");
		user1.setAddress("Indore");
		user1.setAccountType("employee");
        

        User user2 = new User();
        user2.setId(2);
        user2.setName("Himanshi Gupta");
	    user2.setEmail("himanshigupta898@gmail.com");
		user2.setPassword("Hi@shi01");
		user2.setPhone("7049729231");
		user2.setAddress("Indore");
		user2.setAccountType("employee");
    	
		List<User> users = Arrays.asList(user1, user2);
		
		when(userRepo.findAll()).thenReturn(users);

        userService = new UserServiceImpl(null, userRepo);
        
        // Act
        List<Userdatatransfer> result = userService.getAllUsers();

        // Assert
        verify(userRepo, times(1)).findAll();

        // Assertions
        assertEquals(2, result.size());
        assertEquals("Sneha Bhate", result.get(0).getName());
        assertEquals("Himanshi Gupta", result.get(1).getName());
    }
    
    @Test 
    void testgetUserById() {
    	// Arrange
        User user = new User();
        user.setId(1);
        user.setName("Himanshi Gupta");

        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        
        when(userRepo.findById(2)).thenThrow(new ResourceNotFoundException("User", "id", 2));
        
        userService = new UserServiceImpl(null, userRepo);

        // Act
        Userdatatransfer result = userService.getUserById(1);

        // Assert
        verify(userRepo, times(1)).findById(1);
        // verify(userRepo, times(1)).findById(2);

        assertEquals("Himanshi Gupta", result.getName());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.getUserById(2);
        });
    }   

    @Test
    void  deleteUser() {
    	
    	Integer userId = 1;
        User user = new User();
        user.setId(userId);

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepo, times(1)).delete(user);
    }


    @Test
    public void testGetUserByEmail_UserExists() {
   
	    // Arrange
        String email = "himanshigupta898@gmail.com";
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setEmail(email);
        existingUser.setName("Himanshi Gupta"); 
        
        when(userRepo.findByEmail(email)).thenReturn(existingUser);

        userService = new UserServiceImpl(null, userRepo);

        // Act
        Userdatatransfer result = userService.getUserByEmail(email);

        // Assert
        verify(userRepo, times(1)).findByEmail(email);
        
        assertEquals(existingUser.getId(), result.getId());
        assertEquals(existingUser.getName(), result.getName());
        assertEquals(existingUser.getEmail(), result.getEmail());
    }

    @Test
    public void testGetUserByEmail_UserNotExists() {
        // Arrange
        String email = "himanshigupta898@gmail.com";

        when(userRepo.findByEmail(email)).thenReturn(null);

        userService = new UserServiceImpl(null, userRepo);

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.getUserByEmail(email);
        });

        verify(userRepo, times(1)).findByEmail(email);
    }

    @Test
    public void testDtoToUser() {
        // Arrange
        Userdatatransfer userDto = new Userdatatransfer();
        userDto.setId(1);
        userDto.setName("Himanshi Gupta");
        userDto.setEmail("himanshigupta898@gmail.com");
        userDto.setPassword("Hi8y76r5");
        userDto.setPhone("9755115014");
        userDto.setAddress("Indore");
        userDto.setAccountType("employee");

        UserServiceImpl userService = new UserServiceImpl(null, null);

        // Act
        User result = userService.dtoToUser(userDto);

        // Assert assertEquals(userDto.getId(), result.getId());
        assertEquals(userDto.getName(), result.getName());
        assertEquals(userDto.getEmail(), result.getEmail());
        assertEquals(userDto.getPassword(), result.getPassword());
        assertEquals(userDto.getPhone(), result.getPhone());
        assertEquals(userDto.getAccountType(), result.getAccountType());
        assertEquals(userDto.getAddress(), result.getAddress());
    }

    @Test
    public void testUsertoDto() {
        // Arrange
        User user = new User();
        user.setId(1);
        user.setName("Himanshi Gupta");
        user.setEmail("himanshigupta898@gmail.com");
        user.setPassword("Hi*y7t6r");
        user.setPhone("9755115014");
        user.setAddress("Indore");
        user.setAccountType("employee");

        UserServiceImpl userService = new UserServiceImpl(null, null);

        // Act
        Userdatatransfer result = userService.usertoDto(user);

        // Assert assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getPassword(), result.getPassword());
        assertEquals(user.getPhone(), result.getPhone());
        assertEquals(user.getAccountType(), result.getAccountType());
        assertEquals(user.getAddress(), result.getAddress());
    }
}
    
