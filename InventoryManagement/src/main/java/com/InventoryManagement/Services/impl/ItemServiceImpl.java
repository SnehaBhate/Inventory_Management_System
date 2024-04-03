package com.InventoryManagement.Services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.InventoryManagement.Services.ItemService;
import com.InventoryManagement.entities.Item;
import com.InventoryManagement.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
		
	@Autowired
	private ItemRepository itemRepo;
	
	// Parameterized Constructor
	public ItemServiceImpl(ItemRepository itemRepo) {
		super();
		this.itemRepo = itemRepo;
	}

	// Get all products.
	@Override
	public List<Item> getAllItems() {
		
		return itemRepo.findAll();
	}
		
	// Get product by serialNumber.
	@Override
	public Item getItemByserialNumber(Integer serialNumber) {
		
		return itemRepo.findById(serialNumber).get();
	}
		
	// Create new product.
	@Override
	public Item createItem(Item item) {
		// TODO Auto-generated method stub
			
		return itemRepo.save(item);
	}
	 
	// Update product.
	@Override
	public Item updateItem(Item item, Integer serialNumber) {
		Item olditem = itemRepo.findById(serialNumber).get();
		olditem.setProductName(item.getProductName());
		olditem.setWarranty(item.getWarranty());
		olditem.setBillNumber(item.getBillNumber());
		olditem.setDate(item.getDate());
		olditem.setEmpId(item.getEmpId());
		olditem.setStatus(item.getStatus());
		olditem.setEmpName(item.getEmpName());
		return itemRepo.save(olditem);
	}
		
	// Delete product.
	@Override
	public String deleteItem(Integer serialNumber) {
		// TODO Auto-generated method stub
		Item item = itemRepo.findById(serialNumber).get();
		if(item != null)
		{
			itemRepo.delete(item);
			return "Product deleted Successfully";
		}
		return "Something Went Wrong";
	}
}