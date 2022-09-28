package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Account;
import com.revature.model.Profile;
import com.revature.repository.ProfileRepository;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	public Profile findByAccountId(Account account) {
		return this.profileRepository.findByAccountId(account);
	}
	
	public Profile updateProfile(Profile profile) {
		return this.profileRepository.save(profile);
	}
}
