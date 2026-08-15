public class BankTransactionRefValidator {
    public static String normalizeReference(String raw) {
        if (raw == null) {
            return "";
        }
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    public static String validateAndFormat(String reference) {
        String normalized = normalizeReference(reference);

        if (normalized.length() != 14) {
            return "Invalid: reference must be exactly 14 characters";
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(normalized.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(normalized.charAt(i))) {
                return "Invalid: reference body must be numeric digits";
            }
        }

        String bankCode = normalized.substring(0, 3);
        String day = normalized.substring(3, 5);
        String month = normalized.substring(5, 7);
        String year = normalized.substring(7, 9);
        String seq = normalized.substring(9);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bankCode).append("] DATE: ");
        sb.append(day).append("/").append(month).append("/").append(year);
        sb.append(" | SEQ: ").append(seq);

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(validateAndFormat(" hdf03022600042 "));
        System.out.println(validateAndFormat("12F03022600042"));
        System.out.println(validateAndFormat("sbi03022600a42"));
    }
}
