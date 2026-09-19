class BaseGymMember {
    private String memberId;
    private int monthlyFee;
    private int sessionsAttended;

    public BaseGymMember(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void setSessionsAttended(int sessions) {
        this.sessionsAttended = sessions;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public String displayInfo() {
        return "Standard Member | Sessions: " + sessionsAttended;
    }
}

class BasePremiumMember extends BaseGymMember {
    private String trainerName;

    public BasePremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium Member | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
    }
}

class EliteMember extends BasePremiumMember {
    private String lockerNumber;

    public EliteMember(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    public String getLockerNumber() {
        return lockerNumber;
    }

    @Override
    public String displayInfo() {
        return "Elite Member | Trainer: " + getTrainerName() + " | Locker: " + lockerNumber + " | Sessions: " + getSessionsAttended();
    }
}

class GroupClassMember extends BaseGymMember {
    private String className;

    public GroupClassMember(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String displayInfo() {
        return "Group Class Member | Class: " + className + " | Sessions: " + getSessionsAttended();
    }
}

public class GymMembershipTiers {
    public static String classifyGeneration(BaseGymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        }
        if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        }
        if (member instanceof BasePremiumMember) {
            return "Single inheritance descendant";
        }
        return "Base class";
    }

    public static int getTotalSessionsAttended(BaseGymMember[] members) {
        int total = 0;
        for (BaseGymMember m : members) {
            if (m != null) {
                total += m.getSessionsAttended();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(new BaseGymMember("MEM1", 1000).displayInfo());
        System.out.println(new BasePremiumMember("MEM2", 2000, "Coach Riya").displayInfo());
        System.out.println(new EliteMember("MEM3", 3000, "Coach Arjun", "L12").displayInfo());
        System.out.println(new GroupClassMember("MEM4", 1500, "Zumba").displayInfo());

        EliteMember elite = new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember group = new GroupClassMember("MEM4", 1500, "Zumba");
        System.out.println(classifyGeneration(elite));
        System.out.println(classifyGeneration(group));

        BasePremiumMember premium = new BasePremiumMember("MEM2", 2000, "Coach Riya");
        premium.setSessionsAttended(3);
        elite.setSessionsAttended(2);
        group.setSessionsAttended(4);

        BaseGymMember[] members = { premium, elite, group };
        System.out.println(getTotalSessionsAttended(members));
    }
}
