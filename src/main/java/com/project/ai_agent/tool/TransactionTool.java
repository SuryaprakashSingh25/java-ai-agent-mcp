package com.project.ai_agent.tool;

import com.project.ai_agent.model.Transaction;
import com.project.ai_agent.model.TransactionResult;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TransactionTool {
    private final Map<String, Transaction> transactions=Map.of(
            "TXN-1001", new Transaction("TXN-1001", "SUCCESS", 5000, "INR"),
            "TXN-1002", new Transaction("TXN-1002", "PENDING", 2500, "INR"),
            "TXN-1003", new Transaction("TXN-1003", "FAILED", 10000, "INR")
    );

    @Tool(description = "Get the status of a transaction using its transaction ID")
    public TransactionResult getTransactionStatus(String transactionId){
        Transaction transaction=transactions.get(transactionId);
        if(transaction==null){
            return new TransactionResult(
                    false,
                    null,
                    "Transaction not found"
            );
        }
        return new TransactionResult(
                true,
                transaction,
                "Transaction found"
        );
    }
}
