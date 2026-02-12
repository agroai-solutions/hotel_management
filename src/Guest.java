// Stores guest information
public class Guest {
    private int guestId;
    private String name;
    private String phoneNumber;
    private String email;
    private int roomNumber;
    private String checkInDate;
    private String checkOutDate;
    private int numberOfNights;
    private double totalBill;
    
    // Create new guest
    public Guest(int guestId, String name, String phoneNumber, String email, 
                 int roomNumber, String checkInDate, String checkOutDate, 
                 int numberOfNights, double totalBill) {
        this.guestId = guestId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfNights = numberOfNights;
        this.totalBill = totalBill;
    }
    
    // Getters
    public int getGuestId() {
        return guestId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public int getRoomNumber() {
        return roomNumber;
    }
    
    public String getCheckInDate() {
        return checkInDate;
    }
    
    public String getCheckOutDate() {
        return checkOutDate;
    }
    
    public int getNumberOfNights() {
        return numberOfNights;
    }
    
    public double getTotalBill() {
        return totalBill;
    }
    
    // Display guest info
    public void displayInfo() {
        System.out.println("Guest ID: " + guestId + " | Name: " + name + " | Room: " + roomNumber);
        System.out.println("  Phone: " + phoneNumber + " | Email: " + email);
        System.out.println("  Check-in: " + checkInDate + " | Check-out: " + checkOutDate + 
                         " | Nights: " + numberOfNights + " | Bill: $" + totalBill);
    }
}
