//It is used to send data between the client and server
package com.InventoryManagement.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
// It is Used to transfer data from entity to services
public class Userdatatransfer {
	
	private int id;
	private String name;
	private String email;
	private String password;
	private String Address;
	private String phone;
	private String accountType;

	// Parameterized Constructor
	public Userdatatransfer(int id, String name, String email, String password, String address, String phone,
			String accountType) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		Address = address;
		this.phone = phone;
		this.accountType = accountType;
	}

	// Default Constructor
	public Userdatatransfer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Setters and Getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAddress() {
		return Address;
	}
	
	public void setAddress(String address) {
		this.Address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}