// Stores information about service staff members
public class ServiceStaff {
    private int staffId;
    private String name;
    private String role; // "Cleaner", "Personal Assistant", "Maintenance"
    private String status; // "Available", "Busy"
    private int tasksCompleted;
    
    // Create new staff member
    public ServiceStaff(int staffId, String name, String role) {
        this.staffId = staffId;
        this.name = name;
        this.role = role;
        this.status = "Available";
        this.tasksCompleted = 0;
    }
    
    // Getters
    public int getStaffId() {
        return staffId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getRole() {
        return role;
    }
    
    public String getStatus() {
        return status;
    }
    
    public int getTasksCompleted() {
        return tasksCompleted;
    }
    
    // Setters
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void incrementTasks() {
        this.tasksCompleted++;
    }
    
    // Display staff info
    public void displayInfo() {
        System.out.println("ID: " + staffId + " | Name: " + name + " | Role: " + role + 
                         " | Status: " + status + " | Tasks Done: " + tasksCompleted);
    }
}
