//It is responsible for implementing the business logic and operations related to users.
package com.InventoryManagement.Services;

import java.util.List;
import com.InventoryManagement.Payloads.Userdatatransfer;

public interface UserService {
	
	//Methods to Perform CRUD on Users
	Userdatatransfer CreateUser(Userdatatransfer userdto);
   
	Userdatatransfer getUserById(Integer userId);
    
	Userdatatransfer getUserByEmail(String email);
   
	List<Userdatatransfer> getAllUsers();
   
	void deleteUser(Integer userId);
}
