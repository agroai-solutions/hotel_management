// Stores information about customer service requests
public class ServiceRequest {
    private int requestId;
    private int roomNumber;
    private String serviceType; // "Cleaning", "Personal Assistance", "Maintenance", "Laundry"
    private String description;
    private String status; // "Pending", "In Progress", "Completed"
    private String assignedStaff;
    
    // Create new service request
    public ServiceRequest(int requestId, int roomNumber, String serviceType, String description) {
        this.requestId = requestId;
        this.roomNumber = roomNumber;
        this.serviceType = serviceType;
        this.description = description;
        this.status = "Pending";
        this.assignedStaff = "Unassigned";
    }
    
    // Getters
    public int getRequestId() {
        return requestId;
    }
    
    public int getRoomNumber() {
        return roomNumber;
    }
    
    public String getServiceType() {
        return serviceType;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getAssignedStaff() {
        return assignedStaff;
    }
    
    // Setters
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setAssignedStaff(String assignedStaff) {
        this.assignedStaff = assignedStaff;
    }
    
    // Display request info
    public void displayInfo() {
        System.out.println("Request ID: " + requestId + " | Room: " + roomNumber + 
                         " | Type: " + serviceType + " | Status: " + status + 
                         " | Staff: " + assignedStaff);
        System.out.println("  Description: " + description);
    }
}
