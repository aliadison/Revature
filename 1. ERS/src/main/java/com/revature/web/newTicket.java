package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Ticket;
import Utility.AccountUtil;
import Utility.CookiesHandler;
import Utility.DAO;

public class newTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean isLoggedIn = CookiesHandler.checkAuthentication(request);
		boolean isManager = AccountUtil.isManager(request);
		
		if (isLoggedIn && !isManager) {
			int amount = 0;

			try {
				amount = Integer.parseInt(request.getParameter("Amount"));
				if (amount < 1)
					response.getWriter().write("Amount should be greater than 0.\n");
			} catch (NumberFormatException nfe) {
				response.getWriter().write("Amount should be a valid numeric value!\n");
			}

			String description = request.getParameter("Description");
				
			if(description == "") {
				response.getWriter().write("Description is empty\n");
			}
			
			if(amount > 0 && description != "") {
				int userID = Integer.parseInt(CookiesHandler.getCookieValue(request, "UserID"));
				Ticket newTicket = new Ticket(0, amount, description, "Pending", userID, 0);
				DAO dao = new DAO();
				response.getWriter().write("Reimbursement request sent.\n");
				dao.makeNewTicket(newTicket);
				response.setStatus(201);
			}
		}else{
			response.getWriter().write("You don't have permission to submit reimbursement requests.\n");
			response.setStatus(401);
		}

	}

}
