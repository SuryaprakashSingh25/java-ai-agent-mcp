package com.project.ai_agent.controller;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    private final VectorStore vectorStore;

    public DocumentController(VectorStore vectorStore){
        this.vectorStore=vectorStore;
    }

    @PostMapping
    public String addDocument(@RequestBody String content){
        Document document=new Document(
                content,
                Map.of("source","manual")
        );
        vectorStore.add(List.of(document));
        return "Document added successfully";
    }

    @GetMapping("/search")
    public List<Document> search(@RequestParam String query){
        return vectorStore.similaritySearch(query);
    }
}
