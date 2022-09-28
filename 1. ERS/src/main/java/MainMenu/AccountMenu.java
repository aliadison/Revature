package MainMenu;

import Utility.DAO;
import Utility.InputValidation;
import Utility.TicketUtil;

public class AccountMenu {
	static void displayAccountMenu() {
		System.out.println();
		System.out.println("---------Welcome " + ERS.currentAccount.getUsername()+"---------");
		DAO dao = new DAO();
		
		boolean logOut = false;
		while (!logOut) {
			
			System.out.println();
			System.out.println("---------Account menu---------");
			System.out.println("1) Show Balance");

			if (ERS.currentAccount.getRole().equals("associate"))
				System.out.println("2) Request Reimbursement");
			else if (ERS.currentAccount.getRole().equals("manager") )
				System.out.println("2) Reimbursement Requests");

			if (ERS.currentAccount.getRole().equals("associate"))
				System.out.println("3) Pending requests");

			System.out.println("4) Log out");
			

			int userMenuOption = InputValidation.enterInt(1, 4, "Select: ");

			switch (userMenuOption) {
			case 1:
				System.out.println();
				System.out.println("---------Account balance---------");
				System.out.println("$" + ERS.currentAccount.getAccountBalance());
				break;
			case 2:
				if (ERS.currentAccount.getRole().equals("associate")) {
					TicketUtil.makeNewTicket();
				} else if (ERS.currentAccount.getRole().equals("manager"))
					TicketUtil.manageRequests();
				
				ERS.employeesList = dao.retrieveEmployeesList();
				ERS.ticketsList = dao.retrieveTicketsList();
				break;
			case 3:
				if (ERS.currentAccount.getRole().equals("associate"))
					TicketUtil.showEmployeePendingRequests();
				break;
			case 4:
				System.out.println();
				System.out.println("Logging out...");
				logOut = true;
				break;
			}
		}
	}
}
