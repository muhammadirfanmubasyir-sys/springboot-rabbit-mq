package com.irfan.mubasyir.rabbitmq.controller;

import com.irfan.mubasyir.rabbitmq.dto.User;
import com.irfan.mubasyir.rabbitmq.publisher.RabbitMQJsonProducer;
import com.irfan.mubasyir.rabbitmq.publisher.RabbitMQProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageControllerTest {

    @Mock
    private RabbitMQProducer producer;

    @Mock
    private RabbitMQJsonProducer jsonProducer;

    @InjectMocks
    private MessageController controller;

    @Test
    void shouldSendMessageAndReturnOkResponse() {
        ResponseEntity<String> response = controller.sendMessage("Hello");

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Message sent to Rabbit MQ...", response.getBody());
        verify(producer).sendMessage("Hello");
    }

    @Test
    void shouldSendJsonMessageAndReturnOkResponse() {
        User user = new User(1, "John", "Doe");

        ResponseEntity<String> response = controller.sendJsonMessage(user);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Json Message sent to Rabbit MQ...", response.getBody());
        verify(jsonProducer).sendJsonMessage(user);
    }
}
