package com.project.ai_agent.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {
    private final ChatClient chatClient;

    public AiController(ChatClient.Builder chatClientBuilder){
        this.chatClient=chatClientBuilder.build();
    }

    @GetMapping("/api/hello-ai")
    public String helloAi(){
        return chatClient
                .prompt("Explain what is AI in one sentence.")
                .call()
                .content();
    }

}
