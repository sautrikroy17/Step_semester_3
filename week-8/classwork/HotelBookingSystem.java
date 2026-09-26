import java.util.ArrayList;
import java.util.List;

abstract class Room {
    private String roomNumber;
    private String roomType;

    public Room(String roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public abstract double calculatePrice(int days);
}

class StandardRoom extends Room {
    private static final double RATE_PER_NIGHT = 100.0;

    public StandardRoom(String roomNumber) {
        super(roomNumber, "Standard Room");
    }

    @Override
    public double calculatePrice(int days) {
        return RATE_PER_NIGHT * days;
    }
}

class DeluxeRoom extends Room {
    private static final double RATE_PER_NIGHT = 180.0;

    public DeluxeRoom(String roomNumber) {
        super(roomNumber, "Deluxe Room");
    }

    @Override
    public double calculatePrice(int days) {
        return RATE_PER_NIGHT * days;
    }
}

class Suite extends Room {
    private static final double RATE_PER_NIGHT = 300.0;

    public Suite(String roomNumber) {
        super(roomNumber, "Suite");
    }

    @Override
    public double calculatePrice(int days) {
        return RATE_PER_NIGHT * days;
    }
}

class HotelCustomer {
    private String customerId;
    private String name;

    public HotelCustomer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}

class Reservation {
    private String reservationId;
    private HotelCustomer customer;
    private Room room;
    private String dateRange;
    private int startDay;
    private int endDay;
    private double price;
    private boolean active;

    public Reservation(String reservationId, HotelCustomer customer, Room room, String dateRange, int startDay, int endDay) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.room = room;
        this.dateRange = dateRange;
        this.startDay = startDay;
        this.endDay = endDay;
        this.price = room.calculatePrice(endDay - startDay);
        this.active = true;
    }

    public String getReservationId() {
        return reservationId;
    }

    public HotelCustomer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public String getDateRange() {
        return dateRange;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public double getPrice() {
        return price;
    }

    public boolean isActive() {
        return active;
    }

    public void cancel() {
        this.active = false;
    }

    public boolean overlaps(int start, int end) {
        if (!active) {
            return false;
        }
        return !(end <= startDay || start >= endDay);
    }
}

class HotelBookingService {
    private List<Reservation> reservations;

    public HotelBookingService() {
        this.reservations = new ArrayList<>();
    }

    public boolean isAvailable(Room room, int startDay, int endDay) {
        for (Reservation res : reservations) {
            if (res.getRoom().getRoomNumber().equals(room.getRoomNumber()) && res.overlaps(startDay, endDay)) {
                return false;
            }
        }
        return true;
    }

    public void checkAvailability(Room room, String dateRange, int startDay, int endDay) {
        if (isAvailable(room, startDay, endDay)) {
            System.out.println(room.getRoomType() + " " + room.getRoomNumber() + " is available from " + dateRange + ".");
        } else {
            System.out.println(room.getRoomType() + " " + room.getRoomNumber() + " is not available from " + dateRange + ".");
        }
    }

    public Reservation reserveRoom(HotelCustomer customer, Room room, String dateRange, int startDay, int endDay) {
        if (!isAvailable(room, startDay, endDay)) {
            System.out.println(room.getRoomType() + " " + room.getRoomNumber() + " is not available from " + dateRange + ".");
            return null;
        }
        Reservation res = new Reservation("RES" + (reservations.size() + 1), customer, room, dateRange, startDay, endDay);
        reservations.add(res);
        System.out.println("Reservation confirmed for " + customer.getName() + ", " + room.getRoomType() + " " + room.getRoomNumber() + " (" + dateRange + "). Price: $" + (int)res.getPrice() + ".");
        return res;
    }

    public void cancelReservation(Reservation reservation) {
        if (reservation != null && reservation.isActive()) {
            reservation.cancel();
            System.out.println("Reservation for " + reservation.getCustomer().getName() + ", " + reservation.getRoom().getRoomType() + " " + reservation.getRoom().getRoomNumber() + " (" + reservation.getDateRange() + ") cancelled successfully.");
        }
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {
        HotelBookingService service = new HotelBookingService();

        Room standard101 = new StandardRoom("101");
        Room deluxe201 = new DeluxeRoom("201");

        HotelCustomer custA = new HotelCustomer("C1", "Customer A");
        HotelCustomer custB = new HotelCustomer("C2", "Customer B");
        HotelCustomer custC = new HotelCustomer("C3", "Customer C");

        service.checkAvailability(standard101, "Jan 1 to Jan 5", 1, 5);
        Reservation resA = service.reserveRoom(custA, standard101, "Jan 1-5", 1, 5);
        service.reserveRoom(custB, standard101, "Jan 3 to Jan 7", 3, 7);
        service.cancelReservation(resA);
        service.reserveRoom(custC, deluxe201, "Feb 10-12", 10, 12);
    }
}
