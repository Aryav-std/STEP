import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

// ===================== Immutable Product Class =====================
final class Product {
    private final String productId;
    private final String name;
    private final String category;
    private final String manufacturer;
    private final double basePrice;
    private final double weight;
    private final String[] features;
    private final Map<String, String> specifications;

    private Product(String productId, String name, String category, String manufacturer,
                    double basePrice, double weight, String[] features, Map<String, String> specifications) {
        if (productId == null || name == null || category == null || manufacturer == null) {
            throw new IllegalArgumentException("Invalid product data");
        }
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.basePrice = basePrice;
        this.weight = weight;
        this.features = features != null ? features.clone() : new String[0];
        this.specifications = specifications != null ? new HashMap<>(specifications) : new HashMap<>();
    }

    // Factory methods
    public static Product createElectronics(String productId, String name, double basePrice) {
        return new Product(productId, name, "Electronics", "Generic Manufacturer", basePrice, 1.5,
                new String[]{"Warranty"}, Map.of("Voltage", "220V"));
    }

    public static Product createClothing(String productId, String name, double basePrice) {
        return new Product(productId, name, "Clothing", "Generic Manufacturer", basePrice, 0.5,
                new String[]{"Size:M"}, Map.of("Material", "Cotton"));
    }

    public static Product createBooks(String productId, String name, double basePrice) {
        return new Product(productId, name, "Books", "Generic Publisher", basePrice, 0.3,
                new String[]{"Paperback"}, Map.of("Pages", "200"));
    }

    // Getters
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getManufacturer() { return manufacturer; }
    public double getBasePrice() { return basePrice; }
    public double getWeight() { return weight; }
    public String[] getFeatures() { return features.clone(); }
    public Map<String, String> getSpecifications() { return new HashMap<>(specifications); }

    // Final business method
    public final double calculateTax(String region) {
        double rate = 0.1; // default 10%
        if (region.equalsIgnoreCase("US")) rate = 0.07;
        else if (region.equalsIgnoreCase("EU")) rate = 0.2;
        return basePrice * rate;
    }

    @Override
    public String toString() {
        return "Product{" + name + ", " + category + ", Price: " + basePrice + "}";
    }
}

// ===================== Customer Class =====================
class Customer {
    private final String customerId;
    private final String email;
    private String name;
    private String phoneNumber;
    private String preferredLanguage;
    private final String accountCreationDate;

    public Customer(String customerId, String email, String name, String phoneNumber, String preferredLanguage) {
        this.customerId = customerId;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.preferredLanguage = preferredLanguage;
        this.accountCreationDate = LocalDate.now().toString();
    }

    public String getCustomerId() { return customerId; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
    String getCreditRating() { return "A+ Internal Rating"; }
    public String getPublicProfile() { return "Customer: " + name + ", Email: " + email; }

    @Override
    public String toString() {
        return "Customer{" + name + ", " + email + "}";
    }
}

// ===================== Shopping Cart =====================
class ShoppingCart {
    private final String cartId;
    private final String customerId;
    private final List<Object> items = new ArrayList<>();
    private double totalAmount = 0.0;
    private int itemCount = 0;

    public ShoppingCart(String cartId, String customerId) {
        this.cartId = cartId;
        this.customerId = customerId;
    }

    public boolean addItem(Object product, int quantity) {
        if (product instanceof Product p) {
            items.add(p);
            totalAmount += p.getBasePrice() * quantity - calculateDiscount();
            itemCount += quantity;
            System.out.println(quantity + " x " + p.getName() + " added to cart.");
            return true;
        }
        System.out.println("Invalid product type!");
        return false;
    }

    private double calculateDiscount() {
        return 5.0; // simple discount for demo
    }

    String getCartSummary() {
        return "Cart: " + cartId + ", Customer: " + customerId + ", Total: " + totalAmount + ", Items: " + itemCount;
    }

    @Override
    public String toString() {
        return getCartSummary();
    }
}

// ===================== Order & Payment Classes =====================
class Order {
    private final String orderId;
    private final LocalDateTime orderTime;

    public Order(String orderId) {
        this.orderId = orderId;
        this.orderTime = LocalDateTime.now();
    }

    public String getOrderId() { return orderId; }
    public LocalDateTime getOrderTime() { return orderTime; }
}

class PaymentProcessor {
    private final String processorId;
    private final String securityKey;

    public PaymentProcessor(String processorId, String securityKey) {
        this.processorId = processorId;
        this.securityKey = securityKey;
    }

    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " via " + processorId);
    }
}

class ShippingCalculator {
    private final Map<String, Double> shippingRates = Map.of("US", 10.0, "EU", 20.0, "IN", 5.0);

    public double calculateShipping(String region) {
        return shippingRates.getOrDefault(region, 15.0);
    }
}

// ===================== E-Commerce System =====================
final class ECommerceSystem {
    private static final Map<String, Object> productCatalog = new HashMap<>();

    public static void addProduct(Product product) {
        productCatalog.put(product.getProductId(), product);
    }

    public static boolean processOrder(Object order, Object customer) {
        if (order instanceof Order o && customer instanceof Customer c) {
            System.out.println("Processing order " + o.getOrderId() + " for customer " + c.getName());
            return true;
        }
        return false;
    }

    public static Map<String, Object> getCatalog() { return productCatalog; }
}

// ===================== Main Test =====================
public class ECommerceDemo {
    public static void main(String[] args) {
        // Products
        Product laptop = Product.createElectronics("P001", "Laptop", 1200);
        Product tshirt = Product.createClothing("P002", "T-Shirt", 25);
        Product book = Product.createBooks("P003", "Java Programming", 40);

        ECommerceSystem.addProduct(laptop);
        ECommerceSystem.addProduct(tshirt);
        ECommerceSystem.addProduct(book);

        // Customer
        Customer customer1 = new Customer("C001", "john@example.com", "John Doe", "555-1234", "EN");

        // Shopping Cart
        ShoppingCart cart = new ShoppingCart("CART001", customer1.getCustomerId());
        cart.addItem(laptop, 1);
        cart.addItem(tshirt, 2);

        System.out.println("\n" + cart);

        // Order
        Order order1 = new Order("ORD001");
        ECommerceSystem.processOrder(order1, customer1);

        // Payment & Shipping
        PaymentProcessor processor = new PaymentProcessor("PayPal", "SEC123");
        processor.processPayment(1250);
        ShippingCalculator shipping = new ShippingCalculator();
        System.out.println("Shipping cost to US: $" + shipping.calculateShipping("US"));
    }
}
