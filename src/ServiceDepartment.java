import java.util.Scanner;

// Main class for managing service requests and staff
public class ServiceDepartment {
    // Arrays for storage
    private static ServiceRequest[] requests = new ServiceRequest[100];
    private static ServiceStaff[] staff = new ServiceStaff[50];
    private static int requestCount = 0;
    private static int staffCount = 0;
    private static int nextRequestId = 1;
    private static int nextStaffId = 1;
    
    private static Scanner scanner = new Scanner(System.in);
    
    // Main method
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("   HOTEL SERVICE DEPARTMENT SYSTEM");
        System.out.println("======================================\n");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addServiceRequest();
                    break;
                case 2:
                    viewAllRequests();
                    break;
                case 3:
                    assignStaffToRequest();
                    break;
                case 4:
                    updateRequestStatus();
                    break;
                case 5:
                    completeRequest();
                    break;
                case 6:
                    addStaff();
                    break;
                case 7:
                    viewAllStaff();
                    break;
                case 8:
                    viewPendingRequests();
                    break;
                case 9:
                    System.out.println("\nThank you for using Service Department System!");
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
        System.out.println("1. Add Service Request");
        System.out.println("2. View All Requests");
        System.out.println("3. Assign Staff to Request");
        System.out.println("4. Update Request Status");
        System.out.println("5. Complete Request");
        System.out.println("6. Add Staff Member");
        System.out.println("7. View All Staff");
        System.out.println("8. View Pending Requests");
        System.out.println("9. Exit");
        System.out.println("======================================");
    }
    
    // Add new service request
    private static void addServiceRequest() {
        System.out.println("\n--- ADD SERVICE REQUEST ---");
        
        if (requestCount >= requests.length) {
            System.out.println("Error: Request database is full!");
            return;
        }
        
        int roomNumber = getIntInput("Enter room number: ");
        
        System.out.println("\nService Types:");
        System.out.println("1. Cleaning");
        System.out.println("2. Personal Assistance");
        System.out.println("3. Maintenance");
        System.out.println("4. Laundry");
        int typeChoice = getIntInput("Select service type: ");
        
        String serviceType;
        switch (typeChoice) {
            case 1: serviceType = "Cleaning"; break;
            case 2: serviceType = "Personal Assistance"; break;
            case 3: serviceType = "Maintenance"; break;
            case 4: serviceType = "Laundry"; break;
            default: 
                System.out.println("Invalid choice!");
                return;
        }
        
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        
        ServiceRequest newRequest = new ServiceRequest(nextRequestId, roomNumber, serviceType, description);
        requests[requestCount] = newRequest;
        requestCount++;
        nextRequestId++;
        
        System.out.println("\n✓ Service request added successfully!");
        System.out.println("Request ID: " + newRequest.getRequestId());
    }
    
    // View all service requests
    private static void viewAllRequests() {
        System.out.println("\n--- ALL SERVICE REQUESTS ---");
        
        if (requestCount == 0) {
            System.out.println("No service requests in the system.");
            return;
        }
        
        System.out.println("Total Requests: " + requestCount);
        System.out.println("-----------------------------------------------------------");
        
        for (int i = 0; i < requestCount; i++) {
            requests[i].displayInfo();
        }
    }
    
    // Assign staff to a request
    private static void assignStaffToRequest() {
        System.out.println("\n--- ASSIGN STAFF TO REQUEST ---");
        
        if (requestCount == 0) {
            System.out.println("No service requests available.");
            return;
        }
        
        if (staffCount == 0) {
            System.out.println("No staff members available. Please add staff first.");
            return;
        }
        
        // Show pending requests
        System.out.println("Pending Requests:");
        boolean foundPending = false;
        for (int i = 0; i < requestCount; i++) {
            if (requests[i].getStatus().equals("Pending")) {
                requests[i].displayInfo();
                foundPending = true;
            }
        }
        
        if (!foundPending) {
            System.out.println("No pending requests to assign.");
            return;
        }
        
        int requestId = getIntInput("\nEnter request ID to assign: ");
        ServiceRequest request = findRequestById(requestId);
        
        if (request == null) {
            System.out.println("Error: Request not found!");
            return;
        }
        
        if (!request.getStatus().equals("Pending")) {
            System.out.println("Error: This request is already assigned!");
            return;
        }
        
        // Show available staff
        System.out.println("\nAvailable Staff:");
        boolean foundAvailable = false;
        for (int i = 0; i < staffCount; i++) {
            if (staff[i].getStatus().equals("Available")) {
                staff[i].displayInfo();
                foundAvailable = true;
            }
        }
        
        if (!foundAvailable) {
            System.out.println("No staff available at the moment.");
            return;
        }
        
        int staffId = getIntInput("\nEnter staff ID to assign: ");
        ServiceStaff selectedStaff = findStaffById(staffId);
        
        if (selectedStaff == null) {
            System.out.println("Error: Staff not found!");
            return;
        }
        
        if (!selectedStaff.getStatus().equals("Available")) {
            System.out.println("Error: This staff member is busy!");
            return;
        }
        
        // Assign staff to request
        request.setAssignedStaff(selectedStaff.getName());
        request.setStatus("In Progress");
        selectedStaff.setStatus("Busy");
        
        System.out.println("\n✓ Staff assigned successfully!");
        System.out.println(selectedStaff.getName() + " assigned to Request #" + requestId);
    }
    
    // Update request status
    private static void updateRequestStatus() {
        System.out.println("\n--- UPDATE REQUEST STATUS ---");
        
        if (requestCount == 0) {
            System.out.println("No service requests available.");
            return;
        }
        
        int requestId = getIntInput("Enter request ID: ");
        ServiceRequest request = findRequestById(requestId);
        
        if (request == null) {
            System.out.println("Error: Request not found!");
            return;
        }
        
        System.out.println("\nCurrent Status: " + request.getStatus());
        System.out.println("\nNew Status:");
        System.out.println("1. Pending");
        System.out.println("2. In Progress");
        System.out.println("3. Completed");
        
        int choice = getIntInput("Select new status: ");
        
        switch (choice) {
            case 1: request.setStatus("Pending"); break;
            case 2: request.setStatus("In Progress"); break;
            case 3: request.setStatus("Completed"); break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        
        System.out.println("\n✓ Status updated successfully!");
    }
    
    // Complete a request
    private static void completeRequest() {
        System.out.println("\n--- COMPLETE REQUEST ---");
        
        if (requestCount == 0) {
            System.out.println("No service requests available.");
            return;
        }
        
        // Show in-progress requests
        System.out.println("Requests In Progress:");
        boolean foundInProgress = false;
        for (int i = 0; i < requestCount; i++) {
            if (requests[i].getStatus().equals("In Progress")) {
                requests[i].displayInfo();
                foundInProgress = true;
            }
        }
        
        if (!foundInProgress) {
            System.out.println("No requests in progress.");
            return;
        }
        
        int requestId = getIntInput("\nEnter request ID to complete: ");
        ServiceRequest request = findRequestById(requestId);
        
        if (request == null) {
            System.out.println("Error: Request not found!");
            return;
        }
        
        if (!request.getStatus().equals("In Progress")) {
            System.out.println("Error: This request is not in progress!");
            return;
        }
        
        // Mark as completed and free up staff
        request.setStatus("Completed");
        
        String staffName = request.getAssignedStaff();
        ServiceStaff assignedStaff = findStaffByName(staffName);
        if (assignedStaff != null) {
            assignedStaff.setStatus("Available");
            assignedStaff.incrementTasks();
        }
        
        System.out.println("\n✓ Request completed successfully!");
    }
    
    // Add new staff member
    private static void addStaff() {
        System.out.println("\n--- ADD STAFF MEMBER ---");
        
        if (staffCount >= staff.length) {
            System.out.println("Error: Staff database is full!");
            return;
        }
        
        System.out.print("Enter staff name: ");
        String name = scanner.nextLine();
        
        System.out.println("\nRoles:");
        System.out.println("1. Cleaner");
        System.out.println("2. Personal Assistant");
        System.out.println("3. Maintenance");
        int roleChoice = getIntInput("Select role: ");
        
        String role;
        switch (roleChoice) {
            case 1: role = "Cleaner"; break;
            case 2: role = "Personal Assistant"; break;
            case 3: role = "Maintenance"; break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        
        ServiceStaff newStaff = new ServiceStaff(nextStaffId, name, role);
        staff[staffCount] = newStaff;
        staffCount++;
        nextStaffId++;
        
        System.out.println("\n✓ Staff member added successfully!");
        System.out.println("Staff ID: " + newStaff.getStaffId());
    }
    
    // View all staff members
    private static void viewAllStaff() {
        System.out.println("\n--- ALL STAFF MEMBERS ---");
        
        if (staffCount == 0) {
            System.out.println("No staff members in the system.");
            return;
        }
        
        System.out.println("Total Staff: " + staffCount);
        System.out.println("-----------------------------------------------------------");
        
        for (int i = 0; i < staffCount; i++) {
            staff[i].displayInfo();
        }
    }
    
    // View only pending requests
    private static void viewPendingRequests() {
        System.out.println("\n--- PENDING REQUESTS ---");
        
        if (requestCount == 0) {
            System.out.println("No service requests in the system.");
            return;
        }
        
        boolean foundPending = false;
        for (int i = 0; i < requestCount; i++) {
            if (requests[i].getStatus().equals("Pending")) {
                requests[i].displayInfo();
                foundPending = true;
            }
        }
        
        if (!foundPending) {
            System.out.println("No pending requests.");
        }
    }
    
    // Find request by ID
    private static ServiceRequest findRequestById(int id) {
        for (int i = 0; i < requestCount; i++) {
            if (requests[i].getRequestId() == id) {
                return requests[i];
            }
        }
        return null;
    }
    
    // Find staff by ID
    private static ServiceStaff findStaffById(int id) {
        for (int i = 0; i < staffCount; i++) {
            if (staff[i].getStaffId() == id) {
                return staff[i];
            }
        }
        return null;
    }
    
    // Find staff by name
    private static ServiceStaff findStaffByName(String name) {
        for (int i = 0; i < staffCount; i++) {
            if (staff[i].getName().equals(name)) {
                return staff[i];
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
}
