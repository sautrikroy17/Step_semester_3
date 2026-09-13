package string.class_problems;

public class FirstNonRepeatingChar {
    public static char findFirstNonRepeatingChar(String text) {
        if (text == null || text.isEmpty()) {
            return '\0';
        }

        int[] frequencies = new int[256];
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 256) {
                frequencies[c]++;
            }
        }

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 256 && frequencies[c] == 1) {
                return c;
            }
        }

        return '\0';
    }

    public static void testAndDisplay(String input) {
        System.out.println("Input: \"" + input + "\"");
        char result = findFirstNonRepeatingChar(input);
        if (result != '\0') {
            System.out.println("First Non-Repeating Character: '" + result + "'");
        } else {
            System.out.println("No Non-Repeating Character Found");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        testAndDisplay("swiss");
        testAndDisplay("aabbcc");
        testAndDisplay("placement");
    }
}
