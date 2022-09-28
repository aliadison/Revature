package Utility;

import java.util.ArrayList;

import MainMenu.ERS;
import Models.Ticket;

public class TicketUtil {
	static DAO dao = new DAO();

	public static void makeNewTicket() {
		System.out.println();
		System.out.println("---------New ticket---------");
		int amount = InputValidation.enterPositiveInt("Enter amount: $");
		String description = InputValidation.enterString("Enter description: ");

		Ticket newTicket = new Ticket(0, amount, description, "Pending", ERS.currentAccount.getId(), 0);
		dao.makeNewTicket(newTicket);
	}

	public static void showEmployeePendingRequests() {
		System.out.println();
		System.out.println("---------Pending requests---------");

		for (Ticket ticket : ERS.ticketsList) {
			if (ticket.getMadeByID() == ERS.currentAccount.getId() && ticket.getStatus().equals("Pending"))
				System.out.println(ticket);
		}
		System.out.println("--------------------------------");
	}

	public static void manageRequests() {
		System.out.println();
		System.out.println("---------Reimbursement requests---------");
		int managerNewBalance = ERS.currentAccount.getAccountBalance();
		boolean managerBalanceChanged = false;

		for (Ticket ticket : ERS.ticketsList) {
			if (ticket.getStatus().equals("Pending")) {
				System.out.println(ticket);

				char managerInput = InputValidation.enterChar("adAD", "Accept(a)/ Decline(d): ");
				if (managerInput == 'a') {
					if (managerNewBalance > ticket.getAmount()) {
						managerNewBalance -= ticket.getAmount();
						ERS.currentAccount
								.setAccountBalance(ERS.currentAccount.getAccountBalance() - ticket.getAmount());
						managerBalanceChanged = true;

						int requesterNewBalance = AccountUtil.getAccountBalanceByID(ticket.getMadeByID())
								+ ticket.getAmount();
						AccountUtil.setAccountBalanceByID(ticket.getMadeByID(), requesterNewBalance);
						dao.updateEmployeeBalance(ticket.getMadeByID(), requesterNewBalance);

						dao.updateTicketStatus(ticket.getTicketID(), "Approved", ERS.currentAccount.getId());

						System.out.println("Request approved.");
						System.out.println("You now have $" + managerNewBalance + " in your account.");
					} else {
						System.out.println("You don't have enough balance to approve this request.");
					}
				} else if (managerInput == 'd') {
					System.out.println("Request declined.");
					dao.updateTicketStatus(ticket.getTicketID(), "Declined", ERS.currentAccount.getId());
				}
				System.out.println();
			}
		}

		System.out.println("--------------------------------");
		if (managerBalanceChanged)
			dao.updateEmployeeBalance(ERS.currentAccount.getId(), managerNewBalance);
	}
	
	public static Ticket getTicketByID(int id) {
		DAO dao = new DAO();
		ArrayList<Ticket> ticketsList = dao.retrieveTicketsList();

		for (Ticket ticket : ticketsList) {
			if (ticket.getTicketID() == id)
				return ticket;
		}
		return null;
	}
}
