abstract class Assignment {
    private String title;
    private int maxMarks;
    private int dueDay;

    public Assignment(String title, int maxMarks, int dueDay) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDay = dueDay;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public int getDueDay() {
        return dueDay;
    }

    public abstract double getDailyPenaltyRate();
}

class CodingAssignment extends Assignment {
    public CodingAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    @Override
    public double getDailyPenaltyRate() {
        return 0.10;
    }
}

class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    @Override
    public double getDailyPenaltyRate() {
        return 0.20;
    }
}

class PortalStudent {
    private String id;
    private String name;

    public PortalStudent(String id, String name) {
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

enum SubmissionStatus {
    SUBMITTED,
    GRADED
}

class Submission {
    private PortalStudent student;
    private Assignment assignment;
    private int submissionDay;
    private SubmissionStatus status;
    private int finalMarks;

    public Submission(PortalStudent student, Assignment assignment, int submissionDay) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDay = submissionDay;
        this.status = SubmissionStatus.SUBMITTED;
        int lateDays = Math.max(0, submissionDay - assignment.getDueDay());
        if (lateDays == 0) {
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (on time). Status: Submitted.");
        } else {
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (" + lateDays + " days late). Status: Submitted.");
        }
    }

    public PortalStudent getStudent() {
        return student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public boolean grade(int rawMarks) {
        if (status == SubmissionStatus.GRADED) {
            return false;
        }
        int lateDays = Math.max(0, submissionDay - assignment.getDueDay());
        double penaltyRate = lateDays * assignment.getDailyPenaltyRate();
        int penaltyPercent = (int) Math.round(penaltyRate * 100);
        this.finalMarks = (int) Math.round(rawMarks * (1.0 - penaltyRate));
        this.status = SubmissionStatus.GRADED;

        if (penaltyPercent > 0) {
            System.out.println(student.getName() + " graded: " + finalMarks + "/" + assignment.getMaxMarks() + " after " + penaltyPercent + "% late penalty. Status: Graded.");
        } else {
            System.out.println(student.getName() + " graded: " + finalMarks + "/" + assignment.getMaxMarks() + ". Status: Graded.");
        }
        return true;
    }

    public boolean canResubmit() {
        if (status == SubmissionStatus.GRADED) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
            return false;
        }
        return true;
    }
}

public class TheAssignmentSubmissionPortal {
    public static void main(String[] args) {
        Assignment coding = new CodingAssignment("Linked List Lab", 50, 10);
        Assignment written = new WrittenAssignment("Design Essay", 50, 12);

        PortalStudent asha = new PortalStudent("S1", "Asha");
        PortalStudent ravi = new PortalStudent("S2", "Ravi");

        Submission subAsha = new Submission(asha, coding, 10);
        Submission subRavi = new Submission(ravi, written, 14);

        subAsha.grade(45);
        subRavi.grade(40);

        subAsha.canResubmit();
    }
}
