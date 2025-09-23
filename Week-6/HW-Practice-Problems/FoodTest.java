// Abstract parent class
abstract class Food {
    // Template method
    public final void prepare() {
        wash();
        cook();
        serve();
    }

    // Steps to be implemented/overridden by subclasses
    protected abstract void wash();
    protected abstract void cook();
    protected abstract void serve();
}

// Child class: Pizza
class Pizza extends Food {
    @Override
    protected void wash() {
        System.out.println("Washing hands and cleaning ingredients for Pizza.");
    }

    @Override
    protected void cook() {
        System.out.println("Baking the Pizza in the oven for 15 minutes.");
    }

    @Override
    protected void serve() {
        System.out.println("Serving the Pizza hot with extra cheese.");
    }
}

// Child class: Soup
class Soup extends Food {
    @Override
    protected void wash() {
        System.out.println("Washing vegetables and utensils for Soup.");
    }

    @Override
    protected void cook() {
        System.out.println("Boiling the Soup and adding spices.");
    }

    @Override
    protected void serve() {
        System.out.println("Serving the Soup in a bowl with garnish.");
    }
}

// Main class to test
public class FoodTest {
    public static void main(String[] args) {
        Food pizza = new Pizza();
        Food soup = new Soup();

        System.out.println("--- Preparing Pizza ---");
        pizza.prepare();

        System.out.println("\n--- Preparing Soup ---");
        soup.prepare();
    }
}
