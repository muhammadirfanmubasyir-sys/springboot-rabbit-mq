package com.irfan.mubasyir.rabbitmq.publisher;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RabbitMQProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private RabbitMQProducer producer;

    @Test
    void shouldSendMessageToCorrectExchangeAndRoutingKey() {
        ReflectionTestUtils.setField(producer, "exchange", "test_exchange");
        ReflectionTestUtils.setField(producer, "routingKey", "test_routing_key");

        producer.sendMessage("Hello World");

        verify(rabbitTemplate).convertAndSend("test_exchange", "test_routing_key", "Hello World");
    }

    @Test
    void shouldSendMessageExactlyOnce() {
        ReflectionTestUtils.setField(producer, "exchange", "test_exchange");
        ReflectionTestUtils.setField(producer, "routingKey", "test_routing_key");

        producer.sendMessage("test");

        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString(), any(Object.class));
    }
}
