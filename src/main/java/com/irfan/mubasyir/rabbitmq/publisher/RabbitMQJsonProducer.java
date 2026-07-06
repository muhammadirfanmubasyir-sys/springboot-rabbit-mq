package com.irfan.mubasyir.rabbitmq.publisher;

import com.irfan.mubasyir.rabbitmq.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitMQJsonProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.json.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.json.routing.key}")
    private String routingKey;

    public void sendJsonMessage(User user) {
        log.info("Json Message sent => {}", user);
        rabbitTemplate.convertAndSend(exchange, routingKey, user);
    }
}
