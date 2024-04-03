package com.InventoryManagement.Services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.InventoryManagement.Services.ItemService;
import com.InventoryManagement.entities.Item;
import com.InventoryManagement.repository.ItemRepository;

import static org.mockito.Mockito.*;

public class ItemServiceImplTest {

	@Mock
	private ItemRepository itemRepository;
	
	private ItemService itemService;
	
	AutoCloseable autoCloseable;

	Item item;
	
	@BeforeEach
	void setUp() throws Exception {
		
		autoCloseable = MockitoAnnotations.openMocks(this);
		itemService = new ItemServiceImpl(itemRepository);
		item = new Item(1,"Laptop","1 year","123bhM" ,null, null, null, new Date(2023-03-07));
	}
	
	@AfterEach
	 void tearDown() throws Exception {
		autoCloseable .close();
	}
	
	@Test
	void testSaveProduct() {
		
		when(itemRepository.save(item)).thenReturn(item);
		assertThat( itemService.createItem(item)).isEqualTo(item);	
	}
	
	@Test
	void testGetAllProduct() {

		
		when(itemRepository.findAll()).thenReturn(new ArrayList<Item>(Collections.singleton(item)));
		assertThat(itemService.getAllItems().get(0).getProductName()).isEqualTo(item.getProductName());
	}
	
	@Test
	void testGetProductById() {

		
		when(itemRepository.findById(1)).thenReturn(Optional.ofNullable(item));
		assertThat(itemService.getItemByserialNumber(1).getProductName()).isEqualTo(item.getProductName());
		
	}
	
	@Test
	void testEditProduct() {
		
		Integer serialNumber = 1;
		
        Item existingItem = new Item();
        existingItem.setserialNumber(serialNumber);
        existingItem.setProductName("Old Laptop");
        existingItem.setWarranty("1 year");
        existingItem.setBillNumber("123bhM");
        existingItem.setDate(Date.valueOf("2023-03-07"));
        
        Item updatedItem = new Item();
        updatedItem.setserialNumber(serialNumber);
        updatedItem.setProductName("New Laptop");
        updatedItem.setWarranty("2 years");
        updatedItem.setBillNumber("456cdN");
        updatedItem.setDate(Date.valueOf("2023-07-01"));
        
        when(itemRepository.findById(serialNumber)).thenReturn(java.util.Optional.of(existingItem));
        when(itemRepository.save(existingItem)).thenReturn(existingItem);
        
        // Act
        Item result = itemService.updateItem(updatedItem, serialNumber);

        // Assert
        verify(itemRepository).findById(serialNumber);
        verify(itemRepository).save(existingItem);
        
        assertEquals(updatedItem.getProductName(), result.getProductName());
        assertEquals(updatedItem.getWarranty(), result.getWarranty());
        assertEquals(updatedItem.getBillNumber(), result.getBillNumber());
        assertEquals(updatedItem.getDate(), result.getDate());
	
	}
	
	@Test
	void testDeleteProduct() {
		
		Integer serialNumber = 1;
        Item item = new Item();
        item.setserialNumber(serialNumber);

        when(itemRepository.findById(serialNumber)).thenReturn(java.util.Optional.of(item));

        // Act
        String result = itemService.deleteItem(serialNumber);

        // Assert
        verify(itemRepository, times(1)).delete(item);
        assertEquals("Product deleted Successfully", result);
	}
}
