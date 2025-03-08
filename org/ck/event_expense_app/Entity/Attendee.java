package org.ck.event_expense_app.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Attendee {
	@Id
	private int aId;
	private String userName;
	private String email;
	
	@ManyToOne
	private Event event;

	public int getaId() {
		return aId;
	}

	public void setaId(int aId) {
		this.aId = aId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
	    return "Attendee {" +
	           "aId=" + aId +
	           ", userName='" + userName + '\'' +
	           ", email='" + email + '\'' +
	           ", event=" + event.getEventName() +
	           "}\n";
	}
	
}
