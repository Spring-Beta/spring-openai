package com.example.openai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
public class ChatController {
    private static final Logger log = LoggerFactory.getLogger(ChatController.class);
    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/")
    public String sayHello(){
        return "Hello there!";
    }

    @GetMapping("/story/{ask}")
    Map<String, String> story(@PathVariable(value = "ask") String ask){
        log.info("What is the problem?");
        var response = this.chatClient
                .prompt()
                .user(ask)
                .call()
                .content();

        return Map.of("story", response);
    }

}
