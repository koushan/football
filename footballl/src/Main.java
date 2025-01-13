import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> categories = new ArrayList<>();
        ArrayList<ArrayList<String>> productNames = new ArrayList<>();
        ArrayList<ArrayList<Double>> productPrices = new ArrayList<>();
        ArrayList<ArrayList<Integer>> productAvailability = new ArrayList<>();
        categories.add("Electronics");
        categories.add("Clothing");
        categories.add("Books");
        ArrayList<String> electronics = new ArrayList<>();
        electronics.add("Laptop");
        electronics.add("Smartphone");
        electronics.add("Headphones");
        productNames.add(electronics);
        ArrayList<Double> electronicsPrices = new ArrayList<>();
        electronicsPrices.add(899.99);
        electronicsPrices.add(499.99);
        electronicsPrices.add(149.99);
        productPrices.add(electronicsPrices);
        ArrayList<Integer> electronicsAvailability = new ArrayList<>();
        electronicsAvailability.add(5);
        electronicsAvailability.add(0);
        electronicsAvailability.add(10);
        productAvailability.add(electronicsAvailability);
        ArrayList<String> clothing = new ArrayList<>();
        clothing.add("T-shirt");
        clothing.add("Jeans");
        clothing.add("Jacket");
        productNames.add(clothing);
        ArrayList<Double> clothingPrices = new ArrayList<>();
        clothingPrices.add(19.99);
        clothingPrices.add(39.99);
        clothingPrices.add(89.99);
        productPrices.add(clothingPrices);
        ArrayList<Integer> clothingAvailability = new ArrayList<>();
        clothingAvailability.add(3);
        clothingAvailability.add(0);
        clothingAvailability.add(2);
        productAvailability.add(clothingAvailability);
        ArrayList<String> books = new ArrayList<>();
        books.add("Novel");
        books.add("Comic Book");
        books.add("Educational Book");
        productNames.add(books);
        ArrayList<Double> booksPrices = new ArrayList<>();
        booksPrices.add(12.99);
        booksPrices.add(5.99);
        booksPrices.add(29.99);
        productPrices.add(booksPrices);
        ArrayList<Integer> booksAvailability = new ArrayList<>();
        booksAvailability.add(7);
        booksAvailability.add(0);
        booksAvailability.add(4);
        productAvailability.add(booksAvailability);
        System.out.println("Please select a category to view products:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
        System.out.print("Enter the number of your choice: ");
        int choice = scanner.nextInt();
        if (choice >= 1 && choice <= categories.size()) {
            System.out.println("You selected " + categories.get(choice - 1) + ". Here are the products:");
            ArrayList<String> selectedProductNames = productNames.get(choice - 1);
            ArrayList<Double> selectedProductPrices = productPrices.get(choice - 1);
            ArrayList<Integer> selectedProductAvailability = productAvailability.get(choice - 1);
            for (int i = 0; i < selectedProductNames.size(); i++) {
                String availabilityStatus;
                if (selectedProductAvailability.get(i) > 0) {
                    availabilityStatus = "In Stock";
                } else {
                    availabilityStatus = "Out of Stock";
                }
                System.out.println(selectedProductNames.get(i) + " - $" + selectedProductPrices.get(i) + " - " + availabilityStatus);
            }
        } else {
            System.out.println("Invalid input! Please select a valid category (1, 2, or 3).");
        }
    }
}
