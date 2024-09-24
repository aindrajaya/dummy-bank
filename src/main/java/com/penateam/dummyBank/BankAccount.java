package com.penateam.dummyBank;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BankAccount {
    private String accountId;
    private String accountType;
    private double balance;
    private List<Transaction> transactions;

    public BankAccount(String accountId, String accountType, double balance) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        // Update balance based on the transaction amount
        this.balance += transaction.getAmount();
    }

    public double getBalance() {
        return this.balance;
    }
}