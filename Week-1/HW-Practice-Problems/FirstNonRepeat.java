 import java.util.Scanner;
 public class FirstNonRepeat {
    static char firstNonRepeating(String s) {
        int[] freq = new int[256];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i)] == 1) return s.charAt(i);
        }
        return '\0';
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = sc.nextLine();
        char ch = firstNonRepeating(s);
        if (ch != '\0')
            System.out.println("First non-repeating character: " + ch);
        else
            System.out.println("No unique character found.");
    }
 }