import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter text to encrypt: ");
        String text = scanner.nextLine();
        
        System.out.print("Enter shift value: ");
        int shift = scanner.nextInt();
        
        String encrypted = encrypt(text, shift);
        String decrypted = decrypt(encrypted, shift);
        boolean isValid = validateDecryption(text, decrypted);
        
        displayASCII("Original", text);
        displayASCII("Encrypted", encrypted);
        displayASCII("Decrypted", decrypted);
        System.out.println("Decryption valid: " + isValid);
    }
    
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                result.append((char)(((c - 'A' + shift) % 26 + 26) % 26 + 'A'));
            } else if (c >= 'a' && c <= 'z') {
                result.append((char)(((c - 'a' + shift) % 26 + 26) % 26 + 'a'));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
    
    public static String decrypt(String text, int shift) {
        return encrypt(text, -shift);
    }
    
    public static boolean validateDecryption(String original, String decrypted) {
        return original.equals(decrypted);
    }
    
    public static void displayASCII(String label, String text) {
        System.out.println(label + " text: " + text);
        System.out.print("ASCII values: ");
        for (int i = 0; i < text.length(); i++) {
            System.out.print((int)text.charAt(i) + " ");
        }
        System.out.println();
    }
}