import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
        electronicsNames.add("Laptop");
        electronicsPrices.add(800.00);
        electronicsQuantities.add(10);
        electronicsNames.add("Smartphone");
        electronicsPrices.add(500.00);
        electronicsQuantities.add(5);
        electronicsNames.add("Headphones");
        electronicsPrices.add(150.00);
        electronicsQuantities.add(0);
        clothingNames.add("T-Shirt");
        clothingPrices.add(20.00);
        clothingQuantities.add(50);
        clothingNames.add("Jeans");
        clothingPrices.add(40.00);
        clothingQuantities.add(30);
        clothingNames.add("Jacket");
        clothingPrices.add(60.00);
        clothingQuantities.add(2);
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
        while (true) {
            System.out.println("Welcome to the eCommerce App!");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Customer");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int loginChoice = scanner.nextInt();
            scanner.nextLine();
            if (loginChoice == 1) {
                System.out.print("Enter admin username: ");
                String username = scanner.nextLine();
                System.out.print("Enter admin password: ");
                String password = scanner.nextLine();
                if (username.equals("admin") && password.equals("admin123")) {
                    isAdmin = true;
                    isUserLoggedIn = true;
                    System.out.println("Logged in as Admin.");
                    break;
                } else {
                    System.out.println("Invalid credentials. Please try again.");
                }
            } else if (loginChoice == 2) {
                System.out.print("Enter customer username: ");
                String username = scanner.nextLine();
                System.out.print("Enter customer password: ");
                String password = scanner.nextLine();
                if (username.equals("customer") && password.equals("customer123")) {
                    isUserLoggedIn = true;
                    System.out.println("Logged in as Customer.");
                    break;
                } else {
                    System.out.println("Invalid credentials. Please try again.");
                }
            } else if (loginChoice == 3) {
                System.out.println("Exiting the program.");
                return;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
        while (isUserLoggedIn) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Browse Product Categories");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            if (isAdmin) {
                System.out.println("4. View Orders (Admin)");
            }
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                browseCategories(scanner, electronicsNames, electronicsPrices, electronicsQuantities,
                        clothingNames, clothingPrices, clothingQuantities,
                        booksNames, booksPrices, booksQuantities, cartNames, cartQuantities, cartPrices);
            } else if (choice == 2) {
                displayCart(cartNames, cartQuantities, cartPrices);
            } else if (choice == 3) {
                checkout(cartNames, cartQuantities, cartPrices);
            } else if (choice == 4 && isAdmin) {
                viewOrders(cartNames, cartQuantities, cartPrices);
            } else if (choice == 5) {
                System.out.println("Logging out...");
                isUserLoggedIn = false;
                isAdmin = false;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
    public static void browseCategories(Scanner scanner,
                                        ArrayList<String> electronicsNames, ArrayList<Double> electronicsPrices, ArrayList<Integer> electronicsQuantities,
                                        ArrayList<String> clothingNames, ArrayList<Double> clothingPrices, ArrayList<Integer> clothingQuantities,
                                        ArrayList<String> booksNames, ArrayList<Double> booksPrices, ArrayList<Integer> booksQuantities,
                                        ArrayList<String> cartNames, ArrayList<Integer> cartQuantities, ArrayList<Double> cartPrices) {
        System.out.println("\nSelect a Category:");
        System.out.println("1. Electronics");
        System.out.println("2. Clothing");
        System.out.println("3. Books");
        System.out.print("Choose a category: ");
        int categoryChoice = scanner.nextInt();
        if (categoryChoice == 1) {
            browseProducts(scanner, electronicsNames, electronicsPrices, electronicsQuantities, cartNames, cartQuantities, cartPrices);
        } else if (categoryChoice == 2) {
            browseProducts(scanner, clothingNames, clothingPrices, clothingQuantities, cartNames, cartQuantities, cartPrices);
        } else if (categoryChoice == 3) {
            browseProducts(scanner, booksNames, booksPrices, booksQuantities, cartNames, cartQuantities, cartPrices);
        } else {
            System.out.println("Invalid category. Please try again.");
        }
    }
    public static void browseProducts(Scanner scanner, ArrayList<String> names, ArrayList<Double> prices, ArrayList<Integer> quantities,
                                      ArrayList<String> cartNames, ArrayList<Integer> cartQuantities, ArrayList<Double> cartPrices) {
        System.out.println("\nAvailable Products:");
        for (int i = 0; i < names.size(); i++) {
            String availability = (quantities.get(i) > 0) ? "In Stock" : "Out of Stock";
            System.out.println((i + 1) + ". " + names.get(i) + " - $" + prices.get(i) + " - " + availability);
        }
        System.out.print("Select a product to add to cart: ");
        int productChoice = scanner.nextInt();
        if (productChoice >= 1 && productChoice <= names.size()) {
            int productIndex = productChoice - 1;
            if (quantities.get(productIndex) == 0) {
                System.out.println(names.get(productIndex) + " is out of stock.");
            } else {
                System.out.print("Enter quantity to add to cart: ");
                int quantity = scanner.nextInt();
                if (quantity <= quantities.get(productIndex)) {
                    quantities.set(productIndex, quantities.get(productIndex) - quantity);
                    cartNames.add(names.get(productIndex));
                    cartQuantities.add(quantity);
                    cartPrices.add(prices.get(productIndex) * quantity);
                    System.out.println("Added " + quantity + " of " + names.get(productIndex) + " to your cart.");
                } else {
                    System.out.println("Not enough stock available.");
                }
            }
        } else {
            System.out.println("Invalid product choice.");
        }
    }
    public static void displayCart(ArrayList<String> cartNames, ArrayList<Integer> cartQuantities, ArrayList<Double> cartPrices) {
        if (cartNames.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("\nYour Cart:");
            for (int i = 0; i < cartNames.size(); i++) {
                System.out.println(cartNames.get(i) + " - Quantity: " + cartQuantities.get(i) + " - Total: $" + cartPrices.get(i));
            }
        }
    }
    public static void checkout(ArrayList<String> cartNames, ArrayList<Integer> cartQuantities, ArrayList<Double> cartPrices) {
        double total = 0;
        for (int i = 0; i < cartPrices.size(); i++) {
            total += cartPrices.get(i);
        }
        System.out.println("\nInvoice:");
        displayCart(cartNames, cartQuantities, cartPrices);
        System.out.println("Total Price: $" + total);
        System.out.print("Confirm order? (y/n): ");
        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("y")) {
            System.out.println("Thank you for your purchase!");
        } else {
            System.out.println("Order cancelled.");
        }
    }
    public static void viewOrders(ArrayList<String> cartNames, ArrayList<Integer> cartQuantities, ArrayList<Double> cartPrices) {
        System.out.println("\nOrders:");
        displayCart(cartNames, cartQuantities, cartPrices);
        System.out.println("Mark orders as shipped? (y/n): ");
        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("y")) {
            System.out.println("Order marked as shipped.");
        } else {
            System.out.println("Order remains unshipped.");
        }
    }
}
