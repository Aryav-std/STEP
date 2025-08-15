public class StringBuiltInMethods {
    public static void main(String[] args) {
        String sampleText = " Java Programming is Fun and Challenging! ";

        // 1. Original length
        System.out.println("Original length (with spaces): " + sampleText.length());

        // 2. Trim and new length
        String trimmed = sampleText.trim();
        System.out.println("Trimmed length: " + trimmed.length());

        // 3. Char at index 5
        System.out.println("Character at index 5: " + sampleText.charAt(5));

        // 4. Extract "Programming"
        String sub = trimmed.substring(5, 16);
        System.out.println("Substring 'Programming': " + sub);

        // 5. Index of "Fun"
        System.out.println("Index of 'Fun': " + trimmed.indexOf("Fun"));

        // 6. Contains "Java"
        System.out.println("Contains 'Java': " + trimmed.contains("Java"));

        // 7. Starts with "Java" after trimming
        System.out.println("Starts with 'Java': " + trimmed.startsWith("Java"));

        // 8. Ends with "!"
        System.out.println("Ends with '!': " + trimmed.endsWith("!"));

        // 9. Uppercase
        System.out.println("Uppercase: " + trimmed.toUpperCase());

        // 10. Lowercase
        System.out.println("Lowercase: " + trimmed.toLowerCase());

        // Count vowels
        System.out.println("Vowel count: " + countVowels(trimmed));

        // Find all occurrences of 'a'
        System.out.print("Occurrences of 'a': ");
        findAllOccurrences(trimmed, 'a');
    }

    public static int countVowels(String text) {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (int i = 0; i < text.length(); i++) {
            if (vowels.indexOf(text.charAt(i)) != -1) {
                count++;
            }
        }
        return count;
    }

    public static void findAllOccurrences(String text, char target) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
