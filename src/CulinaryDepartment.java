import java.util.Scanner;

// Main class for culinary department operations
public class CulinaryDepartment {
    // Arrays for storage
    private static MenuItem[] menuItems = new MenuItem[100];
    private static Order[] orders = new Order[200];
    private static int menuCount = 0;
    private static int orderCount = 0;
    private static int nextItemId = 1;
    private static int nextOrderId = 1;
    
    private static Scanner scanner = new Scanner(System.in);
    
    // Main method
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("    HOTEL CULINARY DEPARTMENT SYSTEM");
        System.out.println("======================================\n");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addMenuItem();
                    break;
                case 2:
                    viewMenu();
                    break;
                case 3:
                    createOrder();
                    break;
                case 4:
                    viewAllOrders();
                    break;
                case 5:
                    updateOrderStatus();
                    break;
                case 6:
                    viewPendingOrders();
                    break;
                case 7:
                    completeOrder();
                    break;
                case 8:
                    updateItemAvailability();
                    break;
                case 9:
                    viewOrdersByStatus();
                    break;
                case 10:
                    System.out.println("\nThank you for using Culinary Department System!");
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
        System.out.println("1. Add Menu Item");
        System.out.println("2. View Menu");
        System.out.println("3. Create New Order");
        System.out.println("4. View All Orders");
        System.out.println("5. Update Order Status");
        System.out.println("6. View Pending Orders");
        System.out.println("7. Complete Order");
        System.out.println("8. Update Item Availability");
        System.out.println("9. View Orders by Status");
        System.out.println("10. Exit");
        System.out.println("======================================");
    }
    
    // Add new menu item
    private static void addMenuItem() {
        System.out.println("\n--- ADD MENU ITEM ---");
        
        if (menuCount >= menuItems.length) {
            System.out.println("Error: Menu is full!");
            return;
        }
        
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        
        System.out.println("\nCategories:");
        System.out.println("1. Appetizer");
        System.out.println("2. Main Course");
        System.out.println("3. Dessert");
        System.out.println("4. Beverage");
        int categoryChoice = getIntInput("Select category: ");
        
        String category;
        switch (categoryChoice) {
            case 1: category = "Appetizer"; break;
            case 2: category = "Main Course"; break;
            case 3: category = "Dessert"; break;
            case 4: category = "Beverage"; break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        
        double price = getDoubleInput("Enter price: $");
        
        MenuItem newItem = new MenuItem(nextItemId, name, category, price);
        menuItems[menuCount] = newItem;
        menuCount++;
        nextItemId++;
        
        System.out.println("\n✓ Menu item added successfully!");
        System.out.println("Item ID: " + newItem.getItemId());
    }
    
    // View all menu items
    private static void viewMenu() {
        System.out.println("\n--- RESTAURANT MENU ---");
        
        if (menuCount == 0) {
            System.out.println("Menu is empty.");
            return;
        }
        
        System.out.println("Total Items: " + menuCount);
        System.out.println("-----------------------------------------------------------");
        
        for (int i = 0; i < menuCount; i++) {
            menuItems[i].displayInfo();
        }
    }
    
    // Create new order
    private static void createOrder() {
        System.out.println("\n--- CREATE NEW ORDER ---");
        
        if (menuCount == 0) {
            System.out.println("No menu items available. Please add items first.");
            return;
        }
        
        if (orderCount >= orders.length) {
            System.out.println("Error: Order database is full!");
            return;
        }
        
        int roomNumber = getIntInput("Enter room number: ");
        
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        
        System.out.print("Enter order time (HH:MM): ");
        String orderTime = scanner.nextLine();
        
        Order newOrder = new Order(nextOrderId, roomNumber, customerName, orderTime);
        
        // Show available menu items
        System.out.println("\nAvailable Menu Items:");
        System.out.println("-----------------------------------------------------------");
        boolean hasAvailable = false;
        for (int i = 0; i < menuCount; i++) {
            if (menuItems[i].isAvailable()) {
                menuItems[i].displayInfo();
                hasAvailable = true;
            }
        }
        
        if (!hasAvailable) {
            System.out.println("No items available at the moment.");
            return;
        }
        
        // Add items to order
        boolean addingItems = true;
        while (addingItems) {
            int itemId = getIntInput("\nEnter item ID to add (0 to finish): ");
            
            if (itemId == 0) {
                addingItems = false;
            } else {
                MenuItem item = findMenuItemById(itemId);
                if (item == null) {
                    System.out.println("Error: Item not found!");
                } else if (!item.isAvailable()) {
                    System.out.println("Error: Item not available!");
                } else {
                    newOrder.addItem(item.getName(), item.getPrice());
                    System.out.println("✓ Added: " + item.getName() + " ($" + item.getPrice() + ")");
                }
            }
        }
        
        if (newOrder.getItemCount() == 0) {
            System.out.println("No items added. Order cancelled.");
            return;
        }
        
        orders[orderCount] = newOrder;
        orderCount++;
        nextOrderId++;
        
        System.out.println("\n✓ Order created successfully!");
        System.out.println("Order ID: " + newOrder.getOrderId());
        System.out.println("Total: $" + newOrder.getTotalPrice());
    }
    
    // View all orders
    private static void viewAllOrders() {
        System.out.println("\n--- ALL ORDERS ---");
        
        if (orderCount == 0) {
            System.out.println("No orders in the system.");
            return;
        }
        
        System.out.println("Total Orders: " + orderCount);
        System.out.println("-----------------------------------------------------------");
        
        for (int i = 0; i < orderCount; i++) {
            orders[i].displayInfo();
        }
    }
    
    // Update order status
    private static void updateOrderStatus() {
        System.out.println("\n--- UPDATE ORDER STATUS ---");
        
        if (orderCount == 0) {
            System.out.println("No orders available.");
            return;
        }
        
        int orderId = getIntInput("Enter order ID: ");
        Order order = findOrderById(orderId);
        
        if (order == null) {
            System.out.println("Error: Order not found!");
            return;
        }
        
        System.out.println("\nCurrent Status: " + order.getStatus());
        System.out.println("\nNew Status:");
        System.out.println("1. Pending");
        System.out.println("2. Preparing");
        System.out.println("3. Ready");
        System.out.println("4. Delivered");
        
        int choice = getIntInput("Select new status: ");
        
        switch (choice) {
            case 1: order.setStatus("Pending"); break;
            case 2: order.setStatus("Preparing"); break;
            case 3: order.setStatus("Ready"); break;
            case 4: order.setStatus("Delivered"); break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        
        System.out.println("\n✓ Status updated successfully!");
    }
    
    // View only pending orders
    private static void viewPendingOrders() {
        System.out.println("\n--- PENDING ORDERS ---");
        
        if (orderCount == 0) {
            System.out.println("No orders in the system.");
            return;
        }
        
        boolean foundPending = false;
        for (int i = 0; i < orderCount; i++) {
            if (orders[i].getStatus().equals("Pending")) {
                orders[i].displayInfo();
                foundPending = true;
            }
        }
        
        if (!foundPending) {
            System.out.println("No pending orders.");
        }
    }
    
    // Complete order
    private static void completeOrder() {
        System.out.println("\n--- COMPLETE ORDER ---");
        
        if (orderCount == 0) {
            System.out.println("No orders available.");
            return;
        }
        
        // Show ready orders
        System.out.println("Ready Orders:");
        boolean foundReady = false;
        for (int i = 0; i < orderCount; i++) {
            if (orders[i].getStatus().equals("Ready")) {
                orders[i].displayInfo();
                foundReady = true;
            }
        }
        
        if (!foundReady) {
            System.out.println("No ready orders to deliver.");
            return;
        }
        
        int orderId = getIntInput("\nEnter order ID to deliver: ");
        Order order = findOrderById(orderId);
        
        if (order == null) {
            System.out.println("Error: Order not found!");
            return;
        }
        
        if (!order.getStatus().equals("Ready")) {
            System.out.println("Error: Order is not ready yet!");
            return;
        }
        
        order.setStatus("Delivered");
        
        System.out.println("\n✓ Order delivered successfully!");
        System.out.println("Delivered to Room " + order.getRoomNumber());
    }
    
    // Update item availability
    private static void updateItemAvailability() {
        System.out.println("\n--- UPDATE ITEM AVAILABILITY ---");
        
        if (menuCount == 0) {
            System.out.println("No menu items available.");
            return;
        }
        
        viewMenu();
        
        int itemId = getIntInput("\nEnter item ID to update: ");
        MenuItem item = findMenuItemById(itemId);
        
        if (item == null) {
            System.out.println("Error: Item not found!");
            return;
        }
        
        System.out.println("\nCurrent Status: " + (item.isAvailable() ? "Available" : "Unavailable"));
        System.out.println("\n1. Set as Available");
        System.out.println("2. Set as Unavailable");
        
        int choice = getIntInput("Select option: ");
        
        if (choice == 1) {
            item.setAvailable(true);
            System.out.println("\n✓ Item set as Available!");
        } else if (choice == 2) {
            item.setAvailable(false);
            System.out.println("\n✓ Item set as Unavailable!");
        } else {
            System.out.println("Invalid choice!");
        }
    }
    
    // View orders by status
    private static void viewOrdersByStatus() {
        System.out.println("\n--- VIEW ORDERS BY STATUS ---");
        
        if (orderCount == 0) {
            System.out.println("No orders in the system.");
            return;
        }
        
        System.out.println("\nSelect Status:");
        System.out.println("1. Pending");
        System.out.println("2. Preparing");
        System.out.println("3. Ready");
        System.out.println("4. Delivered");
        
        int choice = getIntInput("Enter choice: ");
        
        String status;
        switch (choice) {
            case 1: status = "Pending"; break;
            case 2: status = "Preparing"; break;
            case 3: status = "Ready"; break;
            case 4: status = "Delivered"; break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        
        System.out.println("\n--- " + status.toUpperCase() + " ORDERS ---");
        System.out.println("-----------------------------------------------------------");
        
        boolean found = false;
        for (int i = 0; i < orderCount; i++) {
            if (orders[i].getStatus().equals(status)) {
                orders[i].displayInfo();
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No orders with status: " + status);
        }
    }
    
    // Find menu item by ID
    private static MenuItem findMenuItemById(int id) {
        for (int i = 0; i < menuCount; i++) {
            if (menuItems[i].getItemId() == id) {
                return menuItems[i];
            }
        }
        return null;
    }
    
    // Find order by ID
    private static Order findOrderById(int id) {
        for (int i = 0; i < orderCount; i++) {
            if (orders[i].getOrderId() == id) {
                return orders[i];
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
