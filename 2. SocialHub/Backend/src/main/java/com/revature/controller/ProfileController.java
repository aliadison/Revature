package com.revature.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.revature.model.Account;
import com.revature.model.Photo;
import com.revature.model.Profile;
import com.revature.service.AWSS3Service;
import com.revature.service.AccountService;
import com.revature.service.ProfileService;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials="true")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AWSS3Service awsS3Service;
	
	@GetMapping("/account")
	public Profile findProfileByAccount(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		int id = (int) session.getAttribute("accountId");
		Account account = this.accountService.findById(id);
		return this.profileService.findByAccountId(account);
	}
	
	@PostMapping("/update")
	public Profile updateProfileByAccount(
			String firstName,String lastName, String email,
			String password, String location, String phone, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		int id = (int) session.getAttribute("accountId");
		Account account = this.accountService.findById(id);
		Profile profile = this.profileService.findByAccountId(account);
		
		account.setFirstName(firstName);
		account.setLastName(lastName);
		account.setEmail(email);
		account.setPassword(password);
		this.accountService.updateAccount(account);
		
		profile.setLocation(location);
		profile.setPhone(phone);
		this.profileService.updateProfile(profile);
		
		return profile;
	}
	
	//for profile pic upload
	@PostMapping("/photoupload")
	public ResponseEntity<Map<String, String>> uploadFile(@RequestParam MultipartFile file, HttpServletRequest request) {

		String publicURL = awsS3Service.uploadFile(file);
		
		HttpSession session = request.getSession(false);
		int id = (int) session.getAttribute("accountId");
		

		Account account = this.accountService.findById(id);
		Profile profile = this.profileService.findByAccountId(account);
		profile.setProfilePic(publicURL);
		this.profileService.updateProfile(profile);
		//----------------------------
		Map<String, String> response = new HashMap<>();
		response.put("publicURL", publicURL);
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.CREATED);
	}
}
