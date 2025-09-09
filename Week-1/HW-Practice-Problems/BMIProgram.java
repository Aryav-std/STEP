import java.util.Scanner;
public class BMIProgram {
    // Method to calculate BMI and status
    static String[][] calculateBMI(double[][] hw) {
        String[][] result = new String[hw.length][4];
        for (int i = 0; i < hw.length; i++) {
            double heightMeters = hw[i][1] / 100.0;
            double bmi = hw[i][0] / (heightMeters * heightMeters);
            String status;
            if (bmi < 18.5) status = "Underweight";
            else if (bmi < 25) status = "Normal";
            else if (bmi < 30) status = "Overweight";
            else status = "Obese";
            result[i][0] = String.format("%.2f", hw[i][0]);
            result[i][1] = String.format("%.2f", hw[i][1]);
            result[i][2] = String.format("%.2f", bmi);
            result[i][3] = status;
        }
        return result;
    }
    // Method to display result
    static void display(String[][] arr) {
        System.out.printf("%-10s %-10s %-10s %-15s%n", "Weight", "Height", "BMI", "Status");
        for (String[] row : arr) {
            System.out.printf("%-10s %-10s %-10s %-15s%n", row[0], row[1], row[2], row[3]);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] hw = new double[10][2];
        for (int i = 0; i < 10; i++) {
            System.out.print("Enter weight (kg) for person " + (i+1) + ": ");
            hw[i][0] = sc.nextDouble();
            System.out.print("Enter height (cm) for person " + (i+1) + ": ");
            hw[i][1] = sc.nextDouble();
        }
        String[][] result = calculateBMI(hw);
        display(result);
    }
}