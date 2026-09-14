package string_methods.class_problems;

public class MaskedPhoneNumberFormatter {
    public static String maskPhoneNumber(String phone) {
        if (phone == null) {
            return "Invalid phone number";
        }

        String cleaned = phone.trim();
        if (cleaned.length() != 10) {
            return "Invalid phone number";
        }

        for (int i = 0; i < cleaned.length(); i++) {
            if (!Character.isDigit(cleaned.charAt(i))) {
                return "Invalid phone number";
            }
        }

        StringBuilder sb = new StringBuilder("XXXXXX");
        sb.append("-");
        sb.append(cleaned.substring(6));

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(maskPhoneNumber("9876543210"));
        System.out.println(maskPhoneNumber("98765"));
        System.out.println(maskPhoneNumber("98765abcde"));
    }
}
