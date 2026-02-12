import java.util.Scanner;

// Main class for air company collaboration operations
public class AirCompanyDepartment {
    // Arrays for storage
    private static Flight[] flights = new Flight[100];
    private static Ticket[] tickets = new Ticket[200];
    private static int flightCount = 0;
    private static int ticketCount = 0;
    private static int nextFlightId = 1;
    private static int nextTicketId = 1;
    
    private static Scanner scanner = new Scanner(System.in);
    
    // Main method
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("  AIR COMPANY COLLABORATION SYSTEM");
        System.out.println("======================================\n");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addFlight();
                    break;
                case 2:
                    viewAllFlights();
                    break;
                case 3:
                    bookTicket();
                    break;
                case 4:
                    viewAllBookings();
                    break;
                case 5:
                    cancelTicket();
                    break;
                case 6:
                    searchFlightsByDestination();
                    break;
                case 7:
                    viewFlightsByAirline();
                    break;
                case 8:
                    searchBooking();
                    break;
                case 9:
                    System.out.println("\nThank you for using Air Company Collaboration System!");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }
    
    // Display main menu
    private static void displayMenu() {
        System.out.println("\n======================================");
        System.out.println("            MAIN MENU");
        System.out.println("======================================");
        System.out.println("1. Add Flight");
        System.out.println("2. View All Flights");
        System.out.println("3. Book Ticket for Guest");
        System.out.println("4. View All Bookings");
        System.out.println("5. Cancel Ticket");
        System.out.println("6. Search Flights by Destination");
        System.out.println("7. View Flights by Airline");
        System.out.println("8. Search Booking");
        System.out.println("9. Exit");
        System.out.println("======================================");
    }
    
    // Add new flight
    private static void addFlight() {
        System.out.println("\n--- ADD NEW FLIGHT ---");
        
        if (flightCount >= flights.length) {
            System.out.println("Error: Flight database is full!");
            return;
        }
        
        System.out.print("Enter flight number: ");
        String flightNumber = scanner.nextLine();
        
        System.out.print("Enter airline name: ");
        String airline = scanner.nextLine();
        
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        
        System.out.print("Enter departure time (DD/MM/YYYY HH:MM): ");
        String departureTime = scanner.nextLine();
        
        double price = getDoubleInput("Enter ticket price: $");
        int seats = getIntInput("Enter available seats: ");
        
        Flight newFlight = new Flight(nextFlightId, flightNumber, airline, destination, 
                                     departureTime, price, seats);
        flights[flightCount] = newFlight;
        flightCount++;
        nextFlightId++;
        
        System.out.println("\n✓ Flight added successfully!");
        System.out.println("Flight ID: " + newFlight.getFlightId());
    }
    
    // View all flights
    private static void viewAllFlights() {
        System.out.println("\n--- ALL AVAILABLE FLIGHTS ---");
        
        if (flightCount == 0) {
            System.out.println("No flights available.");
            return;
        }
        
        System.out.println("Total Flights: " + flightCount);
        System.out.println("-----------------------------------------------------------");
        
        for (int i = 0; i < flightCount; i++) {
            flights[i].displayInfo();
            System.out.println();
        }
    }
    
    // Book ticket for guest
    private static void bookTicket() {
        System.out.println("\n--- BOOK FLIGHT TICKET ---");
        
        if (flightCount == 0) {
            System.out.println("No flights available. Please add flights first.");
            return;
        }
        
        if (ticketCount >= tickets.length) {
            System.out.println("Error: Ticket database is full!");
            return;
        }
        
        // Show available flights
        System.out.println("Available Flights:");
        System.out.println("-----------------------------------------------------------");
        boolean hasSeats = false;
        for (int i = 0; i < flightCount; i++) {
            if (flights[i].getAvailableSeats() > 0) {
                flights[i].displayInfo();
                System.out.println();
                hasSeats = true;
            }
        }
        
        if (!hasSeats) {
            System.out.println("No flights with available seats.");
            return;
        }
        
        int flightId = getIntInput("Enter flight ID to book: ");
        Flight selectedFlight = findFlightById(flightId);
        
        if (selectedFlight == null) {
            System.out.println("Error: Flight not found!");
            return;
        }
        
        if (selectedFlight.getAvailableSeats() <= 0) {
            System.out.println("Error: No seats available on this flight!");
            return;
        }
        
        System.out.print("\nEnter guest name: ");
        String guestName = scanner.nextLine();
        
        int roomNumber = getIntInput("Enter room number: ");
        
        System.out.print("Enter booking date (DD/MM/YYYY): ");
        String bookingDate = scanner.nextLine();
        
        // Create ticket
        Ticket newTicket = new Ticket(nextTicketId, guestName, roomNumber, 
                                     selectedFlight.getFlightNumber(),
                                     selectedFlight.getAirline(),
                                     selectedFlight.getDestination(),
                                     selectedFlight.getDepartureTime(),
                                     selectedFlight.getPrice(),
                                     bookingDate);
        tickets[ticketCount] = newTicket;
        ticketCount++;
        nextTicketId++;
        
        // Update flight seats
        selectedFlight.bookSeat();
        
        System.out.println("\n✓ Ticket booked successfully!");
        System.out.println("Ticket ID: " + newTicket.getTicketId());
        System.out.println("Total Price: $" + newTicket.getPrice());
    }
    
    // View all bookings
    private static void viewAllBookings() {
        System.out.println("\n--- ALL BOOKINGS ---");
        
        if (ticketCount == 0) {
            System.out.println("No bookings found.");
            return;
        }
        
        System.out.println("Total Bookings: " + ticketCount);
        System.out.println("-----------------------------------------------------------");
        
        for (int i = 0; i < ticketCount; i++) {
            tickets[i].displayInfo();
            System.out.println();
        }
    }
    
    // Cancel ticket
    private static void cancelTicket() {
        System.out.println("\n--- CANCEL TICKET ---");
        
        if (ticketCount == 0) {
            System.out.println("No bookings to cancel.");
            return;
        }
        
        // Show confirmed tickets
        System.out.println("Confirmed Tickets:");
        System.out.println("-----------------------------------------------------------");
        boolean foundConfirmed = false;
        for (int i = 0; i < ticketCount; i++) {
            if (tickets[i].getStatus().equals("Confirmed")) {
                tickets[i].displayInfo();
                System.out.println();
                foundConfirmed = true;
            }
        }
        
        if (!foundConfirmed) {
            System.out.println("No confirmed tickets to cancel.");
            return;
        }
        
        int ticketId = getIntInput("Enter ticket ID to cancel: ");
        Ticket ticket = findTicketById(ticketId);
        
        if (ticket == null) {
            System.out.println("Error: Ticket not found!");
            return;
        }
        
        if (!ticket.getStatus().equals("Confirmed")) {
            System.out.println("Error: This ticket is already cancelled!");
            return;
        }
        
        // Cancel ticket and restore seat
        ticket.setStatus("Cancelled");
        
        Flight flight = findFlightByNumber(ticket.getFlightNumber());
        if (flight != null) {
            flight.cancelSeat();
        }
        
        System.out.println("\n✓ Ticket cancelled successfully!");
        System.out.println("Seat restored to flight " + ticket.getFlightNumber());
    }
    
    // Search flights by destination
    private static void searchFlightsByDestination() {
        System.out.println("\n--- SEARCH FLIGHTS BY DESTINATION ---");
        
        if (flightCount == 0) {
            System.out.println("No flights available.");
            return;
        }
        
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine().toLowerCase();
        
        System.out.println("\nFlights to " + destination + ":");
        System.out.println("-----------------------------------------------------------");
        
        boolean found = false;
        for (int i = 0; i < flightCount; i++) {
            if (flights[i].getDestination().toLowerCase().contains(destination)) {
                flights[i].displayInfo();
                System.out.println();
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No flights found to this destination.");
        }
    }
    
    // View flights by airline
    private static void viewFlightsByAirline() {
        System.out.println("\n--- VIEW FLIGHTS BY AIRLINE ---");
        
        if (flightCount == 0) {
            System.out.println("No flights available.");
            return;
        }
        
        System.out.print("Enter airline name: ");
        String airline = scanner.nextLine().toLowerCase();
        
        System.out.println("\nFlights by " + airline + ":");
        System.out.println("-----------------------------------------------------------");
        
        boolean found = false;
        for (int i = 0; i < flightCount; i++) {
            if (flights[i].getAirline().toLowerCase().contains(airline)) {
                flights[i].displayInfo();
                System.out.println();
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No flights found for this airline.");
        }
    }
    
    // Search booking by guest name
    private static void searchBooking() {
        System.out.println("\n--- SEARCH BOOKING ---");
        
        if (ticketCount == 0) {
            System.out.println("No bookings available.");
            return;
        }
        
        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine().toLowerCase();
        
        System.out.println("\nBookings for " + guestName + ":");
        System.out.println("-----------------------------------------------------------");
        
        boolean found = false;
        for (int i = 0; i < ticketCount; i++) {
            if (tickets[i].getGuestName().toLowerCase().contains(guestName)) {
                tickets[i].displayInfo();
                System.out.println();
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No bookings found for this guest.");
        }
    }
    
    // Find flight by ID
    private static Flight findFlightById(int id) {
        for (int i = 0; i < flightCount; i++) {
            if (flights[i].getFlightId() == id) {
                return flights[i];
            }
        }
        return null;
    }
    
    // Find flight by number
    private static Flight findFlightByNumber(String flightNumber) {
        for (int i = 0; i < flightCount; i++) {
            if (flights[i].getFlightNumber().equals(flightNumber)) {
                return flights[i];
            }
        }
        return null;
    }
    
    // Find ticket by ID
    private static Ticket findTicketById(int id) {
        for (int i = 0; i < ticketCount; i++) {
            if (tickets[i].getTicketId() == id) {
                return tickets[i];
            }
        }
        return null;
    }
    
    // Get integer input
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Invalid input! Please enter a number: ");
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
    
    // Get double input
    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            scanner.nextLine();
            System.out.print("Invalid input! Please enter a number: ");
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
}
