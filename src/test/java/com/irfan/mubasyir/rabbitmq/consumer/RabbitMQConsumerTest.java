package com.irfan.mubasyir.rabbitmq.consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class RabbitMQConsumerTest {

    @InjectMocks
    private RabbitMQConsumer consumer;

    @Test
    void shouldConsumeMessageWithoutException() {
        assertDoesNotThrow(() -> consumer.consume("Hello World"));
    }

    @Test
    void shouldConsumeEmptyMessage() {
        assertDoesNotThrow(() -> consumer.consume(""));
    }
}
