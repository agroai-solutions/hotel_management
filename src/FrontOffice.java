import java.util.Scanner;

// Main class for front office operations
public class FrontOffice {
    // Arrays for storage
    private static Room[] rooms = new Room[100];
    private static Guest[] guests = new Guest[200];
    private static int roomCount = 0;
    private static int guestCount = 0;
    private static int nextGuestId = 1;
    
    private static Scanner scanner = new Scanner(System.in);
    
    // Main method
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("      HOTEL FRONT OFFICE SYSTEM");
        System.out.println("======================================\n");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    viewAllRooms();
                    break;
                case 3:
                    viewAvailableRooms();
                    break;
                case 4:
                    checkInGuest();
                    break;
                case 5:
                    checkOutGuest();
                    break;
                case 6:
                    viewAllGuests();
                    break;
                case 7:
                    searchGuest();
                    break;
                case 8:
                    viewOccupiedRooms();
                    break;
                case 9:
                    System.out.println("\nThank you for using Front Office System!");
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
        System.out.println("1. Add Room");
        System.out.println("2. View All Rooms");
        System.out.println("3. View Available Rooms");
        System.out.println("4. Check-In Guest");
        System.out.println("5. Check-Out Guest");
        System.out.println("6. View All Guests");
        System.out.println("7. Search Guest");
        System.out.println("8. View Occupied Rooms");
        System.out.println("9. Exit");
        System.out.println("======================================");
    }
    
    // Add new room
    private static void addRoom() {
        System.out.println("\n--- ADD NEW ROOM ---");
        
        if (roomCount >= rooms.length) {
            System.out.println("Error: Room database is full!");
            return;
        }
        
        int roomNumber = getIntInput("Enter room number: ");
        
        // Check if room already exists
        if (findRoomByNumber(roomNumber) != null) {
            System.out.println("Error: Room number already exists!");
            return;
        }
        
        System.out.println("\nRoom Types:");
        System.out.println("1. Single");
        System.out.println("2. Double");
        System.out.println("3. Suite");
        int typeChoice = getIntInput("Select room type: ");
        
        String roomType;
        switch (typeChoice) {
            case 1: roomType = "Single"; break;
            case 2: roomType = "Double"; break;
            case 3: roomType = "Suite"; break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        
        double price = getDoubleInput("Enter price per night: $");
        
        Room newRoom = new Room(roomNumber, roomType, price);
        rooms[roomCount] = newRoom;
        roomCount++;
        
        System.out.println("\n✓ Room added successfully!");
    }
    
    // View all rooms
    private static void viewAllRooms() {
        System.out.println("\n--- ALL ROOMS ---");
        
        if (roomCount == 0) {
            System.out.println("No rooms in the system.");
            return;
        }
        
        System.out.println("Total Rooms: " + roomCount);
        System.out.println("-----------------------------------------------------------");
        
        for (int i = 0; i < roomCount; i++) {
            rooms[i].displayInfo();
        }
    }
    
    // View only available rooms
    private static void viewAvailableRooms() {
        System.out.println("\n--- AVAILABLE ROOMS ---");
        
        if (roomCount == 0) {
            System.out.println("No rooms in the system.");
            return;
        }
        
        boolean foundAvailable = false;
        for (int i = 0; i < roomCount; i++) {
            if (rooms[i].getStatus().equals("Available")) {
                rooms[i].displayInfo();
                foundAvailable = true;
            }
        }
        
        if (!foundAvailable) {
            System.out.println("No available rooms at the moment.");
        }
    }
    
    // Check in a guest
    private static void checkInGuest() {
        System.out.println("\n--- CHECK-IN GUEST ---");
        
        if (roomCount == 0) {
            System.out.println("No rooms available. Please add rooms first.");
            return;
        }
        
        if (guestCount >= guests.length) {
            System.out.println("Error: Guest database is full!");
            return;
        }
        
        // Show available rooms
        System.out.println("Available Rooms:");
        boolean foundAvailable = false;
        for (int i = 0; i < roomCount; i++) {
            if (rooms[i].getStatus().equals("Available")) {
                rooms[i].displayInfo();
                foundAvailable = true;
            }
        }
        
        if (!foundAvailable) {
            System.out.println("No available rooms for check-in.");
            return;
        }
        
        // Get guest details
        System.out.print("\nEnter guest name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        int roomNumber = getIntInput("Enter room number to book: ");
        
        Room selectedRoom = findRoomByNumber(roomNumber);
        if (selectedRoom == null) {
            System.out.println("Error: Room not found!");
            return;
        }
        
        if (!selectedRoom.getStatus().equals("Available")) {
            System.out.println("Error: Room is not available!");
            return;
        }
        
        System.out.print("Enter check-in date (DD/MM/YYYY): ");
        String checkInDate = scanner.nextLine();
        
        System.out.print("Enter check-out date (DD/MM/YYYY): ");
        String checkOutDate = scanner.nextLine();
        
        int nights = getIntInput("Enter number of nights: ");
        
        double totalBill = selectedRoom.getPricePerNight() * nights;
        
        // Create guest and update room
        Guest newGuest = new Guest(nextGuestId, name, phone, email, roomNumber, 
                                  checkInDate, checkOutDate, nights, totalBill);
        guests[guestCount] = newGuest;
        guestCount++;
        nextGuestId++;
        
        selectedRoom.setStatus("Occupied");
        selectedRoom.setCurrentGuest(name);
        
        System.out.println("\n✓ Guest checked in successfully!");
        System.out.println("Guest ID: " + newGuest.getGuestId());
        System.out.println("Total Bill: $" + totalBill);
    }
    
    // Check out a guest
    private static void checkOutGuest() {
        System.out.println("\n--- CHECK-OUT GUEST ---");
        
        if (guestCount == 0) {
            System.out.println("No guests to check out.");
            return;
        }
        
        // Show occupied rooms
        System.out.println("Occupied Rooms:");
        boolean foundOccupied = false;
        for (int i = 0; i < roomCount; i++) {
            if (rooms[i].getStatus().equals("Occupied")) {
                rooms[i].displayInfo();
                foundOccupied = true;
            }
        }
        
        if (!foundOccupied) {
            System.out.println("No occupied rooms.");
            return;
        }
        
        int guestId = getIntInput("\nEnter guest ID to check out: ");
        
        Guest guest = findGuestById(guestId);
        if (guest == null) {
            System.out.println("Error: Guest not found!");
            return;
        }
        
        Room room = findRoomByNumber(guest.getRoomNumber());
        if (room != null) {
            room.setStatus("Available");
            room.setCurrentGuest("None");
        }
        
        System.out.println("\n✓ Guest checked out successfully!");
        System.out.println("Guest: " + guest.getName());
        System.out.println("Room " + guest.getRoomNumber() + " is now available.");
        System.out.println("Total Bill: $" + guest.getTotalBill());
    }
    
    // View all guests
    private static void viewAllGuests() {
        System.out.println("\n--- ALL GUESTS ---");
        
        if (guestCount == 0) {
            System.out.println("No guests in the system.");
            return;
        }
        
        System.out.println("Total Guests: " + guestCount);
        System.out.println("-----------------------------------------------------------");
        
        for (int i = 0; i < guestCount; i++) {
            guests[i].displayInfo();
        }
    }
    
    // Search for a guest
    private static void searchGuest() {
        System.out.println("\n--- SEARCH GUEST ---");
        
        if (guestCount == 0) {
            System.out.println("No guests in the system.");
            return;
        }
        
        System.out.print("Enter guest name to search: ");
        String searchName = scanner.nextLine().toLowerCase();
        
        System.out.println("\nSearch Results:");
        System.out.println("-----------------------------------------------------------");
        
        boolean found = false;
        for (int i = 0; i < guestCount; i++) {
            if (guests[i].getName().toLowerCase().contains(searchName)) {
                guests[i].displayInfo();
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No guests found with that name.");
        }
    }
    
    // View only occupied rooms
    private static void viewOccupiedRooms() {
        System.out.println("\n--- OCCUPIED ROOMS ---");
        
        if (roomCount == 0) {
            System.out.println("No rooms in the system.");
            return;
        }
        
        boolean foundOccupied = false;
        for (int i = 0; i < roomCount; i++) {
            if (rooms[i].getStatus().equals("Occupied")) {
                rooms[i].displayInfo();
                foundOccupied = true;
            }
        }
        
        if (!foundOccupied) {
            System.out.println("No occupied rooms.");
        }
    }
    
    // Find room by number
    private static Room findRoomByNumber(int roomNumber) {
        for (int i = 0; i < roomCount; i++) {
            if (rooms[i].getRoomNumber() == roomNumber) {
                return rooms[i];
            }
        }
        return null;
    }
    
    // Find guest by ID
    private static Guest findGuestById(int id) {
        for (int i = 0; i < guestCount; i++) {
            if (guests[i].getGuestId() == id) {
                return guests[i];
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
    
    // Get double input
    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            scanner.nextLine();
            System.out.print("Invalid input! Please enter a number: ");
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
}
