package com.example.books.config.rabbitmq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Value("${direct.exchange}")
    private String directExchange;

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(directExchange);
    }
}