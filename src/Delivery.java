// Stores delivery information
public class Delivery {
    private int deliveryId;
    private String resourceName;
    private int quantity;
    private String supplier;
    private String deliveryDate;
    private String status; // "Scheduled", "In Transit", "Delivered"
    
    // Create new delivery
    public Delivery(int deliveryId, String resourceName, int quantity, String supplier, String deliveryDate) {
        this.deliveryId = deliveryId;
        this.resourceName = resourceName;
        this.quantity = quantity;
        this.supplier = supplier;
        this.deliveryDate = deliveryDate;
        this.status = "Scheduled";
    }
    
    // Getters
    public int getDeliveryId() {
        return deliveryId;
    }
    
    public String getResourceName() {
        return resourceName;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public String getSupplier() {
        return supplier;
    }
    
    public String getDeliveryDate() {
        return deliveryDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    // Setters
    public void setStatus(String status) {
        this.status = status;
    }
    
    // Display delivery info
    public void displayInfo() {
        System.out.println("Delivery ID: " + deliveryId + " | Resource: " + resourceName + 
                         " | Qty: " + quantity + " | Supplier: " + supplier);
        System.out.println("  Date: " + deliveryDate + " | Status: " + status);
    }
}
