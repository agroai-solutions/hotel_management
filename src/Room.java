// Stores room information
public class Room {
    private int roomNumber;
    private String roomType; // "Single", "Double", "Suite"
    private double pricePerNight;
    private String status; // "Available", "Occupied", "Maintenance"
    private String currentGuest;
    
    // Create new room
    public Room(int roomNumber, String roomType, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.status = "Available";
        this.currentGuest = "None";
    }
    
    // Getters
    public int getRoomNumber() {
        return roomNumber;
    }
    
    public String getRoomType() {
        return roomType;
    }
    
    public double getPricePerNight() {
        return pricePerNight;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getCurrentGuest() {
        return currentGuest;
    }
    
    // Setters
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setCurrentGuest(String currentGuest) {
        this.currentGuest = currentGuest;
    }
    
    // Display room info
    public void displayInfo() {
        System.out.println("Room " + roomNumber + " | Type: " + roomType + 
                         " | Price: $" + pricePerNight + "/night | Status: " + status + 
                         " | Guest: " + currentGuest);
    }
}
