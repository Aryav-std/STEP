// Base class
class Phone {
    protected String brand;
    protected String model;

    public Phone() {
        this.brand = "Unknown";
        this.model = "Unknown";
        System.out.println("Phone default constructor called");
    }

    public Phone(String brand, String model) {
        this.brand = brand;
        this.model = model;
        System.out.println("Phone parameterized constructor called");
    }

    public void showPhone() {
        System.out.println("Brand: " + brand + ", Model: " + model);
    }
}

// Child class
class SmartPhone extends Phone {
    private String operatingSystem;

    public SmartPhone() {
        super(); // calls Phone default constructor
        this.operatingSystem = "Unknown OS";
        System.out.println("SmartPhone default constructor called");
    }

    public SmartPhone(String brand, String model) {
        super(brand, model); // calls Phone parameterized constructor
        this.operatingSystem = "Unknown OS";
        System.out.println("SmartPhone constructor with brand & model called");
    }

    public SmartPhone(String brand, String model, String operatingSystem) {
        super(brand, model);
        this.operatingSystem = operatingSystem;
        System.out.println("SmartPhone parameterized constructor called");
    }

    public void showSmartPhone() {
        showPhone(); // inherited method
        System.out.println("Operating System: " + operatingSystem);
    }
}

// Test class
public class PhoneTest {
    public static void main(String[] args) {
        System.out.println("--- Default SmartPhone ---");
        SmartPhone phone1 = new SmartPhone();
        phone1.showSmartPhone();

        System.out.println("\n--- Brand & Model SmartPhone ---");
        SmartPhone phone2 = new SmartPhone("Apple", "iPhone 15");
        phone2.showSmartPhone();

        System.out.println("\n--- Full SmartPhone ---");
        SmartPhone phone3 = new SmartPhone("Samsung", "Galaxy S24", "Android 14");
        phone3.showSmartPhone();
    }
}
