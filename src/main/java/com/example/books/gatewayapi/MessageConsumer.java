package com.example.books.gatewayapi;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private static final String QUEUE_NAME = "rabbitmq-gateway";

    @RabbitListener(queues = QUEUE_NAME)
    public void receiveMessage(String message) {
        // Process the received message
        System.out.println("Received message: " + message);
        // Add your custom logic here
    }
}
