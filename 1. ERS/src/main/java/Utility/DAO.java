package Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import MainMenu.ERS;
import Models.Employee;
import Models.Ticket;

public class DAO {
	
	//Account management
	public ArrayList<Employee> retrieveEmployeesList() {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		final String SQL = "SELECT * FROM employees";
		ArrayList<Employee> employeesList = new ArrayList<Employee>();
		
		try {
			conn = ConnectionUtil.makeConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			while(set.next()) {
				Employee newAccount = new Employee(
						set.getInt(1), 
						set.getString(2),
						set.getString(3),
						set.getString(4),
						set.getInt(5));
				employeesList.add(newAccount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
				set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ERS.employeesList.clear();
		return employeesList;
	}
	
	public void addNewEmployee(Employee employee) {
		final String SQL = "INSERT INTO employees VALUES (DEFAULT, ?, ?, 'associate', 0)";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionUtil.makeConnection();
			stmt = conn.prepareStatement(SQL);
			
			stmt.setString(1, employee.getUsername());
			stmt.setString(2, employee.getPassword());
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateEmployeeBalance(int employeeID, int newBalance) {
		Connection conn = null;
		PreparedStatement stmt = null;
		final String SQL = "UPDATE employees SET accountBalance = ? WHERE id = ?"; 
		
		try {
			conn = ConnectionUtil.makeConnection();
			stmt = conn.prepareStatement(SQL);
			
			stmt.setInt(1, newBalance);
			stmt.setInt(2, employeeID);
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	//Ticket Management
	
	public ArrayList<Ticket> retrieveTicketsList(){
		ArrayList<Ticket> ticketsList = new ArrayList<Ticket>();
		final String SQL = "SELECT * FROM tickets";
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionUtil.makeConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			while(set.next()) {
				Ticket ticket = new Ticket(
						set.getInt(1),
						set.getInt(2),
						set.getString(3),
						set.getString(4),
						set.getInt(5),
						set.getInt(6)
						);

				ticketsList.add(ticket);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
				set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

		ERS.ticketsList.clear();
		return ticketsList;
	}
	
	public void makeNewTicket(Ticket ticket) {
		final String SQL = "INSERT INTO tickets VALUES (DEFAULT, ?, ?, 'Pending', ?, NULL)";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionUtil.makeConnection();
			stmt = conn.prepareStatement(SQL);
			
			stmt.setInt(1, ticket.getAmount());
			stmt.setString(2, ticket.getDescription());
			stmt.setInt(3, ticket.getMadeByID());
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void updateTicketStatus(int ticketID, String status, int reviewedByID) {
		Connection conn = null;
		PreparedStatement stmt = null;
		final String SQL = "UPDATE tickets SET status = ?, reviewedByID = ? WHERE ticketID = ?";
		
		try {
			conn = ConnectionUtil.makeConnection();
			stmt = conn.prepareStatement(SQL);
			
			stmt.setString(1, status);
			stmt.setInt(2, reviewedByID);
			stmt.setInt(3, ticketID);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
