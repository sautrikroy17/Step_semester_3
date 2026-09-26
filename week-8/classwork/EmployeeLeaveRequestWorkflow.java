enum RequestStatus {
    PENDING,
    APPROVED,
    REJECTED
}

abstract class Employee {
    private String id;
    private String name;

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract String getEmployeeType();
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String id, String name) {
        super(id, name);
    }

    @Override
    public String getEmployeeType() {
        return "FullTimeEmployee";
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String id, String name) {
        super(id, name);
    }

    @Override
    public String getEmployeeType() {
        return "PartTimeEmployee";
    }
}

class Contractor extends Employee {
    public Contractor(String id, String name) {
        super(id, name);
    }

    @Override
    public String getEmployeeType() {
        return "Contractor";
    }
}

class LeaveRequest {
    private Employee employee;
    private String dateRange;
    private int days;
    private RequestStatus status;

    public LeaveRequest(Employee employee, String dateRange, int days) {
        this.employee = employee;
        this.dateRange = dateRange;
        this.days = days;
        this.status = RequestStatus.PENDING;
        System.out.println("Leave request submitted for " + employee.getName() + " (" + dateRange + "). Status: Pending.");
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getDateRange() {
        return dateRange;
    }

    public int getDays() {
        return days;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public boolean setStatus(RequestStatus newStatus) {
        if (this.status != RequestStatus.PENDING && newStatus == RequestStatus.PENDING) {
            String currentStr = this.status == RequestStatus.APPROVED ? "Approved" : "Rejected";
            System.out.println("Cannot change leave request status from " + currentStr + " to Pending.");
            return false;
        }
        this.status = newStatus;
        return true;
    }
}

class Manager {
    private String name;

    public Manager(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void approveRequest(LeaveRequest request) {
        if (request.setStatus(RequestStatus.APPROVED)) {
            System.out.println(request.getEmployee().getName() + "'s leave request (" + request.getDateRange() + ") approved. Status: Approved.");
        }
    }

    public void rejectRequest(LeaveRequest request) {
        if (request.setStatus(RequestStatus.REJECTED)) {
            System.out.println(request.getEmployee().getName() + "'s leave request (" + request.getDateRange() + ") rejected. Status: Rejected.");
        }
    }
}

public class EmployeeLeaveRequestWorkflow {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("E1", "John");
        Employee jane = new PartTimeEmployee("E2", "Jane");

        Manager alice = new Manager("Alice");
        Manager bob = new Manager("Bob");

        LeaveRequest reqJohn = new LeaveRequest(john, "Jan 1-5", 5);
        alice.approveRequest(reqJohn);

        LeaveRequest reqJane = new LeaveRequest(jane, "Feb 10-11", 2);
        bob.rejectRequest(reqJane);

        reqJohn.setStatus(RequestStatus.PENDING);
    }
}
