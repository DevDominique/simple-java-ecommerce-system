//This class belongs to the e-commerce package
package com.ecommerce.orders;

import com.ecommerce.*;
import java.util.*;


//A class to manage orders placed by customers
public class Order {

	//Instance variables store order information
	private String orderID; //unique order ID
	private Customer customer; //customer placing an order
	private Map<Product, Integer> items; //products and quantities
	private double total; //total order cost
	private String status; //status of order (pending, complete, shipped, delivered)
	
	/* This constructor creates a template object for each order. Each 
	 * mention of 'this' below refers to an instance variable in this class*/
	public Order(String orderID, Customer customer, Map<Product, Integer> items) {
		this.orderID = orderID;
		this.customer = customer;
		this.items = new HashMap<>(items); // copy of the cart
		this.total = calculateTotal(); //calculate cart total
		this.status = "pending"; //default status is pending
	} //end of constructor
	
	//Calculate the total order cost
	private double calculateTotal() {
		double sum = 0.0; //initialize cost variable sum to zero
		for (Map.Entry<Product, Integer> entry: items.entrySet()) { //traverse product selected
			sum += entry.getKey().getPrice() * entry.getValue(); //add price * quantity
		}
		return sum;
	}
	
	
	// -->Getter Method: Order ID
	public String getOrderID() {
		return orderID;
	}
	
	// -->Getter Method: Customer
	public Customer getCustomer() {
		return customer;
	}
	
	// -->Getter Method: Order Items
	public Map<Product, Integer> getItems() {
		return items;
	}
	
	// -->Getter Method: Total Cost
	public double getTotal() {
			return total;
	}
	// -->Getter Method: Order Status
	public String getStatus() {
		return status;
	}
	
	// -->Setter Method: Order Status
	public void SetStatus(String newStatus) {
		this.status = newStatus;
	}
	
	
	//Method: Print detailed order summary
	public void printOrderSummary() {
		System.out.println("\nOrder Summary");
		System.out.println("Order ID: " + orderID);
		System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName());
		System.out.println("Status: " + status);
		System.out.println("Items:");
		
		for (Map.Entry<Product, Integer> entry : items.entrySet()) {
			Product p = entry.getKey(); //get product
			int qty = entry.getValue(); //get quantity
			double lineTotal = p.getPrice() * qty; // multiply price * quantity
			System.out.printf("- %s | Qty: %d | $%.2f each | Line Total: $%.2f%n", p.getProductName(), qty, p.getPrice(), lineTotal);
		}
	
		System.out.printf("Your Order Total: $%.2f%n", total);
		
	} // end order summary method

} //end Order class
