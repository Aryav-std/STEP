public class SecureBankAccount {
    // ================== Private Fields ==================
    private final String accountNumber;
    private double balance;
    private int pin;
    private boolean isLocked;
    private int failedAttempts;

    // ================== Private Constants ==================
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final double MIN_BALANCE = 0.0;

    // ================== Constructor ==================
    public SecureBankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = Math.max(initialBalance, MIN_BALANCE);
        this.pin = 0; // Must be set later
        this.isLocked = false;
        this.failedAttempts = 0;
    }

    // ================== Account Info Methods ==================
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        if (isLocked) {
            System.out.println("Account is locked. Cannot view balance.");
            return -1;
        }
        return balance;
    }

    public boolean isAccountLocked() {
        return isLocked;
    }

    // ================== Security Methods ==================
    public boolean setPin(int oldPin, int newPin) {
        if (this.pin == oldPin) {
            this.pin = newPin;
            System.out.println("PIN successfully updated.");
            return true;
        }
        System.out.println("Incorrect old PIN. PIN not updated.");
        return false;
    }

    public boolean validatePin(int enteredPin) {
        if (isLocked) {
            System.out.println("Account is locked.");
            return false;
        }
        if (this.pin == enteredPin) {
            resetFailedAttempts();
            return true;
        } else {
            incrementFailedAttempts();
            return false;
        }
    }

    public void unlockAccount(int correctPin) {
        if (this.pin == correctPin) {
            isLocked = false;
            resetFailedAttempts();
            System.out.println("Account unlocked successfully.");
        } else {
            System.out.println("Incorrect PIN. Account remains locked.");
        }
    }

    // ================== Transaction Methods ==================
    public void deposit(double amount, int pin) {
        if (!validatePin(pin)) {
            System.out.println("Deposit failed: invalid PIN.");
            return;
        }
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount, int pin) {
        if (!validatePin(pin)) {
            System.out.println("Withdrawal failed: invalid PIN.");
            return;
        }
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }
        if (balance - amount >= MIN_BALANCE) {
            balance -= amount;
            System.out.println("Withdrew: " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Insufficient funds. Withdrawal denied.");
        }
    }

    public void transfer(SecureBankAccount target, double amount, int pin) {
        if (!validatePin(pin)) {
            System.out.println("Transfer failed: invalid PIN.");
            return;
        }
        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
            return;
        }
        if (balance - amount >= MIN_BALANCE) {
            this.balance -= amount;
            target.balance += amount;
            System.out.println("Transferred: " + amount + " to " + target.getAccountNumber());
            System.out.println("New balance: " + this.balance);
        } else {
            System.out.println("Insufficient funds. Transfer denied.");
        }
    }

    // ================== Private Helpers ==================
    private void lockAccount() {
        isLocked = true;
        System.out.println("Account locked due to too many failed attempts!");
    }

    private void resetFailedAttempts() {
        failedAttempts = 0;
    }

    private void incrementFailedAttempts() {
        failedAttempts++;
        System.out.println("Failed attempts: " + failedAttempts);
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            lockAccount();
        }
    }

    // ================== Main (Testing) ==================
    public static void main(String[] args) {
        SecureBankAccount acc1 = new SecureBankAccount("ACC1001", 500);
        SecureBankAccount acc2 = new SecureBankAccount("ACC2002", 1000);

        System.out.println("\n[Trying to access fields directly]");
        // System.out.println(acc1.balance); // ❌ ERROR if uncommented

        System.out.println("\n[Setting PINs]");
        acc1.setPin(0, 1234);
        acc2.setPin(0, 4321);

        System.out.println("\n[Deposits & Withdrawals]");
        acc1.deposit(200, 1234);
        acc1.withdraw(100, 1234);

        System.out.println("\n[Transfers]");
        acc1.transfer(acc2, 150, 1234);

        System.out.println("\n[Security Test: Wrong PIN]");
        acc1.withdraw(50, 9999); // wrong pin
        acc1.withdraw(50, 8888); // wrong pin
        acc1.withdraw(50, 7777); // wrong pin -> should lock

        System.out.println("\n[Attempting after lock]");
        acc1.deposit(50, 1234); // should fail

        System.out.println("\n[Unlocking]");
        acc1.unlockAccount(1234);
        acc1.deposit(50, 1234); // should work now

        System.out.println("\n[Over-withdrawal]");
        acc1.withdraw(10000, 1234); // insufficient funds
    }
}
