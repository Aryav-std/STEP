import java.util.Scanner;

public class SpellChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] dictionary = {"hello", "world", "java", "programming", "computer", "science", "algorithm", "data"};
        
        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();
        
        String[] words = splitSentence(sentence);
        
        System.out.println("Word\t\tSuggestion\tDistance\tStatus");
        System.out.println("----\t\t----------\t--------\t------");
        
        for (String word : words) {
            if (word.isEmpty()) continue;
            String suggestion = findClosestMatch(word, dictionary);
            int distance = calculateDistance(word, suggestion);
            String status = distance == 0 ? "Correct" : "Misspelled";
            System.out.println(word + "\t\t" + suggestion + "\t\t" + distance + "\t\t" + status);
        }
    }
    
    public static String[] splitSentence(String sentence) {
        int wordCount = 0;
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) == ' ' || sentence.charAt(i) == '.' || sentence.charAt(i) == ',') {
                wordCount++;
            }
        }
        wordCount++;
        
        String[] words = new String[wordCount];
        int start = 0;
        int index = 0;
        
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (c == ' ' || c == '.' || c == ',') {
                if (start < i) {
                    words[index++] = sentence.substring(start, i).toLowerCase();
                }
                start = i + 1;
            }
        }
        if (start < sentence.length()) {
            words[index] = sentence.substring(start).toLowerCase();
        }
        
        return words;
    }
    
    public static int calculateDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int diff = Math.abs(len1 - len2);
        
        int minLen = Math.min(len1, len2);
        for (int i = 0; i < minLen; i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diff++;
            }
        }
        
        return diff;
    }
    
    public static String findClosestMatch(String word, String[] dictionary) {
        int minDistance = Integer.MAX_VALUE;
        String closestMatch = word;
        
        for (String dictWord : dictionary) {
            int distance = calculateDistance(word, dictWord);
            if (distance < minDistance && distance <= 2) {
                minDistance = distance;
                closestMatch = dictWord;
            }
        }
        
        return closestMatch;
    }
}