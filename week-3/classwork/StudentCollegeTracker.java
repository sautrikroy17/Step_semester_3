public class StudentCollegeTracker {
    private String name;
    private double attendance;
    private static String collegeName = "SRM Institute of Science and Technology";
    private static int studentCount = 0;

    public StudentCollegeTracker(String name, double attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++;
    }

    public static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }

    public String getName() {
        return name;
    }

    public double getAttendance() {
        return attendance;
    }

    public static void main(String[] args) {
        StudentCollegeTracker student1 = new StudentCollegeTracker("Rohan", 88.5);
        StudentCollegeTracker student2 = new StudentCollegeTracker("Sneha", 92.0);

        StudentCollegeTracker.printCollegeInfo();
    }
}
