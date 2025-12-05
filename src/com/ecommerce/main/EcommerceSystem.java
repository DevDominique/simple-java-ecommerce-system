/*This package contains the main class with the program's main method*/

package com.ecommerce.main; // package name

import java.util.Scanner; //import Scanner class
import java.util.ArrayList; //import Java's ArrayList class
import java.util.Map; //import Map class
import java.util.InputMismatchException; //import an exception 

import com.ecommerce.Product; //import Product class
import com.ecommerce.Customer; //import Customer class
import com.ecommerce.orders.Order; //import Order class

// main class
public class EcommerceSystem {
	// main method to run program
		public static void main(String[] args) {
			
			//Store welcome message
			System.out.println("Welcome to DevDominique's Online Boutique!");
			
			//Create a list to hold inventory
			ArrayList<Product> inventory = new ArrayList<>();
	
			//Create instances of products
			inventory.add(new Product("P001", "Graphic Tee", "S", 21.99, 15));
			inventory.add(new Product("P002", "Graphic Tee", "M", 21.99, 7));
			inventory.add(new Product("P003", "Graphic Tee", "L", 21.99, 10));
			inventory.add(new Product("P004", "Summer Tote", "one-size", 15.99, 20));
			inventory.add(new Product("P005", "Stainless Steel Tumbler", "32 oz", 19.99, 8));
			
			//Create instance of a customer
			Customer customer = new Customer("C001", "Jane", "Doe");
				
			
			//Display inventory to the user (customer)
			System.out.println("\nBrowse Our Summer 2025 Collection:");
			for (Product p : inventory) {
				System.out.printf("- %s (%s): $%.2f | Stock: %d%n", p.getProductName(), p.getProductID(), p.getPrice(), p.getStock());
			}
			
			//--> Allow Customers to Interact with E-Commerce Platform
				
			Scanner scanner = new Scanner(System.in); //create scanner object to take user input
			boolean exit = false; //set a condition to control scanner using a while loop
			
			while(!exit) { //while condition is met (true), do the following...
				System.out.println("\nChoose an Option:");
				System.out.println("1: View Products");
				System.out.println("2: Add to Cart");
				System.out.println("3: View Cart or Remove Products");
				System.out.println("4: Checkout");
				System.out.println("5: Exit");
				
				System.out.println("Enter Your Choice (1-5): ");
				
				try { // try to run the following section without errors
					int choice = scanner.nextInt(); //take integer input from user
					scanner.nextLine();
					
					/*A switch statement allow user to choose between menu options */
					switch (choice) { //switch statement for user input above
					
						/* Each case below will determine what happens when a user enters an integer */
						
						//CASE 1: Display products
						case 1: 
							for (Product p : inventory) {
								System.out.printf("- %s (%s): $%.2f | Stock: %d%n", p.getProductName(), p.getProductID(), p.getPrice(), p.getStock());
							}
							break; //break out of the switch
						
						//CASE 2: Add & Remove Products	
						case 2:
							Product selectedProduct = null; //initialize product variable to nothing
							
							/* Create a loop to re-prompt user if needed based on their input*/
							while(true) {
								System.out.print("Enter a product ID to it add to your cart: ");
								String productID = scanner.nextLine().toUpperCase().trim(); //prompt user for input
								
								//add an option to return to main menu
								if (productID.equalsIgnoreCase("back")) {
									System.out.println("🔙 Return to main menu.");
									break;
								}
								
								for (Product p :inventory) { //traverse the inventory
									if (p.getProductID().equalsIgnoreCase(productID)) { //if user input matches a product ID
										selectedProduct = p; //store that product value in the variable
										break;
									}
								}
							
								if (selectedProduct == null) {
									System.out.println("Product not found. Please try again.");
								} else {
									break; // valid product found successfully
								}
							}
							
							if (selectedProduct == null) { //if user chooses to go back
								break; //exit to main menu
							}
							
							System.out.print("Enter Quantity: ");
							int quantity = scanner.nextInt(); //prompt user to enter product quantity desired
							scanner.nextLine();
							
							customer.addToCart(selectedProduct, quantity);
							System.out.println("✅ Added to cart!");
							break; //exit to main menu
							
						//CASE 3: View Cart
						case 3:
							customer.viewCart();
							
							if(customer.getCart().isEmpty()) { //if there is nothing in the cart
								System.out.print("Your cart is empty.");
								break; //exit to main menu
							}
							
							//Provide customer the option to remove an item from their cart
							System.out.println("Would you like to remove an item from your cart? Enter YES or NO: ");
							String removeItem = scanner.nextLine().trim().toLowerCase();
							
							if (removeItem.equals("yes")) { //if customer enters "yes"
								Product productToRemove = null; //initialize variable to null
								
								/* Create a loop to re-prompt user if needed based on their input*/
								while(true) {
									System.out.print("Enter the product ID to remove (or type 'back' to cancel): ");
									String removeID = scanner.nextLine().trim().toUpperCase();
									
									if(removeID.equalsIgnoreCase("back")) { //if user enters "back"
										System.out.println("🔙 Returning to main menu.");
										break; //exit to main menu
									}
									
									for (Product p : customer.getCart().keySet()) { //traverse products in cart
										if (p.getProductID().equalsIgnoreCase(removeID) ) { //if ID entered matches one there
											productToRemove = p; //remove it
											break; 
										}
									}
									
									if (productToRemove != null) { //if there are products in the cart
										int currentQty = customer.getCart().get(productToRemove); //count how many
										System.out.printf("You have %d of %s in your cart.%n", currentQty, productToRemove.getProductName()); //tell user
										
										System.out.print("Enter quantity to remove: "); //prompt user to specify a quantity
										int qtyToRemove = scanner.nextInt(); //take integer input
										scanner.nextLine();
										
										if (qtyToRemove <= 0) { //if there aren't any of this item in the cart
											System.out.println("Quantity must be 1 or more.");
										} else { //otherwise if there are 1 or more items...
											customer.removeFromCart(productToRemove, qtyToRemove); //remove specified quantity
											System.out.println(" Your cart has been updated!");
										}
										break;								
									} else {
										System.out.println(" Product not found in your cart. Please try again.");
										break; //re-prompt user
									}
								} //end while loop		
							}
							break; //exit to main menu
							
						//CASE 4: Checkout
						case 4:
							//Create new order
							if (customer.getCart().isEmpty()) {
								System.out.println("Your cart is empty. Please add items to check out.");
								break;
							}
							//Generate an order ID & Create instance of an order using cart
							String orderID = "ABC123";
							Order order = new Order(orderID, customer, customer.getCart());
							
							order.printOrderSummary();
							
							//Reduce Stock
							for (Map.Entry<Product, Integer> entry : customer.getCart().entrySet()) {
								Product product = entry.getKey();
								int quantityOrdered = entry.getValue();
								product.reduceStock(quantityOrdered);
							}
							//Clear cart after checkout
							customer.clearCart();
							
							System.out.println("✅ Success: We have received your order!");
							break; //exit to main menu
							
						//CASE 5: Exit
						case 5:	
							exit = true;
							System.out.println("Thank you for shopping, we'll see you again soon!");
							break;
							
						//DEFAULT CASE
						default: //default action if used input is invalid
							System.out.println("Invalid Input - Plese enter a number: ");
						
					}// end switch statement
				
				} catch (InputMismatchException e) {
					System.out.println("⚠️ Invalid Input - Please enter a number: ");
					scanner.nextLine(); //clear the invalid input
				} //end catch
					
			} //end while loop
			
			// Close the Scanner
			scanner.close();
		
	} //end of main method

} //end of main class (EcommerceSystem)
