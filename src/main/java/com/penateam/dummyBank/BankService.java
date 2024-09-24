package com.penateam.dummyBank;

import com.penateam.dummyBank.BankAccount;
import com.penateam.dummyBank.Transaction;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BankService {
    private final Map<String, BankAccount> accounts = new HashMap<>();

    public BankService() {
        // Create a dummy bank account
        BankAccount account = new BankAccount("123456789", "CHECKING", 1000.00);
        account.addTransaction(new Transaction("1001", "Grocery", -50.00, "2023-01-01"));
        account.addTransaction(new Transaction("1002", "Salary", 2000.00, "2023-01-05"));
        accounts.put(account.getAccountId(), account);
    }

    public BankAccount getAccount(String accountId) {
        return accounts.get(accountId);
    }
}