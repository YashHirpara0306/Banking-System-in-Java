import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
        this.transactionHistory.add("Account created with initial balance 0.0");
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount);
            System.out.println("Withdrew: " + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction history for account " + accountNumber + ":");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

public class BankingSystem {

    private static Scanner scanner = new Scanner(System.in);
    private static List<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Banking System Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Balance");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdrawal();
                    break;
                case 4:
                    viewBalance();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        BankAccount account = new BankAccount(accountNumber);
        accounts.add(account);
        System.out.println("Account created successfully.");
    }

    private static BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        System.out.println("Account not found.");
        return null;
    }

    private static void performDeposit() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        }
    }

    private static void performWithdrawal() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        }
    }

    private static void viewBalance() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("Balance: " + account.getBalance());
        }
    }

    private static void viewTransactionHistory() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.displayTransactionHistory();
        }
    }
}
