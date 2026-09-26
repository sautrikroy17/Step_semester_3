import java.util.ArrayList;
import java.util.List;

enum OrderStatus {
    PENDING,
    PAID
}

class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}

interface PaymentMethod {
    String getMethodName();
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    private boolean shouldSucceed;

    public CreditCardPayment(boolean shouldSucceed) {
        this.shouldSucceed = shouldSucceed;
    }

    @Override
    public String getMethodName() {
        return "Credit Card";
    }

    @Override
    public boolean processPayment(double amount) {
        return shouldSucceed;
    }
}

class PayPalPayment implements PaymentMethod {
    private boolean shouldSucceed;

    public PayPalPayment(boolean shouldSucceed) {
        this.shouldSucceed = shouldSucceed;
    }

    @Override
    public String getMethodName() {
        return "PayPal";
    }

    @Override
    public boolean processPayment(double amount) {
        return shouldSucceed;
    }
}

class BankTransferPayment implements PaymentMethod {
    private boolean shouldSucceed;

    public BankTransferPayment(boolean shouldSucceed) {
        this.shouldSucceed = shouldSucceed;
    }

    @Override
    public String getMethodName() {
        return "Bank Transfer";
    }

    @Override
    public boolean processPayment(double amount) {
        return shouldSucceed;
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

class Order {
    private String orderId;
    private Customer customer;
    private List<OrderItem> items;
    private OrderStatus status;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        System.out.println("Order created for " + customer.getName() + ".");
    }

    public void addProduct(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public boolean processPayment(PaymentMethod paymentMethod) {
        if (items.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return false;
        }

        System.out.println("Payment initiated via " + paymentMethod.getMethodName() + " for " + orderId + ".");
        boolean success = paymentMethod.processPayment(calculateTotal());
        if (success) {
            this.status = OrderStatus.PAID;
            System.out.println("Payment for " + orderId + " successful. Order status: Paid.");
            return true;
        } else {
            System.out.println("Payment for " + orderId + " failed. Order status: Pending.");
            return false;
        }
    }
}

public class PaymentProcessingSystem {
    public static void main(String[] args) {
        Product prodA = new Product("P1", "Product A", 50.0);
        Product prodB = new Product("P2", "Product B", 30.0);
        Product prodC = new Product("P3", "Product C", 40.0);

        Customer custX = new Customer("C1", "Customer X");
        Customer custY = new Customer("C2", "Customer Y");
        Customer custZ = new Customer("C3", "Customer Z");

        Order orderX = new Order("Order X", custX);
        orderX.addProduct(prodA, 2);
        orderX.addProduct(prodB, 1);
        orderX.processPayment(new CreditCardPayment(true));

        Order orderY = new Order("Order Y", custY);
        orderY.processPayment(new CreditCardPayment(true));

        Order orderZ = new Order("Order Z", custZ);
        orderZ.addProduct(prodC, 1);
        orderZ.processPayment(new PayPalPayment(false));
    }
}
