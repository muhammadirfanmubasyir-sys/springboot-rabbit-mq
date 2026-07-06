package com.irfan.mubasyir.rabbitmq.consumer;

import com.irfan.mubasyir.rabbitmq.dto.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class RabbitMQJsonConsumerTest {

    @InjectMocks
    private RabbitMQJsonConsumer jsonConsumer;

    @Test
    void shouldConsumeJsonMessageWithoutException() {
        User user = new User(1, "John", "Doe");
        assertDoesNotThrow(() -> jsonConsumer.consumeJsonMessage(user));
    }

    @Test
    void shouldConsumeJsonMessageWithNullFields() {
        User user = new User();
        assertDoesNotThrow(() -> jsonConsumer.consumeJsonMessage(user));
    }
}
