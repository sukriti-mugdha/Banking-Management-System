package banking;

public class SavingsAccount extends BankAccount {
    public SavingsAccount(String acNumber, String name,String accType, double balance) {
        super(acNumber, name, accType,balance);
    }

    @Override
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited ₹" + amount + ". New balance: ₹" + balance);
            return true;
        } else {
            System.out.println("Deposit amount must be positive.");
            return false;
        }
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && balance - amount >= 2000) {  // Assuming min balance requirement
            balance -= amount;
            System.out.println("Withdrawn ₹" + amount + ". New balance: ₹" + balance);
            return true;
        } else {
            System.out.println("Withdrawal failed. Insufficient balance or minimum balance required.");
            return false;
        }
    }
}
