package com.example.books.rabbitmq;

import com.example.books.model.Log;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueueProducer {

    @Value("${direct.exchange}")
    private String directExchange;

    private final RabbitTemplate rabbitTemplate;

    @Value("${queue.name}")
    private String queueName;

    @Autowired
    public QueueProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produce(Log log, long queueId) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(directExchange, queueName + queueId, new ObjectMapper().writeValueAsString(log));
    }
}
