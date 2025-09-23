// Base class
class Color {
    protected String name;

    public Color() {
        this.name = "Unknown Color";
        System.out.println("Color default constructor called");
    }

    public Color(String name) {
        this.name = name;
        System.out.println("Color parameterized constructor called: " + name);
    }

    public void showColor() {
        System.out.println("Color: " + name);
    }
}

// Intermediate class
class PrimaryColor extends Color {
    protected int intensity; // 0 - 100 scale

    public PrimaryColor() {
        super();
        this.intensity = 50;
        System.out.println("PrimaryColor default constructor called");
    }

    public PrimaryColor(String name, int intensity) {
        super(name);
        this.intensity = intensity;
        System.out.println("PrimaryColor parameterized constructor called");
    }

    public void showPrimaryColor() {
        showColor();
        System.out.println("Intensity: " + intensity);
    }
}

// Most specific class
class RedColor extends PrimaryColor {
    private String shade;

    public RedColor() {
        super();
        this.shade = "Default Red";
        System.out.println("RedColor default constructor called");
    }

    public RedColor(String name, int intensity, String shade) {
        super(name, intensity);
        this.shade = shade;
        System.out.println("RedColor parameterized constructor called");
    }

    public void showRedColor() {
        showPrimaryColor();
        System.out.println("Shade: " + shade);
    }
}

// Test class
public class ColorTest {
    public static void main(String[] args) {
        System.out.println("--- Default RedColor ---");
        RedColor red1 = new RedColor();
        red1.showRedColor();

        System.out.println("\n--- Parameterized RedColor ---");
        RedColor red2 = new RedColor("Bright Red", 80, "Scarlet");
        red2.showRedColor();
    }
}
