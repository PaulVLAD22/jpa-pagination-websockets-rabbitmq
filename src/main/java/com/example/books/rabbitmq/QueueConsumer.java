package com.example.books.rabbitmq;

import com.example.books.model.Log;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${queue.name}")
    private String queueName;

    @Autowired
    public QueueConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private String receiveMessage(long queueId) {
        String message = (String) rabbitTemplate.receiveAndConvert(queueName + queueId);
        return message;
    }

    //TODO:: nu se creaza coada cu userId


    public Log processMessage(long queueId) throws JsonProcessingException {
        String message = receiveMessage(queueId);
        return new ObjectMapper().readValue(message, Log.class);
    }
}
