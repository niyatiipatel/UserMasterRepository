package com.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.user.model.AddressMaster;
import com.user.model.UserMaster;
import com.user.repo.UserMasterRepo;

@Component
public class UserService {
	
	@Autowired
	private UserMasterRepo userRepo;

	public UserMaster UpsertUser(UserMaster userMstObj) {
		
		UserMaster obj = userRepo.findByEmail(userMstObj.getEmail());
		if(obj!=null && obj.getUserId()>0)
		{
			userMstObj.setUserId(obj.getUserId());
			List<AddressMaster> arrList = (userMstObj.getAddressDtls() !=null && !userMstObj.getAddressDtls().isEmpty()) 
					? userMstObj.getAddressDtls() : new ArrayList<AddressMaster>();
			  List<AddressMaster> arrAddDtls = obj.getAddressDtls();//set the new address dtls in existing dtls 
			  if(!arrAddDtls.isEmpty()) 
			  { 
				  for(AddressMaster addObj :arrAddDtls) 
				  {
					  arrList.add(addObj);
				 }
			  }
			  userMstObj.setAddressDtls(arrList);
			 
			userMstObj = userRepo.save(userMstObj);//if record exists, it will update
		}
		else
		{
			userMstObj = userRepo.save(userMstObj);	
		}

		return userMstObj;

	}

	public String IsUserExists(String email) {
		
		String msg;
		UserMaster userObj = userRepo.findByEmail(email);
		if(userObj!=null && userObj.getUserId()>0)
		{
			msg = "User exists";
		}
		else
		{
			msg = "User does not exists";
		}
		return msg;
	}

	public UserMaster GetUserDetails(int userId) {

		return userRepo.findById(userId).orElse(new UserMaster());
	}

	public List<UserMaster> ListUsers() {

		return userRepo.findAll();
	}

	public String DeleteUser(int userId) {
		
		String msg ="No user found to delete";
		UserMaster obj = GetUserDetails(userId);
		if(obj!=null && obj.getUserId()>0)
		{
			userRepo.deleteById(userId);	
			msg = "User deleted";
		}
		return msg;
	}

	public UserMaster UpdateAddress(AddressMaster addMstObj, int userId) {

		UserMaster userMstObj = new UserMaster();
		if(userId>0)
		{
			userMstObj = GetUserDetails(userId);
			if(userMstObj!=null && userMstObj.getUserId()>0)
			{
				userMstObj.getAddressDtls().add(addMstObj);
				userRepo.save(userMstObj);
			}
		}
		return userMstObj;
	}

}
