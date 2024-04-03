//UserRepo is used to interact with the underlying database or data source for operations related to the User entity.
//package com.InventoryManagement.repository;
//
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.InventoryManagement.entities.User;
//
//public interface UserRepo extends JpaRepository<User, Integer> {
//	//Method to find the details of user by email
//	  User findByEmail(String email);
//	  
//	
//}
package com.InventoryManagement.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.InventoryManagement.entities.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
		
	//Method to find the details of user by email
	   User findByEmail(String email);
	   
	User findByEmailAndVerificationToken(String email, String token);

	Optional<User> findById(Integer id);
		
}
	  