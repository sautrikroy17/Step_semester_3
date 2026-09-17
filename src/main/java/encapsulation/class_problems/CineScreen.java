package encapsulation.class_problems;

public class CineScreen {
    private int seatsTotal;
    private int seatsAvailable;

    public CineScreen(int seatsTotal) {
        if (seatsTotal <= 0) {
            System.out.println("construction rejected");
            this.seatsTotal = 0;
            this.seatsAvailable = 0;
            return;
        }
        this.seatsTotal = seatsTotal;
        this.seatsAvailable = seatsTotal;
    }

    public void bookSeat() {
        if (seatsAvailable > 0) {
            seatsAvailable--;
        }
    }

    public void cancelBooking() {
        if (seatsAvailable < seatsTotal) {
            seatsAvailable++;
        }
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public static void main(String[] args) {
        new CineScreen(0);

        CineScreen screen = new CineScreen(2);
        screen.bookSeat();
        screen.bookSeat();
        screen.bookSeat();
        System.out.println(screen.getSeatsAvailable());

        screen.cancelBooking();
        screen.cancelBooking();
        screen.cancelBooking();
        System.out.println(screen.getSeatsAvailable());
    }
}
