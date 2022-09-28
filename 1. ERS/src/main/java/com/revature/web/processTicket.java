package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Employee;
import Models.Ticket;
import Utility.AccountUtil;
import Utility.CookiesHandler;
import Utility.DAO;
import Utility.TicketUtil;

public class processTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isLoggedIn = CookiesHandler.checkAuthentication(request);
		boolean isManager = AccountUtil.isManager(request);

		if (isLoggedIn && isManager) {
			int ticketID = 0;
			boolean isApproved = false;
			try {
				ticketID = Integer.parseInt(request.getParameter("Ticket ID"));
				isApproved = Boolean.parseBoolean(request.getParameter("Approve"));
			}catch (NumberFormatException e) {
				response.getWriter().write("Please enter valid values.\n");
			}

			int userID = Integer.parseInt(CookiesHandler.getCookieValue(request, "UserID"));
			Employee manager = AccountUtil.getEmployeeByID(userID);
			Ticket ticket = TicketUtil.getTicketByID(ticketID);

			DAO dao = new DAO();

			if (ticket != null) {
				if (ticket.getStatus().equals("Pending")) {
					if (isApproved) {
						int managerBalance = manager.getAccountBalance();
						if (managerBalance > ticket.getAmount()) {
							managerBalance -= ticket.getAmount();
							dao.updateEmployeeBalance(manager.getId(), managerBalance);

							int requesterNewBalance = AccountUtil.getAccountBalanceByID(ticket.getMadeByID())
									+ ticket.getAmount();
							dao.updateEmployeeBalance(ticket.getMadeByID(), requesterNewBalance);

							dao.updateTicketStatus(ticket.getTicketID(), "Approved", manager.getId());

							response.getWriter().write("Request approved.\n");
							response.setStatus(201);
						} else response.getWriter().write("You don't have enough balance to approve this request.\n");
						
					} else {
						response.getWriter().write("Request declined.\n");
						dao.updateTicketStatus(ticket.getTicketID(), "Declined", manager.getId());
						response.setStatus(201);
					}
				} else {
					response.getWriter().write("Ticket already processed.\n");
					response.setStatus(403);
				}
			} else response.getWriter().write("Ticket doesn't exist.\n");
		} else {
			response.getWriter().write("You don't have permission to process tickets.\n");
			response.setStatus(401);
		}
	}

}
