/**
 * Employee class - Represents a hired employee in the hotel
 * This class stores information about employed staff members
 */
public class Employee {
    // Employee attributes
    private int id;
    private String name;
    private String position;
    private String phoneNumber;
    private String hireDate;
    
    // Constructor - creates a new employee
    public Employee(int id, String name, String position, String phoneNumber, String hireDate) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
    }
    
    // Getter methods - to access private data
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPosition() {
        return position;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getHireDate() {
        return hireDate;
    }
    
    // Method to display employee information
    public void displayInfo() {
        System.out.println("ID: " + id + " | Name: " + name + " | Position: " + position + 
                         " | Phone: " + phoneNumber + " | Hired: " + hireDate);
    }
}
