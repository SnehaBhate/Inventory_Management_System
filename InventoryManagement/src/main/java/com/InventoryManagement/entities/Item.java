package com.InventoryManagement.entities;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int serialNumber;
	
	@NotEmpty(message="Product name must not be empty")
	@Pattern(regexp = "^[A-Z][a-zA-Z\\s]+$", message = "Name should only contain letters and spaces")
	private String productName;
	
	@NotEmpty(message="warranty must not be empty")
	@Pattern(regexp = "^\\d[a-zA-Z0-9\\s]*$", message = "Warranty should start with a digit and be followed by at least one uppercase and one lowercase letter")
	private String warranty;
	
	@NotEmpty(message="bill number must not be empty")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Bill number should only contain uppercase letters, lowercase letters, and digits")
	private String billNumber;
	
	private String empName;
	
	private Long empId;
	
	@Column(nullable = true, columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean status;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date of purchase")
	@NotNull(message="Date field is mandatory")
	@Past(message="Invalid date")
	private Date date;
	
	
	//Default Constructor
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Parameterized Constructor
	public Item(int serialNumber,
			@NotEmpty(message = "Product name must not be empty") @Pattern(regexp = "^[A-Z][a-zA-Z\\s]+$", message = "Name should only contain letters and spaces") String productName,
			@NotEmpty(message = "warranty must not be empty") @Pattern(regexp = "^\\d[a-zA-Z0-9\\s]*$", message = "Warranty should start with a digit and be followed by at least one uppercase and one lowercase letter") String warranty,
			@NotEmpty(message = "bill number must not be empty") @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Bill number should only contain uppercase letters, lowercase letters, and digits") String billNumber,
			String empName, Long empId, Boolean status,
			@NotNull(message = "Date field is mandatory") @Past(message = "Invalid date") Date date) {
		super();
		this.serialNumber = serialNumber;
		this.productName = productName;
		this.warranty = warranty;
		this.billNumber = billNumber;
		this.empName = empName;
		this.empId = empId;
		this.status = status;
		this.date = date;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getserialNumber() {
		return serialNumber;
	}
	
	public void setserialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getWarranty() {
		return warranty;
	}
	
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}
	
	public String getBillNumber() {
		return billNumber;
	}
	
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	
	public String getEmpName() {
		return empName;
	}
	
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	public Long getEmpId() {
		return empId;
	}
	
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	
	public Boolean getStatus() {
		return status;
	}
	
	public void setStatus(Boolean status) {
		this.status = status;
	}
}