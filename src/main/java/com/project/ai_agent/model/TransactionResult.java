package com.project.ai_agent.model;

public record TransactionResult(
        boolean found,
        Transaction transaction,
        String message
) {
}
