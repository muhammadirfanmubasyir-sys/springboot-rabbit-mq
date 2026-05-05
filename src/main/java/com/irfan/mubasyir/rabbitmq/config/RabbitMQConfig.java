package com.irfan.mubasyir.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.name}") //created automatically by spring boot
    private String queue;

    @Value("${rabbitmq.json.queue.name}") //created automatically by spring boot
    private String jsonQueue;

    @Value("${rabbitmq.exchange.name}") //created automatically by spring boot
    private String exchange;

    @Value("${rabbitmq.json.exchange.name}") //created automatically by spring boot
    private String jsonExchange;

    @Value("${rabbitmq.routing.key}") //created automatically by spring boot
    private String routingKey;

    @Value("${rabbitmq.json.routing.key}") //created automatically by spring boot
    private String jsonRoutingKey;

    // spring bean for rabbitmq queue
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }

    // spring bean for rabbitmq exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }


    // binding between queue and exchange
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    public Binding jsonBinding() {
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(jsonRoutingKey);
    }

    @Bean
    public MessageConverter converter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());

        return rabbitTemplate;
    }

    // ConnectionFactory
    // RabbitTemplate
    // RabbitAdmin
}
