package com.penateam.dummyBank;

import com.penateam.dummyBank.BankAccount;
import com.penateam.dummyBank.BankService;
import com.webcohesion.ofx4j.io.OFXHandler;
import com.webcohesion.ofx4j.domain.data.ResponseEnvelope;
import com.webcohesion.ofx4j.domain.data.signon.SignonResponse;
import com.webcohesion.ofx4j.io.OFXSyntaxException;

public class CustomOFXHandler implements OFXHandler {
    private final BankService bankService;
    private final StringBuilder responseBuilder;
    private String userId;
    private String financialInstitution;
    private String transactionId;
    private String accountId;


    public CustomOFXHandler(BankService bankService, StringBuilder responseBuilder) {
        this.bankService = bankService;
        this.responseBuilder = responseBuilder;
    }

    @Override
    public void onHeader(String name, String value) throws OFXSyntaxException {
        responseBuilder.append("Header: ").append(name).append(" = ").append(value).append("\n");
    }

    @Override
    public void onElement(String name, String value) throws OFXSyntaxException {
        switch (name){
            case "USERID":
                userId = value;
                break;
            case "ORG":
                financialInstitution = value;
                break;
            case "TRNUID": // Capture transaction ID
                transactionId = value;
                break;
            case "ACCTID":  // Capture the account ID
                accountId = value;
                break;
            default:
                break;
        }
    }

    @Override
    public void startAggregate(String aggregateName) throws OFXSyntaxException {
        responseBuilder.append("Start Aggregate: ").append(aggregateName).append("\n");
    }

    @Override
    public void endAggregate(String aggregateName) throws OFXSyntaxException {
        responseBuilder.append("End Aggregate: ").append(aggregateName).append("\n");
        if ("SONRQ".equals(aggregateName)) {
            // When exiting the sign-on request aggregate, append collected data
            responseBuilder.append("Signon Response:\n");
            if (userId != null) {
                responseBuilder.append("User ID: ").append(userId).append("\n");
            }
            if (financialInstitution != null) {
                responseBuilder.append("Financial Institution: ").append(financialInstitution).append("\n");
            }
        } else if ("STMTRQ".equals(aggregateName)) {
            if (accountId != null) {
                BankAccount account = bankService.getAccount(accountId);
                if (account != null) {
                    double balance = account.getBalance(); // Assuming getBalance() method exists
                    responseBuilder.append("Latest Balance: ").append(balance).append("\n");
                    if (transactionId != null) {
                        responseBuilder.append("Transaction ID: ").append(transactionId).append("\n");
                    }
                } else {
                    responseBuilder.append("Account not found for ID: ").append(accountId).append("\n");
                }
            } else {
                responseBuilder.append("Account ID not provided.\n");
            }
        }
    }
}