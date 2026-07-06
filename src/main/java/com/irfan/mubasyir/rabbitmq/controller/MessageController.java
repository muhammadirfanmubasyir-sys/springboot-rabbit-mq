package com.irfan.mubasyir.rabbitmq.controller;

import com.irfan.mubasyir.rabbitmq.dto.User;
import com.irfan.mubasyir.rabbitmq.publisher.RabbitMQJsonProducer;
import com.irfan.mubasyir.rabbitmq.publisher.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MessageController {

    private final RabbitMQProducer producer;
    private final RabbitMQJsonProducer jsonProducer;

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to Rabbit MQ...");
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {
        jsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Json Message sent to Rabbit MQ...");
    }
}
