package com.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.model.UserMaster;

@Repository
public interface UserMasterRepo extends JpaRepository<UserMaster, Integer>{

	UserMaster findByEmail(String email);

}
