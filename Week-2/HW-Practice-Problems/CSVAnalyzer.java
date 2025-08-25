import java.util.Scanner;

public class CSVAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter CSV data (end with empty line):");
        StringBuilder csvData = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) break;
            csvData.append(line).append("\n");
        }
        
        String[][] data = parseCSV(csvData.toString());
        displayData(data);
    }
    
    public static String[][] parseCSV(String csvData) {
        int rows = 0;
        for (int i = 0; i < csvData.length(); i++) {
            if (csvData.charAt(i) == '\n') rows++;
        }
        rows++;
        
        int cols = 0;
        int lineEnd = csvData.indexOf('\n');
        if (lineEnd == -1) lineEnd = csvData.length();
        for (int i = 0; i < lineEnd; i++) {
            if (csvData.charAt(i) == ',') cols++;
        }
        cols++;
        
        String[][] data = new String[rows][cols];
        int row = 0;
        int col = 0;
        int start = 0;
        boolean inQuotes = false;
        
        for (int i = 0; i < csvData.length(); i++) {
            char c = csvData.charAt(i);
            
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                data[row][col] = csvData.substring(start, i).replace("\"", "").trim();
                col++;
                start = i + 1;
            } else if (c == '\n' && !inQuotes) {
                data[row][col] = csvData.substring(start, i).replace("\"", "").trim();
                row++;
                col = 0;
                start = i + 1;
            }
        }
        
        if (start < csvData.length()) {
            data[row][col] = csvData.substring(start).replace("\"", "").trim();
        }
        
        return data;
    }
    
    public static void displayData(String[][] data) {
        int[] colWidths = new int[data[0].length];
        for (int j = 0; j < data[0].length; j++) {
            for (int i = 0; i < data.length; i++) {
                if (data[i][j] != null) {
                    colWidths[j] = Math.max(colWidths[j], data[i][j].length());
                }
            }
            colWidths[j] += 2;
        }
        
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != null) {
                    System.out.printf("%-" + colWidths[j] + "s", data[i][j]);
                }
            }
            System.out.println();
        }
    }
}