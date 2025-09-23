// Base class
class Bird {
    protected String species;

    public Bird() {
        this.species = "Unknown";
        System.out.println("Bird default constructor called");
    }

    public Bird(String species) {
        this.species = species;
        System.out.println("Bird parameterized constructor called");
    }

    public void fly() {
        System.out.println(species + " is flying in a generic way.");
    }

    public void showSpecies() {
        System.out.println("Species: " + species);
    }
}

// Child class - Penguin
class Penguin extends Bird {
    public Penguin() {
        super("Penguin");
        System.out.println("Penguin constructor called");
    }

    @Override
    public void fly() {
        System.out.println(species + " cannot fly! It swims instead.");
    }
}

// Child class - Eagle
class Eagle extends Bird {
    public Eagle() {
        super("Eagle");
        System.out.println("Eagle constructor called");
    }

    @Override
    public void fly() {
        System.out.println(species + " soars high in the sky.");
    }
}

// Test class
public class BirdTest {
    public static void main(String[] args) {
        Bird genericBird = new Bird("Generic Bird");
        Penguin penguin = new Penguin();
        Eagle eagle = new Eagle();

        System.out.println("\n--- Testing individual fly() methods ---");
        genericBird.fly();
        penguin.fly();
        eagle.fly();

        System.out.println("\n--- Testing polymorphism ---");
        Bird[] birds = {genericBird, penguin, eagle};
        for (Bird b : birds) {
            b.fly(); // calls overridden version depending on object type
        }
    }
}
