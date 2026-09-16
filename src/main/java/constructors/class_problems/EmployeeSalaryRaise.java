package constructors.class_problems;

public class EmployeeSalaryRaise {
    private String empId;
    private double salary;

    public EmployeeSalaryRaise(String empId, double salary) {
        this.empId = empId;
        this.salary = salary;
    }

    public void raiseSalary(double salary) {
        this.salary += salary;
    }

    public void printFinalSalary() {
        System.out.printf("%s | Final Salary: Rs %.1f%n", empId, salary);
    }

    public static void main(String[] args) {
        String[] empIds = {"E-101", "E-102", "E-103", "E-104"};
        double[] startingSalaries = {40000.0, 55000.0, 62000.0, 48000.0};

        EmployeeSalaryRaise[] employees = new EmployeeSalaryRaise[empIds.length];
        for (int i = 0; i < empIds.length; i++) {
            employees[i] = new EmployeeSalaryRaise(empIds[i], startingSalaries[i]);
            employees[i].raiseSalary(5000.0);
            employees[i].printFinalSalary();
        }
    }
}
