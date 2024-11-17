package com.example.openai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ChatController {
    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("story/{ask}")
    Map<String, String> story(@PathVariable String ask){
        var response = this.chatClient
                .prompt()
                .user(ask)
                .call()
                .content();

        return Map.of("story", response);
    }

}
