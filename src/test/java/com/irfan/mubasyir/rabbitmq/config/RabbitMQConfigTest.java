package com.irfan.mubasyir.rabbitmq.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class RabbitMQConfigTest {

    @InjectMocks
    private RabbitMQConfig config;

    @Test
    void shouldCreateQueue() {
        ReflectionTestUtils.setField(config, "queue", "test_queue");
        Queue queue = config.queue();
        assertEquals("test_queue", queue.getName());
    }

    @Test
    void shouldCreateJsonQueue() {
        ReflectionTestUtils.setField(config, "jsonQueue", "test_json_queue");
        Queue jsonQueue = config.jsonQueue();
        assertEquals("test_json_queue", jsonQueue.getName());
    }

    @Test
    void shouldCreateExchange() {
        ReflectionTestUtils.setField(config, "exchange", "test_exchange");
        TopicExchange exchange = config.exchange();
        assertEquals("test_exchange", exchange.getName());
    }

    @Test
    void shouldCreateBinding() {
        ReflectionTestUtils.setField(config, "queue", "test_queue");
        ReflectionTestUtils.setField(config, "exchange", "test_exchange");
        ReflectionTestUtils.setField(config, "routingKey", "test_routing_key");

        Binding binding = config.binding();
        assertNotNull(binding);
    }

    @Test
    void shouldCreateJsonBinding() {
        ReflectionTestUtils.setField(config, "jsonQueue", "test_json_queue");
        ReflectionTestUtils.setField(config, "exchange", "test_exchange");
        ReflectionTestUtils.setField(config, "jsonRoutingKey", "test_json_routing_key");

        Binding binding = config.jsonBinding();
        assertNotNull(binding);
    }

    @Test
    void shouldCreateJsonMessageConverter() {
        MessageConverter converter = config.converter();
        assertInstanceOf(JacksonJsonMessageConverter.class, converter);
    }

    @Test
    void shouldCreateAmqpTemplateWithJsonConverter() {
        ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
        AmqpTemplate template = config.amqpTemplate(connectionFactory);

        assertInstanceOf(RabbitTemplate.class, template);
    }
}
