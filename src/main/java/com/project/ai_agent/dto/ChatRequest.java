package com.project.ai_agent.dto;

import jakarta.validation.constraints.NotBlank;

public record ChatRequest(
        @NotBlank(message="Message cannot be blank")
        String message) {
}
