package inheritance.class_problems;

import java.util.Arrays;

class FineLibraryMember {
    private String memberId;
    private int[] fineHistory;
    private int fineCount;
    private int totalFine;

    public FineLibraryMember(String memberId) {
        this.memberId = memberId;
        this.fineHistory = new int[10];
        this.fineCount = 0;
        this.totalFine = 0;
    }

    protected void chargeFine(int amount) {
        if (fineCount < fineHistory.length) {
            fineHistory[fineCount++] = amount;
        }
        totalFine += amount;
    }

    public int[] getFineHistory() {
        return Arrays.copyOf(fineHistory, fineCount);
    }

    public int getTotalFine() {
        return totalFine;
    }
}

class FineStudentMember extends FineLibraryMember {
    private String course;

    public FineStudentMember(String memberId, String course) {
        super(memberId);
        this.course = course;
    }

    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }
}

public class StudentFineLedger {
    public static void main(String[] args) {
        FineStudentMember s = new FineStudentMember("STU5", "CSE");
        s.chargeFine(100);
        System.out.println(s.getTotalFine());

        int[] history = s.getFineHistory();
        history[0] = 999;
        System.out.println(Arrays.toString(s.getFineHistory()));
    }
}
