import java.util.Scanner;

public class TextCompressor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter text to compress: ");
        String text = scanner.nextLine();
        
        char[] chars = new char[256];
        int[] freqs = new int[256];
        int uniqueCount = countFrequency(text, chars, freqs);
        
        char[][] mapping = createMapping(chars, freqs, uniqueCount);
        
        String compressed = compressText(text, mapping);
        String decompressed = decompressText(compressed, mapping);
        
        double ratio = (double) text.length() / compressed.length();
        double efficiency = (1 - (double) compressed.length() / text.length()) * 100;
        
        System.out.println("Character Frequency Table:");
        for (int i = 0; i < uniqueCount; i++) {
            System.out.println("'" + chars[i] + "': " + freqs[i] + " times");
        }
        
        System.out.println("\nCompression Mapping:");
        for (int i = 0; i < mapping.length && mapping[i][0] != 0; i++) {
            System.out.println("'" + mapping[i][0] + "' -> '" + mapping[i][1] + "'");
        }
        
        System.out.println("\nOriginal text: " + text);
        System.out.println("Compressed text: " + compressed);
        System.out.println("Decompressed text: " + decompressed);
        System.out.println("Compression ratio: " + String.format("%.2f", ratio));
        System.out.println("Efficiency: " + String.format("%.2f", efficiency) + "%");
        System.out.println("Decompression valid: " + text.equals(decompressed));
    }
    
    public static int countFrequency(String text, char[] chars, int[] freqs) {
        int[] tempFreqs = new int[256];
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            tempFreqs[c]++;
        }
        
        int count = 0;
        for (int i = 0; i < 256; i++) {
            if (tempFreqs[i] > 0) {
                chars[count] = (char) i;
                freqs[count] = tempFreqs[i];
                count++;
            }
        }
        return count;
    }
    
    public static char[][] createMapping(char[] chars, int[] freqs, int count) {
        char[][] mapping = new char[256][2];
        
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                if (freqs[j] > freqs[i]) {
                    int tempFreq = freqs[i];
                    freqs[i] = freqs[j];
                    freqs[j] = tempFreq;
                    
                    char tempChar = chars[i];
                    chars[i] = chars[j];
                    chars[j] = tempChar;
                }
            }
        }
        
        String codes = "1234567890!@#$%^&*()";
        
        for (int i = 0; i < count && i < codes.length(); i++) {
            mapping[i][0] = chars[i];
            mapping[i][1] = codes.charAt(i);
        }
        
        return mapping;
    }
    
    public static String compressText(String text, char[][] mapping) {
        StringBuilder compressed = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            boolean found = false;
            for (int j = 0; j < mapping.length && mapping[j][0] != 0; j++) {
                if (mapping[j][0] == c) {
                    compressed.append(mapping[j][1]);
                    found = true;
                    break;
                }
            }
            if (!found) {
                compressed.append(c);
            }
        }
        return compressed.toString();
    }
    
    public static String decompressText(String compressed, char[][] mapping) {
        StringBuilder decompressed = new StringBuilder();
        for (int i = 0; i < compressed.length(); i++) {
            char c = compressed.charAt(i);
            boolean found = false;
            for (int j = 0; j < mapping.length && mapping[j][1] != 0; j++) {
                if (mapping[j][1] == c) {
                    decompressed.append(mapping[j][0]);
                    found = true;
                    break;
                }
            }
            if (!found) {
                decompressed.append(c);
            }
        }
        return decompressed.toString();
    }
}