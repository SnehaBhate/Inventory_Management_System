package com.InventoryManagement.Services;

import java.util.List;
import com.InventoryManagement.entities.Item;

public interface ItemService {

	//get all product.
    public List<Item> getAllItems();
    
    //get product by serialNumber.
  	public Item getItemByserialNumber(Integer serialNumber);
	
  	//create product.
	public Item createItem(Item item);
	
	//update product.
	public Item updateItem(Item item,Integer serialNumber);
	
	//delete product by serialNumber.
	public String deleteItem(Integer serialNumber);
}

