package com.irfan.mubasyir.rabbitmq.controller;

import com.irfan.mubasyir.rabbitmq.dto.User;
import com.irfan.mubasyir.rabbitmq.publisher.RabbitMQJsonProducer;
import com.irfan.mubasyir.rabbitmq.publisher.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class JsonMessageController {
    @Autowired
    private RabbitMQJsonProducer producer;

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {
        producer.sendJsonMessage(user);
        return ResponseEntity.ok("Json Message sent to Rabbit MQ...");
    }
}
