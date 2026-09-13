package com.project.ai_agent.model;

public record Transaction(
        String transactionId,
        String status,
        double amount,
        String currency
) {
}
