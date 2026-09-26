abstract class Vehicle {
    private String id;
    private String model;
    private boolean rented;

    public Vehicle(String id, String model) {
        this.id = id;
        this.model = model;
        this.rented = false;
    }

    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public abstract double calculateRentalCharge(int days);
}

class Sedan extends Vehicle {
    private static final double DAILY_RATE = 50.0;

    public Sedan(String id, String model) {
        super(id, model);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return DAILY_RATE * days;
    }
}

class SUV extends Vehicle {
    private static final double DAILY_RATE = 80.0;

    public SUV(String id, String model) {
        super(id, model);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return DAILY_RATE * days;
    }
}

class Truck extends Vehicle {
    private static final double DAILY_RATE = 120.0;

    public Truck(String id, String model) {
        super(id, model);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return DAILY_RATE * days;
    }
}

class RentalCustomer {
    private String customerId;
    private String name;

    public RentalCustomer(String customerId, String name) {
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

class Rental {
    private RentalCustomer customer;
    private Vehicle vehicle;
    private int days;
    private double charge;

    public Rental(RentalCustomer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        this.charge = vehicle.calculateRentalCharge(days);
    }

    public RentalCustomer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getDays() {
        return days;
    }

    public double getCharge() {
        return charge;
    }
}

class RentalService {
    public Rental rentVehicle(RentalCustomer customer, Vehicle vehicle, int days) {
        if (vehicle.isRented()) {
            System.out.println(vehicle.getModel() + " is currently unavailable.");
            return null;
        }
        vehicle.setRented(true);
        Rental rental = new Rental(customer, vehicle, days);
        System.out.println(vehicle.getModel() + " rented successfully by " + customer.getName() + ". Rental charge: $" + (int)rental.getCharge() + ".");
        return rental;
    }

    public void returnVehicle(Vehicle vehicle, RentalCustomer customer) {
        vehicle.setRented(false);
        System.out.println(vehicle.getModel() + " returned by " + customer.getName() + ".");
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        RentalService service = new RentalService();
        Vehicle sedanA = new Sedan("V101", "Sedan A");
        Vehicle suvB = new SUV("V102", "SUV B");

        RentalCustomer customer1 = new RentalCustomer("C1", "Customer 1");
        RentalCustomer customer2 = new RentalCustomer("C2", "Customer 2");
        RentalCustomer customer3 = new RentalCustomer("C3", "Customer 3");

        service.rentVehicle(customer1, sedanA, 3);
        service.rentVehicle(customer2, sedanA, 2);
        service.returnVehicle(sedanA, customer1);
        service.rentVehicle(customer3, suvB, 5);
    }
}
