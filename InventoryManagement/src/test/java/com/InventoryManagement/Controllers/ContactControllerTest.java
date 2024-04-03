package com.InventoryManagement.Controllers;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.InventoryManagement.Services.ContactService;
import com.InventoryManagement.entities.Contact;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ContactService contactService;
	
	Contact contactOne = new Contact(1, "Himanshi Gupta", "himanshigupta898@gmail.com", "JAVA");
	
	@BeforeEach
	void setUp()
	{
		
	}
	 
	@AfterEach
	void tearDown() 
	{
		
	}
	
	@Test
	void testSaveContact() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		
		String contactJson = ow.writeValueAsString(contactOne);
		
		when(contactService.saveContact(contactOne)).thenReturn(contactOne);
		
		mockMvc.perform(post("/api/saveContact")
				.contentType(MediaType.APPLICATION_JSON)
				.content(contactJson))
				.andExpect(status().isCreated());
	}
}