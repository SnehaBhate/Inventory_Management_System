//Implementation class for userService.
package com.InventoryManagement.Services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.InventoryManagement.Exceptions.*;
import com.InventoryManagement.Payloads.Userdatatransfer;
import com.InventoryManagement.Services.UserService;
import com.InventoryManagement.entities.User;
import com.InventoryManagement.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
    
	private final PasswordEncoder passwordEncoder;
	
	@Autowired 
	private UserRepo userRepo;

	//constructor injection
	@Autowired
	public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepo userRepo) {
	    this.passwordEncoder = passwordEncoder;
	    this.userRepo = userRepo;
	}
	

	
	//Method to save user information in database
	public Userdatatransfer CreateUser(Userdatatransfer userdto) {

        User user=this.dtoToUser(userdto);
        String email=user.getEmail();
        
        //To check if email already exists or not.
        if(userRepo.findByEmail(email)!=null)
        {
        	throw new IllegalArgumentException("Email already exists!");
        }
        
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User savedUser=this.userRepo.save(user);
        
		return this.usertoDto(savedUser);
	}
	
    //Get User By Id
	@Override
	public Userdatatransfer getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		return this.usertoDto(user);
	}
	
	@Override
	public List<Userdatatransfer> getAllUsers() {
		List<User> users=this.userRepo.findAll();
		List<Userdatatransfer>UserDtos=users.stream().map(user->this.usertoDto(user)).collect(Collectors.toList());
		return UserDtos;
	}

	//Delete User By Id
	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		this.userRepo.delete(user);
	}
	
	//Convert  UserDto to User
	public User dtoToUser(Userdatatransfer userDTO)
	{
		User user=new User();
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setPhone(userDTO.getPhone());
		user.setAccountType(userDTO.getAccountType());
		user.setAddress(userDTO.getAddress());
		return user;
	}
	
	//Convert User to UserDto
	public Userdatatransfer usertoDto(User user)
	{
		Userdatatransfer userDto=new Userdatatransfer();
		userDto.setName(user.getName());
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setPhone(user.getPhone());
		userDto.setAccountType(user.getAccountType());
		userDto.setAddress(user.getAddress());
		return userDto;
	}

	// Get user details by email
	@Override
	public Userdatatransfer getUserByEmail(String email) {
	  
	  User user=this.userRepo.findByEmail(email);
	  
	  if(user==null)
	  {
		  throw new UsernameNotFoundException("User Not Found");
	  }
	  
	  Userdatatransfer A=this.usertoDto(user);
	  return A;
	}
}
