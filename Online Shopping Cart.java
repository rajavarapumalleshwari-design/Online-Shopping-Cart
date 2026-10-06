import java.util.*;

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class OnlineShoppingCart {

    // ArrayList stores available products
    static ArrayList<Product> products = new ArrayList<>();

    // HashMap stores product name and quantity
    static HashMap<String, Integer> cart = new HashMap<>();

    // HashMap stores product name and price
    static HashMap<String, Double> prices = new HashMap<>();

    // Add products to the store
    static void addProducts() {
        products.add(new Product("Laptop", 55000));
        products.add(new Product("Mobile Phone", 25000));
        products.add(new Product("Headphones", 2000));
        products.add(new Product("Keyboard", 1500));
        products.add(new Product("Mouse", 800));

        for (Product p : products) {
            prices.put(p.name, p.price);
        }
    }

    // Display available products
    static void displayProducts() {
        System.out.println("\n===== Available Products =====");

        for (Product p : products) {
            System.out.printf("%-15s ₹%.2f%n", p.name, p.price);
        }
    }

    // Add item to cart
    static void addToCart(String item, int quantity) {

        if (!prices.containsKey(item)) {
            System.out.println("Product not found.");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return;
        }

        cart.put(item, cart.getOrDefault(item, 0) + quantity);

        System.out.println(quantity + " " + item +
                "(s) added to your cart.");
    }

    // View cart
    static void viewCart() {

        if (cart.isEmpty()) {
            System.out.println("\nYour cart is empty.");
            return;
        }

        System.out.println("\n========== Your Cart ==========");

        double total = 0;

        for (Map.Entry<String, Integer> entry : cart.entrySet()) {

            String item = entry.getKey();
            int quantity = entry.getValue();
            double price = prices.get(item);

            double itemTotal = price * quantity;
            total += itemTotal;

            System.out.printf(
                    "%-15s Qty: %-3d Price: ₹%.2f%n",
                    item, quantity, itemTotal
            );
        }

        System.out.println("-------------------------------");
        System.out.printf("Total Price: ₹%.2f%n", total);
    }

    // Remove item
    static void removeFromCart(String item) {

        if (cart.containsKey(item)) {
            cart.remove(item);
            System.out.println(item + " removed from cart.");
        } else {
            System.out.println("Item is not present in the cart.");
        }
    }

    // Calculate total price
    static double calculateTotal() {

        double total = 0;

        for (Map.Entry<String, Integer> entry : cart.entrySet()) {

            String item = entry.getKey();
            int quantity = entry.getValue();
            double price = prices.get(item);

            total += price * quantity;
        }

        return total;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        addProducts();

        while (true) {

            System.out.println("\n===== Online Shopping Cart =====");
            System.out.println("1. View Products");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Remove Item");
            System.out.println("5. Calculate Total");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    displayProducts();
                    break;

                case 2:
                    displayProducts();

                    System.out.print("\nEnter product name: ");
                    String item = sc.nextLine();

                    System.out.print("Enter quantity: ");
                    int quantity = sc.nextInt();
                    sc.nextLine();

                    addToCart(item, quantity);
                    break;

                case 3:
                    viewCart();
                    break;

                case 4:
                    System.out.print("Enter item to remove: ");
                    String removeItem = sc.nextLine();

                    removeFromCart(removeItem);
                    break;

                case 5:
                    double total = calculateTotal();

                    System.out.printf(
                            "Total Cart Value: ₹%.2f%n",
                            total
                    );
                    break;

                case 6:
                    System.out.println(
                            "Thank you for shopping! Goodbye."
                    );
                    sc.close();
                    return;

                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }
    }
}