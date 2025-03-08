package org.ck.event_expense_app.Dao;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.ck.event_expense_app.Entity.Attendee;
import org.ck.event_expense_app.Entity.Event;
import org.ck.event_expense_app.Entity.Expense;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

public class EventExpenseAppDao {

	Scanner sc = new Scanner(System.in);
	Configuration cfg = new Configuration().configure().addAnnotatedClass(Event.class).addAnnotatedClass(Attendee.class)
			.addAnnotatedClass(Expense.class);
	SessionFactory sf = cfg.buildSessionFactory();
	Session session = sf.openSession();
	CriteriaBuilder cb = session.getCriteriaBuilder();

	CriteriaQuery<Event> EventQuery = cb.createQuery(Event.class);
	CriteriaQuery<Attendee> AQuery = cb.createQuery(Attendee.class);
	CriteriaQuery<Expense> EQuery = cb.createQuery(Expense.class);

	Root<Event> Evroot = EventQuery.from(Event.class);
	Root<Attendee> Aroot = AQuery.from(Attendee.class);
	Root<Expense> Eroot = EQuery.from(Expense.class);

	public void createEvent() {
		/*
		 * Take input form the user.
		 * 
		 * Adding details eventID eventName eventDate eventPrice
		 */

		Event event = new Event();
		System.out.println("Enter the event id");
		event.setEventId(sc.nextInt());
		sc.nextLine(); // Consume the leftover newline

		System.out.println("Enter the event hall name");
		event.setEventName(sc.nextLine());

		System.out.println("Enter the event date");
		event.setEventDate(sc.nextLine());

		System.out.println("Enter the event price");
		event.setEventTicketPrice(sc.nextInt());
		sc.nextLine(); // Consume the leftover newline (if needed later)

		Transaction tran = session.beginTransaction();

		session.save(event);

		tran.commit();

	}

	public void registerAttendee() {
		/*
		 * Take input from the user
		 *
		 * Adding details attendeeId userName userEmail setEvent()
		 */
		Transaction tran = session.beginTransaction();
		Attendee attendees = new Attendee();
		System.out.println("Enter the attendees id");
		attendees.setaId(sc.nextInt());
		sc.nextLine(); // Consume the leftover newline

		System.out.println("Enter the user name");
		attendees.setUserName(sc.nextLine());

		System.out.println("Enter the email");
		attendees.setEmail(sc.nextLine());

		System.out.println("Enter the valid event id");
		Event event = session.get(Event.class, sc.nextInt());
		attendees.setEvent(event);

		List<Attendee> alist = event.getAttendee();
		alist.add(attendees);

		event.setAttendee(alist);

		session.save(attendees);

		tran.commit();

	}

	public void logingExpense() {
		/*
		 * Take input from the user
		 *
		 * Adding details exeId amount Date category setEvent()
		 */
		Transaction tran = session.beginTransaction();

		Expense expense = new Expense();

		System.out.println("Enter the expense id");
		expense.setExeId(sc.nextInt());
		sc.nextLine();

		System.out.println("Enter the amount ");
		expense.setAmount(sc.nextInt());
		sc.nextLine();

		System.out.println("Enter the date");
		expense.setExedate(sc.next());
		sc.nextLine();
		System.out.println("Enter the catogire");
		expense.setCategory(sc.nextLine());

		System.out.println("Enter the valid event id");
		Event event = session.get(Event.class, sc.nextInt());
		expense.setEvent(event);

		List<Expense> elist = event.getExpense();
		elist.add(expense);

		event.setExpense(elist);

		session.save(expense);

		tran.commit();
	}

	// From now on-words use the criteriaBuilder
	public void viewEventDetails() {
		/*
		 * Display an event with its attendees and total expenses. like 1) event details
		 * 2) Attendees 3) Expenses
		 */
		EventQuery.select(Evroot);
		AQuery.select(Aroot);
		EQuery.select(Eroot);

		Query<Event> eq = session.createQuery(EventQuery);
		List<Event> elist = eq.list();
		Query<Attendee> aq = session.createQuery(AQuery);
		List<Attendee> alist = aq.list();
		Query<Expense> exq = session.createQuery(EQuery);
		List<Expense> exlist = exq.list();

		Iterator<Event> et = elist.iterator();
		Iterator<Attendee> at = alist.iterator();
		Iterator<Expense> ext = exlist.iterator();
		while (et.hasNext() && at.hasNext() && ext.hasNext()) {
			Event event = (Event) et.next();
			System.out.println(event);
			System.out.println(at.next());
			System.out.println(ext.next());
		}

	}

	public void updateEventName() {
		/*
		 * Update the location by id
		 */
		Transaction tran = session.beginTransaction();
		CriteriaUpdate<Event> cu = cb.createCriteriaUpdate(Event.class);
		Root<Event> root = cu.from(Event.class);

		System.out.println("Enter the event hall name");
		cu.set(root.get("eventName"), sc.nextLine());

		System.out.println("Enter the event id to change");
		cu.where(cb.equal(root.get("eventId"), sc.nextInt()));

		Query<Event> eq = session.createQuery(cu);
		int rowupdated = eq.executeUpdate();
		System.out.println("Name got updated");
		tran.commit();

	}

	public void deleteExpense() {
		/*
		 * delete the expense.
		 */

		Transaction tran = session.beginTransaction();

		// Create CriteriaDelete for Expense
		CriteriaDelete<Expense> ecd = cb.createCriteriaDelete(Expense.class);
		Root<Expense> root1 = ecd.from(Expense.class);

		// Set the where condition (assuming exeId is the foreign key to Event)
		ecd.where(cb.equal(root1.get("exeId"), 23));

		// Execute the delete query
		Query<Expense> eq = session.createQuery(ecd); // Type should be Expense, not Event
		int rowsUpdated = eq.executeUpdate();
		System.out.println("Deleted " + rowsUpdated + " rows");

		tran.commit();

	}

	public void listOutEventTicketPrice() {
		/*
		 * list out the events between minimum and maximum price
		 */
		System.out.println("Min value");
		int min = sc.nextInt();
		System.out.println("Max value");
		int max = sc.nextInt();
		EventQuery.select(Evroot);
		EventQuery.where(cb.and(cb.between(Evroot.get("eventTicketPrice"), min, max)));
		Query<Event> eq = session.createQuery(EventQuery);
		List<Event> elist = eq.list();
		Iterator<Event> et = elist.iterator();
		while (et.hasNext()) {
			Event event = (Event) et.next();
			System.out.println(event);
			
		}
	}


}
