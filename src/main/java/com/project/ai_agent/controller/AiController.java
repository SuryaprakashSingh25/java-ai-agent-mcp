package com.project.ai_agent.controller;

import com.project.ai_agent.dto.ChatRequest;
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
    public String chat(@RequestBody ChatRequest request){
        return chatClient
                .prompt(request.message())
                .call()
                .content();
    }

}
