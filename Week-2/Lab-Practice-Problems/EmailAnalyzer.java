import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class EmailAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> emails = new ArrayList<>();
        
        System.out.println("Enter email addresses (type 'done' to finish):");
        while (true) {
            String email = scanner.nextLine();
            if (email.equalsIgnoreCase("done")) break;
            emails.add(email);
        }
        
        ArrayList<EmailData> emailDataList = new ArrayList<>();
        int validCount = 0;
        int invalidCount = 0;
        HashMap<String, Integer> domainCount = new HashMap<>();
        int totalUsernameLength = 0;
        
        for (String email : emails) {
            boolean isValid = validateEmail(email);
            EmailData data = extractEmailComponents(email, isValid);
            emailDataList.add(data);
            
            if (isValid) {
                validCount++;
                totalUsernameLength += data.username.length();
                domainCount.put(data.domain, domainCount.getOrDefault(data.domain, 0) + 1);
            } else {
                invalidCount++;
            }
        }
        
        String mostCommonDomain = "";
        int maxCount = 0;
        for (String domain : domainCount.keySet()) {
            if (domainCount.get(domain) > maxCount) {
                maxCount = domainCount.get(domain);
                mostCommonDomain = domain;
            }
        }
        
        double avgUsernameLength = validCount > 0 ? (double)totalUsernameLength / validCount : 0;
        
        displayResults(emailDataList, validCount, invalidCount, mostCommonDomain, avgUsernameLength);
    }
    
    public static boolean validateEmail(String email) {
        int atIndex = email.indexOf('@');
        int lastAtIndex = email.lastIndexOf('@');
        
        if (atIndex == -1 || atIndex != lastAtIndex) return false;
        if (atIndex == 0 || atIndex == email.length() - 1) return false;
        
        int dotIndex = email.indexOf('.', atIndex);
        if (dotIndex == -1 || dotIndex == atIndex + 1 || dotIndex == email.length() - 1) return false;
        
        return true;
    }
    
    public static EmailData extractEmailComponents(String email, boolean isValid) {
        EmailData data = new EmailData();
        data.email = email;
        data.isValid = isValid;
        
        if (isValid) {
            int atIndex = email.indexOf('@');
            data.username = email.substring(0, atIndex);
            data.domain = email.substring(atIndex + 1);
            
            int dotIndex = data.domain.indexOf('.');
            if (dotIndex != -1) {
                data.domainName = data.domain.substring(0, dotIndex);
                data.extension = data.domain.substring(dotIndex + 1);
            } else {
                data.domainName = data.domain;
                data.extension = "";
            }
        }
        
        return data;
    }
    
    public static void displayResults(ArrayList<EmailData> dataList, int validCount, int invalidCount, 
                                    String mostCommonDomain, double avgUsernameLength) {
        System.out.println("Email\t\tUsername\tDomain\t\tDomain Name\tExtension\tValid");
        for (EmailData data : dataList) {
            System.out.println(data.email + "\t" + data.username + "\t" + data.domain + "\t" + 
                             data.domainName + "\t" + data.extension + "\t" + data.isValid);
        }
        
        System.out.println("\nStatistics:");
        System.out.println("Valid emails: " + validCount);
        System.out.println("Invalid emails: " + invalidCount);
        System.out.println("Most common domain: " + mostCommonDomain);
        System.out.println("Average username length: " + avgUsernameLength);
    }
    
    static class EmailData {
        String email;
        String username;
        String domain;
        String domainName;
        String extension;
        boolean isValid;
    }
}