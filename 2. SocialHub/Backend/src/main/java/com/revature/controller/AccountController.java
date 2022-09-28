package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Account;
import com.revature.service.AccountService;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials="true")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@GetMapping(path ="/test")
	public String test(HttpServletRequest request) {
		System.out.println("test endpoint");
		HttpSession session = request.getSession(false);
		if(session != null) {
			int id =  (int) session.getAttribute("accountId");
			return "accountID:" + id;
		}else {
			return "you're logged out";
		}
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Account account, HttpServletRequest request) {
		return this.accountService.login(account, request);
	}
	
	@GetMapping("/logout")
	public String logOut(HttpServletRequest request) {
		return this.accountService.logout(request);
	}
	
	@PostMapping("/register")
	public String register(@RequestBody Account account) {
		return this.accountService.register(account);
	}
	
	@GetMapping("/allemail")
	public List<Account> findAllEmail() {
		return this.accountService.findAllEmail();
	}
	
	//check if user is logged in
	@GetMapping("/checksession")
	public String checkSession(HttpServletRequest request) {
		return this.accountService.checkSession(request);
	}
}
