import java.util.Scanner;
import java.util.ArrayList;

public class TextFormatter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter text to format: ");
        String text = scanner.nextLine();
        
        System.out.print("Enter line width: ");
        int width = scanner.nextInt();
        
        ArrayList<String> words = splitText(text);
        
        long startTime = System.nanoTime();
        String justifiedText = justifyText(words, width);
        long endTime = System.nanoTime();
        long builderTime = endTime - startTime;
        
        startTime = System.nanoTime();
        String concatText = justifyTextConcat(words, width);
        endTime = System.nanoTime();
        long concatTime = endTime - startTime;
        
        String centeredText = centerText(text, width);
        
        displayResults(text, justifiedText, centeredText, builderTime, concatTime);
    }
    
    public static ArrayList<String> splitText(String text) {
        ArrayList<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (start < i) {
                    words.add(text.substring(start, i));
                }
                start = i + 1;
            }
        }
        if (start < text.length()) {
            words.add(text.substring(start));
        }
        return words;
    }
    
    public static String justifyText(ArrayList<String> words, int width) {
        StringBuilder result = new StringBuilder();
        StringBuilder currentLine = new StringBuilder();
        
        for (String word : words) {
            if (currentLine.length() + word.length() + 1 > width) {
                if (currentLine.length() > 0) {
                    result.append(currentLine).append("\n");
                    currentLine = new StringBuilder();
                }
            }
            
            if (currentLine.length() > 0) {
                currentLine.append(" ");
            }
            currentLine.append(word);
        }
        
        if (currentLine.length() > 0) {
            result.append(currentLine);
        }
        
        return result.toString();
    }
    
    public static String justifyTextConcat(ArrayList<String> words, int width) {
        String result = "";
        String currentLine = "";
        
        for (String word : words) {
            if (currentLine.length() + word.length() + 1 > width) {
                if (currentLine.length() > 0) {
                    result += currentLine + "\n";
                    currentLine = "";
                }
            }
            
            if (currentLine.length() > 0) {
                currentLine += " ";
            }
            currentLine += word;
        }
        
        if (currentLine.length() > 0) {
            result += currentLine;
        }
        
        return result;
    }
    
    public static String centerText(String text, int width) {
        StringBuilder result = new StringBuilder();
        String[] lines = text.split("\n");
        
        for (String line : lines) {
            int padding = (width - line.length()) / 2;
            if (padding > 0) {
                for (int i = 0; i < padding; i++) {
                    result.append(" ");
                }
            }
            result.append(line).append("\n");
        }
        
        return result.toString();
    }
    
    public static void displayResults(String original, String justified, String centered, long builderTime, long concatTime) {
        System.out.println("Original text:\n" + original);
        System.out.println("\nJustified text:");
        String[] justifiedLines = justified.split("\n");
        for (int i = 0; i < justifiedLines.length; i++) {
            System.out.println((i + 1) + ". " + justifiedLines[i] + " (" + justifiedLines[i].length() + " chars)");
        }
        
        System.out.println("\nCentered text:\n" + centered);
        System.out.println("\nPerformance comparison:");
        System.out.println("StringBuilder time: " + builderTime + " ns");
        System.out.println("String concat time: " + concatTime + " ns");
        System.out.println("Difference: " + (concatTime - builderTime) + " ns");
    }
}