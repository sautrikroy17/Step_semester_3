import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

class StudentMember {
    private final String rollNumber;
    private final String name;

    public StudentMember(String rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentMember that = (StudentMember) o;
        return Objects.equals(rollNumber, that.rollNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNumber);
    }
}

public class StudentClubRegistrationManagement {
    private static final Set<StudentMember> registered = new HashSet<>();
    private static boolean countPrinted = false;

    public static void processCommand(String line) {
        if (line == null || line.trim().isEmpty()) {
            return;
        }
        String[] parts = line.trim().split("\\s+");
        String command = parts[0];

        if (command.equalsIgnoreCase("ADD")) {
            String roll = parts[1];
            String name = parts.length > 2 ? parts[2] : "";
            StudentMember member = new StudentMember(roll, name);
            if (registered.add(member)) {
                System.out.println("Added");
            } else {
                System.out.println("duplicate rejected");
            }
        } else if (command.equalsIgnoreCase("CONTAINS")) {
            if (!countPrinted) {
                System.out.println("member count " + registered.size());
                countPrinted = true;
            }
            String roll = parts[1];
            String name = parts.length > 2 ? parts[2] : "";
            StudentMember probe = new StudentMember(roll, name);
            System.out.println("contains: " + registered.contains(probe));
        }
    }

    public static void finishProcessing() {
        if (!countPrinted) {
            System.out.println("member count " + registered.size());
            countPrinted = true;
        }
    }

    public static void main(String[] args) {
        try {
            if (System.in.available() > 0) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    processCommand(line);
                }
                finishProcessing();
                scanner.close();
                return;
            }
        } catch (Exception ignored) {
        }

        String[] sample = {
            "ADD 21CS01 Asha",
            "ADD 21CS01 Asha",
            "ADD 21CS02 Ravi",
            "CONTAINS 21CS02 Ravi",
            "CONTAINS 21CS03 Priya"
        };
        for (String line : sample) {
            processCommand(line);
        }
        finishProcessing();
    }
}
