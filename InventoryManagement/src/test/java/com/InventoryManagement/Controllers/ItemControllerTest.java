package com.InventoryManagement.Controllers;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.InventoryManagement.Services.ItemService;
import com.InventoryManagement.entities.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ItemService itemService;

	Item item_1;
	Item item_2;

	List<Item> itemList = new ArrayList<>();
	
	@BeforeEach
	void setUp() throws Exception {
		
		item_1 = new Item(1, "Laptop", "1 year", "123bhM", null, null, null, Date.valueOf("2023-03-07"));
		item_2 = new Item(2, "PC", "2 year", "125bhM", null, null, null, Date.valueOf("2023-04-23"));

		itemList.add(item_1);
		itemList.add(item_2);

	}

	@AfterEach
	void tearDown() throws Exception {
		
	}
	
	@Test
	void testSaveProduct() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		
		String requestJson = ow.writeValueAsString(item_1);
		
		when(itemService.createItem(item_1)).thenReturn(item_1);
		
		mockMvc.perform(post("/api/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJson))
				.andDo(print())
				.andExpect(status().isCreated());
	}
	
	@Test
	void testGetAllProduct() throws Exception {
		
		when(itemService.getAllItems()).thenReturn(itemList);
		
		mockMvc.perform(get("/api/getItems"))
		       .andDo(print())
		       .andExpect(status().isOk());
	}
	
	@Test
	void testGetProductById() throws Exception {
		
		when(itemService.getItemByserialNumber(1)).thenReturn(item_1);
		
		mockMvc.perform(get("/api/getItem/1"))
		            .andDo(print())
		            .andExpect(status().isOk());

	}
	
	@Test
	void testEditProduct() throws Exception {
		
		Item item = new Item();
		item.setBillNumber("H87U1");
		item.setDate(Date.valueOf("2023-02-11"));
		item.setProductName("Pen");
		item.setStatus(false);
		item.setWarranty("1 day");
		
		ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(item);

        when(itemService.updateItem(item, 1)).thenReturn(item);
        
        mockMvc.perform(put("/api/updateItem/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                        .andDo(print())
                        .andExpect(status().isCreated());
	}
	
	@Test
	void testDeleteProduct() throws Exception {
		
		when(itemService.deleteItem(1)).thenReturn("Product deleted Succesfully");
		
		mockMvc.perform(delete("/api/deleteItem/" + "1"))
		            .andDo(print())
		            .andExpect(status().isOk());
	}
}
