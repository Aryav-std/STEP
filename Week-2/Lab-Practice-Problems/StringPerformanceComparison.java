import java.util.Scanner;

public class StringPerformanceComparison {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of iterations: ");
        int iterations = scanner.nextInt();
        
        PerformanceResult stringResult = testStringConcatenation(iterations);
        PerformanceResult builderResult = testStringBuilder(iterations);
        PerformanceResult bufferResult = testStringBuffer(iterations);
        
        displayResults(stringResult, builderResult, bufferResult);
    }
    
    public static PerformanceResult testStringConcatenation(int iterations) {
        long startTime = System.currentTimeMillis();
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += "test";
        }
        long endTime = System.currentTimeMillis();
        return new PerformanceResult("String", endTime - startTime, result.length());
    }
    
    public static PerformanceResult testStringBuilder(int iterations) {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            builder.append("test");
        }
        long endTime = System.currentTimeMillis();
        return new PerformanceResult("StringBuilder", endTime - startTime, builder.length());
    }
    
    public static PerformanceResult testStringBuffer(int iterations) {
        long startTime = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            buffer.append("test");
        }
        long endTime = System.currentTimeMillis();
        return new PerformanceResult("StringBuffer", endTime - startTime, buffer.length());
    }
    
    public static void displayResults(PerformanceResult string, PerformanceResult builder, PerformanceResult buffer) {
        System.out.println("Method\t\tTime (ms)\tLength");
        System.out.println(string.method + "\t\t" + string.time + "\t\t" + string.length);
        System.out.println(builder.method + "\t" + builder.time + "\t\t" + builder.length);
        System.out.println(buffer.method + "\t" + buffer.time + "\t\t" + buffer.length);
    }
    
    static class PerformanceResult {
        String method;
        long time;
        int length;
        
        PerformanceResult(String method, long time, int length) {
            this.method = method;
            this.time = time;
            this.length = length;
        }
    }
}