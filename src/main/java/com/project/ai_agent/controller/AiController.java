package com.project.ai_agent.controller;

import com.project.ai_agent.dto.ChatRequest;
import com.project.ai_agent.dto.ChatResponse;
import jakarta.validation.Valid;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AiController {
    private final ChatClient chatClient;

    public AiController(ChatClient.Builder chatClientBuilder){
        this.chatClient=chatClientBuilder.build();
    }

    @PostMapping("/chat")
    public ChatResponse chat(@Valid @RequestBody ChatRequest request){
        String response=chatClient
                .prompt(request.message())
                .call()
                .content();

        return new ChatResponse(response);
    }

}
