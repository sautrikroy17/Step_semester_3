class SettlementGymMember {
    private static int membersEnrolled = 0;
    private static int counter = 2000;
    final String membershipNumber;
    private int monthlyFee;
    private int feesPaid;
    private String lastPaymentMode;

    public SettlementGymMember(int monthlyFee) {
        membersEnrolled++;
        this.membershipNumber = "GYM-" + (++counter);
        this.monthlyFee = monthlyFee;
        this.feesPaid = 0;
    }

    public void payFee(int amount) {
        this.feesPaid += amount;
    }

    public void payFee(int amount, String mode) {
        this.lastPaymentMode = mode;
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }
}

class GroupClassMemberSettlement extends SettlementGymMember {
    private String className;

    public GroupClassMemberSettlement(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}

public class GymCheckInSettlement {
    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'G') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(3));
    }

    public static String processWeeklyCheckIn(SettlementGymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        if (members != null) {
            for (SettlementGymMember m : members) {
                if (m == null) {
                    nullSkipped++;
                    continue;
                }
                processed++;
                if (m instanceof GroupClassMemberSettlement) {
                    group++;
                } else {
                    individual++;
                }
            }
        }
        return String.format("%d processed | %d null skipped | %d group | %d individual", processed, nullSkipped, group, individual);
    }

    public static void main(String[] args) {
        SettlementGymMember m1 = new SettlementGymMember(1000);
        System.out.println(m1.membershipNumber);
        System.out.println(SettlementGymMember.getMembersEnrolled());

        System.out.println(isValidReferralCode("G45B"));
        System.out.println(isValidReferralCode("G4B"));
        System.out.println(isValidReferralCode("X45B"));

        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println(m1.getFeesPaid());

        SettlementGymMember[] batch = {
            new GroupClassMemberSettlement(1500, "Zumba"),
            null,
            new SettlementGymMember(1000)
        };
        System.out.println(processWeeklyCheckIn(batch));
    }
}
