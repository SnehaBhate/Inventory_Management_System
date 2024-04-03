package com.InventoryManagement.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.InventoryManagement.Services.ContactService;
import com.InventoryManagement.entities.Contact;
import com.InventoryManagement.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService{
	
	@Autowired
	private ContactRepository contactRepository;
	
	//Parameterized constructor
	public ContactServiceImpl(ContactRepository contactRepository) {
		super();
		this.contactRepository = contactRepository;
	}

	//Default Constructor
	public ContactServiceImpl() {
		
	}

	@Override
	public Contact saveContact(Contact contact) {
		
		return contactRepository.save(contact);
	}

}