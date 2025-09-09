import java.util.Random;

public class BankAccount {
    String accountHolder;
    int accountNumber;
    double balance;

    // Default constructor with balance 0
    public BankAccount() {
        accountHolder = "Unknown";
        accountNumber = 0;
        balance = 0;
    }

    // Constructor with account holder name, random account number
    public BankAccount(String accountHolder) {
        this.accountHolder = accountHolder;
        this.accountNumber = generateRandomAccountNumber();
        balance = 0;
    }

    // Constructor with account holder name and initial balance
    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = generateRandomAccountNumber();
        this.balance = initialBalance;
    }

    // Generates random 6-digit account number
    private int generateRandomAccountNumber() {
        Random rand = new Random();
        return 100000 + rand.nextInt(900000);
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) balance -= amount;
        else System.out.println("Insufficient balance for withdrawal");
    }

    // Display account details
    public void displayAccount() {
        System.out.println("Account Holder: " + accountHolder + ", Account Number: " + accountNumber + ", Balance: " + balance);
    }

    public static void main(String[] args) {
        BankAccount a1 = new BankAccount();
        BankAccount a2 = new BankAccount("Alice");
        BankAccount a3 = new BankAccount("Bob", 5000);

        a1.deposit(1000);
        a2.deposit(2000);
        a3.withdraw(1500);

        a1.displayAccount();
        a2.displayAccount();
        a3.displayAccount();
    }
}
