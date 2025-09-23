// Base class (package-private)
class Vehicle {
    protected String brand, model;
    protected int year;
    protected String engineType;

    private String registrationNumber;
    private boolean isRunning;

    public Vehicle() {
        this.brand = "Generic";
        this.model = "Standard";
        this.year = 2025;
        this.engineType = "Petrol";
        this.registrationNumber = "REG" + (int)(Math.random()*1000);
        this.isRunning = false;
        System.out.println("Vehicle default constructor called");
    }

    public Vehicle(String brand, String model, int year, String engineType) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engineType = engineType;
        this.registrationNumber = "REG" + (int)(Math.random()*1000);
        this.isRunning = false;
        System.out.println("Vehicle parameterized constructor called");
    }

    public void start() {
        isRunning = true;
        System.out.println("Vehicle started");
    }

    public void stop() {
        isRunning = false;
        System.out.println("Vehicle stopped");
    }

    public String getVehicleInfo() {
        return brand + " " + model + " (" + year + "), Engine: " + engineType;
    }

    public void displaySpecs() {
        System.out.println("Vehicle Specs: " + getVehicleInfo());
    }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String reg) { this.registrationNumber = reg; }
    public boolean isRunning() { return isRunning; }
}

// Derived class (public)
public class Car extends Vehicle {
    private int numberOfDoors;
    private String fuelType, transmissionType;

    public Car() {
        super();
        this.numberOfDoors = 4;
        this.fuelType = "Petrol";
        this.transmissionType = "Manual";
        System.out.println("Car default constructor called");
    }

    public Car(String brand, String model, int year, String engineType,
               int numberOfDoors, String fuelType, String transmissionType) {
        super(brand, model, year, engineType);
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        System.out.println("Car parameterized constructor called");
    }

    @Override
    public void start() {
        super.start();
        System.out.println("Car-specific startup sequence");
    }

    @Override
    public void displaySpecs() {
        super.displaySpecs();
        System.out.println("Doors: " + numberOfDoors + ", Fuel: " + fuelType + ", Transmission: " + transmissionType);
    }

    public void openTrunk() { System.out.println("Trunk opened"); }
    public void playRadio() { System.out.println("Radio playing music"); }

    public static void main(String[] args) {
        System.out.println("--- Default Car ---");
        Car defaultCar = new Car();
        defaultCar.displaySpecs();
        defaultCar.start();
        defaultCar.openTrunk();
        defaultCar.playRadio();

        System.out.println("\n--- Parameterized Car ---");
        Car paramCar = new Car("Toyota", "Corolla", 2023, "Hybrid", 4, "Hybrid", "Automatic");
        paramCar.displaySpecs();
        paramCar.start();
        paramCar.openTrunk();
        paramCar.playRadio();
    }
}
