import java.util.Scanner;
public class UniqueChars {
    static int stringLength(String s) {
        int count = 0;
        try {
            while (true) {
                s.charAt(count);
                count++;
            }
        } catch (Exception e) {}
        return count;
    }
    static char[] uniqueCharacters(String s) {
        int len = stringLength(s);
        char[] unique = new char[len];
        int index = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            boolean isUnique = true;
            for (int j = 0; j < i; j++) {
                if (s.charAt(j) == c) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) unique[index++] = c;
        }
        char[] result = new char[index];
        System.arraycopy(unique, 0, result, 0, index);
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = sc.nextLine();
        char[] unique = uniqueCharacters(s);
        System.out.print("Unique characters: ");
        for (char c : unique) System.out.print(c + " ");
    }
}