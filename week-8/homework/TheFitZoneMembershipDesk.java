abstract class MembershipPlan {
    private String planName;
    private int durationMonths;

    public MembershipPlan(String planName, int durationMonths) {
        this.planName = planName;
        this.durationMonths = durationMonths;
    }

    public String getPlanName() {
        return planName;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public abstract double calculateFee(double monthlyBaseRate);
}

class MonthlyPlan extends MembershipPlan {
    public MonthlyPlan() {
        super("Monthly", 1);
    }

    @Override
    public double calculateFee(double monthlyBaseRate) {
        return monthlyBaseRate * getDurationMonths();
    }
}

class QuarterlyPlan extends MembershipPlan {
    public QuarterlyPlan() {
        super("Quarterly", 3);
    }

    @Override
    public double calculateFee(double monthlyBaseRate) {
        return monthlyBaseRate * getDurationMonths() * 0.90;
    }
}

class AnnualPlan extends MembershipPlan {
    public AnnualPlan() {
        super("Annual", 12);
    }

    @Override
    public double calculateFee(double monthlyBaseRate) {
        return monthlyBaseRate * getDurationMonths() * 0.75;
    }
}

class Member {
    private String id;
    private String name;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

enum MembershipStatus {
    ACTIVE,
    FROZEN,
    EXPIRED
}

class Membership {
    private static final double BASE_RATE = 1000.00;
    private Member member;
    private MembershipPlan plan;
    private double fee;
    private MembershipStatus status;

    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.fee = plan.calculateFee(BASE_RATE);
        this.status = MembershipStatus.ACTIVE;
        System.out.printf("%s membership created for %s. Fee: ₹%,.2f. Status: Active.%n",
                plan.getPlanName(), member.getName(), fee);
    }

    public Member getMember() {
        return member;
    }

    public MembershipPlan getPlan() {
        return plan;
    }

    public double getFee() {
        return fee;
    }

    public MembershipStatus getStatus() {
        return status;
    }

    public boolean checkIn() {
        if (status == MembershipStatus.ACTIVE) {
            System.out.println(member.getName() + " checked in successfully.");
            return true;
        } else {
            String statusStr = status == MembershipStatus.FROZEN ? "Frozen" : "Expired";
            System.out.println("Check-in denied: " + member.getName() + "'s membership is " + statusStr + ".");
            return false;
        }
    }

    public boolean freeze() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot freeze an Expired membership.");
            return false;
        }
        if (status == MembershipStatus.FROZEN) {
            return false;
        }
        this.status = MembershipStatus.FROZEN;
        System.out.println(member.getName() + "'s membership frozen. Status: Frozen.");
        return true;
    }

    public boolean unfreeze() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot unfreeze an Expired membership.");
            return false;
        }
        this.status = MembershipStatus.ACTIVE;
        System.out.println(member.getName() + "'s membership unfrozen. Status: Active.");
        return true;
    }

    public void expire() {
        this.status = MembershipStatus.EXPIRED;
        System.out.println(member.getName() + "'s membership expired. Status: Expired.");
    }
}

public class TheFitZoneMembershipDesk {
    public static void main(String[] args) {
        Member asha = new Member("M1", "Asha");
        Member ravi = new Member("M2", "Ravi");

        MembershipPlan quarterly = new QuarterlyPlan();
        MembershipPlan monthly = new MonthlyPlan();

        Membership ashaMem = new Membership(asha, quarterly);
        Membership raviMem = new Membership(ravi, monthly);

        ashaMem.checkIn();
        ashaMem.freeze();
        ashaMem.checkIn();

        raviMem.expire();
        raviMem.freeze();
    }
}
