package org.ck.event_expense_app;

import java.util.Scanner;

import org.ck.event_expense_app.Dao.EventExpenseAppDao;

/**
 * Hello world!
 *
 */
public class EventExpenseApp {
    public static void main(String[] args) {
        EventExpenseAppDao e1 = new EventExpenseAppDao();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nEvent Expense App Menu:");
            System.out.println("1. Create Event");
            System.out.println("2. Register Attendee");
            System.out.println("3. Log Expense");
            System.out.println("4. View Event Details");
            System.out.println("5. Update Event Name");
            System.out.println("6. Delete Expense");
            System.out.println("7. List Event Ticket Prices");
            System.out.println("8. Exit");
            System.out.print("Enter your choice (1-8): ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
                continue;
            }
            
            switch (choice) {
                case 1:
                    e1.createEvent();
                    break;
                    
                case 2:
                    e1.registerAttendee();
                    break;
                    
                case 3:
                    e1.logingExpense();
                    break;
                    
                case 4:
                    e1.viewEventDetails();
                    break;
                    
                case 5:
                    e1.updateEventName();
                    break;
                    
                case 6:
                    e1.deleteExpense();
                    break;
                    
                case 7:
                    e1.listOutEventTicketPrice();
                    break;
                    
                case 8:
                    System.out.println("Exiting application...");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice! Please select 1-8.");
                    break;
            }
        }
    }
}