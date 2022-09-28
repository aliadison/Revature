package MainMenu;

import java.util.Scanner;

import Models.Employee;
import Models.Ticket;
import Utility.AccountUtil;
import Utility.DAO;
import Utility.InputValidation;

import java.util.ArrayList;

public class ERS {
	public static ArrayList<Employee> employeesList = new ArrayList<Employee>();
	public static ArrayList<Ticket> ticketsList = new ArrayList<Ticket>();
	public static Employee currentAccount = null;
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		DAO dao = new DAO();
		employeesList = dao.retrieveEmployeesList();
		ticketsList = dao.retrieveTicketsList();
		
		boolean quit = false;
		while (!quit) {

			System.out.println();
			System.out.println("---------Main menu---------");
			System.out.println("1) Login");
			System.out.println("2) Register");
			System.out.println("3) Show all employees");
			System.out.println("4) Quit");

			int mainMenuOption = InputValidation.enterInt(1, 4, "Select: ");

			switch (mainMenuOption) {
			case 1:
				currentAccount = AccountUtil.login();
				if (currentAccount != null)
					AccountMenu.displayAccountMenu();
				break;
			case 2:
				AccountUtil.register();
				employeesList = dao.retrieveEmployeesList();
				break;
			case 3:
				AccountUtil.showAllEmployees();
				break;
			case 4:
				System.out.println();
				System.out.println("Exiting program...");
				quit = true;
				scan.close();
				break;
			}
		}
	}
}
