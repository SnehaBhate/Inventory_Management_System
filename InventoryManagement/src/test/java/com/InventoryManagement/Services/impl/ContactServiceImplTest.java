package com.InventoryManagement.Services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.InventoryManagement.Services.ContactService;
import com.InventoryManagement.entities.Contact;
import com.InventoryManagement.repository.ContactRepository;

public class ContactServiceImplTest {

	@Mock
	private ContactRepository contactRepository;
	
	private ContactService contactService; 
	
	AutoCloseable autocloseable;
	
	Contact contact;
	Contact contactTwo = new Contact();
	
	@BeforeEach
	void setUp()
	{
		autocloseable = MockitoAnnotations.openMocks(this);
		
		contactService = new ContactServiceImpl(contactRepository);
		contact = new Contact(1, "Himanshi Gupta", "himanshigupta898@gmail.com", "Hello There");
	    contactTwo.setContactId(3);
	    contactTwo.setContactName("Himanshi Gupta");
	    contactTwo.setContactEmail("guptahimanshi646@gmail.com");
	    contactTwo.setMessage("Hi");
	    
	}
	
	@AfterEach
	void tearDowm() throws Exception
	{
		autocloseable.close();
	}
	
	@Test
	void testSaveContact()
	{
		// Create a mock object of the Contact class
//		mock(Contact.class);
		
		// Create a mock object of the ContactRepository class
//		mock(ContactRepository.class);
		
		// Define the behavior of the mock object
		when(contactRepository.save(contact)).thenReturn(contact);
		
		// Perform assertions
		assertThat(contactService.saveContact(contact)).isEqualTo(contact);
		
		// Define the behavior of the mock object
		when(contactRepository.save(contactTwo)).thenReturn(contactTwo);
		
		// Perform assertions 
		assertThat(contactService.saveContact(contactTwo)).isEqualTo(contactTwo);
	}

	
}
