package org.example;
import java.util.ArrayList;
import java.util.List;

public class BankingSystem {
    private String accountHolder;
    private double balance;
    private List<String> transactionHistory;

    public BankingSystem(String accountHolder) {
        this.accountHolder = accountHolder;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
        } else {
            transactionHistory.add("Failed deposit attempt: Invalid amount $" + amount);
            throw new IllegalArgumentException("Deposit amount must be positive!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
        } else {
            transactionHistory.add("Failed withdrawal attempt: Invalid amount $" + amount);
            throw new IllegalArgumentException("Invalid withdrawal amount!");
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void displayAccountInfo() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Current Balance: $" + balance);
        System.out.println("Transaction History: ");
        for (String transaction : transactionHistory) {
            System.out.println(" - " + transaction);
        }
    }

    public void transferFunds(BankingSystem targetAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            withdraw(amount);
            targetAccount.deposit(amount);
            transactionHistory.add("Transferred: $" + amount + " to " + targetAccount.getAccountHolder());
            targetAccount.addTransaction("Received: $" + amount + " from " + accountHolder);
        } else {
            transactionHistory.add("Failed transfer attempt: Invalid amount $" + amount);
            throw new IllegalArgumentException("Invalid transfer amount!");
        }
    }

    private void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }
}
