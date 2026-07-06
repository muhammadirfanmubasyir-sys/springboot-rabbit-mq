package com.irfan.mubasyir.rabbitmq.publisher;

import com.irfan.mubasyir.rabbitmq.dto.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RabbitMQJsonProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private RabbitMQJsonProducer jsonProducer;

    @Test
    void shouldSendJsonMessageToCorrectExchangeAndRoutingKey() {
        ReflectionTestUtils.setField(jsonProducer, "exchange", "json_exchange");
        ReflectionTestUtils.setField(jsonProducer, "routingKey", "json_routing_key");

        User user = new User(1, "John", "Doe");
        jsonProducer.sendJsonMessage(user);

        verify(rabbitTemplate).convertAndSend("json_exchange", "json_routing_key", user);
    }

    @Test
    void shouldSendJsonMessageExactlyOnce() {
        ReflectionTestUtils.setField(jsonProducer, "exchange", "json_exchange");
        ReflectionTestUtils.setField(jsonProducer, "routingKey", "json_routing_key");

        User user = new User(1, "John", "Doe");
        jsonProducer.sendJsonMessage(user);

        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString(), any(Object.class));
    }
}
