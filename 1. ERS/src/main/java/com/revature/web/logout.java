package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utility.CookiesHandler;

public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		boolean isLoggedIn = CookiesHandler.checkAuthentication(request);
		
		if(isLoggedIn) {
			response.getWriter().write("Logged out.");
			CookiesHandler.setCookieValue(request, response, "authenticated", "false");
			CookiesHandler.setCookieValue(request, response, "UserID", "0");
			response.setStatus(201);
		}else 
			response.getWriter().write("Not logged in.");
	}

}
