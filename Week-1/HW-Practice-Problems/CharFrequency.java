import java.util.Scanner;
public class CharFrequency {
    static String[][] charFrequency(String s) {
        int[] freq = new int[256];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
        }
        String[][] result = new String[s.length()][2];
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (freq[c] != 0) {
                result[index][0] = String.valueOf(c);
                result[index][1] = String.valueOf(freq[c]);
                freq[c] = 0;
                index++;
            }
        }
        String[][] finalResult = new String[index][2];
        System.arraycopy(result, 0, finalResult, 0, index);
        return finalResult;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = sc.nextLine();
        String[][] freq = charFrequency(s);
        for (String[] row : freq) {
            System.out.println(row[0] + " : " + row[1]);
        }
    }
}