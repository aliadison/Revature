package Utility;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import MainMenu.ERS;
import Models.Employee;

public class AccountUtil {
	public static Employee login() {
		System.out.println();
		System.out.println("---------Log in---------");

		String username = InputValidation.enterString("Enter username: ");
		String password = InputValidation.enterString("Enter password: ");

		if (ERS.employeesList.isEmpty()) {
			System.out.println("No existing account.");
		} else {
			for (Employee account : ERS.employeesList) {
				if (username.equals(account.getUsername()) && password.equals(account.getPassword())) {
					return account;
				}
			}
			System.out.println("Wrong username or password");
		}
		return null;
	}

	public static void register() {
		System.out.println();
		System.out.println("---------Register an account---------");
		String username = "";
		String password = "";

		boolean validUsername = false;
		while (!validUsername) {
			username = InputValidation.enterString("Enter username: ").toLowerCase();

			if (ERS.employeesList.isEmpty()) {
				validUsername = true;
			} else {
				for (Employee account : ERS.employeesList) {
					if (username.equals(account.getUsername())) {
						System.out.println("Username already taken.");
						validUsername = false;
						break;
					} else {
						validUsername = true;
					}
				}
			}
		}

		password = InputValidation.enterString("Enter password: ");

		Employee newAccount = new Employee(0, username, password, "doesn't matter", 0);
		DAO dao = new DAO();
		dao.addNewEmployee(newAccount);
	}

	public static void showAllEmployees() {
		System.out.println();
		System.out.println("---------Employees list---------");

		for (Employee employee : ERS.employeesList)
			System.out.println(employee);

		System.out.println("--------------------------------");

	}

	public static int getAccountBalanceByID(int id) {
		DAO dao = new DAO();
		ArrayList<Employee> employeesList = dao.retrieveEmployeesList();

		for (Employee employee : employeesList) {
			if (employee.getId() == id)
				return employee.getAccountBalance();
		}
		return 0;
	}

	public static void setAccountBalanceByID(int id, int newBalance) {
		DAO dao = new DAO();
		ArrayList<Employee> employeesList = dao.retrieveEmployeesList();

		for (Employee employee : employeesList) {
			if (employee.getId() == id)
				employee.setAccountBalance(newBalance);
		}
	}

	public static String getUsernameByID(int id) {
		DAO dao = new DAO();
		ArrayList<Employee> employeesList = dao.retrieveEmployeesList();

		for (Employee employee : employeesList) {
			if (employee.getId() == id)
				return employee.getUsername();
		}
		return "";
	}

	public static Employee getEmployeeByID(int id) {
		DAO dao = new DAO();
		ArrayList<Employee> employeesList = dao.retrieveEmployeesList();

		for (Employee employee : employeesList) {
			if (employee.getId() == id)
				return employee;
		}
		return null;
	}

	public static boolean isManager(HttpServletRequest request) {
		int userID = Integer.parseInt(CookiesHandler.getCookieValue(request, "UserID"));
		if (userID != 0) {
			Employee employee = getEmployeeByID(userID);

			if (employee.getRole().equals("manager"))
				return true;
		}
		return false;
	}
}
