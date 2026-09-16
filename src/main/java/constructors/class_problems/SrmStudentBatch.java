package constructors.class_problems;

public class SrmStudentBatch {
    private static String collegeName;
    private static String academicYear;
    private String studentName;

    static {
        collegeName = "SRM Institute of Science and Technology";
        academicYear = "2026-2027";
        System.out.println("College info loaded");
    }

    public SrmStudentBatch(String studentName) {
        this.studentName = studentName;
        System.out.println("Student record created: " + studentName);
    }

    public String getStudentName() {
        return studentName;
    }

    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};

        for (String name : names) {
            new SrmStudentBatch(name);
        }
    }
}
