import java.util.Scanner;

public class FileOrganizer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter file names (comma separated): ");
        String input = scanner.nextLine();
        String[] files = input.split(",");
        
        System.out.println("Original\tExtension\tCategory\tNew Name");
        System.out.println("--------\t---------\t--------\t--------");
        
        int docCount = 0, imgCount = 0, codeCount = 0, otherCount = 0;
        
        for (String file : files) {
            String trimmed = file.trim();
            String name = extractFileName(trimmed);
            String extension = extractExtension(trimmed);
            String category = categorizeFile(extension);
            String newName = generateNewName(name, extension, category);
            
            System.out.println(trimmed + "\t" + extension + "\t" + category + "\t" + newName);
            
            switch (category) {
                case "Document": docCount++; break;
                case "Image": imgCount++; break;
                case "Code": codeCount++; break;
                default: otherCount++; break;
            }
        }
        
        System.out.println("\nCategory\tCount");
        System.out.println("--------\t-----");
        System.out.println("Document\t" + docCount);
        System.out.println("Image\t\t" + imgCount);
        System.out.println("Code\t\t" + codeCount);
        System.out.println("Other\t\t" + otherCount);
    }
    
    public static String extractFileName(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1) return filename;
        return filename.substring(0, dotIndex);
    }
    
    public static String extractExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1) return "";
        return filename.substring(dotIndex + 1);
    }
    
    public static String categorizeFile(String extension) {
        if (extension.equalsIgnoreCase("txt") || extension.equalsIgnoreCase("doc") || 
            extension.equalsIgnoreCase("pdf") || extension.equalsIgnoreCase("xls")) {
            return "Document";
        } else if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png") || 
                  extension.equalsIgnoreCase("gif") || extension.equalsIgnoreCase("bmp")) {
            return "Image";
        } else if (extension.equalsIgnoreCase("java") || extension.equalsIgnoreCase("py") || 
                  extension.equalsIgnoreCase("cpp") || extension.equalsIgnoreCase("js")) {
            return "Code";
        }
        return "Other";
    }
    
    public static String generateNewName(String name, String extension, String category) {
        String date = "2024";
        return category.toLowerCase() + "_" + name + "_" + date + "." + extension;
    }
}