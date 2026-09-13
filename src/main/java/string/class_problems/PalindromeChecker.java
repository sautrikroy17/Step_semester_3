package string.class_problems;

public class PalindromeChecker {
    public static boolean isPalindromeIterative(String text) {
        if (text == null) {
            return false;
        }
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        if (text == null) {
            return false;
        }
        return checkRecursiveHelper(text, 0, text.length() - 1);
    }

    private static boolean checkRecursiveHelper(String text, int left, int right) {
        if (left >= right) {
            return true;
        }
        if (text.charAt(left) != text.charAt(right)) {
            return false;
        }
        return checkRecursiveHelper(text, left + 1, right - 1);
    }

    public static boolean isPalindromeArrayReversal(String text) {
        if (text == null) {
            return false;
        }
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];
        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }
        for (int i = 0; i < original.length; i++) {
            if (original[i] != reversed[i]) {
                return false;
            }
        }
        return true;
    }

    private static String formatResult(boolean result) {
        return result ? "Palindrome" : "Not Palindrome";
    }

    public static void testAndPrint(String input) {
        boolean iterative = isPalindromeIterative(input);
        boolean recursive = isPalindromeRecursive(input);
        boolean arrayReversal = isPalindromeArrayReversal(input);

        System.out.println("Input: \"" + input + "\"");
        System.out.println("Iterative: " + formatResult(iterative) +
                " | Recursive: " + formatResult(recursive) +
                " | Array Reversal: " + formatResult(arrayReversal));
        System.out.println();
    }

    public static void main(String[] args) {
        testAndPrint("madam");
        testAndPrint("hello");
        testAndPrint("racecar");
    }
}
