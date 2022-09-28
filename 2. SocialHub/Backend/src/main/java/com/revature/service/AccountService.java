package com.revature.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Account;
import com.revature.model.Profile;
import com.revature.repository.AccountRepository;
import com.revature.repository.ProfileRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ProfileRepository profileRepository;

	public String login(Account account, HttpServletRequest request) {
		Account isValidAccount = this.accountRepository.findByEmailAndPassword(account.getEmail(),
				account.getPassword());
		if (isValidAccount == null) {
			return "Wrong email or password";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("accountId", isValidAccount.getId());
			session.setMaxInactiveInterval(120 * 60);
			return "Logged in successfully";
		}
	}

	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute("accountId");
			session.invalidate();
			return "Logged out successfully";
		}
		return "Already Logged out";
	}

	public String register(Account account) {
		Account accountSearch = this.accountRepository.findByEmail(account.getEmail());
		if (accountSearch == null) {
			this.accountRepository.save(account);

			// for saving new Profile
			accountSearch = this.accountRepository.findByEmail(account.getEmail());
			String defaultPic = "https://muhammad-socialhub.s3.us-east-2.amazonaws.com/07c2c87c-1844-49a9-9a1b-642c19015b5c.png";
			Profile profile = new Profile(0, "", "", defaultPic, accountSearch);
			profileRepository.save(profile);
			return "New account registered";
		} else {
			return "Account with this email already exists";
		}
	}

	public Account findById(int id) {
		return this.accountRepository.findById(id);
	}

	public List<Account> findAllEmail() {
		return this.accountRepository.findAll();
	}

	public Account updateAccount(Account account) {
		return this.accountRepository.save(account);
	}

	public String checkSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String s = "Not logged in";
		if (session != null) {
			s = "LoggedIn in session";
		}
		return s;

	}
}
