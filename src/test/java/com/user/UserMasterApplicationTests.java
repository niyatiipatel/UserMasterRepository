package com.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.user.model.AddressMaster;
import com.user.model.UserMaster;
import com.user.repo.UserMasterRepo;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserMasterApplicationTests {

	
	@Autowired
	private UserMasterRepo userRepo;
	
	String email = "test123@gmail.com";
	
	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveUser()
	{
		UserMaster userObj = new UserMaster();
		userObj.setFirstName("FirstName");
		userObj.setLastName("LastName");
		userObj.setEmail(email);
		
		AddressMaster addObj = new AddressMaster();
		addObj.setAddressLine1("Address Line 1");
		addObj.setCity("city");
		addObj.setState("state");
		addObj.setZip("V61YR5");
		List<AddressMaster> listAdd = new ArrayList<AddressMaster>();
		listAdd.add(addObj);
		userObj.setAddressDtls(listAdd);
		
		userRepo.save(userObj);	
		
		assertThat(userObj.getUserId()).isGreaterThan(0);
		assertThat(userObj.getAddressDtls().size()).isGreaterThan(0);
		assertThat(userObj.getEmail()).isNotNull();
		
	}
	
	@Test
	@Order(2)
	public void isUserExistsByEmail()
	{
		UserMaster userObj = userRepo.findByEmail(email);
		
		assertThat(userObj.getUserId()).isGreaterThan(0);
		assertThat(userObj.getEmail()).isEqualTo(email);
		
	}

	@Test
	@Order(3)
	public void listUsers()
	{
		List<UserMaster> arrListUser = userRepo.findAll();
		
		assertThat(arrListUser.size()).isGreaterThan(0);
		
	}
	
	@Test
	@Order(4)
	@Rollback(value = true)
	public void updateUser()
	{
		UserMaster userObj = userRepo.findByEmail(email);
		userObj.setFirstName("FirstNameNew");
		
		UserMaster userObjUpdated = userRepo.save(userObj);	
		
		assertThat(userObjUpdated.getFirstName()).isEqualTo("FirstNameNew");
		
	}
	
	@Test
	@Order(5)
	@Rollback(value = true)
	public void updateAddress()
	{
		UserMaster userObj = userRepo.findByEmail(email);
		
		AddressMaster addObj = new AddressMaster();
		addObj.setAddressLine1("Address Line 2");
		addObj.setCity("city2");
		addObj.setState("state2");
		addObj.setZip("V61YR9");
		
		List<AddressMaster> arrList = new ArrayList<AddressMaster>();
		arrList.add(addObj);
		userObj.setAddressDtls(arrList);
		  
		UserMaster userObjUpdated = userRepo.save(userObj);	
		
		assertThat(userObjUpdated.getAddressDtls().size()).isGreaterThan(0);
	}

	@Test
	@Order(6)
	@Rollback(value = true)
	public void deleteUser()
	{
		UserMaster userObj = userRepo.findByEmail(email);
		userRepo.delete(userObj);
		
		UserMaster userObjUpdated = userRepo.findByEmail(email);
		
		assertThat(userObjUpdated).isNull();
		
	}
}
