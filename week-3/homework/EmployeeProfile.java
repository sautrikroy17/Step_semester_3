public class EmployeeProfile {
    private String empId;
    private String empName;
    private double salary;
    private boolean isIntern;

    public EmployeeProfile(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.isIntern = false;
    }

    public EmployeeProfile(String empId, String empName) {
        this(empId, empName, 0.0);
        this.isIntern = true;
    }

    public void printProfile() {
        System.out.printf("%s | %s | Rs %.1f | Intern: %b%n", empId, empName, salary, isIntern);
    }

    public static void main(String[] args) {
        EmployeeProfile permanentEmp = new EmployeeProfile("E-101", "Divya", 65000.0);
        EmployeeProfile internEmp = new EmployeeProfile("E-102", "Arjun");

        permanentEmp.printProfile();
        internEmp.printProfile();
    }
}
