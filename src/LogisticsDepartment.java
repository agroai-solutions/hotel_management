import java.util.Scanner;

// Main class for logistics department operations
public class LogisticsDepartment {
    // Arrays for storage
    private static Resource[] inventory = new Resource[100];
    private static Delivery[] deliveries = new Delivery[100];
    private static int resourceCount = 0;
    private static int deliveryCount = 0;
    private static int nextResourceId = 1;
    private static int nextDeliveryId = 1;
    
    private static Scanner scanner = new Scanner(System.in);
    
    // Main method
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("   HOTEL LOGISTICS DEPARTMENT SYSTEM");
        System.out.println("======================================\n");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addResource();
                    break;
                case 2:
                    viewInventory();
                    break;
                case 3:
                    updateStock();
                    break;
                case 4:
                    viewLowStock();
                    break;
                case 5:
                    scheduleDelivery();
                    break;
                case 6:
                    viewDeliveries();
                    break;
                case 7:
                    receiveDelivery();
                    break;
                case 8:
                    viewInventoryByCategory();
                    break;
                case 9:
                    searchResource();
                    break;
                case 10:
                    System.out.println("\nThank you for using Logistics Department System!");
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
        System.out.println("1. Add Resource to Inventory");
        System.out.println("2. View Full Inventory");
        System.out.println("3. Update Stock Quantity");
        System.out.println("4. View Low Stock Items");
        System.out.println("5. Schedule Delivery");
        System.out.println("6. View All Deliveries");
        System.out.println("7. Receive Delivery");
        System.out.println("8. View Inventory by Category");
        System.out.println("9. Search Resource");
        System.out.println("10. Exit");
        System.out.println("======================================");
    }
    
    // Add new resource to inventory
    private static void addResource() {
        System.out.println("\n--- ADD RESOURCE TO INVENTORY ---");
        
        if (resourceCount >= inventory.length) {
            System.out.println("Error: Inventory is full!");
            return;
        }
        
        System.out.print("Enter resource name: ");
        String name = scanner.nextLine();
        
        System.out.println("\nCategories:");
        System.out.println("1. Kitchen Supplies");
        System.out.println("2. Toiletries");
        System.out.println("3. Cleaning Supplies");
        System.out.println("4. Linens");
        int categoryChoice = getIntInput("Select category: ");
        
        String category;
        switch (categoryChoice) {
            case 1: category = "Kitchen Supplies"; break;
            case 2: category = "Toiletries"; break;
            case 3: category = "Cleaning Supplies"; break;
            case 4: category = "Linens"; break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        
        int quantity = getIntInput("Enter initial quantity: ");
        int minStock = getIntInput("Enter minimum stock level: ");
        
        System.out.print("Enter supplier name: ");
        String supplier = scanner.nextLine();
        
        Resource newResource = new Resource(nextResourceId, name, category, quantity, minStock, supplier);
        inventory[resourceCount] = newResource;
        resourceCount++;
        nextResourceId++;
        
        System.out.println("\n✓ Resource added to inventory!");
        System.out.println("Resource ID: " + newResource.getResourceId());
    }
    
    // View full inventory
    private static void viewInventory() {
        System.out.println("\n--- FULL INVENTORY ---");
        
        if (resourceCount == 0) {
            System.out.println("Inventory is empty.");
            return;
        }
        
        System.out.println("Total Resources: " + resourceCount);
        System.out.println("-----------------------------------------------------------");
        
        for (int i = 0; i < resourceCount; i++) {
            inventory[i].displayInfo();
        }
    }
    
    // Update stock quantity
    private static void updateStock() {
        System.out.println("\n--- UPDATE STOCK QUANTITY ---");
        
        if (resourceCount == 0) {
            System.out.println("No resources in inventory.");
            return;
        }
        
        int resourceId = getIntInput("Enter resource ID: ");
        Resource resource = findResourceById(resourceId);
        
        if (resource == null) {
            System.out.println("Error: Resource not found!");
            return;
        }
        
        System.out.println("\nCurrent quantity: " + resource.getQuantity());
        System.out.println("\n1. Add to stock");
        System.out.println("2. Remove from stock");
        System.out.println("3. Set new quantity");
        
        int choice = getIntInput("Select option: ");
        
        switch (choice) {
            case 1:
                int addAmount = getIntInput("Enter amount to add: ");
                resource.addQuantity(addAmount);
                System.out.println("\n✓ Stock updated! New quantity: " + resource.getQuantity());
                break;
            case 2:
                int removeAmount = getIntInput("Enter amount to remove: ");
                if (removeAmount > resource.getQuantity()) {
                    System.out.println("Error: Not enough stock!");
                } else {
                    resource.removeQuantity(removeAmount);
                    System.out.println("\n✓ Stock updated! New quantity: " + resource.getQuantity());
                }
                break;
            case 3:
                int newQuantity = getIntInput("Enter new quantity: ");
                resource.setQuantity(newQuantity);
                System.out.println("\n✓ Stock updated! New quantity: " + resource.getQuantity());
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    // View low stock items
    private static void viewLowStock() {
        System.out.println("\n--- LOW STOCK ITEMS ---");
        
        if (resourceCount == 0) {
            System.out.println("No resources in inventory.");
            return;
        }
        
        boolean foundLowStock = false;
        System.out.println("-----------------------------------------------------------");
        
        for (int i = 0; i < resourceCount; i++) {
            if (inventory[i].isLowStock()) {
                inventory[i].displayInfo();
                foundLowStock = true;
            }
        }
        
        if (!foundLowStock) {
            System.out.println("All items are properly stocked.");
        }
    }
    
    // Schedule a delivery
    private static void scheduleDelivery() {
        System.out.println("\n--- SCHEDULE DELIVERY ---");
        
        if (deliveryCount >= deliveries.length) {
            System.out.println("Error: Delivery database is full!");
            return;
        }
        
        System.out.print("Enter resource name: ");
        String resourceName = scanner.nextLine();
        
        int quantity = getIntInput("Enter quantity: ");
        
        System.out.print("Enter supplier name: ");
        String supplier = scanner.nextLine();
        
        System.out.print("Enter delivery date (DD/MM/YYYY): ");
        String deliveryDate = scanner.nextLine();
        
        Delivery newDelivery = new Delivery(nextDeliveryId, resourceName, quantity, supplier, deliveryDate);
        deliveries[deliveryCount] = newDelivery;
        deliveryCount++;
        nextDeliveryId++;
        
        System.out.println("\n✓ Delivery scheduled successfully!");
        System.out.println("Delivery ID: " + newDelivery.getDeliveryId());
    }
    
    // View all deliveries
    private static void viewDeliveries() {
        System.out.println("\n--- ALL DELIVERIES ---");
        
        if (deliveryCount == 0) {
            System.out.println("No deliveries scheduled.");
            return;
        }
        
        System.out.println("Total Deliveries: " + deliveryCount);
        System.out.println("-----------------------------------------------------------");
        
        for (int i = 0; i < deliveryCount; i++) {
            deliveries[i].displayInfo();
        }
    }
    
    // Receive a delivery and update inventory
    private static void receiveDelivery() {
        System.out.println("\n--- RECEIVE DELIVERY ---");
        
        if (deliveryCount == 0) {
            System.out.println("No deliveries scheduled.");
            return;
        }
        
        // Show scheduled or in-transit deliveries
        System.out.println("Pending Deliveries:");
        boolean foundPending = false;
        for (int i = 0; i < deliveryCount; i++) {
            String status = deliveries[i].getStatus();
            if (status.equals("Scheduled") || status.equals("In Transit")) {
                deliveries[i].displayInfo();
                foundPending = true;
            }
        }
        
        if (!foundPending) {
            System.out.println("No pending deliveries.");
            return;
        }
        
        int deliveryId = getIntInput("\nEnter delivery ID to receive: ");
        Delivery delivery = findDeliveryById(deliveryId);
        
        if (delivery == null) {
            System.out.println("Error: Delivery not found!");
            return;
        }
        
        if (delivery.getStatus().equals("Delivered")) {
            System.out.println("Error: This delivery has already been received!");
            return;
        }
        
        // Update delivery status
        delivery.setStatus("Delivered");
        
        // Update inventory if resource exists
        Resource resource = findResourceByName(delivery.getResourceName());
        if (resource != null) {
            resource.addQuantity(delivery.getQuantity());
            System.out.println("\n✓ Delivery received and inventory updated!");
            System.out.println("Resource: " + resource.getName());
            System.out.println("New quantity: " + resource.getQuantity());
        } else {
            System.out.println("\n✓ Delivery received!");
            System.out.println("Note: Resource not found in inventory. Please add it manually.");
        }
    }
    
    // View inventory by category
    private static void viewInventoryByCategory() {
        System.out.println("\n--- VIEW INVENTORY BY CATEGORY ---");
        
        if (resourceCount == 0) {
            System.out.println("Inventory is empty.");
            return;
        }
        
        System.out.println("\nCategories:");
        System.out.println("1. Kitchen Supplies");
        System.out.println("2. Toiletries");
        System.out.println("3. Cleaning Supplies");
        System.out.println("4. Linens");
        
        int choice = getIntInput("Select category: ");
        
        String category;
        switch (choice) {
            case 1: category = "Kitchen Supplies"; break;
            case 2: category = "Toiletries"; break;
            case 3: category = "Cleaning Supplies"; break;
            case 4: category = "Linens"; break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        
        System.out.println("\n--- " + category.toUpperCase() + " ---");
        System.out.println("-----------------------------------------------------------");
        
        boolean found = false;
        for (int i = 0; i < resourceCount; i++) {
            if (inventory[i].getCategory().equals(category)) {
                inventory[i].displayInfo();
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No items in this category.");
        }
    }
    
    // Search for a resource
    private static void searchResource() {
        System.out.println("\n--- SEARCH RESOURCE ---");
        
        if (resourceCount == 0) {
            System.out.println("Inventory is empty.");
            return;
        }
        
        System.out.print("Enter resource name to search: ");
        String searchName = scanner.nextLine().toLowerCase();
        
        System.out.println("\nSearch Results:");
        System.out.println("-----------------------------------------------------------");
        
        boolean found = false;
        for (int i = 0; i < resourceCount; i++) {
            if (inventory[i].getName().toLowerCase().contains(searchName)) {
                inventory[i].displayInfo();
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No resources found with that name.");
        }
    }
    
    // Find resource by ID
    private static Resource findResourceById(int id) {
        for (int i = 0; i < resourceCount; i++) {
            if (inventory[i].getResourceId() == id) {
                return inventory[i];
            }
        }
        return null;
    }
    
    // Find resource by name
    private static Resource findResourceByName(String name) {
        for (int i = 0; i < resourceCount; i++) {
            if (inventory[i].getName().equalsIgnoreCase(name)) {
                return inventory[i];
            }
        }
        return null;
    }
    
    // Find delivery by ID
    private static Delivery findDeliveryById(int id) {
        for (int i = 0; i < deliveryCount; i++) {
            if (deliveries[i].getDeliveryId() == id) {
                return deliveries[i];
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
