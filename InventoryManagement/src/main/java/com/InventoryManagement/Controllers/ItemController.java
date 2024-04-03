package com.InventoryManagement.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.InventoryManagement.Services.ItemService;
import com.InventoryManagement.entities.Item;
import jakarta.validation.Valid;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {
	
	@Autowired
	private ItemService  itemService ;
	
	
	//Get all Products.
	@GetMapping("/getItems")
	public ResponseEntity<?> getAllItems()
	{
		return new ResponseEntity<>(itemService.getAllItems(),HttpStatus.OK);
	}
	
	//Get Product by ID.
	@GetMapping("/getItem/{serialNumber}")
	public ResponseEntity<?> getProductByserialNumber(@PathVariable Integer serialNumber) 
	{
		return new ResponseEntity<>(itemService.getItemByserialNumber(serialNumber),HttpStatus.OK);
	}
	
	// Create Product.
	// validation  with @Valid
	@PostMapping("/save")
	public ResponseEntity<?> createProduct(@Valid @RequestBody Item item ){
		System.out.println("Request Payload: " + item.getProductName());
		return new ResponseEntity<>(itemService.createItem(item),HttpStatus.CREATED);
			
	}
	
	//Update Product.
	@PutMapping("/updateItem/{serialNumber}")
	public ResponseEntity<?>updateItem(@Valid @RequestBody Item item ,@PathVariable Integer serialNumber  ){
		return new ResponseEntity<>(itemService.updateItem(item,serialNumber),HttpStatus.CREATED);
		
	}
	
	//Delete Product by serialNumber.
	@DeleteMapping("/deleteItem/{serialNumber}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer serialNumber)
	{
		return new ResponseEntity<>(itemService.deleteItem(serialNumber),HttpStatus.OK);
	}
}
