package com.example.books.controller;

import com.example.books.model.Log;
import com.example.books.rabbitmq.QueueConsumer;
import com.example.books.rabbitmq.QueueProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    private QueueProducer queueProducer;

    @Autowired
    private QueueConsumer queueConsumer;

    @GetMapping
    public ResponseEntity<?> getLogs() throws JsonProcessingException {
        Log log = queueConsumer.processMessage();
        return new ResponseEntity<>(log, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> storeLog(@RequestBody Log log) throws JsonProcessingException {
        System.out.println(log);
        queueProducer.produce(log);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //TODO:: mai intelege astea. Adauga in CV ca stii
}
