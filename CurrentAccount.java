package banking;

public class CurrentAccount extends BankAccount {
    public CurrentAccount(String acNumber, String name,String acType, double balance) {
        super(acNumber, name, "Current", balance);
    }

    @Override
    public boolean deposit(double amount) {
        balance += amount;
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance - amount >= 5000) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
