package com.penateam.dummyBank;

import lombok.Data;
@Data
public class Transaction {
    private String transactionId;
    private String description;
    private double amount;
    private String date;

    public Transaction(String transactionId, String description, double amount, String date) {
        this.transactionId = transactionId;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }
}
