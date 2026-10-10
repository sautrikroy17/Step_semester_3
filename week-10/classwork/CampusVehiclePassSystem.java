import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

interface Chargeable {
    boolean canCharge();
}

abstract class Vehicle {
    private final String passNumber;
    private final String ownerName;
    private final String vehicleType;

    public Vehicle(String passNumber, String ownerName, String vehicleType) {
        this.passNumber = passNumber;
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
    }

    public String getPassNumber() {
        return passNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public abstract int getPassFee();
}

class Bike extends Vehicle {
    public Bike(String passNumber, String ownerName) {
        super(passNumber, ownerName, "Bike");
    }

    @Override
    public int getPassFee() {
        return 300;
    }
}

class Car extends Vehicle {
    public Car(String passNumber, String ownerName) {
        super(passNumber, ownerName, "Car");
    }

    @Override
    public int getPassFee() {
        return 1000;
    }
}

class EBike extends Vehicle implements Chargeable {
    public EBike(String passNumber, String ownerName) {
        super(passNumber, ownerName, "E-Bike");
    }

    public EBike(String passNumber, String ownerName, String rawType) {
        super(passNumber, ownerName, rawType);
    }

    @Override
    public int getPassFee() {
        return 300;
    }

    @Override
    public boolean canCharge() {
        return true;
    }
}

class ECar extends Vehicle implements Chargeable {
    public ECar(String passNumber, String ownerName) {
        super(passNumber, ownerName, "ECar");
    }

    public ECar(String passNumber, String ownerName, String rawType) {
        super(passNumber, ownerName, rawType);
    }

    @Override
    public int getPassFee() {
        return 1000;
    }

    @Override
    public boolean canCharge() {
        return true;
    }
}

public class CampusVehiclePassSystem {

    public static Vehicle createVehicle(String type, String passNumber, String owner) {
        String normalized = type.replace("-", "").toLowerCase();
        if (normalized.equals("bike")) {
            return new Bike(passNumber, owner);
        } else if (normalized.equals("car")) {
            return new Car(passNumber, owner);
        } else if (normalized.equals("ebike")) {
            return new EBike(passNumber, owner, type);
        } else if (normalized.equals("ecar")) {
            return new ECar(passNumber, owner, type);
        }
        return null;
    }

    public static void executeCommand(Map<String, Vehicle> registry, String line) {
        if (line == null || line.trim().isEmpty()) {
            return;
        }
        String[] parts = line.trim().split("\\s+");
        String command = parts[0];

        if (command.equalsIgnoreCase("PASS")) {
            String type = parts[1];
            String passNumber = parts[2];
            String owner = parts[3];
            Vehicle v = createVehicle(type, passNumber, owner);
            if (v != null) {
                registry.put(passNumber, v);
                System.out.println(v.getPassNumber() + " (" + v.getVehicleType() + ") pass fee " + v.getPassFee());
            }
        } else if (command.equalsIgnoreCase("CHARGE")) {
            String passNumber = parts[1];
            Vehicle v = registry.get(passNumber);
            if (v instanceof Chargeable && ((Chargeable) v).canCharge()) {
                System.out.println(passNumber + " charging bay allotted");
            } else {
                System.out.println(passNumber + " rejected: charging unsupported");
            }
        }
    }

    public static void main(String[] args) {
        Map<String, Vehicle> registry = new LinkedHashMap<>();
        try {
            if (System.in.available() > 0) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    executeCommand(registry, line);
                }
                scanner.close();
                return;
            }
        } catch (Exception ignored) {
        }

        String[] sample = {
            "PASS Bike KA01 Asha",
            "PASS ECar KA02 Ravi",
            "CHARGE KA02",
            "CHARGE KA01"
        };
        for (String line : sample) {
            executeCommand(registry, line);
        }
    }
}
