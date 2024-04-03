package com.InventoryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.InventoryManagement.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}


