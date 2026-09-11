package com.project.ai_agent.dto;

import jakarta.validation.constraints.NotBlank;

public record RagRequest(
        @NotBlank(message = "Question cannot be blank")
        String question
) {
}
