package banking;

import java.util.Scanner;

public class BankingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = null;

        while (true) {
            System.out.println("\n=== Banking Application Menu ===");
            System.out.println("1. Create Account");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer Money");
            System.out.println("6. Update Account Holder Name");
            System.out.println("7. Exit");
            System.out.print("Choose an option (1-7): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Create Account
                    System.out.print("Enter Account Number: ");
                    String acNumber = scanner.next();
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Account Type (Savings/Current): ");
                    String acType = scanner.next();
                    System.out.print("Enter Initial Balance: ");
                    double balance = scanner.nextDouble();

                    account = acType.equalsIgnoreCase("Savings")
                            ? new SavingsAccount(acNumber, name, acType ,balance)
                            : new CurrentAccount(acNumber, name, acType,balance);

                    account.saveToDatabase();
                    System.out.println("✅ Account created successfully!");
                    break;

                case 2: // Check Balance
                    if (account != null) {
                        System.out.println("✅ Account Holder: " + account.getName());
                        System.out.println("💰 Current Balance: ₹" + account.getBalance());
                    } else {
                        System.out.println("⚠ No account found. Create an account first.");
                    }
                    break;

                case 3: // Deposit
                    if (account != null) {
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        if (depositAmount <= 0) {
                            System.out.println("❌ You can't deposit a negative amount.");
                        } else {
                            account.deposit(depositAmount);
                            System.out.println("✅ Amount Deposited Successfully!");
                            System.out.println("💰 Current Balance: ₹" + account.getBalance());
                        }
                    } else {
                        System.out.println("⚠ No account found. Create an account first.");
                    }
                    break;

                case 4: // Withdraw
                    if (account != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        if (withdrawAmount > 0 && account.withdraw(withdrawAmount)) {
                            System.out.println("✅ Amount Withdrawn Successfully!");
                            System.out.println("💰 Current Balance: ₹" + account.getBalance());
                        }
                    } else {
                        System.out.println("⚠ No account found. Create an account first.");
                    }
                    break;

                case 5: // Transfer Money
                    if (account != null) {
                        System.out.print("Enter Recipient Account Number: ");
                        String recipientAccountNumber = scanner.next();
                        System.out.print("Enter IFSC Code: ");
                        String ifscCode = scanner.next();
                        System.out.print("Enter Transfer Amount: ");
                        double transferAmount = scanner.nextDouble();

                        if (account.transferMoney(recipientAccountNumber, ifscCode, transferAmount)) {
                            System.out.println("✅ Amount Transferred Successfully!");
                            System.out.println("💰 Current Balance: ₹" + account.getBalance());
                        }
                    } else {
                        System.out.println("⚠ No account found. Create an account first.");
                    }
                    break;

                case 6: // Update Name
                    if (account != null) {
                        System.out.print("Enter New Account Holder Name: ");
                        String newName = scanner.next();
                        account.updateAccountHolderName(newName);
                        System.out.println("✅ Name Changed Successfully!");
                        System.out.println("💰 Current Balance: ₹" + account.getBalance());
                        System.out.println("👤 Account Holder: " + account.getName());
                    } else {
                        System.out.println("⚠ No account found. Create an account first.");
                    }
                    break;

                case 7: // Exit
                    System.out.println("👋 Thank you for using Banking Application!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("⚠ Invalid Option. Try again.");
            }
        }
    }
}
