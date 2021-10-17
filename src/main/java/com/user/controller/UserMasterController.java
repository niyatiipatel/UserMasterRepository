package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.model.AddressMaster;
import com.user.model.UserMaster;
import com.user.service.UserService;

@RestController
public class UserMasterController {

	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/UpsertUser", consumes = "application/json")
	@ResponseBody
	public UserMaster UpsertUser(@RequestBody UserMaster userMstObj)
	{
		return userService.UpsertUser(userMstObj);
	}
	
	@GetMapping(value ="/IsUserExists/{email}", produces = "application/json")
	@ResponseBody
	public String IsUserExists(@PathVariable(name="email") String email)
	{
		return userService.IsUserExists(email);
	}
	
	@GetMapping(value ="/ListUsers", produces = "application/json")
	@ResponseBody
	public List<UserMaster> ListUsers()
	{
		return userService.ListUsers();
	}
	
	@GetMapping(value ="/GetUserDetails/{userId}", produces = "application/json")
	@ResponseBody
	public UserMaster GetUserDetails(@PathVariable(name ="userId") int userId)
	{
		return userService.GetUserDetails(userId);
	}
	
	@DeleteMapping(value = "/DeleteUser/{userId}")
	public String DeleteUser(@PathVariable(name = "userId") int userId)
	{
		return userService.DeleteUser(userId);
	}
	
	@PostMapping(value = "UpdateAddress/{userId}", consumes = "application/json")
	public UserMaster UpdateAddress(@RequestBody AddressMaster addMstObj, @PathVariable int userId)
	{
		return userService.UpdateAddress(addMstObj,userId);
	}
	
}
