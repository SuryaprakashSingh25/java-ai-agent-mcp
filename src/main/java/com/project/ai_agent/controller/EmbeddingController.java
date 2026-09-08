package com.project.ai_agent.controller;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmbeddingController {
    private final EmbeddingModel embeddingModel;

    public EmbeddingController(EmbeddingModel embeddingModel){
        this.embeddingModel=embeddingModel;
    }

    @GetMapping("/embedding")
    public int embedding(@RequestParam String text){
        float[] embedding=embeddingModel.embed(text);
        return embedding.length;
    }
}
