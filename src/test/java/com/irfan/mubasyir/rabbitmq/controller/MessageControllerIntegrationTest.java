package com.irfan.mubasyir.rabbitmq.controller;

import com.irfan.mubasyir.rabbitmq.dto.User;
import com.irfan.mubasyir.rabbitmq.publisher.RabbitMQJsonProducer;
import com.irfan.mubasyir.rabbitmq.publisher.RabbitMQProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MessageControllerIntegrationTest {

    private MockMvc mockMvc;
    private RabbitMQProducer producer;
    private RabbitMQJsonProducer jsonProducer;

    @BeforeEach
    void setUp() {
        producer = mock(RabbitMQProducer.class);
        jsonProducer = mock(RabbitMQJsonProducer.class);
        MessageController controller = new MessageController(producer, jsonProducer);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldReturnOkWhenPublishingStringMessage() throws Exception {
        mockMvc.perform(get("/api/v1/publish")
                        .param("message", "Hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Message sent to Rabbit MQ..."));

        verify(producer).sendMessage("Hello");
    }

    @Test
    void shouldReturnOkWhenPublishingJsonMessage() throws Exception {
        String userJson = """
                {
                    "id": 1,
                    "firstName": "John",
                    "lastName": "Doe"
                }
                """;

        mockMvc.perform(post("/api/v1/publish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Json Message sent to Rabbit MQ..."));

        verify(jsonProducer).sendJsonMessage(any(User.class));
    }

    @Test
    void shouldReturn400WhenPublishingInvalidJson() throws Exception {
        mockMvc.perform(post("/api/v1/publish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("invalid json"))
                .andExpect(status().isBadRequest());
    }
}
