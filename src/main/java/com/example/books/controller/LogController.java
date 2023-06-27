package com.example.books.controller;

import com.example.books.model.Log;
import com.example.books.rabbitmq.QueueConsumer;
import com.example.books.rabbitmq.QueueProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    private AmqpAdmin amqpAdmin;
    @Autowired
    private QueueProducer queueProducer;

    @Autowired
    private QueueConsumer queueConsumer;

    @Value("${queue.name}")
    private String queueName;

    @GetMapping("/{queueId}")
    public ResponseEntity<?> getLogs(@PathVariable long queueId) throws JsonProcessingException {

        Log log = queueConsumer.processMessage(queueId);
        return new ResponseEntity<>(log, HttpStatus.OK);
    }

    @PostMapping("/{queueId}")
    public ResponseEntity<?> storeLog(@RequestBody Log log, @PathVariable long queueId) throws JsonProcessingException {
        Queue queue = new Queue(queueName + queueId, true);
        amqpAdmin.declareQueue(queue);

        queueProducer.produce(log, queueId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    //TODO:: acum se creaza cozi
    //TODO:: nu se adauga mesajele la nicio coada

}
