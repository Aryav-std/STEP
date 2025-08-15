import java.util.*;

public class StringManipulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();

        sentence = sentence.trim();
        System.out.println("Trimmed: " + sentence);

        sentence = sentence.replace(" ", "_");
        System.out.println("Spaces replaced with underscores: " + sentence);

        sentence = sentence.replaceAll("\\d", "");
        System.out.println("Digits removed: " + sentence);

        String[] words = sentence.split("_");
        System.out.println("Words array: " + Arrays.toString(words));

        String joined = String.join(" | ", words);
        System.out.println("Joined with ' | ': " + joined);

        System.out.println("No punctuation: " + removePunctuation(joined));
        System.out.println("Capitalized: " + capitalizeWords(joined));
        System.out.println("Reversed word order: " + reverseWordOrder(joined));

        System.out.println("Word frequency:");
        countWordFrequency(joined);

        scanner.close();
    }

    public static String removePunctuation(String text) {
        return text.replaceAll("\\p{Punct}", "");
    }

    public static String capitalizeWords(String text) {
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                sb.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1).toLowerCase())
                  .append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static String reverseWordOrder(String text) {
        String[] words = text.split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    public static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
