public class StringManipulation {
    public static void main(String[] args) {
        // 1. String literal
        String str1 = "Java Programming";

        // 2. Using new String() constructor
        String str2 = new String("Java Programming");

        // 3. Using character array
        char[] chars = {'J', 'a', 'v', 'a', ' ', 'P', 'r', 'o', 'g', 'r', 'a', 'm', 'm', 'i', 'n', 'g'};
        String str3 = new String(chars);

        // Compare strings using == and .equals()
        System.out.println("str1 == str2: " + (str1 == str2)); // false because they are different objects
        System.out.println("str1.equals(str2): " + str1.equals(str2)); // true because values are the same

        System.out.println("str1 == str3: " + (str1 == str3)); // false
        System.out.println("str1.equals(str3): " + str1.equals(str3)); // true

        // Escape sequences example
        String quote = "Programming Quote:\n\"Code is poetry\" - Unknown\nPath: C:\\Java\\Projects";
        System.out.println("\n" + quote);
    }
}

