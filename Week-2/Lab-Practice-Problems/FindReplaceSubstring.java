import java.util.Scanner;
import java.util.ArrayList;

public class FindReplaceSubstring {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter main text: ");
        String mainText = scanner.nextLine();
        
        System.out.print("Enter substring to find: ");
        String findText = scanner.nextLine();
        
        System.out.print("Enter replacement text: ");
        String replaceText = scanner.nextLine();
        
        ArrayList<Integer> positions = findOccurrences(mainText, findText);
        String manualResult = manualReplace(mainText, findText, replaceText, positions);
        String builtInResult = mainText.replace(findText, replaceText);
        boolean isSame = compareResults(manualResult, builtInResult);
        
        System.out.println("Manual replace result: " + manualResult);
        System.out.println("Built-in replace result: " + builtInResult);
        System.out.println("Results match: " + isSame);
    }
    
    public static ArrayList<Integer> findOccurrences(String text, String substring) {
        ArrayList<Integer> positions = new ArrayList<>();
        int index = 0;
        while (index != -1) {
            index = text.indexOf(substring, index);
            if (index != -1) {
                positions.add(index);
                index += substring.length();
            }
        }
        return positions;
    }
    
    public static String manualReplace(String text, String find, String replace, ArrayList<Integer> positions) {
        if (positions.isEmpty()) return text;
        
        StringBuilder result = new StringBuilder();
        int currentIndex = 0;
        
        for (int pos : positions) {
            result.append(text.substring(currentIndex, pos));
            result.append(replace);
            currentIndex = pos + find.length();
        }
        result.append(text.substring(currentIndex));
        
        return result.toString();
    }
    
    public static boolean compareResults(String manual, String builtIn) {
        return manual.equals(builtIn);
    }
}