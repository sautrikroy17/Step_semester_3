package constructors.class_problems;

class FeeAccount {
    public void processAccountPayment(double amount) {
        System.out.println("Paid in one go (day-scholar account)");
    }
}

class HostelFeeAccount extends FeeAccount {
    @Override
    public void processAccountPayment(double amount) {
        System.out.println("Paid in two installments (hostel account)");
    }
}

public class AccountPaymentBatch {
    public static void processPayment(FeeAccount account, double amount) {
        account.processAccountPayment(amount);
    }

    public static void main(String[] args) {
        FeeAccount[] accounts = {
            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };

        int hostelCount = 0;
        int dayScholarCount = 0;

        for (FeeAccount acc : accounts) {
            if (acc instanceof HostelFeeAccount) {
                hostelCount++;
            } else if (acc instanceof FeeAccount) {
                dayScholarCount++;
            }
            processPayment(acc, 60000.0);
        }

        System.out.printf("Hostel accounts processed: %d | Day-scholar accounts processed: %d%n", hostelCount, dayScholarCount);
    }
}
