/**
 * Applicant class - Represents a job applicant in the hotel
 * This class stores information about people applying for jobs
 */
public class Applicant {
    // Applicant attributes
    private int id;
    private String name;
    private String position;
    private String phoneNumber;
    private String status; // "Pending", "Interviewed", "Hired", "Rejected"
    
    // Constructor - creates a new applicant
    public Applicant(int id, String name, String position, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.status = "Pending"; // Default status
    }
    
    // Getter methods - to access private data
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPosition() {
        return position;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getStatus() {
        return status;
    }
    
    // Setter for status - to update applicant status
    public void setStatus(String status) {
        this.status = status;
    }
    
    // Method to display applicant information
    public void displayInfo() {
        System.out.println("ID: " + id + " | Name: " + name + " | Position: " + position + 
                         " | Phone: " + phoneNumber + " | Status: " + status);
    }
}
