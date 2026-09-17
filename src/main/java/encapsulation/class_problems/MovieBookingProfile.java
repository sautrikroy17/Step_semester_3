package encapsulation.class_problems;

public class MovieBookingProfile {
    private String name;
    private boolean confirmed;
    private String otp;

    public MovieBookingProfile() {
    }

    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public static void main(String[] args) {
        MovieBookingProfile p1 = new MovieBookingProfile("Rahul Dev");
        System.out.println(p1.getName());

        MovieBookingProfile p2 = new MovieBookingProfile("Rahul Dev");
        p2.setConfirmed(true);
        System.out.println(p2.isConfirmed());

        p2.setOtp("4471");
    }
}
