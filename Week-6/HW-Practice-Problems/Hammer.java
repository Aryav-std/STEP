// Parent class
class Tool {
    private String serialNumber;      // only accessible via getter/setter
    protected String toolType;        // accessible in child classes
    public String manufacturer;       // accessible everywhere

    // Constructor
    public Tool(String serialNumber, String toolType, String manufacturer) {
        this.serialNumber = serialNumber;
        this.toolType = toolType;
        this.manufacturer = manufacturer;
    }

    // Getter for private field
    public String getSerialNumber() {
        return serialNumber;
    }

    // Setter for private field
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    // Method to display tool info
    public void displayToolInfo() {
        System.out.println("Serial Number: " + serialNumber);
        System.out.println("Tool Type: " + toolType);
        System.out.println("Manufacturer: " + manufacturer);
    }
}

// Child class
public class Hammer extends Tool {
    private double weight; // specific to Hammer

    // Constructor
    public Hammer(String serialNumber, String toolType, String manufacturer, double weight) {
        super(serialNumber, toolType, manufacturer);
        this.weight = weight;
    }

    // Method to test access
    public void testAccess() {
        System.out.println("Accessing protected field toolType: " + toolType); // allowed
        System.out.println("Accessing public field manufacturer: " + manufacturer); // allowed

        // Cannot access private field directly: serialNumber
        // System.out.println(serialNumber); // ❌ would cause error
        System.out.println("Accessing private field via getter: " + getSerialNumber()); // allowed

        System.out.println("Hammer weight: " + weight);
    }

    public static void main(String[] args) {
        Hammer hammer = new Hammer("SN1234", "Claw Hammer", "Acme Tools", 1.5);

        System.out.println("--- Displaying Tool Info ---");
        hammer.displayToolInfo();

        System.out.println("\n--- Testing Access from Child ---");
        hammer.testAccess();

        System.out.println("\n--- Testing Access from Main ---");
        // Direct access
        // System.out.println(hammer.toolType); // protected → accessible because same package
        System.out.println("Public manufacturer: " + hammer.manufacturer); // public → always accessible
        System.out.println("Private serial via getter: " + hammer.getSerialNumber()); // allowed
    }
}
