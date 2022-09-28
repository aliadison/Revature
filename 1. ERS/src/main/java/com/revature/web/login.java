package com.revature.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Employee;
import Utility.CookiesHandler;
import Utility.DAO;

public class login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	DAO dao = new DAO();
	

	public login() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Employee> employeesList = dao.retrieveEmployeesList();
		
		String username = request.getParameter("username").toLowerCase();
		String password = request.getParameter("password");
		boolean successfullLogin = false;
		
		for (Employee account : employeesList) {
			if (username.equals(account.getUsername()) && password.equals(account.getPassword())) {
				response.getWriter().write("Logged In!");
				Cookie authCookie = new Cookie("authenticated","true");
				response.addCookie(authCookie);
				Cookie idCookie = new Cookie("UserID", String.valueOf(account.getId()));
				response.addCookie(idCookie);
				successfullLogin = true;
				response.setStatus(201);
			}
		}
		if(!successfullLogin) {
			response.setStatus(401);
			response.getWriter().write("Wrong username or password!");
			CookiesHandler.setCookieValue(request, response, "authenticated", "false");
			CookiesHandler.setCookieValue(request, response, "UserID", "0");
		}
		
	}

}
