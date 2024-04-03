package com.InventoryManagement.Controllers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import com.InventoryManagement.entities.User;
import com.InventoryManagement.repository.UserRepo;

@SpringBootTest
@AutoConfigureMockMvc
public class VerificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepo userRepo;

    @Test
    void testVerifyUser_ValidUser() throws Exception {
        String email = "himanshigupta898@gmail.com";
        String token = "12345";

        // Create a mock user
        User user = mock(User.class);

        // Configure the mock behavior
        when(userRepo.findByEmailAndVerificationToken(email, token)).thenReturn(user);

        // Perform the request and assert the response
        mockMvc.perform(get("/verify")
                .param("email", email)
                .param("token", token))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost:3000/login?verified=true"));

        // Verify that the user was saved with null verification token
        verify(user).setVerificationToken(null);
        verify(userRepo).save(user);
    }

    @Test
    void testVerifyUser_InvalidUser() throws Exception {
        String email = "himanshigupta898@gmail.com";
        String token = "12345";

        // Configure the mock behavior to return null for the user
        when(userRepo.findByEmailAndVerificationToken(anyString(), anyString())).thenReturn(null);

        // Perform the request and assert the response
        mockMvc.perform(get("/verify")
                .param("email", email)
                .param("token", token))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost:3000/login?verified=false"));

        // Verify that no interactions occurred with userRepo
        verify(userRepo).findByEmailAndVerificationToken(email, token);
        verifyNoMoreInteractions(userRepo);
    }
}
