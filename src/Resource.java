// Stores resource/product information in inventory
public class Resource {
    private int resourceId;
    private String name;
    private String category; // "Kitchen Supplies", "Toiletries", "Cleaning Supplies", "Linens"
    private int quantity;
    private int minStockLevel; // Alert when below this
    private String supplier;
    
    // Create new resource
    public Resource(int resourceId, String name, String category, int quantity, int minStockLevel, String supplier) {
        this.resourceId = resourceId;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.minStockLevel = minStockLevel;
        this.supplier = supplier;
    }
    
    // Getters
    public int getResourceId() {
        return resourceId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCategory() {
        return category;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public int getMinStockLevel() {
        return minStockLevel;
    }
    
    public String getSupplier() {
        return supplier;
    }
    
    // Setters
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void addQuantity(int amount) {
        this.quantity += amount;
    }
    
    public void removeQuantity(int amount) {
        this.quantity -= amount;
    }
    
    // Check if stock is low
    public boolean isLowStock() {
        return quantity < minStockLevel;
    }
    
    // Display resource info
    public void displayInfo() {
        String stockStatus = isLowStock() ? "[LOW STOCK]" : "";
        System.out.println("ID: " + resourceId + " | " + name + " | Category: " + category + 
                         " | Qty: " + quantity + " | Min: " + minStockLevel + 
                         " | Supplier: " + supplier + " " + stockStatus);
    }
}
