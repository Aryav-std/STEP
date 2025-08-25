import java.util.Scanner;

public class TextCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter mathematical expression: ");
        String expression = scanner.nextLine();
        
        if (validateExpression(expression)) {
            double result = evaluateExpression(expression);
            System.out.println("Result: " + result);
        } else {
            System.out.println("Invalid expression");
        }
    }
    
    public static boolean validateExpression(String expression) {
        int parenCount = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (!((c >= '0' && c <= '9') || c == '+' || c == '-' || c == '*' || c == '/' || c == ' ' || c == '(' || c == ')')) {
                return false;
            }
            if (c == '(') parenCount++;
            if (c == ')') parenCount--;
        }
        return parenCount == 0;
    }
    
    public static double evaluateExpression(String expression) {
        expression = expression.replaceAll(" ", "");
        
        while (expression.contains("(")) {
            int start = expression.lastIndexOf("(");
            int end = expression.indexOf(")", start);
            String subExpr = expression.substring(start + 1, end);
            double subResult = evaluateSimpleExpression(subExpr);
            expression = expression.substring(0, start) + subResult + expression.substring(end + 1);
        }
        
        return evaluateSimpleExpression(expression);
    }
    
    public static double evaluateSimpleExpression(String expression) {
        StringBuilder currentNumber = new StringBuilder();
        double result = 0;
        char lastOperator = '+';
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if (c >= '0' && c <= '9' || c == '.') {
                currentNumber.append(c);
            }
            
            if (c == '+' || c == '-' || c == '*' || c == '/' || i == expression.length() - 1) {
                if (currentNumber.length() > 0) {
                    double number = Double.parseDouble(currentNumber.toString());
                    
                    switch (lastOperator) {
                        case '+': result += number; break;
                        case '-': result -= number; break;
                        case '*': result *= number; break;
                        case '/': result /= number; break;
                    }
                    
                    currentNumber = new StringBuilder();
                }
                lastOperator = c;
            }
        }
        
        return result;
    }
}