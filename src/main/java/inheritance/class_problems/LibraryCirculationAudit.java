package inheritance.class_problems;

class AuditLibraryMember {
    private static int membersEnrolled = 0;
    private static int counter = 100;
    final String memberNumber;
    private int borrowLimit;
    private int booksBorrowed;
    private String lastGenre;

    public AuditLibraryMember(int borrowLimit) {
        membersEnrolled++;
        this.memberNumber = "LIB-" + (++counter);
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {
        this.booksBorrowed++;
    }

    public void borrowBook(String genre) {
        this.lastGenre = genre;
        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }
}

class AuditFacultyMember extends AuditLibraryMember {
    private String department;

    public AuditFacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}

public class LibraryCirculationAudit {
    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'R') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(3));
    }

    public static String processNightlyAudit(AuditLibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        if (members != null) {
            for (AuditLibraryMember m : members) {
                if (m == null) {
                    nullSkipped++;
                    continue;
                }
                processed++;
                if (m instanceof AuditFacultyMember) {
                    faculty++;
                } else {
                    regular++;
                }
            }
        }
        return String.format("%d processed | %d null skipped | %d faculty | %d regular", processed, nullSkipped, faculty, regular);
    }

    public static void main(String[] args) {
        AuditLibraryMember m1 = new AuditLibraryMember(3);
        System.out.println(m1.memberNumber);
        System.out.println(AuditLibraryMember.getMembersEnrolled());

        System.out.println(isValidRenewalCode("R12A"));
        System.out.println(isValidRenewalCode("R1A"));
        System.out.println(isValidRenewalCode("X12A"));

        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println(m1.getBooksBorrowed());

        AuditLibraryMember[] batch = {
            new AuditFacultyMember(5, "Physics"),
            null,
            new AuditLibraryMember(3)
        };
        System.out.println(processNightlyAudit(batch));
    }
}
