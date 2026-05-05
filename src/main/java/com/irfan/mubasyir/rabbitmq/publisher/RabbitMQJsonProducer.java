package com.irfan.mubasyir.rabbitmq.publisher;

import com.irfan.mubasyir.rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    @Value("${rabbitmq.exchange.name}") //created automatically by spring boot
    private String exchange;

    @Value("${rabbitmq.json.routing.key}") //created automatically by spring boot
    private String jsonRoutingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendJsonMessage(User user) {
        LOGGER.info(String.format("Json Message sent => %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, user);
    }
}
