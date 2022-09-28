package com.revature.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import Models.Employee;
import Utility.AccountUtil;
import Utility.CookiesHandler;
import Utility.DAO;

public class allEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean isLoggedIn = CookiesHandler.checkAuthentication(request);
		boolean isManager = AccountUtil.isManager(request);

		if (isLoggedIn && isManager) {
			DAO dao = new DAO();
			ArrayList<Employee> employeesList = dao.retrieveEmployeesList();
			response.setContentType("application/json");

			ObjectMapper myMapper = new ObjectMapper();
			String json = myMapper.writeValueAsString(employeesList);
			response.getWriter().write(json);
			response.setStatus(200);
		} else {
			response.getWriter().write("You don't have permission to access employees list.\n");
			response.setStatus(401);
		}
	}

}
