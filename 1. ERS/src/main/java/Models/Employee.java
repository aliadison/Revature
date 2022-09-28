package Models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Employee {
	private int id;
	private String username;
	@JsonIgnore
	private String password;
	private String role;
	private int accountBalance;

	public Employee() {
		
	}

	public Employee(int id, String username, String password, String role, int accountBalance) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.accountBalance = accountBalance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	
	@Override
	public String toString() {
		return "[Username=" + username + ", Role=" + role + ", id=" + id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountBalance, id, password, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return accountBalance == other.accountBalance && id == other.id && Objects.equals(password, other.password)
				&& Objects.equals(role, other.role) && Objects.equals(username, other.username);
	}
}
