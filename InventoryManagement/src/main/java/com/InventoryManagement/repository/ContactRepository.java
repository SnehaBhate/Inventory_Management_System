package com.InventoryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.InventoryManagement.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
