// Base class
class Fruit {
    protected String color;
    protected String taste;

    public Fruit() {
        this.color = "Unknown";
        this.taste = "Unknown";
        System.out.println("Fruit default constructor called");
    }

    public Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
        System.out.println("Fruit parameterized constructor called");
    }

    public void showFruit() {
        System.out.println("Color: " + color + ", Taste: " + taste);
    }
}

// Child class
class Apple extends Fruit {
    private String variety;

    public Apple() {
        super(); // calls Fruit default constructor
        this.variety = "Unknown";
        System.out.println("Apple default constructor called");
    }

    public Apple(String color, String taste, String variety) {
        super(color, taste); // calls Fruit parameterized constructor
        this.variety = variety;
        System.out.println("Apple parameterized constructor called");
    }

    public void showApple() {
        showFruit(); // access inherited method
        System.out.println("Variety: " + variety);
    }
}

// Test class
public class FruitTest {
    public static void main(String[] args) {
        // Using default constructors
        Apple apple1 = new Apple();
        apple1.showApple();

        System.out.println();

        // Using parameterized constructors
        Apple apple2 = new Apple("Red", "Sweet", "Honeycrisp");
        apple2.showApple();

        System.out.println();

        // Access inherited fields directly
        System.out.println("Accessing inherited fields directly:");
        System.out.println("Color: " + apple2.color);
        System.out.println("Taste: " + apple2.taste);
    }
}
