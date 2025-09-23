// Base class
class Animal {
    protected String species;
    protected String habitat;
    protected int lifespan;
    protected boolean isWildlife;

    public Animal(String species, String habitat, int lifespan, boolean isWildlife) {
        this.species = species;
        this.habitat = habitat;
        this.lifespan = lifespan;
        this.isWildlife = isWildlife;
        System.out.println("Animal constructor: Creating " + species);
    }

    public void eat() { System.out.println("Animal is eating"); }
    public void sleep() { System.out.println("Animal is sleeping"); }
    public void move() { System.out.println("Animal is moving"); }
    public String getAnimalInfo() {
        return "Species: " + species + ", Habitat: " + habitat +
               ", Lifespan: " + lifespan + " years, Wildlife: " + isWildlife;
    }
}

// Intermediate class
class Mammal extends Animal {
    protected String furColor;
    protected boolean hasWarmBlood;
    protected int gestationPeriod;

    public Mammal(String species, String habitat, int lifespan, boolean isWildlife,
                  String furColor, int gestationPeriod) {
        super(species, habitat, lifespan, isWildlife);
        this.furColor = furColor;
        this.hasWarmBlood = true;
        this.gestationPeriod = gestationPeriod;
        System.out.println("Mammal constructor: Adding mammal traits");
    }

    @Override
    public void move() {
        super.move();
        System.out.println("Mammal is walking/running");
    }

    public void nurse() { System.out.println("Mammal is nursing offspring"); }
    public void regulateTemperature() { System.out.println("Maintaining body temperature"); }
}

// Public class with main
public class Dog extends Mammal {
    private String breed;
    private boolean isDomesticated;
    private int loyaltyLevel;
    private String favoriteActivity;

    public Dog() {
        super("Dog", "Domestic", 13, false, "Brown", 60);
        this.breed = "Mixed";
        this.isDomesticated = true;
        this.loyaltyLevel = 5;
        this.favoriteActivity = "Playing";
        System.out.println("Dog constructor: Creating " + breed + " dog");
    }

    public Dog(String species, String habitat, int lifespan, boolean isWildlife,
               String furColor, int gestationPeriod,
               String breed, boolean isDomesticated, int loyaltyLevel, String favoriteActivity) {
        super(species, habitat, lifespan, isWildlife, furColor, gestationPeriod);
        this.breed = breed;
        this.isDomesticated = isDomesticated;
        this.loyaltyLevel = loyaltyLevel;
        this.favoriteActivity = favoriteActivity;
        System.out.println("Dog constructor: Creating " + breed + " dog");
    }

    public Dog(Dog other) {
        this(other.species, other.habitat, other.lifespan, other.isWildlife,
             other.furColor, other.gestationPeriod,
             other.breed, other.isDomesticated, other.loyaltyLevel, other.favoriteActivity);
    }

    @Override
    public void eat() {
        super.eat();
        System.out.println("Dog is wagging tail while eating");
    }

    @Override
    public void move() { System.out.println("Dog is running and playing"); }

    @Override
    public void sleep() { System.out.println("Dog is sleeping in doghouse"); }

    public void bark() { System.out.println("Woof! Woof!"); }
    public void fetch() { System.out.println("Dog is fetching the ball"); }
    public void showLoyalty() { System.out.println("Dog loyalty level: " + loyaltyLevel + "/10"); }

    public void demonstrateInheritance() {
        System.out.println("\n--- Demonstrating Inheritance ---");
        System.out.println(getAnimalInfo());
        move();
        eat();
        sleep();
        nurse();
        regulateTemperature();
        bark();
        fetch();
        showLoyalty();
    }

    public static void main(String[] args) {
        Dog basicDog = new Dog();
        basicDog.demonstrateInheritance();

        Dog detailedDog = new Dog("Dog", "Farm", 12, false, "Golden", 65,
                                  "Golden Retriever", true, 9, "Swimming");
        detailedDog.demonstrateInheritance();

        Dog copyDog = new Dog(detailedDog);
        copyDog.demonstrateInheritance();

        System.out.println("\n--- instanceof Checks ---");
        System.out.println("basicDog instanceof Dog: " + (basicDog instanceof Dog));
        System.out.println("basicDog instanceof Mammal: " + (basicDog instanceof Mammal));
        System.out.println("basicDog instanceof Animal: " + (basicDog instanceof Animal));
    }
}
