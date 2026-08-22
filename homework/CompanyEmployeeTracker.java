public class CompanyEmployeeTracker {
    private String empName;
    private double salary;
    private static String companyName = "Bright Horizon Technologies";
    private static int employeeCount = 0;

    public CompanyEmployeeTracker(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }

    public String getEmpName() {
        return empName;
    }

    public double getSalary() {
        return salary;
    }

    public static void main(String[] args) {
        CompanyEmployeeTracker emp1 = new CompanyEmployeeTracker("Kavya", 55000.0);
        CompanyEmployeeTracker emp2 = new CompanyEmployeeTracker("Manoj", 60000.0);
        CompanyEmployeeTracker emp3 = new CompanyEmployeeTracker("Deepak", 52000.0);

        CompanyEmployeeTracker.printCompanyInfo();
    }
}
