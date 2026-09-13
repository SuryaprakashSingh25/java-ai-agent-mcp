package com.project.ai_agent.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimeTool {
    @Tool(description = "Get the current date and time")
    public String getCurrentTime(){
        return LocalDateTime.now().toString();
    }
}
