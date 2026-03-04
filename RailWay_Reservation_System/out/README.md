# 🚆 Railway Reservation System

A console-based **Railway Ticket Reservation System** built in **Core Java** — developed as part of the **Zoho Level 3 Interview Challenge**.

---

## 📋 Problem Statement

Design and implement a Railway Ticket Reservation System that handles ticket booking, cancellation, and automatic seat promotion. The system must manage berth allocation based on passenger age and gender, and handle RAC and Waiting List queues efficiently.

### Constraints
- **63 confirmed berths** available per coach
- **9 side-lower berths** for RAC (2 passengers per berth = 18 RAC slots)
- **10 Waiting List** slots maximum
- If waiting list is full → print `"No tickets available"`
- **Children below age 5** → no berth allotted, but details are stored
- **Senior citizens (age 60+)** and **ladies** → priority for Lower berth
- **RAC passengers** → allocated Side-Lower berths only
- On cancellation → RAC is promoted to Confirmed, Waiting List is promoted to RAC

---

## ✨ Features

| # | Feature | Description |
|---|---------|-------------|
| 1 | 🎫 Book Ticket | Allocates berth based on availability, age, and gender priority |
| 2 | ❌ Cancel Ticket | Cancels ticket and auto-promotes RAC → Confirmed, WL → RAC |
| 3 | 📊 Check Availability | Displays remaining seats across all berth types |
| 4 | 📋 View All Tickets | Prints all booked tickets grouped by berth type |
| 5 | 🔢 Input Validation | Validates age, gender, and berth preference inputs |
| 6 | 👶 Child Handling | Children below 5 stored without berth allocation |
| 7 | 👴 Senior Priority | Age 60+ and females automatically get Lower berth |
| 8 | 🔄 Auto Promotion | RAC and Waiting List passengers promoted on cancellation |

---

## 🏗️ Project Structure

```
RailwayReservationSystem/
│
├── Passenger.java          # Passenger data model
│                           # Fields: ticketId, name, age, gender,
│                           #         berthPreference, allotedPreference
│
├── TicketSystem.java       # Core business logic
│                           # bookTicket()       - allocates berth
│                           # cancelTicket()     - cancels & promotes
│                           # availableTicket()  - shows availability
│                           # printAllTickets()  - prints all bookings
│
└── BookingSystem.java      # Main entry point
                            # Scanner-based console menu (choices 1–5)
```

---

## 🪑 Berth Allocation Rules

```
Priority Order for Booking:
──────────────────────────────────────────────────
1. Lower Berth   → Senior (60+) / Female (priority)
2. Lower Berth   → Any passenger (if still available)
3. Middle Berth  → General allocation
4. Upper Berth   → General allocation
5. RAC           → Side-Lower berth (shared)
6. Waiting List  → No berth allotted yet
7. No Vacancy    → Ticket not available
──────────────────────────────────────────────────
```

---

## 🔄 Cancellation & Promotion Flow

```
When a Confirmed ticket is cancelled:
  RAC passenger  ──promoted──▶  Confirmed Berth
  WL passenger   ──promoted──▶  RAC

When an RAC ticket is cancelled:
  WL passenger   ──promoted──▶  RAC

When a Waiting List ticket is cancelled:
  Removed from queue (no promotion needed)
```

---

## 📦 Berth Capacity (Demo / Test Values)

> ⚠️ These are reduced values used for testing. Scale to 63/18/10 for production.

| Berth Type   | Capacity |
|--------------|----------|
| Lower Berth  | 2        |
| Middle Berth | 2        |
| Upper Berth  | 2        |
| RAC          | 1        |
| Waiting List | 1        |

---

## 🚀 How to Run

### ▶️ In IntelliJ IDEA
```
1. Open the project in IntelliJ IDEA
2. Right-click  BookingSystem.java
3. Click        Run 'BookingSystem.main()'
```

### ▶️ In Terminal / Command Prompt
```bash
# Step 1: Compile all files
javac Passenger.java TicketSystem.java BookingSystem.java

# Step 2: Run the application
java BookingSystem
```

---

## 🖥️ Sample Console Output

```
================================
 1. Book Ticket
 2. Cancel Ticket
 3. View Available Tickets
 4. View All Booked Tickets
 5. Exit
================================
Enter your choice: 1

Enter Name, Age, Gender (male/female), Berth Preference (L/M/U):
Alice 65 female L

Ticket Booked Successfully!
----------------------------------
  Ticket ID : 1
  Name      : Alice
  Age       : 65
  Gender    : female
  Allotted  : LOWER
----------------------------------
```

```
========== ALL BOOKED TICKETS ==========

--- LOWER BERTH (2 / 2) ---
  TicketID : 1 | Name : Alice | Age : 65 | Gender : female | Berth : LOWER
  TicketID : 3 | Name : Carol | Age : 30 | Gender : female | Berth : LOWER

--- MIDDLE BERTH (1 / 2) ---
  TicketID : 2 | Name : Bob   | Age : 25 | Gender : male   | Berth : MIDDLE

--- RAC (1 / 1) ---
  TicketID : 4 | Name : Dave  | Age : 40 | Gender : male   | Berth : RAC

--- WAITING LIST (1 / 1) ---
  TicketID : 5 | Name : Eve   | Age : 22 | Gender : female | Berth : WAITING LIST

========== TOTAL BOOKED: 5 ==========
```

---

## 🗂️ Data Structures Used

| Structure | Used For | Why |
|-----------|----------|-----|
| `ArrayList` | Lower / Middle / Upper berths | Fast iteration and index-based access |
| `Queue (LinkedList)` | RAC and Waiting List | FIFO — first booked, first promoted |

---

## ✅ Edge Cases Handled

- Child below age 5 → stored but no berth assigned
- Senior citizen / Female → automatically gets Lower berth if available
- All berths full → falls to RAC → falls to Waiting List → rejects
- Cancel triggers automatic promotion chain
- Invalid gender / berth preference / age → validation error shown
- Non-numeric menu input → caught with try-catch, loop continues

---

## 🛠️ Tech Stack

| Technology | Details        |
|------------|----------------|
| Language   | Java (Core)    |
| IDE        | IntelliJ IDEA  |
| Input      | Scanner (Console) |
| Java Version | Java 14+ (Switch expressions used) |

---

## 👤 Author

**Your Name**
- 🔗 LinkedIn: `linkedin.com/in/yourprofile`
- 💻 GitHub: `github.com/yourusername`

---

## 📌 Note

> This project was built as part of the **Zoho Interview - Level 3 Coding Round**.  
> The challenge requires completing the full working application within **2.5 hours**  
> with proper OOP design, edge case handling, and clean code standards.