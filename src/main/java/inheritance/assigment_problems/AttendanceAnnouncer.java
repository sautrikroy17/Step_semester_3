package inheritance.assigment_problems;

class AnnounceGymMember {
    private String memberId;
    private int sessionsAttended;

    public AnnounceGymMember(String memberId, int sessionsAttended) {
        this.memberId = memberId;
        this.sessionsAttended = sessionsAttended;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public String displayInfo() {
        return "Standard | Sessions: " + sessionsAttended;
    }
}

class AnnouncePremiumMember extends AnnounceGymMember {
    private String trainerName;

    public AnnouncePremiumMember(String memberId, int sessionsAttended, String trainerName) {
        super(memberId, sessionsAttended);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
    }
}

public class AttendanceAnnouncer {
    public static String batchPrint(AnnounceGymMember[] members) {
        StringBuilder sb = new StringBuilder();
        for (AnnounceGymMember m : members) {
            sb.append(m.displayInfo());
            if (m instanceof AnnouncePremiumMember) {
                AnnouncePremiumMember p = (AnnouncePremiumMember) m;
                sb.append(" [Trainer via downcast: ").append(p.getTrainerName()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AnnounceGymMember[] members = {
            new AnnounceGymMember("MEM6", 0),
            new AnnouncePremiumMember("MEM7", 0, "Coach Riya")
        };

        System.out.println(batchPrint(members));
    }
}
