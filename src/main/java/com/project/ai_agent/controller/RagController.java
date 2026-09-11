package com.project.ai_agent.controller;

import com.project.ai_agent.dto.RagRequest;
import com.project.ai_agent.dto.RagResponse;
import com.project.ai_agent.service.RagService;
import jakarta.validation.Valid;
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
    private final RagService ragService;

    public RagController(RagService ragService){
        this.ragService=ragService;
    }

    @PostMapping
    public RagResponse ask(@Valid @RequestBody RagRequest request){
        String answer=ragService.answer(request.question());
        return new RagResponse(answer);
    }
}
