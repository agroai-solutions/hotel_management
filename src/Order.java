// Stores customer order information
public class Order {
    private int orderId;
    private int roomNumber;
    private String customerName;
    private String[] items; // Array of ordered items
    private int itemCount;
    private double totalPrice;
    private String status; // "Pending", "Preparing", "Ready", "Delivered"
    private String orderTime;
    
    // Create new order
    public Order(int orderId, int roomNumber, String customerName, String orderTime) {
        this.orderId = orderId;
        this.roomNumber = roomNumber;
        this.customerName = customerName;
        this.orderTime = orderTime;
        this.items = new String[20]; // Max 20 items per order
        this.itemCount = 0;
        this.totalPrice = 0.0;
        this.status = "Pending";
    }
    
    // Add item to order
    public void addItem(String itemName, double price) {
        if (itemCount < items.length) {
            items[itemCount] = itemName;
            itemCount++;
            totalPrice += price;
        }
    }
    
    // Getters
    public int getOrderId() {
        return orderId;
    }
    
    public int getRoomNumber() {
        return roomNumber;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public String[] getItems() {
        return items;
    }
    
    public int getItemCount() {
        return itemCount;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getOrderTime() {
        return orderTime;
    }
    
    // Setters
    public void setStatus(String status) {
        this.status = status;
    }
    
    // Display order info
    public void displayInfo() {
        System.out.println("Order ID: " + orderId + " | Room: " + roomNumber + 
                         " | Customer: " + customerName + " | Status: " + status);
        System.out.println("  Time: " + orderTime + " | Total: $" + totalPrice);
        System.out.print("  Items: ");
        for (int i = 0; i < itemCount; i++) {
            System.out.print(items[i]);
            if (i < itemCount - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
