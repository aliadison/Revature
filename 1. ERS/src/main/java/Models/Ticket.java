package Models;

import java.util.Objects;

import Utility.AccountUtil;

public class Ticket {
	private int ticketID;
	private int amount;
	private String description;
	private String status;
	private int madeByID;
	private int reviewedByID;

	public Ticket() {

	}

	public Ticket(int ticketID, int amount, String description, String status, int madeByID, int reviewedByID) {
		this.ticketID = ticketID;
		this.amount = amount;
		this.description = description;
		this.status = status;
		this.madeByID = madeByID;
		this.reviewedByID = reviewedByID;
	}

	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getMadeByID() {
		return madeByID;
	}

	public void setMadeByID(int madeBy) {
		this.madeByID = madeBy;
	}

	public int getReviewedByID() {
		return reviewedByID;
	}

	public void setReviewedByID(int reviewedBy) {
		this.reviewedByID = reviewedBy;
	}

	@Override
	public String toString() {
		return "Ticket [ID= " + ticketID + ", amount= $" + amount + ", description=" + description + ", status=" + status + ", madeBy=" + AccountUtil.getUsernameByID(madeByID)
				+ ", reviewedBy=" + reviewedByID + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, description, madeByID, reviewedByID, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return amount == other.amount && Objects.equals(description, other.description)
				&& Objects.equals(madeByID, other.madeByID) && Objects.equals(reviewedByID, other.reviewedByID)
				&& Objects.equals(status, other.status);
	}

}
