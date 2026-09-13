package com.project.ai_agent.controller;

import com.project.ai_agent.tool.TimeTool;
import com.project.ai_agent.tool.TransactionTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agent")
public class AgentController {
    private final ChatClient chatClient;
    private final TimeTool timeTool;
    private final TransactionTool transactionTool;

    public AgentController(ChatClient.Builder chatClientBuilder,
                           TimeTool timeTool,
                           TransactionTool transactionTool){
        this.chatClient=chatClientBuilder.build();
        this.timeTool=timeTool;
        this.transactionTool=transactionTool;
    }

    @PostMapping
    public String ask(@RequestBody String question){
        return chatClient
                .prompt()
                .user(question)
                .tools(timeTool,transactionTool)
                .call()
                .content();
    }

}
