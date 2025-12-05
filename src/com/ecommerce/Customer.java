//This class belongs to the e-commerce package
package com.ecommerce;

//import all Java utility classes
import java.util.*;

//A class to manage customers
public class Customer {

	//Instance variables store customer information
	private String customerID; //customer ID
	private String firstName; //customer first name
	private String lastName; //customer last name
	private ArrayList<String> purchaseList; //list of customer purchases
	
	
	/* This map functions as a private shopping cart for each
	 * customer in terms of product and quantity */
	private Map<Product, Integer> cart = new HashMap<>();
	
	//Static list of all customers
	public static ArrayList<Customer> allCustomers = new ArrayList<>();
		
	
	/* This constructor creates a template object for each customer. Each 
	 * mention of 'this' below refers to an instance variable in this class*/
	public Customer(String customerID, String firstName, String lastName) {
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.purchaseList = new ArrayList<>(); //adds purchases to customer profile
		this.cart = new HashMap<>(); //attached shopping cart to customer profile
	} //end of constructor
	
	
	/*In the following section, getter and setter methods are
	 * used, respectively, to view or update the information stored
	 * in each of the instance methods for this class. These methods
	 * allow the user to create and update customer profiles. */
	
	// -->Getter Method 1: access customer ID
	public String getCustomerID() {
		return customerID; //return the value of customerID
	}
	// -->Setter Method 1: update customer ID
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	// -->Getter Method 2: access customer first name
	public String getFirstName() {
		return firstName; //return the value of customerID
	}
	// -->Setter Method 2: update customer first name
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	// -->Getter Method 3: access customer last name
	public String getLastName() {
		return lastName; //return the value of customerID
	}
	// -->Setter Method 3: update customer last name
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	// -->Getter Method 4: access customer purchases
	public ArrayList<String> getPurchases() {
		return purchaseList;
	}
	
	// -->Getter Method 5: access shopping cart
	public Map<Product, Integer> getCart() {
		return cart;
	}
	
	
	//Shopping Cart Methods: 
	
	// --> Add to Cart
	public void addToCart(Product product, int quantity) {
		if(cart.containsKey(product)) { //if a product is in the cart
			cart.put(product, cart.get(product) + quantity);
		} else {
			cart.put(product,  quantity);
		}
	}// end of addToCart method
	
	// --> Remove From Cart
	public void removeFromCart(Product product, int quantityToRemove) {
		if (cart.containsKey(product)) { //check to see if product is in the cart
			int currentQty = cart.get(product); //count how many
			if (quantityToRemove >= currentQty) { //if removing > or = to quantity present
				cart.remove(product); //remove all items with this product ID
			} else { //otherwise...
			cart.put(product, currentQty - quantityToRemove); //remove SOME items only
			}
		}	
	} //end removeFromCart method	
	
	// --> View Cart
	public void viewCart() {
		System.out.println("\n🛒 Shopping Cart for: " + firstName +" " + lastName +":");
		if (cart.isEmpty()) { //if cart is empty
			System.out.println("Your cart is empty."); //notify customer
			return; //exit method
		}
		
		double total = 0.0; //initialize a variable to track shopping cart total
		
		for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
			Product p = entry.getKey(); //get the product
			int qty = entry.getValue(); //get the quantity
			double cartTotal = p.getPrice() * qty; //multiply product price by quantity in cart
			total += cartTotal; //add to this line total
			
			//Display product details in a formatted line
			System.out.printf("- %s | Qty: %d | $%.2f each | Cart Total: $%.2f%n", p.getProductName(), qty, p.getPrice(), cartTotal);
		}
		
		//Display the final shopping cart total
		System.out.printf(" Total Cart Value: $%.2f%n", total);
	
	} //end of viewCart method
	
	//Method to clear the shopping cart after checkout
	public void clearCart() {
		cart.clear(); //empty the map
	}
	
} //end class Customer
