package com.project.ai_agent.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RagService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public RagService(ChatClient.Builder chatClientBuilder,
                      VectorStore vectorStore){
        this.chatClient=chatClientBuilder.build();
        this.vectorStore=vectorStore;
    }

    public String answer(String question){

        SearchRequest searchRequest=SearchRequest.builder()
                .query(question)
                .topK(3)
                .similarityThreshold(0.7)
                .build();

        List<Document> documents=vectorStore.similaritySearch(searchRequest);

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
