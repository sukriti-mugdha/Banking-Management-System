package banking;
 
import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BankAccount {
    protected String acNumber;
    protected String name;
    protected double balance;
    protected String accType;

    public BankAccount(String acNumber, String name, String accType ,double balance ) {
        this.acNumber = acNumber;
        this.name = name;
        this.balance = balance;
        this.accType = accType;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void saveToDatabase() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO accounts (acNumber, name, balance) VALUES (?, ?, ?) " +
                         "ON DUPLICATE KEY UPDATE name=?, balance=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, acNumber);
            stmt.setString(2, name);
            stmt.setDouble(3, balance); 
            stmt.setString(4, name);
            stmt.setDouble(5, balance);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("❌ You can't deposit a negative amount.");
            return false;
        }
        balance += amount;
        saveToDatabase();
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("❌ Insufficient balance!");
            return false;
        }
        balance -= amount;
        saveToDatabase();
        return true;
    }

    public boolean transferMoney(String recipientAccount, String ifscCode, double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid Transfer Amount.");
            return false;
        }
        if (withdraw(amount)) {
            System.out.println("✅ Amount Transferred: ₹" + amount);
            return true;
        } else {
            System.out.println("❌ Transfer Failed. Insufficient Balance.");
            return false;
        }
    }

    public void updateAccountHolderName(String newName) {
        this.name = newName;
        saveToDatabase();
    }
}
