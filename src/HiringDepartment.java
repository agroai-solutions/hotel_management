import java.util.Scanner;

public class HiringDepartment {
    // Arrays to store data
    private static Applicant[] applicants = new Applicant[100]; // Max 100 applicants
    private static Employee[] employees = new Employee[100];    // Max 100 employees
    private static int applicantCount = 0; // Current number of applicants
    private static int employeeCount = 0;  // Current number of employees
    private static int nextApplicantId = 1; // Auto-increment ID for applicants
    private static int nextEmployeeId = 1;  // Auto-increment ID for employees
    
    // Scanner for user input
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("   HOTEL HIRING DEPARTMENT SYSTEM");
        System.out.println("======================================\n");
        
        // Main menu loop
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addApplicant();
                    break;
                case 2:
                    viewAllApplicants();
                    break;
                case 3:
                    interviewApplicant();
                    break;
                case 4:
                    hireApplicant();
                    break;
                case 5:
                    viewAllEmployees();
                    break;
                case 6:
                    searchApplicant();
                    break;
                case 7:
//                    System.out.println("\nThank you for using Hiring Department System!");
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

    private static void displayMenu() {
        System.out.println("\n======================================");
        System.out.println("            MAIN MENU");
        System.out.println("======================================");
        System.out.println("1. Add New Applicant");
        System.out.println("2. View All Applicants");
        System.out.println("3. Interview Applicant");
        System.out.println("4. Hire Applicant");
        System.out.println("5. View All Employees");
        System.out.println("6. Search Applicant");
        System.out.println("7. Exit");
        System.out.println("======================================");
    }

    private static void addApplicant() {
        System.out.println("\n--- ADD NEW APPLICANT ---");
        
        // Check if array is full
        if (applicantCount >= applicants.length) {
            System.out.println("Error: Applicant database is full!");
            return;
        }
        
        // Get applicant details from user
        System.out.print("Enter applicant name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter position applying for: ");
        String position = scanner.nextLine();
        
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        
        // Create new applicant and add to array
        Applicant newApplicant = new Applicant(nextApplicantId, name, position, phone);
        applicants[applicantCount] = newApplicant;
        applicantCount++;
        nextApplicantId++;
        
        System.out.println("\n✓ Applicant added successfully!");
        System.out.println("Applicant ID: " + newApplicant.getId());
    }

    private static void viewAllApplicants() {
        System.out.println("\n--- ALL APPLICANTS ---");
        
        if (applicantCount == 0) {
            System.out.println("No applicants in the system.");
            return;
        }
        
        System.out.println("Total Applicants: " + applicantCount);
        System.out.println("-----------------------------------------------------------");
        
        // Loop through all applicants and display
        for (int i = 0; i < applicantCount; i++) {
            applicants[i].displayInfo();
        }
    }

    private static void interviewApplicant() {
        System.out.println("\n--- INTERVIEW APPLICANT ---");
        
        if (applicantCount == 0) {
            System.out.println("No applicants available for interview.");
            return;
        }
        
        // Show only pending applicants
        System.out.println("Applicants available for interview:");
        boolean foundPending = false;
        for (int i = 0; i < applicantCount; i++) {
            if (applicants[i].getStatus().equals("Pending")) {
                applicants[i].displayInfo();
                foundPending = true;
            }
        }
        
        if (!foundPending) {
            System.out.println("No pending applicants to interview.");
            return;
        }
        
        // Get applicant ID to interview
        int id = getIntInput("\nEnter applicant ID to interview: ");
        
        // Find applicant
        Applicant applicant = findApplicantById(id);
        if (applicant == null) {
            System.out.println("Error: Applicant not found!");
            return;
        }
        
        if (!applicant.getStatus().equals("Pending")) {
            System.out.println("Error: This applicant has already been interviewed!");
            return;
        }
        
        // Interview process
        System.out.println("\nInterviewing: " + applicant.getName());
        System.out.println("Position: " + applicant.getPosition());
        System.out.println("\nInterview Result:");
        System.out.println("1. Pass (Ready to hire)");
        System.out.println("2. Reject");
        
        int result = getIntInput("Enter result: ");
        
        if (result == 1) {
            applicant.setStatus("Interviewed");
            System.out.println("\n✓ Interview passed! Applicant is ready for hiring.");
        } else if (result == 2) {
            applicant.setStatus("Rejected");
            System.out.println("\n✓ Applicant has been rejected.");
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private static void hireApplicant() {
        System.out.println("\n--- HIRE APPLICANT ---");
        
        if (applicantCount == 0) {
            System.out.println("No applicants in the system.");
            return;
        }
        
        if (employeeCount >= employees.length) {
            System.out.println("Error: Employee database is full!");
            return;
        }
        
        // Show only interviewed applicants
        System.out.println("Applicants ready to hire:");
        boolean foundInterviewed = false;
        for (int i = 0; i < applicantCount; i++) {
            if (applicants[i].getStatus().equals("Interviewed")) {
                applicants[i].displayInfo();
                foundInterviewed = true;
            }
        }
        
        if (!foundInterviewed) {
            System.out.println("No interviewed applicants available for hiring.");
            return;
        }
        
        // Get applicant ID to hire
        int id = getIntInput("\nEnter applicant ID to hire: ");
        
        // Find applicant
        Applicant applicant = findApplicantById(id);
        if (applicant == null) {
            System.out.println("Error: Applicant not found!");
            return;
        }
        
        if (!applicant.getStatus().equals("Interviewed")) {
            System.out.println("Error: This applicant must be interviewed first!");
            return;
        }
        
        // Get hire date
        System.out.print("Enter hire date (DD/MM/YYYY): ");
        String hireDate = scanner.nextLine();
        
        // Create new employee
        Employee newEmployee = new Employee(nextEmployeeId, applicant.getName(), 
                                          applicant.getPosition(), applicant.getPhoneNumber(), 
                                          hireDate);
        employees[employeeCount] = newEmployee;
        employeeCount++;
        nextEmployeeId++;
        
        // Update applicant status
        applicant.setStatus("Hired");
        
        System.out.println("\n✓ Applicant hired successfully!");
        System.out.println("Employee ID: " + newEmployee.getId());
    }

    private static void viewAllEmployees() {
        System.out.println("\n--- ALL EMPLOYEES ---");
        
        if (employeeCount == 0) {
            System.out.println("No employees in the system.");
            return;
        }
        
        System.out.println("Total Employees: " + employeeCount);
        System.out.println("-----------------------------------------------------------");
        
        // Loop through all employees and display
        for (int i = 0; i < employeeCount; i++) {
            employees[i].displayInfo();
        }
    }

    private static void searchApplicant() {
        System.out.println("\n--- SEARCH APPLICANT ---");
        
        if (applicantCount == 0) {
            System.out.println("No applicants in the system.");
            return;
        }
        
        System.out.print("Enter applicant name to search: ");
        String searchName = scanner.nextLine().toLowerCase();
        
        System.out.println("\nSearch Results:");
        System.out.println("-----------------------------------------------------------");
        
        boolean found = false;
        for (int i = 0; i < applicantCount; i++) {
            if (applicants[i].getName().toLowerCase().contains(searchName)) {
                applicants[i].displayInfo();
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No applicants found with that name.");
        }
    }

    private static Applicant findApplicantById(int id) {
        for (int i = 0; i < applicantCount; i++) {
            if (applicants[i].getId() == id) {
                return applicants[i];
            }
        }
        return null; // Not found
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.nextLine(); // Clear invalid input
            System.out.print("Invalid input! Please enter a number: ");
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        return value;
    }
}
