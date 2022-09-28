package com.revature;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.controllers.AuthController;
import com.revature.dtos.LoginRequest;
import com.revature.models.Review;
import com.revature.models.User;
import com.revature.services.ReviewService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ECommerceApplicationTests {

	@Autowired
	AuthController auth;
	
	@Autowired
	ReviewService rs;
	
	LoginRequest login = new LoginRequest();
	
	@BeforeAll
	public static void beforeAll() {
		
	}
	
	@Test
	public void test1() {
		
		int statuscode;
		
		HttpSession session = mock(HttpSession.class);
		login.setEmail("testuser@gmail.com");
		login.setPassword("password");
		ResponseEntity<User> user= auth.login(login, session);
		statuscode = user.getStatusCodeValue();
		User u = user.getBody();
		Assertions.assertEquals(200, statuscode);
		Assertions.assertTrue(u.getFirstName().equals("Test"));
		Review r = rs.findById(4);
		Assertions.assertEquals(4, r.getId());
		
	}
	@Test
	void contextLoads() {
	}

}
