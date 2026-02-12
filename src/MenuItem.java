// Stores menu item information
public class MenuItem {
    private int itemId;
    private String name;
    private String category; // "Appetizer", "Main Course", "Dessert", "Beverage"
    private double price;
    private boolean available;
    
    // Create new menu item
    public MenuItem(int itemId, String name, String category, double price) {
        this.itemId = itemId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.available = true;
    }
    
    // Getters
    public int getItemId() {
        return itemId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCategory() {
        return category;
    }
    
    public double getPrice() {
        return price;
    }
    
    public boolean isAvailable() {
        return available;
    }
    
    // Setters
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    // Display item info
    public void displayInfo() {
        String status = available ? "Available" : "Unavailable";
        System.out.println("ID: " + itemId + " | " + name + " | Category: " + category + 
                         " | Price: $" + price + " | " + status);
    }
}
