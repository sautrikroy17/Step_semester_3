import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

abstract class Seat {
    private String seatNumber;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public abstract double getPrice();
}

class RegularSeat extends Seat {
    public RegularSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 150.00;
    }
}

class PremiumSeat extends Seat {
    public PremiumSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 250.00;
    }
}

class ReclinerSeat extends Seat {
    public ReclinerSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 400.00;
    }
}

class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
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

class Show {
    private String showName;
    private Set<String> bookedSeatNumbers;

    public Show(String showName) {
        this.showName = showName;
        this.bookedSeatNumbers = new HashSet<>();
    }

    public String getShowName() {
        return showName;
    }

    public boolean isSeatBooked(String seatNumber) {
        return bookedSeatNumbers.contains(seatNumber);
    }

    public void bookSeat(String seatNumber) {
        bookedSeatNumbers.add(seatNumber);
    }

    public void releaseSeat(String seatNumber) {
        bookedSeatNumbers.remove(seatNumber);
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> seats;
    private boolean active;

    public Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = seats;
        this.active = true;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Seat s : seats) {
            total += s.getPrice();
        }
        return total;
    }

    public boolean isActive() {
        return active;
    }

    public void cancel() {
        this.active = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seats.size(); i++) {
            Seat s = seats.get(i);
            show.releaseSeat(s.getSeatNumber());
            sb.append(s.getSeatNumber());
            if (i < seats.size() - 1) {
                sb.append(", ");
            }
        }
        System.out.println(customer.getName() + "'s booking cancelled. Seats " + sb.toString() + " released.");
    }
}

class TicketCounter {
    public Booking bookTickets(Customer customer, Show show, List<Seat> seats) {
        if (seats.size() > 6) {
            System.out.println("Maximum 6 seats allowed per booking.");
            return null;
        }

        for (Seat s : seats) {
            if (show.isSeatBooked(s.getSeatNumber())) {
                System.out.println("Seat " + s.getSeatNumber() + " is already booked for this show.");
                return null;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seats.size(); i++) {
            Seat s = seats.get(i);
            show.bookSeat(s.getSeatNumber());
            sb.append(s.getSeatNumber());
            if (i < seats.size() - 1) {
                sb.append(", ");
            }
        }

        Booking booking = new Booking(customer, show, seats);
        System.out.printf("Booking confirmed for %s: %s. Total: ₹%.2f.%n",
                customer.getName(), sb.toString(), booking.calculateTotal());
        return booking;
    }
}

public class TheCampusPremiereTicketCounter {
    public static void main(String[] args) {
        Show show7PM = new Show("7 PM Show");
        TicketCounter counter = new TicketCounter();

        Customer asha = new Customer("C1", "Asha");
        Customer ravi = new Customer("C2", "Ravi");
        Customer neha = new Customer("C3", "Neha");

        Seat a1 = new RegularSeat("A1");
        Seat a2 = new RegularSeat("A2");
        Seat f5 = new PremiumSeat("F5");
        Seat r1 = new ReclinerSeat("R1");

        List<Seat> ashaSeats = new ArrayList<>();
        ashaSeats.add(a1);
        ashaSeats.add(a2);
        ashaSeats.add(f5);

        Booking ashaBooking = counter.bookTickets(asha, show7PM, ashaSeats);

        List<Seat> raviAttemptSeats = new ArrayList<>();
        raviAttemptSeats.add(a2);
        counter.bookTickets(ravi, show7PM, raviAttemptSeats);

        List<Seat> raviSeats = new ArrayList<>();
        raviSeats.add(r1);
        counter.bookTickets(ravi, show7PM, raviSeats);

        ashaBooking.cancel();

        List<Seat> nehaSeats = new ArrayList<>();
        nehaSeats.add(a2);
        counter.bookTickets(neha, show7PM, nehaSeats);
    }
}
