package com.irfan.mubasyir.rabbitmq.consumer;

import com.irfan.mubasyir.rabbitmq.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMQJsonConsumer {

    @RabbitListener(queues = {"${rabbitmq.json.queue.name}"})
    public void consumeJsonMessage(User user) {
        log.info("Received JSON message -> {}", user);
    }
}
