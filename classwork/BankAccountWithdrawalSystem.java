import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

abstract class BankAccount {
    private final String id;
    protected double balance;

    public BankAccount(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public abstract void withdraw(double amount);

    protected void printSuccessBalance() {
        if (balance == (long) balance) {
            System.out.println(id + " balance " + (long) balance);
        } else {
            System.out.println(id + " balance " + balance);
        }
    }
}

class SavingsAccount extends BankAccount {
    private static final double MIN_BALANCE = 1000.0;

    public SavingsAccount(String id, double balance) {
        super(id, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount < MIN_BALANCE) {
            System.out.println(getId() + " rejected: minimum balance 1000");
        } else {
            balance -= amount;
            printSuccessBalance();
        }
    }
}

class CurrentAccount extends BankAccount {
    private static final double OVERDRAFT_LIMIT = 5000.0;

    public CurrentAccount(String id, double balance) {
        super(id, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount < -OVERDRAFT_LIMIT) {
            System.out.println(getId() + " rejected: overdraft limit 5000");
        } else {
            balance -= amount;
            printSuccessBalance();
        }
    }
}

public class BankAccountWithdrawalSystem {

    public static void executeCommand(Map<String, BankAccount> accounts, String line) {
        if (line == null || line.trim().isEmpty()) {
            return;
        }
        String[] parts = line.trim().split("\\s+");
        String command = parts[0];

        if (command.equalsIgnoreCase("Savings")) {
            String id = parts[1];
            double initialBalance = Double.parseDouble(parts[2]);
            accounts.put(id, new SavingsAccount(id, initialBalance));
        } else if (command.equalsIgnoreCase("Current")) {
            String id = parts[1];
            double initialBalance = Double.parseDouble(parts[2]);
            accounts.put(id, new CurrentAccount(id, initialBalance));
        } else if (command.equalsIgnoreCase("WITHDRAW")) {
            String id = parts[1];
            double amount = Double.parseDouble(parts[2]);
            BankAccount acc = accounts.get(id);
            if (acc == null) {
                System.out.println("Account not found");
            } else {
                acc.withdraw(amount);
            }
        }
    }

    public static void main(String[] args) {
        Map<String, BankAccount> accounts = new LinkedHashMap<>();
        try {
            if (System.in.available() > 0) {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    executeCommand(accounts, line);
                }
                scanner.close();
                return;
            }
        } catch (Exception ignored) {
        }

        String[] sample = {
            "Savings S1 5000",
            "WITHDRAW S1 4500",
            "Current C1 2000",
            "WITHDRAW C1 6000",
            "WITHDRAW S1 3000",
            "WITHDRAW C1 2000"
        };
        for (String line : sample) {
            executeCommand(accounts, line);
        }
    }
}
