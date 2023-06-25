package com.example.books.config.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketChatConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocketApp").setAllowedOrigins("http://localhost:3000").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setUserDestinationPrefix("/user");
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableStompBrokerRelay("/topic").setRelayHost("localhost").setRelayPort(61613).setClientLogin("admin")
                .setClientPasscode("admin")
                .setSystemLogin("admin") // Add this line
                .setSystemPasscode("admin"); // Add this line
    }

//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(new ChannelInterceptor() {
//            @Override
//            public Message<?> preSend(Message<?> message, MessageChannel channel) {
//                System.out.println(channel);
//                StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//                System.out.println(accessor.getCommand());
//                if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
//                    String user = accessor.getUser().getName(); // Get the username
//                    String destination = accessor.getDestination(); // Get the destination
//                    System.out.println("User '" + user + "' subscribed to '" + destination + "'");
//                }
//                return message;
//            }
//        });
//    }
}