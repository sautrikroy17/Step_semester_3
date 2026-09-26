abstract class WashType {
    private String name;
    private int durationMinutes;
    private double charge;

    public WashType(String name, int durationMinutes, double charge) {
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.charge = charge;
    }

    public String getName() {
        return name;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public double getCharge() {
        return charge;
    }
}

class QuickWash extends WashType {
    public QuickWash() {
        super("Quick wash", 30, 20.00);
    }
}

class NormalWash extends WashType {
    public NormalWash() {
        super("Normal wash", 45, 30.00);
    }
}

class HeavyWash extends WashType {
    public HeavyWash() {
        super("Heavy wash", 60, 45.00);
    }
}

class HostelStudent {
    private String id;
    private String name;

    public HostelStudent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class WashingMachine {
    private String machineId;
    private boolean busy;
    private WashCycle currentCycle;

    public WashingMachine(String machineId) {
        this.machineId = machineId;
        this.busy = false;
        this.currentCycle = null;
    }

    public String getMachineId() {
        return machineId;
    }

    public boolean isBusy() {
        return busy;
    }

    public boolean startWash(HostelStudent student, WashType washType) {
        if (busy) {
            System.out.println("Machine " + machineId + " is currently busy.");
            return false;
        }
        this.busy = true;
        this.currentCycle = new WashCycle(student, this, washType);
        System.out.printf("%s started on %s for %s (%d min). Charge: ₹%.2f.%n",
                washType.getName(), machineId, student.getName(), washType.getDurationMinutes(), washType.getCharge());
        return true;
    }

    public void completeCycle() {
        if (!busy) {
            System.out.println("Machine " + machineId + " is not currently running a cycle.");
            return;
        }
        this.busy = false;
        this.currentCycle = null;
        System.out.println(machineId + " cycle completed. " + machineId + " is now free.");
    }
}

class WashCycle {
    private HostelStudent student;
    private WashingMachine machine;
    private WashType washType;

    public WashCycle(HostelStudent student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public HostelStudent getStudent() {
        return student;
    }

    public WashingMachine getMachine() {
        return machine;
    }

    public WashType getWashType() {
        return washType;
    }
}

public class TheHostelLaundryQueue {
    public static void main(String[] args) {
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        HostelStudent asha = new HostelStudent("S1", "Asha");
        HostelStudent ravi = new HostelStudent("S2", "Ravi");
        HostelStudent neha = new HostelStudent("S3", "Neha");

        WashType quick = new QuickWash();
        WashType normal = new NormalWash();
        WashType heavy = new HeavyWash();

        m1.startWash(asha, quick);
        m1.startWash(ravi, heavy);
        m2.startWash(ravi, heavy);
        m1.completeCycle();
        m1.startWash(neha, normal);
    }
}
