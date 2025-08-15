public class StringPerformanceComparison {
    public static void main(String[] args) {
        System.out.println("=== PERFORMANCE COMPARISON ===");

        // String
        long startTime = System.nanoTime();
        concatenateWithString(10000);
        long endTime = System.nanoTime();
        System.out.println("String: " + (endTime - startTime) + " ns");

        // StringBuilder
        startTime = System.nanoTime();
        concatenateWithStringBuilder(10000);
        endTime = System.nanoTime();
        System.out.println("StringBuilder: " + (endTime - startTime) + " ns");

        // StringBuffer
        startTime = System.nanoTime();
        concatenateWithStringBuffer(10000);
        endTime = System.nanoTime();
        System.out.println("StringBuffer: " + (endTime - startTime) + " ns");

        demonstrateStringBuilderMethods();
        compareStringComparisonMethods();
    }

    public static String concatenateWithString(int iterations) {
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += "Java " + i + " ";
        }
        return result;
    }

    public static String concatenateWithStringBuilder(int iterations) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java ").append(i).append(" ");
        }
        return sb.toString();
    }

    public static String concatenateWithStringBuffer(int iterations) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java ").append(i).append(" ");
        }
        return sb.toString();
    }

    public static void demonstrateStringBuilderMethods() {
        StringBuilder sb = new StringBuilder("Hello World");
        sb.append("!");
        sb.insert(5, " Java");
        sb.delete(0, 6);
        sb.deleteCharAt(0);
        sb.reverse();
        sb.replace(0, 5, "Hey");
        sb.setCharAt(0, 'h');
        System.out.println("StringBuilder final: " + sb);
        System.out.println("Capacity: " + sb.capacity());
        sb.ensureCapacity(50);
        System.out.println("New Capacity: " + sb.capacity());
        sb.trimToSize();
        System.out.println("Trimmed Capacity: " + sb.capacity());
    }

    public static void compareStringComparisonMethods() {
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");

        System.out.println("== : " + (str1 == str2)); // true
        System.out.println("== (new String): " + (str1 == str3)); // false
        System.out.println("equals: " + str1.equals(str3));
        System.out.println("equalsIgnoreCase: " + str1.equalsIgnoreCase("hello"));
        System.out.println("compareTo: " + str1.compareTo(str3));
        System.out.println("compareToIgnoreCase: " + str1.compareToIgnoreCase("hello"));
    }
}
