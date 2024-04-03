package com.InventoryManagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contactId;
	
	@NotEmpty(message="Name must not be empty")
	@Pattern(regexp = "^[A-Z][a-z]+\\s[A-Z][a-z]+$", message="Name format : Firstname Lastname")
	private String contactName;
	
	@NotEmpty(message="email must not be empty")
	private String contactEmail;
	
	@NotEmpty(message="message must not be empty")
	private String message;
	
	//Parameterized Constructor
	public Contact(int contactId,
			@NotEmpty(message = "Name must not be empty") @Pattern(regexp = "^[A-Z][a-z]+\\s[A-Z][a-z]+$", message = "Name format : Firstname Lastname") String contactName,
			@NotEmpty(message = "email must not be empty") String contactEmail,
			@NotEmpty(message = "message must not be empty") String message) {
		super();
		this.contactId = contactId;
		this.contactName = contactName;
		this.contactEmail = contactEmail;
		this.message = message;
	}

	//Default Constructor
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Setters and Getters
	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}