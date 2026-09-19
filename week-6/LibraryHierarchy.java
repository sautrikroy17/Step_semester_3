class BaseLibraryMember {
    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;

    public BaseLibraryMember(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void setBooksBorrowed(int count) {
        this.booksBorrowed = count;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String displayInfo() {
        return "General Member | Books Borrowed: " + booksBorrowed;
    }
}

class BaseStudentMember extends BaseLibraryMember {
    private String course;

    public BaseStudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String displayInfo() {
        return "Student Member | Course: " + course + " | Books Borrowed: " + getBooksBorrowed();
    }
}

class HonorsStudentMember extends BaseStudentMember {
    private int bonusLimit;

    public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    public int getBonusLimit() {
        return bonusLimit;
    }

    @Override
    public String displayInfo() {
        return "Honors Student Member | Course: " + getCourse() + " | Bonus Limit: " + bonusLimit + " | Books Borrowed: " + getBooksBorrowed();
    }
}

class FacultyMember extends BaseLibraryMember {
    private String department;

    public FacultyMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String displayInfo() {
        return "Faculty Member | Department: " + department + " | Books Borrowed: " + getBooksBorrowed();
    }
}

public class LibraryHierarchy {
    public static String classifyGeneration(BaseLibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        }
        if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        }
        if (member instanceof BaseStudentMember) {
            return "Single inheritance descendant";
        }
        return "Base class";
    }

    public static int getTotalBooksBorrowed(BaseLibraryMember[] members) {
        int total = 0;
        for (BaseLibraryMember m : members) {
            if (m != null) {
                total += m.getBooksBorrowed();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(new BaseLibraryMember("STU1", 3).displayInfo());
        System.out.println(new BaseStudentMember("STU2", 3, "CSE").displayInfo());
        System.out.println(new HonorsStudentMember("STU3", 3, "ECE", 2).displayInfo());
        System.out.println(new FacultyMember("STU4", 5, "Physics").displayInfo());

        HonorsStudentMember honors = new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember faculty = new FacultyMember("STU4", 5, "Physics");
        System.out.println(classifyGeneration(honors));
        System.out.println(classifyGeneration(faculty));

        BaseStudentMember student = new BaseStudentMember("STU2", 3, "CSE");
        student.setBooksBorrowed(2);
        honors.setBooksBorrowed(1);
        faculty.setBooksBorrowed(3);

        BaseLibraryMember[] group = { student, honors, faculty };
        System.out.println(getTotalBooksBorrowed(group));
    }
}
