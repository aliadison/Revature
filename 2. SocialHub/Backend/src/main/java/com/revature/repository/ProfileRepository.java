package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Account;
import com.revature.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	//List<Profile> findAll();
	
	//Profile findById(int id);
	
	Profile findByAccountId(Account account);
	
	
}
