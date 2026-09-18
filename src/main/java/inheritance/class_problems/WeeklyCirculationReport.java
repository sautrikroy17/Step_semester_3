package inheritance.class_problems;

class ReportLibraryMember {
    private String memberId;
    private int booksBorrowed;

    public ReportLibraryMember(String memberId, int booksBorrowed) {
        this.memberId = memberId;
        this.booksBorrowed = booksBorrowed;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String displayInfo() {
        return "General | Books: " + booksBorrowed;
    }
}

class ReportStudentMember extends ReportLibraryMember {
    private String course;

    public ReportStudentMember(String memberId, int booksBorrowed, String course) {
        super(memberId, booksBorrowed);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String displayInfo() {
        return "Student | Course: " + course + " | Books: " + getBooksBorrowed();
    }
}

public class WeeklyCirculationReport {
    public static String batchPrint(ReportLibraryMember[] members) {
        StringBuilder sb = new StringBuilder();
        for (ReportLibraryMember m : members) {
            sb.append(m.displayInfo());
            if (m instanceof ReportStudentMember) {
                ReportStudentMember s = (ReportStudentMember) m;
                sb.append(" [Course via downcast: ").append(s.getCourse()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ReportLibraryMember[] members = {
            new ReportLibraryMember("LB5", 0),
            new ReportStudentMember("STU6", 0, "ECE")
        };

        System.out.println(batchPrint(members));
    }
}
