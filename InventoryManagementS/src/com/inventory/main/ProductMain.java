package com.inventory.main;


import java.util.List;
import java.util.Scanner;

import com.inventory.model.Product;
import com.inventory.service.ProductService;

public class ProductMain {
    static void headerfooter(String content) {
        // Calculate the number of asterisks needed for the title
        int titleLength = content.length();
        int totalAsterisks = 40; // Adjust this value based on your desired width
        int padding = (totalAsterisks - titleLength) / 2;

        // Print the top border
        for (int i = 0; i < totalAsterisks; i++) {
            System.out.print("*");
        }
        System.out.println();

        // Print the title with padding
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print(content);
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.println();

        // Print the bottom border
        for (int i = 0; i < totalAsterisks; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
  static  void menu() {   
    	  String End = "Thank You";
        ProductService ps=new ProductService();
    	Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Create Product");
            System.out.println("2. Retrieve Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Get All Products");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            
				scanner.nextLine();
			// Consume the newline character

         
		try {
			switch (choice) {
                case 1:
				try {
					Product newProduct = new Product();
                    System.out.print("Enter Product Name: ");
                    newProduct.setProductName(scanner.nextLine());
                    System.out.print("Enter Category: ");
                    newProduct.setCategory(scanner.nextLine());
                    System.out.print("Enter Subcategory: ");
                    newProduct.setSubCategory(scanner.nextLine());
                    System.out.print("Enter Brand: ");
                    newProduct.setBrand(scanner.nextLine());
                    System.out.print("Enter Product Weight: ");
                    try {
						newProduct.setProductWeight(scanner.nextInt());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("Invalid Input type");
						//e.printStackTrace();
						headerfooter("Going back to the main menu");
						  menu();
						//System.exit(0);
						   
						 
						
					}
                 

                    ps.createProduct(newProduct);
                    System.out.println("Product created successfully!");
                    System.out.println();
                    headerfooter("Going back to the main menu");
                    break;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Invalid Input type");
					//e.printStackTrace();
					headerfooter("Going back to the main menu");
					
					  menu();
				}

                case 2:
                    System.out.print("Enter Product ID to retrieve: ");
				int productId=0;
				try {
					productId = scanner.nextInt();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    Product retrievedProduct = ps.getProductById(productId);
                    if (retrievedProduct != null) {
                        System.out.println("Retrieved Product:");
                        System.out.println("+----------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
                        System.out.printf("| %-15s | %-20s | %-20s | %-20s | %-20s | %-20s |\n",
                                "Product ID", "Product Name", "Category", "Subcategory", "Weight", "Brand");
                        System.out.println("+----------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
                        System.out.printf("| %-15d | %-20s | %-20s | %-20s | %-20s | %-20s |\n",
                                retrievedProduct.getProductId(),
                                retrievedProduct.getProductName(),
                                retrievedProduct.getCategory(),
                                retrievedProduct.getSubCategory(),
                                retrievedProduct.getProductWeight(),
                                retrievedProduct.getBrand());
                        
                        System.out.println("+----------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
                    
                    } else {
                        System.out.println("Product not found!");
                    }
                    headerfooter("Going back to the main menu");
                    break;

                case 3:
                    System.out.print("Enter Product ID to update: ");
                    int updateProductId = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
                    Product updatedProduct = ps.getProductById(updateProductId);
                    if (updatedProduct != null) {
                        System.out.print("Enter new Product Name: ");
                        updatedProduct.setProductName(scanner.nextLine());
                        ps.updateProduct(updatedProduct);
                        System.out.println("Product updated successfully!");
                    } else {
                        System.out.println("Product not found!");
                    }
                    System.out.println();
                    headerfooter("Going back to the main menu");
                    break;

                case 4:
                    System.out.print("Enter Product ID to delete: ");
                    int deleteProductId = scanner.nextInt();
                    ps.deleteProduct(deleteProductId);
                    System.out.println("Product deleted successfully!");
                    System.out.println();
                    headerfooter("Going back to the main menu");
                    break;

                case 5:
                    List<Product> productList = ps.getAllProducts();
                    if (!productList.isEmpty()) {
                        System.out.println("All Products:");
                        System.out.println("+----------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
                        System.out.printf("| %-14s | %-20s | %-20s | %-20s | %-20s | %-20s |\n",
                                "Product ID", "Product Name", "Category", "Subcategory", "Weight", "Brand");
                        System.out.println("+----------------+----------------------+----------------------+----------------------+----------------------+----------------------+");

                        for (Product product : productList) {
                            System.out.printf("| %-14d | %-20s | %-20s | %-20s | %-20s | %-20s |\n",
                                    product.getProductId(),
                                    product.getProductName(),
                                    product.getCategory(),
                                    product.getSubCategory(),
                                    product.getProductWeight(),
                                    product.getBrand());
                            System.out.println("+----------------+----------------------+----------------------+----------------------+----------------------+----------------------+");

                        }
                    } else {
                        System.out.println("No products found!");
                    }
                    System.out.println();
                    headerfooter("Going back to the main menu");
                    break;

                case 0:
                    scanner.close();
                    headerfooter(End);
                    System.exit(0);
                    System.out.println();
                    headerfooter("Going back to the main menu");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    headerfooter(End);
            }
          }catch (Exception e) {
              System.out.println("Invalid input. Please enter a number.");
              scanner.nextLine(); // Clear the buffer
          }}
    }
    public static void main(String[] args) {
    			System.out.println("       __________________");
    	        System.out.println("      /\\                \\ ") ;
    	        System.out.println("     /  \\         		  \\");
    	        System.out.println("    /    \\                \\");
    	        System.out.println("   /______\\________________\\");
    	        System.out.println("  |        |                ||");
    	        System.out.println("  |  ____  |                ||");
    	        System.out.println("  | |    | |                ||");
    	        System.out.println("  | |    | |                ||");
    	        System.out.println("  | |    | |                ||");
    	        System.out.println("  | |    | |                ||");
    	        System.out.println("  |_|____|_|________________||");
    	

        
        String title = "Inventory Management System";
      
        headerfooter(title);
        menu();
 


    }
}
