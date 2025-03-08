package org.ck.event_expense_app.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Expense {
	@Id
	private int exeId;
	private int amount;
	private String exedate;
	public String getExedate() {
		return exedate;
	}

	public void setExedate(String exedate) {
		this.exedate = exedate;
	}

	private String category;

	@ManyToOne
	private Event event;

	public int getExeId() {
		return exeId;
	}

	public void setExeId(int exeId) {
		this.exeId = exeId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
	    return "Expense {" +
	           "exeId=" + exeId +
	           ", amount=" + amount +
	           ", exedate=" + exedate +
	           ", category='" + category + '\'' +
	           ", event=" + event.getEventName() +
	           "}\n";
	}
	

}
