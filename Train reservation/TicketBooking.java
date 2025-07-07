import java.util.*;

public class TicketBooking
{
    public static void main(String[] args)
    {
        
        TicketSystem ticketSystem = new TicketSystem();
        Scanner sc = new Scanner(System.in);


        while(true)
        {
            System.out.println("\n Railway Booking System: ");
            System.out.println("1. Book Tickets");
            System.out.println("2. Cancel Tickets");
            System.out.println("3. View Confirmed Tickets");
            System.out.println("4. View Available Tickets");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Gender: ");
                    String gender = sc.nextLine();
                    System.out.print("Enter BerthPreferance(L/U/M): ");
                    String berthPreferance = sc.nextLine();
                    ticketSystem.bookTicket(name, age, gender, berthPreferance);
                    break;
            
                case 2:
                    System.out.println("Enter the ticket ID to cancel: ");
                    String ticketId = sc.nextLine();
                    ticketSystem.cancelTicket(ticketId);
                    break;
                
                case 3:
                    ticketSystem.printBookedTickets();
                    break;

                case 4:
                    ticketSystem.printAvailableTickets();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice tryy again. ");
            }
        }



    }          



}
