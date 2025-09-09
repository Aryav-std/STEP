public class GameController {
    private String controllerBrand;
    private String connectionType;
    private boolean hasVibration;
    private int batteryLevel;
    private double sensitivity;

    public GameController() {
        controllerBrand = "GenericPad";
        connectionType = "USB";
        hasVibration = true;
        batteryLevel = 100;
        sensitivity = 1.0;
    }

    public GameController(String controllerBrand, String connectionType, boolean hasVibration, int batteryLevel, double sensitivity) {
        this.controllerBrand = controllerBrand;
        this.connectionType = connectionType;
        this.hasVibration = hasVibration;
        this.batteryLevel = batteryLevel < 0 ? 0 : (batteryLevel > 100 ? 100 : batteryLevel);
        this.sensitivity = sensitivity < 0.1 ? 0.1 : (sensitivity > 3.0 ? 3.0 : sensitivity);
    }

    public GameController(String brand, String connectionType) {
        controllerBrand = brand;
        this.connectionType = connectionType;
        hasVibration = true;
        batteryLevel = 100;
        sensitivity = 1.0;
    }

    public void calibrateController() {
        System.out.println("Calibrating " + controllerBrand + " controller...");
    }

    public void displayConfiguration() {
        System.out.println("Brand: " + controllerBrand);
        System.out.println("Connection: " + connectionType);
        System.out.println("Vibration: " + (hasVibration ? "Enabled" : "Disabled"));
        System.out.println("Battery Level: " + batteryLevel);
        System.out.println("Sensitivity: " + sensitivity);
    }

    public void testVibration() {
        if (hasVibration) {
            System.out.println("*BUZZ* Vibration test successful!");
        } else {
            System.out.println("Vibration disabled on this controller.");
        }
    }

    public static void main(String[] args) {
        GameController gcDefault = new GameController();
        GameController gcCustom = new GameController("ProPad", "Bluetooth", false, 75, 2.5);
        GameController gcConvenience = new GameController("LitePad", "Wireless");

        System.out.println("=== GAMING CONTROLLER SETUP ===");

        gcDefault.displayConfiguration();
        gcDefault.calibrateController();
        gcDefault.testVibration();

        gcCustom.displayConfiguration();
        gcCustom.calibrateController();
        gcCustom.testVibration();

        gcConvenience.displayConfiguration();
        gcConvenience.calibrateController();
        gcConvenience.testVibration();
    }
}
