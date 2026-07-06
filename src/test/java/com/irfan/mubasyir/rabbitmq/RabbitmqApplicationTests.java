package com.irfan.mubasyir.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class RabbitmqApplicationTests {

	@MockitoBean
	private RabbitTemplate rabbitTemplate;

	@Test
	void contextLoads() {
	}
}
