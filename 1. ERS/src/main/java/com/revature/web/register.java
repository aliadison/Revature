package com.revature.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Employee;
import Utility.DAO;

public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAO dao = new DAO();

		ArrayList<Employee> employeesList = dao.retrieveEmployeesList();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username != null && username != "" && password != null && password != "" ) {
			username = username.toLowerCase();

			boolean validUsername = false;

			for (Employee account : employeesList) {
				if (username.equals(account.getUsername())) {
					validUsername = false;
					break;
				} else {
					validUsername = true;
				}
			}
			if (validUsername) {
				Employee newAccount = new Employee(0, username, password, "doesn't matter", 0);
				dao.addNewEmployee(newAccount);
				response.getWriter().write("New employee added.");
				response.setStatus(201);
			} else {
				response.setStatus(400);
				response.getWriter().write("Username already taken.");
			}
		} else {
			response.getWriter().write("Username or password invalid.");
			response.setStatus(400);
		}
	}

}
