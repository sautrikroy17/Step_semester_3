public class ReferenceDeskAccessChecker {
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if ("SAME_CLASS".equals(accessorContext)) {
            return "ALLOWED";
        }
        if ("SAME_PACKAGE".equals(accessorContext)) {
            return "private".equals(fieldModifier) ? "DENIED" : "ALLOWED";
        }
        if ("DIFFERENT_PACKAGE".equals(accessorContext)) {
            return "public".equals(fieldModifier) ? "ALLOWED" : "DENIED";
        }
        if ("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext)) {
            return ("protected".equals(fieldModifier) || "public".equals(fieldModifier)) ? "ALLOWED" : "DENIED";
        }
        if ("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE".equals(accessorContext)) {
            return "public".equals(fieldModifier) ? "ALLOWED" : "DENIED";
        }
        return "DENIED";
    }

    public static String firstDeniedAttempt(String[][] attempts) {
        for (int i = 0; i < attempts.length; i++) {
            String mod = attempts[i][0];
            String ctx = attempts[i][1];
            String result = classifyAccess(mod, ctx);
            if ("DENIED".equals(result)) {
                return String.format("%s via %s (attempt #%d)", mod, ctx, i + 1);
            }
        }
        return "None Denied";
    }

    public static void main(String[] args) {
        String[][] attempts1 = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(firstDeniedAttempt(attempts1));

        String[][] attempts2 = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(firstDeniedAttempt(attempts2));
    }
}
