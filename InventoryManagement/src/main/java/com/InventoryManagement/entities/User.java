// entity class represents a persistent data object that maps to a database table.
package com.InventoryManagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employee_table")
@NoArgsConstructor
@Getter
@Setter
public class User {

	
	private int id;
	//Back-end Validation
	@NotEmpty(message = "Name is required")
	@Pattern(regexp="^[A-Z][a-z]+\\s[A-Z][a-z]+$", message = "Name formate : Firstname Lastname")
	private String name;
	
	@NotEmpty(message = "Email is required")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail.com+$")
	@Column(unique=true)
	private String email;
	
	@Column(name = "verification_token")
    private String verificationToken;
	
	@NotEmpty(message = "Password is required")
	@Size(min = 8, message = "Password should be at least 8 characters")
	private String password;
	 
	@NotEmpty(message = "Address is required")
	@Pattern(regexp = "^[a-zA-Z0-9\\s,.]*$", message = "Address can not contain special characters")
    private String Address;
	 
    @NotEmpty(message = "Phone is required")
    @Pattern(regexp="^[6-9]\\d{9}$")
	private String phone;
     
    @NotEmpty(message = "Account type is required")
	private String accountType;
    
    
	// Parameterized Constructor
	public User(int id,
			@NotEmpty(message = "Name is required") @Pattern(regexp = "^[A-Z][a-z]+\\s[A-Z][a-z]+$", message = "Name formate : Firstname Lastname") String name,
			@NotEmpty(message = "Email is required") @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail.com+$") String email,
			String verificationToken,
			@NotEmpty(message = "Password is required") @Size(min = 8, message = "Password should be at least 8 characters") String password,
			@NotEmpty(message = "Address is required") @Pattern(regexp = "^[a-zA-Z0-9\\s,.]*$", message = "Address can not contain special characters") String address,
			@NotEmpty(message = "Phone is required") @Pattern(regexp = "^[6-9]\\d{9}$") String phone,
			@NotEmpty(message = "Account type is required") String accountType) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.verificationToken = verificationToken;
		this.password = password;
		Address = address;
		this.phone = phone;
		this.accountType = accountType;
	}
	

	// Default constructor
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Setters and Getters
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	public String getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
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