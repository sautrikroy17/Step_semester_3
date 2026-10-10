import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class StaffMember {
    private final String name;
    private final String type;

    public StaffMember(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public abstract long calculatePay();

    public String getPayslip() {
        return "Payslip[name=" + name + ", type=" + type + ", pay=" + calculatePay() + "]";
    }
}

class FullTimeStaff extends StaffMember {
    private final long salary;

    public FullTimeStaff(String name, long salary) {
        super(name, "FullTime");
        this.salary = salary;
    }

    @Override
    public long calculatePay() {
        return salary;
    }
}

class PartTimeStaff extends StaffMember {
    private final long hours;
    private final long rate;

    public PartTimeStaff(String name, long hours, long rate) {
        super(name, "PartTime");
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    public long calculatePay() {
        return hours * rate;
    }
}

class InternStaff extends StaffMember {
    private final long stipend;

    public InternStaff(String name, long stipend) {
        super(name, "Intern");
        this.stipend = stipend;
    }

    @Override
    public long calculatePay() {
        return stipend;
    }
}

public class PayrollRegisterSystem {

    public static StaffMember parseStaff(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        String[] parts = line.trim().split("\\s+");
        String type = parts[0];
        String name = parts[1];

        if (type.equalsIgnoreCase("FullTime")) {
            long salary = Long.parseLong(parts[2]);
            return new FullTimeStaff(name, salary);
        } else if (type.equalsIgnoreCase("PartTime")) {
            long hours = Long.parseLong(parts[2]);
            long rate = Long.parseLong(parts[3]);
            return new PartTimeStaff(name, hours, rate);
        } else if (type.equalsIgnoreCase("Intern")) {
            long stipend = Long.parseLong(parts[2]);
            return new InternStaff(name, stipend);
        }
        return null;
    }

    public static void generatePayrollReport(List<StaffMember> staffList) {
        if (staffList.isEmpty()) {
            return;
        }
        long totalPay = 0;
        long maxPay = Long.MIN_VALUE;
        String topEarner = "";

        for (StaffMember staff : staffList) {
            long pay = staff.calculatePay();
            System.out.println(staff.getPayslip());
            totalPay += pay;
            if (pay > maxPay) {
                maxPay = pay;
                topEarner = staff.getName();
            }
        }

        System.out.println("Total " + totalPay);
        System.out.println("top earner " + topEarner);
    }

    public static void main(String[] args) {
        List<StaffMember> staffList = new ArrayList<>();

        try {
            if (System.in.available() > 0) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    StaffMember staff = parseStaff(scanner.nextLine());
                    if (staff != null) {
                        staffList.add(staff);
                    }
                }
                scanner.close();
                generatePayrollReport(staffList);
                return;
            }
        } catch (Exception ignored) {
        }

        String[] sample = {
            "FullTime Asha 50000",
            "PartTime Ravi 80 300",
            "Intern Neha 15000"
        };
        for (String line : sample) {
            StaffMember staff = parseStaff(line);
            if (staff != null) {
                staffList.add(staff);
            }
        }
        generatePayrollReport(staffList);
    }
}
