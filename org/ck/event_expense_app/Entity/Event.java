package org.ck.event_expense_app.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Event {
	@Id
	private int eventId;
	private String eventName;
	private String eventDate;
	private int eventTicketPrice;

	@OneToMany(mappedBy = "event")
	private List<Attendee> attendee;

	
	public List<Attendee> getAttendee() {
		return attendee;
	}

	public void setAttendee(List<Attendee> attendee) {
		this.attendee = attendee;
	}

	@OneToMany(mappedBy = "event")
	private List<Expense> expense;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public List<Expense> getExpense() {
		return expense;
	}

	public void setExpense(List<Expense> expense) {
		this.expense = expense;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public int getEventTicketPrice() {
		return eventTicketPrice;
	}

	public void setEventTicketPrice(int eventTicketPrice) {
		this.eventTicketPrice = eventTicketPrice;
	}

	@Override
	public String toString() {
	    return "Event {" +
	           "eventId=" + eventId +
	           ", eventName='" + eventName + '\'' +
	           ", eventDate=" + eventDate +
	           ", eventTicketPrice=" + eventTicketPrice +
	           '}';
	}

	

}
