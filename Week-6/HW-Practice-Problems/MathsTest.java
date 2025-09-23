// Parent class
class BasicMath {
    // Addition
    public int calculate(int a, int b) {
        return a + b;
    }

    // Subtraction
    public int calculate(int a, int b, boolean subtract) {
        if (subtract) return a - b;
        return a + b;
    }

    // Multiplication
    public int calculate(int a, int b, int c) {
        return a * b * c;
    }
}

// Child class
class AdvancedMath extends BasicMath {
    // Division
    public double calculate(double a, double b) {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero");
        return a / b;
    }

    // Power
    public double calculate(double a, int power) {
        return Math.pow(a, power);
    }

    // Modulus
    public int calculateMod(int a, int b) {
        return a % b;
    }
}

// Main class to test
public class MathTest {
    public static void main(String[] args) {
        BasicMath bm = new BasicMath();
        AdvancedMath am = new AdvancedMath();

        System.out.println("--- BasicMath Tests ---");
        System.out.println("Add 5 + 3: " + bm.calculate(5, 3));
        System.out.println("Subtract 5 - 3: " + bm.calculate(5, 3, true));
        System.out.println("Multiply 2*3*4: " + bm.calculate(2, 3, 4));

        System.out.println("\n--- AdvancedMath Tests ---");
        System.out.println("Divide 10/2: " + am.calculate(10.0, 2.0));
        System.out.println("2^3: " + am.calculate(2.0, 3));
        System.out.println("5 % 3: " + am.calculateMod(5, 3));

        // Inherited methods
        System.out.println("Inherited Add 7+8: " + am.calculate(7, 8));
    }
}
