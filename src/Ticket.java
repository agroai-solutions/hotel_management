// Stores booked ticket information
public class Ticket {
    private int ticketId;
    private String guestName;
    private int roomNumber;
    private String flightNumber;
    private String airline;
    private String destination;
    private String departureTime;
    private double price;
    private String bookingDate;
    private String status; // "Confirmed", "Cancelled"
    
    // Create new ticket
    public Ticket(int ticketId, String guestName, int roomNumber, String flightNumber, 
                  String airline, String destination, String departureTime, 
                  double price, String bookingDate) {
        this.ticketId = ticketId;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.destination = destination;
        this.departureTime = departureTime;
        this.price = price;
        this.bookingDate = bookingDate;
        this.status = "Confirmed";
    }
    
    // Getters
    public int getTicketId() {
        return ticketId;
    }
    
    public String getGuestName() {
        return guestName;
    }
    
    public int getRoomNumber() {
        return roomNumber;
    }
    
    public String getFlightNumber() {
        return flightNumber;
    }
    
    public String getAirline() {
        return airline;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public String getDepartureTime() {
        return departureTime;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getBookingDate() {
        return bookingDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    // Setters
    public void setStatus(String status) {
        this.status = status;
    }
    
    // Display ticket info
    public void displayInfo() {
        System.out.println("Ticket ID: " + ticketId + " | Guest: " + guestName + " | Room: " + roomNumber);
        System.out.println("  Flight: " + flightNumber + " | Airline: " + airline + " | To: " + destination);
        System.out.println("  Departure: " + departureTime + " | Price: $" + price);
        System.out.println("  Booked: " + bookingDate + " | Status: " + status);
    }
}
