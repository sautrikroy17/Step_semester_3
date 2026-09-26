import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface NotificationChannel {
    String getChannelName();
    void send(NoticeStudent student, Notice notice);
}

class EmailChannel implements NotificationChannel {
    @Override
    public String getChannelName() {
        return "Email";
    }

    @Override
    public void send(NoticeStudent student, Notice notice) {
        System.out.println("[Email → " + student.getName() + "] " + notice.getTitle());
    }
}

class SmsChannel implements NotificationChannel {
    @Override
    public String getChannelName() {
        return "SMS";
    }

    @Override
    public void send(NoticeStudent student, Notice notice) {
        System.out.println("[SMS → " + student.getName() + "] " + notice.getTitle());
    }
}

class AppChannel implements NotificationChannel {
    @Override
    public String getChannelName() {
        return "App";
    }

    @Override
    public void send(NoticeStudent student, Notice notice) {
        System.out.println("[App → " + student.getName() + "] " + notice.getTitle());
    }
}

class NoticeStudent {
    private String id;
    private String name;
    private String department;
    private List<NotificationChannel> preferredChannels;

    public NoticeStudent(String id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.preferredChannels = new ArrayList<>();
    }

    public void addPreferredChannel(NotificationChannel channel) {
        preferredChannels.add(channel);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public List<NotificationChannel> getPreferredChannels() {
        return preferredChannels;
    }
}

class Notice {
    private String title;
    private List<String> targetDepartments;

    public Notice(String title, List<String> targetDepartments) {
        this.title = title;
        this.targetDepartments = targetDepartments != null ? targetDepartments : new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTargetDepartments() {
        return targetDepartments;
    }

    public boolean isValid() {
        return title != null && !title.trim().isEmpty() && !targetDepartments.isEmpty();
    }
}

class NoticeBoard {
    private List<NoticeStudent> students;

    public NoticeBoard() {
        this.students = new ArrayList<>();
    }

    public void registerStudent(NoticeStudent student) {
        students.add(student);
    }

    public boolean postNotice(Notice notice) {
        if (!notice.isValid()) {
            if (notice.getTargetDepartments().isEmpty()) {
                System.out.println("Cannot post notice: At least one target department is required.");
            } else {
                System.out.println("Cannot post notice: Title is required.");
            }
            return false;
        }

        String deptList = String.join(", ", notice.getTargetDepartments());
        System.out.println("Notice '" + notice.getTitle() + "' posted to " + deptList + ".");

        for (NoticeStudent student : students) {
            if (notice.getTargetDepartments().contains(student.getDepartment())) {
                for (NotificationChannel channel : student.getPreferredChannels()) {
                    channel.send(student, notice);
                }
            }
        }
        return true;
    }
}

public class TheCampusNoticeBroadcaster {
    public static void main(String[] args) {
        NotificationChannel email = new EmailChannel();
        NotificationChannel sms = new SmsChannel();
        NotificationChannel app = new AppChannel();

        NoticeStudent asha = new NoticeStudent("S1", "Asha", "CSE");
        asha.addPreferredChannel(email);
        asha.addPreferredChannel(app);

        NoticeStudent ravi = new NoticeStudent("S2", "Ravi", "ECE");
        ravi.addPreferredChannel(sms);

        NoticeBoard noticeBoard = new NoticeBoard();
        noticeBoard.registerStudent(asha);
        noticeBoard.registerStudent(ravi);

        Notice notice1 = new Notice("Lab Closed Tomorrow", Arrays.asList("CSE"));
        noticeBoard.postNotice(notice1);

        Notice notice2 = new Notice("Fee Deadline Extended", Arrays.asList("CSE", "ECE"));
        noticeBoard.postNotice(notice2);

        Notice notice3 = new Notice("Sports Day", new ArrayList<>());
        noticeBoard.postNotice(notice3);
    }
}
