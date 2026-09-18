package inheritance.assigment_problems;

import java.util.Arrays;

class FeeGymMember {
    private String memberId;
    private int[] lateFeeHistory;
    private int feeCount;
    private int totalLateFees;

    public FeeGymMember(String memberId) {
        this.memberId = memberId;
        this.lateFeeHistory = new int[10];
        this.feeCount = 0;
        this.totalLateFees = 0;
    }

    protected void chargeLateFee(int amount) {
        if (feeCount < lateFeeHistory.length) {
            lateFeeHistory[feeCount++] = amount;
        }
        totalLateFees += amount;
    }

    public int[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }

    public int getTotalLateFees() {
        return totalLateFees;
    }
}

class FeePremiumMember extends FeeGymMember {
    private String trainerName;

    public FeePremiumMember(String memberId, String trainerName) {
        super(memberId);
        this.trainerName = trainerName;
    }

    @Override
    protected void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }
}

public class GymLateFeeLedger {
    public static void main(String[] args) {
        FeePremiumMember p = new FeePremiumMember("MEM5", "Coach Riya");
        p.chargeLateFee(200);
        System.out.println(p.getTotalLateFees());

        int[] history = p.getLateFeeHistory();
        history[0] = 999;
        System.out.println(Arrays.toString(p.getLateFeeHistory()));
    }
}
