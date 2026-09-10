package com.project.ai_agent.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rag")
public class RagController {
    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public RagController(ChatClient.Builder chatClientBuilder,
                         VectorStore vectorStore){
        this.chatClient=chatClientBuilder.build();
        this.vectorStore=vectorStore;
    }

    @PostMapping
    public String ask(@RequestBody String question){
        List<Document> documents=vectorStore.similaritySearch(question);

        String context=documents.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n\n"));

        return chatClient
                .prompt()
                .system("""
                        Answer the user's question using only the provided context.
                        If the answer cannot be found in the context, say:
                        "I don't have enough information in the provided context."
                        
                        Context:
                        %s
                        """.formatted(context))
                .user(question)
                .call()
                .content();
    }
}
