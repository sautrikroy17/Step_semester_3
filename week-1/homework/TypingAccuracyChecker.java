public class TypingAccuracyChecker {
    public static void checkTypingAccuracy(String original, String typed) {
        if (original == null || typed == null) {
            System.out.println("Invalid input strings.");
            return;
        }

        int minLen = Math.min(original.length(), typed.length());
        int totalLen = Math.max(original.length(), typed.length());

        if (totalLen == 0) {
            System.out.println("Matched: 0/0 | Accuracy: 100.00% | No Mismatches");
            return;
        }

        int matches = 0;
        int firstMismatchPos = -1;
        char origChar = ' ';
        char typedChar = ' ';

        for (int i = 0; i < minLen; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matches++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1;
                origChar = original.charAt(i);
                typedChar = typed.charAt(i);
            }
        }

        if (firstMismatchPos == -1 && original.length() != typed.length()) {
            firstMismatchPos = minLen + 1;
            origChar = original.length() > minLen ? original.charAt(minLen) : ' ';
            typedChar = typed.length() > minLen ? typed.charAt(minLen) : ' ';
        }

        double accuracy = ((double) matches / totalLen) * 100.0;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Matched: %d/%d | Accuracy: %.2f%%", matches, totalLen, accuracy));

        if (firstMismatchPos != -1) {
            sb.append(String.format(" | First Mismatch at position %d ('%c' vs '%c')",
                    firstMismatchPos, origChar, typedChar));
        } else {
            sb.append(" | No Mismatches");
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}
