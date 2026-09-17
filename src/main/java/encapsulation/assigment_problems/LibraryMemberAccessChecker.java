package encapsulation.assigment_problems;

public class LibraryMemberAccessChecker {
    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;

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
        return "DENIED";
    }

    public static String summarizeByModifier(String[][] attempts) {
        String[] modifiers = {"private", "default", "protected", "public"};
        int[] allowed = new int[4];
        int[] denied = new int[4];

        for (String[] attempt : attempts) {
            String mod = attempt[0];
            String ctx = attempt[1];
            String res = classifyAccess(mod, ctx);

            for (int i = 0; i < modifiers.length; i++) {
                if (modifiers[i].equals(mod)) {
                    if ("ALLOWED".equals(res)) {
                        allowed[i]++;
                    } else {
                        denied[i]++;
                    }
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < modifiers.length; i++) {
            if (i > 0) {
                sb.append(" | ");
            }
            sb.append(modifiers[i]).append(": ").append(allowed[i]).append(" allowed / ").append(denied[i]).append(" denied");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] batch = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeByModifier(batch));
    }
}
