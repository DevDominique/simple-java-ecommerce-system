//This class belongs to the e-commerce package
package com.ecommerce;

//Import Java's class to create ArrayList
import java.util.ArrayList;

// A class to manage products
public class Product {
	
	// Instance variables store product information
	private String productID;
	private String productName;
	private String size;
	private double price;
	private int stock;
	private int amountSold;
	
	//Static collection to store all products for sale (inventory)
	public static ArrayList<Product> Products = new ArrayList<>();
	
	//Constructor: create a template object for each product
	public Product(String productID, String productName, String size, double price, int stock) {
		this.productID = productID;
		this.productName = productName;
		this.size = size;
		this.price = price;
		this.stock = stock;
		this.amountSold = 0; //initially no products sold
	} //end constructor method

	
	// Public getter methods to access product info

    // --> Getter Method 1: access product ID
    public String getProductID() { 
        return productID; //return the value of product ID variable
    }
    
    // --> Getter Method 2: access product name
    public String getProductName() {
    	return productName; //return the value of productName variable
    }
    
    
    // --> Getter Method 3: access product size
    public String getSize() {
    	return size; //return the value of productName variable
    }
    
    // --> Getter Method 4: access product price
    public double getPrice() {
    	return price; // return the value of price variable
    }
    
    // --> Getter Method 5: access product stock
    public int getStock() {
    	return stock; // return the value of stock variable
    }
    
    // --> Getter Method 6: access amount sold
    public int getAmountSold() {
    	return amountSold;
    }
    
    
    //Method to reduce stock upon purchase
    public boolean reduceStock(int quantity) {
    	if (stock >= quantity && quantity > 0) {
    		stock-= quantity; //subtract the quantity from the stock
    		amountSold += quantity; //increase the amount sold by the quantity purchased
    		return true; //this means the purchase was successful
    	} else {
    		System.out.println("Out of stock: " + productName);
    		return false; //this means the purchase failed
    	}
    } //end of reduceStock method
    
} //end Product class
