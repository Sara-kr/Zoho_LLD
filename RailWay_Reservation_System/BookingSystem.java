import java.util.Scanner;

public class BookingSystem {
    public static void main(String[] args) {
        TicketSystem ticketSystem = new TicketSystem();
        Scanner sc = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("\n================================");
            System.out.println(" 1. Book Ticket");
            System.out.println(" 2. Cancel Ticket");
            System.out.println(" 3. View Available Tickets");
            System.out.println(" 4. View All Booked Tickets");
            System.out.println(" 5. Exit");
            System.out.println("================================");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1-5.");
                sc.nextLine(); // clear buffer
                continue;
            }

            switch (choice) {

                case 1 -> {
                    System.out.println("Enter Name, Age, Gender (male/female), Berth Preference (L/M/U):");
                    try {
                        String name      = sc.next();
                        int    age       = sc.nextInt();
                        String gender    = sc.next();
                        String berthPref = sc.next().toUpperCase();

                        // ── Input Validations ──
                        if (!berthPref.equals("L") && !berthPref.equals("M") && !berthPref.equals("U")) {
                            System.out.println("Invalid Berth Preference. Enter L, M, or U.");
                            sc.nextLine();
                            continue;
                        }
                        if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")) {
                            System.out.println("Invalid Gender. Enter male or female.");
                            sc.nextLine();
                            continue;
                        }
                        if (age <= 0) {
                            System.out.println("Invalid Age. Age must be a positive number.");
                            sc.nextLine();
                            continue;
                        }

                        Passenger p = new Passenger(name, age, gender, berthPref);
                        boolean booked = ticketSystem.bookTicket(p);

                        if (booked) {
                            System.out.println("\nTicket Booked Successfully!");
                            ticketSystem.bookedTicket(p);
                        } else {
                            System.out.println("Sorry! No tickets available.");
                        }

                    } catch (Exception e) {
                        System.out.println("Invalid input format. Please try again.");
                        sc.nextLine(); // clear buffer
                    }
                }

                case 2 -> {
                    System.out.print("Enter Ticket ID to cancel: ");
                    try {
                        int id = sc.nextInt();
                        if (ticketSystem.cancelTicket(id)) {
                            System.out.println("Ticket Cancelled Successfully!");
                        } else {
                            System.out.println("Ticket ID not found. Please check and try again.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid Ticket ID. Please enter a number.");
                        sc.nextLine(); // clear buffer
                    }
                }

                case 3 -> ticketSystem.availableTicket();

                case 4 -> ticketSystem.printAllTickets();

                case 5 -> {
                    System.out.println("Exiting... Thank you!");
                    loop = false;
                }

                default -> System.out.println("Invalid choice. Please choose between 1-5.");
            }
        }

        sc.close();
    }
}