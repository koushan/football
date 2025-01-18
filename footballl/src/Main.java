import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Initialize product lists
        ArrayList<String> electronicsNames = new ArrayList<>();
        ArrayList<Double> electronicsPrices = new ArrayList<>();
        ArrayList<Integer> electronicsQuantities = new ArrayList<>();
        ArrayList<String> clothingNames = new ArrayList<>();
        ArrayList<Double> clothingPrices = new ArrayList<>();
        ArrayList<Integer> clothingQuantities = new ArrayList<>();
        ArrayList<String> booksNames = new ArrayList<>();
        ArrayList<Double> booksPrices = new ArrayList<>();
        ArrayList<Integer> booksQuantities = new ArrayList<>();
        ArrayList<String> cartNames = new ArrayList<>();
        ArrayList<Integer> cartQuantities = new ArrayList<>();
        ArrayList<Double> cartPrices = new ArrayList<>();
        // Initialize products
        // Electronics
        electronicsNames.add("Laptop");
        electronicsPrices.add(800.00);
        electronicsQuantities.add(10);
        electronicsNames.add("Smartphone");
        electronicsPrices.add(500.00);
        electronicsQuantities.add(5);
        electronicsNames.add("Headphones");
        electronicsPrices.add(150.00);
        electronicsQuantities.add(0);
        // Clothing
        clothingNames.add("T-Shirt");
        clothingPrices.add(20.00);
        clothingQuantities.add(50);
        clothingNames.add("Jeans");
        clothingPrices.add(40.00);
        clothingQuantities.add(30);
        clothingNames.add("Jacket");
        clothingPrices.add(60.00);
        clothingQuantities.add(2);
        // Books
        booksNames.add("Java Programming");
        booksPrices.add(30.00);
        booksQuantities.add(3);
        booksNames.add("Data Structures");
        booksPrices.add(25.00);
        booksQuantities.add(5);
        booksNames.add("Design Patterns");
        booksPrices.add(35.00);
        booksQuantities.add(0);
        boolean isUserLoggedIn = false;
        boolean isAdmin = false;
        // User login loop
        while (true) {
            System.out.println("Welcome to the eCommerce App!");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Customer");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int loginChoice = scanner.nextInt();
            scanner.nextLine();
            if (loginChoice == 1) {
                // Admin login
                System.out.print("Enter admin username: ");
                String username = scanner.nextLine();
                System.out.print("Enter admin password: ");
                String password = scanner.nextLine();
                if (username.equals("admin") && password.equals("admin123")) {
                    isAdmin = true;
                    isUserLoggedIn = true;
                    System.out.println("Logged in as Admin.");
                    break; // Break to admin menu
                } else {
                    System.out.println("Invalid credentials. Please try again.");
                }
            } else if (loginChoice == 2) {
                // Customer login (no credentials required)
                isUserLoggedIn = true;
                System.out.println("Logged in as Customer.");
                break; // Break to customer menu
            } else if (loginChoice == 3) {
                // Exit the program
                System.out.println("Exiting the program.");
                return;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
        // Admin menu loop
        if (isAdmin) {
            while (isUserLoggedIn) {
                System.out.println("\nAdmin Menu:");
                System.out.println("1. View Orders");
                System.out.println("2. Logout");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (choice == 1) {
                    // View Orders
                    System.out.println("\nOrders:");
                    if (cartNames.isEmpty()) {
                        System.out.println("No orders to display.");
                    } else {
                        for (int i = 0; i < cartNames.size(); i++) {
                            System.out.println(cartNames.get(i) + " - Quantity: " + cartQuantities.get(i) + " - Total: $" + cartPrices.get(i));
                        }
                    }
                    System.out.println("Mark orders as shipped? (y/n): ");
                    String confirmation = scanner.nextLine();
                    if (confirmation.equalsIgnoreCase("y")) {
                        System.out.println("Order marked as shipped.");
                    } else {
                        System.out.println("Order remains unshipped.");
                    }
                } else if (choice == 2) {
                    // Logout
                    System.out.println("Logging out...");
                    isUserLoggedIn = false;
                    isAdmin = false;
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            // Customer menu loop
            while (isUserLoggedIn) {
                System.out.println("\nMain Menu:");
                System.out.println("1. Browse Product Categories");
                System.out.println("2. View Cart");
                System.out.println("3. Checkout");
                System.out.println("4. Logout");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (choice == 1) {
                    // Browse product categories
                    System.out.println("\nSelect a Category:");
                    System.out.println("1. Electronics");
                    System.out.println("2. Clothing");
                    System.out.println("3. Books");
                    System.out.print("Choose a category: ");
                    int categoryChoice = scanner.nextInt();
                    // Handle Electronics
                    if (categoryChoice == 1) {
                        System.out.println("\nAvailable Electronics:");
                        for (int i = 0; i < electronicsNames.size(); i++) {
                            String availability = (electronicsQuantities.get(i) > 0) ? "In Stock" : "Out of Stock";
                            System.out.println((i + 1) + ". " + electronicsNames.get(i) + " - $" + electronicsPrices.get(i) + " - " + availability);
                        }
                        System.out.print("Select a product to add to cart: ");
                        int productChoice = scanner.nextInt();
                        if (productChoice >= 1 && productChoice <= electronicsNames.size()) {
                            int productIndex = productChoice - 1;
                            if (electronicsQuantities.get(productIndex) == 0) {
                                System.out.println(electronicsNames.get(productIndex) + " is out of stock.");
                            } else {
                                System.out.print("Enter quantity to add to cart: ");
                                int quantity = scanner.nextInt();
                                if (quantity <= electronicsQuantities.get(productIndex)) {
                                    electronicsQuantities.set(productIndex, electronicsQuantities.get(productIndex) - quantity);
                                    cartNames.add(electronicsNames.get(productIndex));
                                    cartQuantities.add(quantity);
                                    cartPrices.add(electronicsPrices.get(productIndex) * quantity);
                                    System.out.println("Added " + quantity + " of " + electronicsNames.get(productIndex) + " to your cart.");
                                } else {
                                    System.out.println("Not enough stock available.");
                                }
                            }
                        } else {
                            System.out.println("Invalid product choice.");
                        }
                    }
                    // Handle Clothing
                    else if (categoryChoice == 2) {
                        System.out.println("\nAvailable Clothing:");
                        for (int i = 0; i < clothingNames.size(); i++) {
                            String availability = (clothingQuantities.get(i) > 0) ? "In Stock" : "Out of Stock";
                            System.out.println((i + 1) + ". " + clothingNames.get(i) + " - $" + clothingPrices.get(i) + " - " + availability);
                        }
                        System.out.print("Select a product to add to cart: ");
                        int productChoice = scanner.nextInt();
                        if (productChoice >= 1 && productChoice <= clothingNames.size()) {
                            int productIndex = productChoice - 1;
                            if (clothingQuantities.get(productIndex) == 0) {
                                System.out.println(clothingNames.get(productIndex) + " is out of stock.");
                            } else {
                                System.out.print("Enter quantity to add to cart: ");
                                int quantity = scanner.nextInt();
                                if (quantity <= clothingQuantities.get(productIndex)) {
                                    clothingQuantities.set(productIndex, clothingQuantities.get(productIndex) - quantity);
                                    cartNames.add(clothingNames.get(productIndex));
                                    cartQuantities.add(quantity);
                                    cartPrices.add(clothingPrices.get(productIndex) * quantity);
                                    System.out.println("Added " + quantity + " of " + clothingNames.get(productIndex) + " to your cart.");
                                } else {
                                    System.out.println("Not enough stock available.");
                                }
                            }
                        } else {
                            System.out.println("Invalid product choice.");
                        }
                    }
                    // Handle Books
                    else if (categoryChoice == 3) {
                        System.out.println("\nAvailable Books:");
                        for (int i = 0; i < booksNames.size(); i++) {
                            String availability = (booksQuantities.get(i) > 0) ? "In Stock" : "Out of Stock";
                            System.out.println((i + 1) + ". " + booksNames.get(i) + " - $" + booksPrices.get(i) + " - " + availability);
                        }
                        System.out.print("Select a product to add to cart: ");
                        int productChoice = scanner.nextInt();
                        if (productChoice >= 1 && productChoice <= booksNames.size()) {
                            int productIndex = productChoice - 1;
                            if (booksQuantities.get(productIndex) == 0) {
                                System.out.println(booksNames.get(productIndex) + " is out of stock.");
                            } else {
                                System.out.print("Enter quantity to add to cart: ");
                                int quantity = scanner.nextInt();
                                if (quantity <= booksQuantities.get(productIndex)) {
                                    booksQuantities.set(productIndex, booksQuantities.get(productIndex) - quantity);
                                    cartNames.add(booksNames.get(productIndex));
                                    cartQuantities.add(quantity);
                                    cartPrices.add(booksPrices.get(productIndex) * quantity);
                                    System.out.println("Added " + quantity + " of " + booksNames.get(productIndex) + " to your cart.");
                                } else {
                                    System.out.println("Not enough stock available.");
                                }
                            }
                        } else {
                            System.out.println("Invalid product choice.");
                        }
                    } else {
                        System.out.println("Invalid category. Please try again.");
                    }
                }
                // View Cart
                else if (choice == 2) {
                    if (cartNames.isEmpty()) {
                        System.out.println("Your cart is empty.");
                    } else {
                        System.out.println("\nYour Cart:");
                        for (int i = 0; i < cartNames.size(); i++) {
                            System.out.println(cartNames.get(i) + " - Quantity: " + cartQuantities.get(i) + " - Total: $" + cartPrices.get(i));
                        }
                    }
                }
                // Checkout
                else if (choice == 3) {
                    double total = 0;
                    for (int i = 0; i < cartPrices.size(); i++) {
                        total += cartPrices.get(i);
                    }
                    System.out.println("\nInvoice:");
                    if (cartNames.isEmpty()) {
                        System.out.println("Your cart is empty.");
                    } else {
                        for (int i = 0; i < cartNames.size(); i++) {
                            System.out.println(cartNames.get(i) + " - Quantity: " + cartQuantities.get(i) + " - Total: $" + cartPrices.get(i));
                        }
                    }
                    System.out.println("Total Price: $" + total);
                    System.out.print("Confirm order? (y/n): ");
                    String confirmation = scanner.nextLine();
                    if (confirmation.equalsIgnoreCase("y")) {
                        System.out.println("Thank you for your purchase!");
                    } else {
                        System.out.println("Order cancelled.");
                    }
                }
                // Logout
                else if (choice == 4) {
                    System.out.println("Logging out...");
                    isUserLoggedIn = false;
                    isAdmin = false;
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
        scanner.close();
    }
}