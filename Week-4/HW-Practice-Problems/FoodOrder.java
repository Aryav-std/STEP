public class FoodOrder {
    String customerName;
    String foodItem;
    int quantity;
    double price;
    static final double fixedRate = 150;

    // Default constructor assigns "Unknown" order
    public FoodOrder() {
        customerName = "Unknown";
        foodItem = "Unknown";
        quantity = 0;
        price = 0;
    }

    // Constructor with food item, quantity=1, default price
    public FoodOrder(String foodItem) {
        this.foodItem = foodItem;
        quantity = 1;
        price = fixedRate;
        customerName = "Unknown";
    }

    // Constructor with food item and quantity, price calculated
    public FoodOrder(String foodItem, int quantity) {
        this.foodItem = foodItem;
        this.quantity = quantity;
        price = quantity * fixedRate;
        customerName = "Unknown";
    }

    // Print bill method
    public void printBill() {
        System.out.println("Customer: " + customerName + ", Food: " + foodItem + ", Quantity: " + quantity + ", Total Price: " + price);
    }

    public static void main(String[] args) {
        FoodOrder o1 = new FoodOrder();
        FoodOrder o2 = new FoodOrder("Burger");
        FoodOrder o3 = new FoodOrder("Pizza", 3);

        o1.printBill();
        o2.printBill();
        o3.printBill();
    }
}
