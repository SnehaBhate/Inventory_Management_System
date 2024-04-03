package com.InventoryManagement.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.InventoryManagement.Services.ContactService;
import com.InventoryManagement.entities.Contact;
import jakarta.validation.Valid;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	//Save Product.
	@PostMapping("/saveContact")
	public ResponseEntity<?> saveContact(@Valid @RequestBody Contact contact ){
		
		return new ResponseEntity<>(contactService.saveContact(contact),HttpStatus.CREATED);
				
	}
}
