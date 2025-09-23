// Parent class (package-private)
class Fruit {
    protected String color;
    protected String taste;

    // Default constructor
    public Fruit() {
        color = "Unknown";
        taste = "Unknown";
        System.out.println("Fruit default constructor called");
    }

    // Parameterized constructor
    public Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
        System.out.println("Fruit parameterized constructor called");
    }

    // Method to display fruit info
    public void displayInfo() {
        System.out.println("Color: " + color);
        System.out.println("Taste: " + taste);
    }
}

// Child class (public)
public class Apple extends Fruit {
    private String variety;

    // Default constructor
    public Apple() {
        super(); // calls Fruit default constructor
        variety = "Unknown";
        System.out.println("Apple default constructor called");
    }

    // Parameterized constructor
    public Apple(String color, String taste, String variety) {
        super(color, taste); // calls Fruit parameterized constructor
        this.variety = variety;
        System.out.println("Apple parameterized constructor called");
    }

    // Method to display full apple info
    public void displayAppleInfo() {
        super.displayInfo(); // display parent fields
        System.out.println("Variety: " + variety);
    }

    public static void main(String[] args) {
        System.out.println("--- Default Apple ---");
        Apple defaultApple = new Apple();
        defaultApple.displayAppleInfo();

        System.out.println("\n--- Parameterized Apple ---");
        Apple redApple = new Apple("Red", "Sweet", "Fuji");
        redApple.displayAppleInfo();

        // Accessing inherited protected fields directly
        redApple.color = "Green";
        redApple.taste = "Sour";
        System.out.println("\nAfter modifying inherited fields:");
        redApple.displayAppleInfo();
    }
}
