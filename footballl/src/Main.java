import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<String> categories = new ArrayList<>();
    static ArrayList<ArrayList<String[]>> products = new ArrayList<>();
    static ArrayList<String[]> cart = new ArrayList<>();  // Cart to hold items (Product, Price, Quantity)
    static int cartSize = 0;
    public static void main(String[] args) {
        initializeCategoriesAndProducts();
        while (true) {
            System.out.println("\nWelcome to the Online Shop!");
            System.out.println("1. Browse Categories");
            System.out.println("2. Checkout");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                browseCategories();
            } else if (choice == 2) {
                checkout();
            } else if (choice == 3) {
                System.out.println("Thank you for visiting! Goodbye.");
                break;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    public static void initializeCategoriesAndProducts() {
        categories.add("Electronics");
        categories.add("Clothing");
        categories.add("Books");
        ArrayList<String[]> electronics = new ArrayList<>();
        electronics.add(new String[]{"Laptop", "$999.99", "10"});
        electronics.add(new String[]{"Smartphone", "$499.99", "5"});
        electronics.add(new String[]{"Headphones", "$79.99", "0"});
        ArrayList<String[]> clothing = new ArrayList<>();
        clothing.add(new String[]{"T-shirt", "$19.99", "20"});
        clothing.add(new String[]{"Jeans", "$49.99", "3"});
        clothing.add(new String[]{"Jacket", "$99.99", "2"});
        ArrayList<String[]> books = new ArrayList<>();
        books.add(new String[]{"Java Programming", "$29.99", "10"});
        books.add(new String[]{"Data Structures", "$39.99", "0"});
        books.add(new String[]{"Python Programming", "$24.99", "5"});
        products.add(electronics);
        products.add(clothing);
        products.add(books);
    }
    public static void browseCategories() {
        System.out.println("\nSelect a category to browse:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
        System.out.print("Enter your choice: ");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine();
        if (categoryChoice == 1) {
            browseProducts(products.get(0), "Electronics");
        } else if (categoryChoice == 2) {
            browseProducts(products.get(1), "Clothing");
        } else if (categoryChoice == 3) {
            browseProducts(products.get(2), "Books");
        } else {
            System.out.println("Invalid category! Please try again.");
        }
    }
    public static void browseProducts(ArrayList<String[]> categoryProducts, String categoryName) {
        System.out.println("\nProducts in " + categoryName + " category:");
        for (int i = 0; i < categoryProducts.size(); i++) {
            String[] product = categoryProducts.get(i);
            String productName = product[0];
            String productPrice = product[1];
            String productAvailability = product[2];
            String availabilityMessage = Integer.parseInt(productAvailability) > 0 ? productAvailability : "Out of Stock";
            System.out.println((i + 1) + ". " + productName + " - Price: " + productPrice + " - Available: " + availabilityMessage);
        }
        System.out.print("\nEnter the product number to add to cart (0 to go back): ");
        int productChoice = scanner.nextInt();
        scanner.nextLine();
        if (productChoice == 0) {
            return;
        }
        if (productChoice < 1 || productChoice > categoryProducts.size()) {
            System.out.println("Invalid product number! Please try again.");
            return;
        }
        String[] selectedProduct = categoryProducts.get(productChoice - 1);
        String selectedProductName = selectedProduct[0];
        String selectedProductPrice = selectedProduct[1];
        int availableStock = Integer.parseInt(selectedProduct[2]);
        if (availableStock == 0) {
            System.out.println("This product is out of stock!");
            return;
        }
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        if (quantity > availableStock) {
            System.out.println("Sorry, we only have " + availableStock + " available. Please enter a valid quantity.");
            return;
        }
        selectedProduct[2] = String.valueOf(availableStock - quantity);
        cart.add(new String[]{selectedProductName, selectedProductPrice, String.valueOf(quantity)});
        System.out.println(quantity + " " + selectedProductName + " added to cart.");
        showCart();
    }
    public static void showCart() {
        System.out.println("\nYour Cart:");
        if (cartSize == 0) {
            System.out.println("Your cart is empty.");
        } else {
            double total = 0;
            for (int i = 0; i < cart.size(); i++) {
                String[] item = cart.get(i);
                String product = item[0];
                String price = item[1];
                int quantity = Integer.parseInt(item[2]);
                double itemPrice = Double.parseDouble(price.substring(1)); // Remove '$' from price
                System.out.println(product + " x" + quantity + " = " + "$" + (itemPrice * quantity));
                total += itemPrice * quantity;
            }
            System.out.println("Total: $" + total);
        }
    }
    public static void checkout() {
        if (cart.size() == 0) {
            System.out.println("Your cart is empty. Please add items to your cart before checking out.");
            return;
        }
        System.out.println("\nCheckout Summary:");
        showCart();
        System.out.println("\nDo you want to confirm your order? (Y/N): ");
        String confirmOrder = scanner.nextLine();
        if (confirmOrder.equalsIgnoreCase("Y")) {
            System.out.println("Thank you for your purchase!");
            cart.clear();
        } else {
            System.out.println("Order canceled.");
        }
    }
}
