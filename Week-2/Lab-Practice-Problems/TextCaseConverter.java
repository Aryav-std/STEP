import java.util.Scanner;

public class TextCaseConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter text: ");
        String text = scanner.nextLine();
        
        String asciiUpper = toUpperCaseASCII(text);
        String asciiLower = toLowerCaseASCII(text);
        String asciiTitle = toTitleCaseASCII(text);
        
        String builtInUpper = text.toUpperCase();
        String builtInLower = text.toLowerCase();
        
        System.out.println("Original: " + text);
        System.out.println("ASCII Upper: " + asciiUpper);
        System.out.println("Built-in Upper: " + builtInUpper);
        System.out.println("ASCII Lower: " + asciiLower);
        System.out.println("Built-in Lower: " + builtInLower);
        System.out.println("Title Case: " + asciiTitle);
    }
    
    public static String toUpperCaseASCII(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'a' && c <= 'z') {
                result.append((char)(c - 32));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
    
    public static String toLowerCaseASCII(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                result.append((char)(c + 32));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
    
    public static String toTitleCaseASCII(String text) {
        StringBuilder result = new StringBuilder();
        boolean newWord = true;
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            
            if (newWord && c >= 'a' && c <= 'z') {
                result.append((char)(c - 32));
                newWord = false;
            } else if (newWord && c >= 'A' && c <= 'Z') {
                result.append(c);
                newWord = false;
            } else if (!newWord && c >= 'A' && c <= 'Z') {
                result.append((char)(c + 32));
            } else {
                result.append(c);
            }
            
            if (c == ' ') {
                newWord = true;
            }
        }
        return result.toString();
    }
}