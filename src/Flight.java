// Stores flight information
public class Flight {
    private int flightId;
    private String flightNumber;
    private String airline;
    private String destination;
    private String departureTime;
    private double price;
    private int availableSeats;
    
    // Create new flight
    public Flight(int flightId, String flightNumber, String airline, String destination, 
                  String departureTime, double price, int availableSeats) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.destination = destination;
        this.departureTime = departureTime;
        this.price = price;
        this.availableSeats = availableSeats;
    }
    
    // Getters
    public int getFlightId() {
        return flightId;
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
    
    public int getAvailableSeats() {
        return availableSeats;
    }
    
    // Setters
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    
    public void bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
        }
    }
    
    public void cancelSeat() {
        availableSeats++;
    }
    
    // Display flight info
    public void displayInfo() {
        System.out.println("ID: " + flightId + " | Flight: " + flightNumber + " | Airline: " + airline);
        System.out.println("  Destination: " + destination + " | Departure: " + departureTime);
        System.out.println("  Price: $" + price + " | Available Seats: " + availableSeats);
    }
}
