import java.util.*;

public class ASCIIProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        for (char ch : input.toCharArray()) {
            int ascii = (int) ch;
            System.out.println(ch + " -> " + ascii + " (" + classifyCharacter(ch) + ")");
            if (Character.isLetter(ch)) {
                char toggled = toggleCase(ch);
                System.out.println("Toggled case: " + toggled + " -> " + (int) toggled);
                System.out.println("Case ASCII diff: " + Math.abs(ch - toggled));
            }
        }

        System.out.println("Caesar Cipher (+3): " + caesarCipher(input, 3));
        displayASCIITable(65, 90); // A-Z example

        int[] asciiArray = stringToASCII(input);
        System.out.println("ASCII Array: " + Arrays.toString(asciiArray));
        System.out.println("Back to String: " + asciiToString(asciiArray));

        scanner.close();
    }

    public static String classifyCharacter(char ch) {
        if (Character.isUpperCase(ch)) return "Uppercase Letter";
        if (Character.isLowerCase(ch)) return "Lowercase Letter";
        if (Character.isDigit(ch)) return "Digit";
        return "Special Character";
    }

    public static char toggleCase(char ch) {
        if (Character.isUpperCase(ch)) return (char) (ch + 32);
        if (Character.isLowerCase(ch)) return (char) (ch - 32);
        return ch;
    }

    public static String caesarCipher(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                sb.append((char) ((ch - base + shift) % 26 + base));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void displayASCIITable(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(i + " -> " + (char) i);
        }
    }

    public static int[] stringToASCII(String text) {
        int[] ascii = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            ascii[i] = text.charAt(i);
        }
        return ascii;
    }

    public static String asciiToString(int[] asciiValues) {
        StringBuilder sb = new StringBuilder();
        for (int val : asciiValues) {
            sb.append((char) val);
        }
        return sb.toString();
    }
}
