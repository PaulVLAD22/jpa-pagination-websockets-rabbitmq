package com.example.books.controller;

import com.example.books.model.WebSocketChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class WebSocketChatController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public WebSocketChatMessage sendMessage(@Payload WebSocketChatMessage webSocketChatMessage,
                                            SimpMessageHeaderAccessor headerAccessor) {
        System.out.println(headerAccessor);

        messagingTemplate.convertAndSend("/topic/public", webSocketChatMessage);
//        messagingTemplate.convertAndSend("/user/" + webSocketChatMessage.getSender() + "/queue/reply",
//                webSocketChatMessage);
        return webSocketChatMessage;
    }

    @MessageMapping("/chat.newUser")
    public WebSocketChatMessage newUser(@Payload WebSocketChatMessage webSocketChatMessage,
                                        SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", webSocketChatMessage.getSender());

        messagingTemplate.convertAndSend("/topic/public", webSocketChatMessage);
        return webSocketChatMessage;
    }
}