import java.util.Scanner;
import java.util.Random;

public class PasswordAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.print("Enter passwords to analyze (comma separated): ");
        String input = scanner.nextLine();
        String[] passwords = input.split(",");
        
        System.out.println("Password\tLength\tUpper\tLower\tDigits\tSpecial\tScore\tStrength");
        System.out.println("--------\t------\t-----\t-----\t------\t-------\t-----\t--------");
        
        for (String password : passwords) {
            String trimmed = password.trim();
            int upper = countUppercase(trimmed);
            int lower = countLowercase(trimmed);
            int digits = countDigits(trimmed);
            int special = countSpecial(trimmed);
            int score = calculateScore(trimmed, upper, lower, digits, special);
            String strength = getStrength(score);
            
            System.out.println(trimmed + "\t" + trimmed.length() + "\t" + upper + "\t" + 
                             lower + "\t" + digits + "\t" + special + "\t" + score + "\t" + strength);
        }
        
        System.out.print("Enter desired password length: ");
        int length = scanner.nextInt();
        
        for (int i = 0; i < 3; i++) {
            String newPassword = generatePassword(length, random);
            System.out.println("Generated password " + (i+1) + ": " + newPassword);
        }
    }
    
    public static int countUppercase(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (c >= 65 && c <= 90) count++;
        }
        return count;
    }
    
    public static int countLowercase(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (c >= 97 && c <= 122) count++;
        }
        return count;
    }
    
    public static int countDigits(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (c >= 48 && c <= 57) count++;
        }
        return count;
    }
    
    public static int countSpecial(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if ((c >= 33 && c <= 47) || (c >= 58 && c <= 64) || 
                (c >= 91 && c <= 96) || (c >= 123 && c <= 126)) {
                count++;
            }
        }
        return count;
    }
    
    public static int calculateScore(String password, int upper, int lower, int digits, int special) {
        int score = 0;
        score += Math.max(0, (password.length() - 8) * 2);
        if (upper > 0) score += 10;
        if (lower > 0) score += 10;
        if (digits > 0) score += 10;
        if (special > 0) score += 10;
        
        if (password.contains("123") || password.contains("abc") || password.contains("qwerty")) {
            score -= 20;
        }
        
        return Math.max(0, score);
    }
    
    public static String getStrength(int score) {
        if (score <= 20) return "Weak";
        if (score <= 50) return "Medium";
        return "Strong";
    }
    
    public static String generatePassword(int length, Random random) {
        StringBuilder password = new StringBuilder();
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specials = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        
        password.append(uppercase.charAt(random.nextInt(uppercase.length())));
        password.append(lowercase.charAt(random.nextInt(lowercase.length())));
        password.append(numbers.charAt(random.nextInt(numbers.length())));
        password.append(specials.charAt(random.nextInt(specials.length())));
        
        String allChars = uppercase + lowercase + numbers + specials;
        for (int i = 4; i < length; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }
        
        return shuffleString(password.toString(), random);
    }
    
    public static String shuffleString(String input, Random random) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }
}