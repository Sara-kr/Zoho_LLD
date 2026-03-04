import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TicketSystem {

    private int ticketIdCounter = 1;

    private static ArrayList<Passenger> upperBerth  = new ArrayList<>();
    private static ArrayList<Passenger> middleBerth = new ArrayList<>();
    private static ArrayList<Passenger> lowerBerth  = new ArrayList<>();
    private static Queue<Passenger>     rac          = new LinkedList<>();
    private static Queue<Passenger>     waitingList  = new LinkedList<>();

    private static final int MAX_UPPER   = 2;
    private static final int MAX_MIDDLE  = 2;
    private static final int MAX_LOWER   = 2;
    private static final int MAX_RAC     = 1;
    private static final int MAX_WAITING = 1;

    // ── Module 1: Book Ticket ──────────────────────────────────────────────
    public boolean bookTicket(Passenger p) {

        // Children below age 5 — store but don't allot berth
        if (p.getAge() < 5) {
            System.out.println("Child below 5 - no berth allotted for: " + p.getName());
            return true;
        }

        // Senior citizens (60+) or Female → priority for Lower berth
        boolean needsLower = p.getAge() >= 60 ||
                p.getGender().equalsIgnoreCase("female");

        if (needsLower && lowerBerth.size() < MAX_LOWER) {
            p.setTicketId(ticketIdCounter++);
            p.setAllotedPreference("LOWER");
            lowerBerth.add(p);
            return true;
        } else if (lowerBerth.size() < MAX_LOWER) {
            p.setTicketId(ticketIdCounter++);
            p.setAllotedPreference("LOWER");
            lowerBerth.add(p);
            return true;
        } else if (middleBerth.size() < MAX_MIDDLE) {
            p.setTicketId(ticketIdCounter++);
            p.setAllotedPreference("MIDDLE");
            middleBerth.add(p);
            return true;
        } else if (upperBerth.size() < MAX_UPPER) {
            p.setTicketId(ticketIdCounter++);
            p.setAllotedPreference("UPPER");
            upperBerth.add(p);
            return true;
        } else if (rac.size() < MAX_RAC) {
            p.setTicketId(ticketIdCounter++);
            p.setAllotedPreference("RAC");
            rac.add(p);
            return true;
        } else if (waitingList.size() < MAX_WAITING) {
            p.setTicketId(ticketIdCounter++);
            p.setAllotedPreference("WAITING LIST");
            waitingList.add(p);
            return true;
        } else {
            return false; // No tickets available
        }
    }

    // ── Module 2: Cancel Ticket ────────────────────────────────────────────
    public boolean cancelTicket(int ticketId) {

        // Check Lower Berth
        for (Passenger p : lowerBerth) {
            if (p.getTicketId() == ticketId) {
                lowerBerth.remove(p);
                promoteRACToConfirmed(lowerBerth, "LOWER");
                promoteWaitingToRAC();
                System.out.println("Ticket " + ticketId + " cancelled from LOWER BERTH.");
                return true;
            }
        }

        // Check Middle Berth
        for (Passenger p : middleBerth) {
            if (p.getTicketId() == ticketId) {
                middleBerth.remove(p);
                promoteRACToConfirmed(middleBerth, "MIDDLE");
                promoteWaitingToRAC();
                System.out.println("Ticket " + ticketId + " cancelled from MIDDLE BERTH.");
                return true;
            }
        }

        // Check Upper Berth
        for (Passenger p : upperBerth) {
            if (p.getTicketId() == ticketId) {
                upperBerth.remove(p);
                promoteRACToConfirmed(upperBerth, "UPPER");
                promoteWaitingToRAC();
                System.out.println("Ticket " + ticketId + " cancelled from UPPER BERTH.");
                return true;
            }
        }

        // Check RAC
        for (Passenger p : rac) {
            if (p.getTicketId() == ticketId) {
                rac.remove(p);
                promoteWaitingToRAC();
                System.out.println("Ticket " + ticketId + " cancelled from RAC.");
                return true;
            }
        }

        // Check Waiting List
        for (Passenger p : waitingList) {
            if (p.getTicketId() == ticketId) {
                waitingList.remove(p);
                System.out.println("Ticket " + ticketId + " cancelled from WAITING LIST.");
                return true;
            }
        }

        return false; // Ticket not found
    }

    // ── Promote RAC → Confirmed berth ─────────────────────────────────────
    private void promoteRACToConfirmed(ArrayList<Passenger> berth, String berthLabel) {
        if (!rac.isEmpty()) {
            Passenger promoted = rac.poll();
            promoted.setAllotedPreference(berthLabel);
            berth.add(promoted);
            System.out.println("  ➜ " + promoted.getName() + " promoted: RAC → " + berthLabel);
        }
    }

    // ── Promote Waiting List → RAC ─────────────────────────────────────────
    private void promoteWaitingToRAC() {
        if (!waitingList.isEmpty() && rac.size() < MAX_RAC) {
            Passenger promoted = waitingList.poll();
            promoted.setAllotedPreference("RAC");
            rac.add(promoted);
            System.out.println("  ➜ " + promoted.getName() + " promoted: WAITING LIST → RAC");
        }
    }

    // ── Module 3: Check Availability ──────────────────────────────────────
    public void availableTicket() {
        System.out.println("\n========== AVAILABILITY ==========");
        System.out.println("Lower Berth  : " + (MAX_LOWER   - lowerBerth.size())  + " left / " + MAX_LOWER   + " total");
        System.out.println("Middle Berth : " + (MAX_MIDDLE  - middleBerth.size()) + " left / " + MAX_MIDDLE  + " total");
        System.out.println("Upper Berth  : " + (MAX_UPPER   - upperBerth.size())  + " left / " + MAX_UPPER   + " total");
        System.out.println("RAC          : " + (MAX_RAC     - rac.size())         + " left / " + MAX_RAC     + " total");
        System.out.println("Waiting List : " + (MAX_WAITING - waitingList.size()) + " left / " + MAX_WAITING + " total");
        System.out.println("==================================\n");
    }

    // ── Module 4: Print All Tickets ────────────────────────────────────────
    public void printAllTickets() {
        System.out.println("\n========== ALL BOOKED TICKETS ==========");

        System.out.println("\n--- LOWER BERTH (" + lowerBerth.size() + " / " + MAX_LOWER + ") ---");
        printList(lowerBerth);

        System.out.println("\n--- MIDDLE BERTH (" + middleBerth.size() + " / " + MAX_MIDDLE + ") ---");
        printList(middleBerth);

        System.out.println("\n--- UPPER BERTH (" + upperBerth.size() + " / " + MAX_UPPER + ") ---");
        printList(upperBerth);

        System.out.println("\n--- RAC (" + rac.size() + " / " + MAX_RAC + ") ---");
        printQueue(rac);

        System.out.println("\n--- WAITING LIST (" + waitingList.size() + " / " + MAX_WAITING + ") ---");
        printQueue(waitingList);

        int total = lowerBerth.size() + middleBerth.size() +
                upperBerth.size() + rac.size() + waitingList.size();
        System.out.println("\n========== TOTAL BOOKED: " + total + " ==========\n");
    }

    // ── Print booked ticket immediately after booking ──────────────────────
    public void bookedTicket(Passenger p) {
        System.out.println("----------------------------------");
        System.out.println("  Ticket ID : " + p.getTicketId());
        System.out.println("  Name      : " + p.getName());
        System.out.println("  Age       : " + p.getAge());
        System.out.println("  Gender    : " + p.getGender());
        System.out.println("  Allotted  : " + p.getAllotedPreference());
        System.out.println("----------------------------------");
    }

    // ── Helper: Print ArrayList ────────────────────────────────────────────
    private void printList(List<Passenger> list) {
        if (list.isEmpty()) { System.out.println("  None"); return; }
        for (Passenger p : list) printPassenger(p);
    }

    // ── Helper: Print Queue (without removing) ─────────────────────────────
    private void printQueue(Queue<Passenger> queue) {
        if (queue.isEmpty()) { System.out.println("  None"); return; }
        for (Passenger p : queue) printPassenger(p);   // for-each — safe, no removal
    }

    // ── Helper: Print single passenger row ────────────────────────────────
    private void printPassenger(Passenger p) {
        System.out.println(
                "  TicketID : " + p.getTicketId()          +
                        " | Name   : " + p.getName()               +
                        " | Age    : " + p.getAge()                +
                        " | Gender : " + p.getGender()             +
                        " | Berth  : " + p.getAllotedPreference()
        );
    }
}