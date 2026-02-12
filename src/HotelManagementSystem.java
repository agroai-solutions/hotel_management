import java.util.Scanner;

// Main interface for Hotel Management System
public class HotelManagementSystem {
    
    private static Scanner scanner = new Scanner(System.in);
    
    // Main method - entry point
    public static void main(String[] args) {
        displayWelcome();
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    System.out.println("\n=== Opening Hiring Department ===\n");
                    HiringDepartment.main(new String[0]);
                    break;
                case 2:
                    System.out.println("\n=== Opening Service Department ===\n");
                    ServiceDepartment.main(new String[0]);
                    break;
                case 3:
                    System.out.println("\n=== Opening Front Office ===\n");
                    FrontOffice.main(new String[0]);
                    break;
                case 4:
                    System.out.println("\n=== Opening Culinary Department ===\n");
                    CulinaryDepartment.main(new String[0]);
                    break;
                case 5:
                    System.out.println("\n=== Opening Logistics Department ===\n");
                    LogisticsDepartment.main(new String[0]);
                    break;
                case 6:
                    System.out.println("\n=== Opening Air Company Department ===\n");
                    AirCompanyDepartment.main(new String[0]);
                    break;
                case 7:
//                    System.out.println("\n=== Opening Sales/Marketing Department ===\n");
                    SalesMarketingDepartment.main(new String[0]);
                    break;
                case 8:
//                    System.out.println("\n=== Opening Recreational Facilities ===\n");
                    RecreationalFacilities.main(new String[0]);
                    break;
                case 9:
                    displayAbout();
                    break;
                case 10:
                    System.out.println("\n==========================================");
                    System.out.println("   Thank you for using our Hotel System!");
                    System.out.println("              Goodbye!");
                    System.out.println("==========================================\n");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }
            
            if (running && choice != 9) {
                System.out.println("\n=== Returning to Main Menu ===");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
            }
        }
//
//        scanner.close();
    }
    
    // Display welcome screen
    private static void displayWelcome() {
        System.out.println("==========================================");
        System.out.println("                                          ");
        System.out.println("     HOTEL MANAGEMENT SYSTEM              ");
        System.out.println("                                          ");
        System.out.println("     Welcome to Our System                ");
        System.out.println("                                          ");
        System.out.println("==========================================\n");
    }
    
    // Display main menu
    private static void displayMainMenu() {
        System.out.println("\n==========================================");
        System.out.println("           MAIN MENU");
        System.out.println("==========================================");
        System.out.println("1.  Hiring Department");
        System.out.println("2.  Service Department");
        System.out.println("3.  Front Office (Check-in/out & Booking)");
        System.out.println("4.  Culinary Department");
        System.out.println("5.  Logistics Department");
        System.out.println("6.  Air Company Collaboration");
        System.out.println("7.  Sales/Marketing Department");
        System.out.println("8.  Recreational Facilities");
        System.out.println("9.  About System");
        System.out.println("10. Exit");
        System.out.println("==========================================");
    }
    
    // Display about/info screen
    private static void displayAbout() {
        System.out.println("\n==========================================");
        System.out.println("         ABOUT THIS SYSTEM");
        System.out.println("==========================================");
        System.out.println("Hotel Management System");
        System.out.println("Version: 1.0");
        System.out.println("\nDepartments Available:");
        System.out.println("- Hiring: Manage applicants and employees");
        System.out.println("- Service: Handle customer service requests");
        System.out.println("- Front Office: Room booking and check-in/out");
        System.out.println("- Culinary: Manage menu and food orders");
        System.out.println("- Logistics: Inventory and delivery management");
        System.out.println("- Air Company: Book flight tickets for guests");
        System.out.println("- Sales/Marketing: Marketing campaigns and sales");
        System.out.println("- Recreational: Manage hotel facilities");
        System.out.println("==========================================");
        System.out.println("\nPress Enter to return to main menu...");
        scanner.nextLine();
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
