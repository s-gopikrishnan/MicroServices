package io.gtech.kafkaconsumer.listeners;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import io.gtech.kafkaconsumer.models.Employee;

@Service
public class KafkaConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
	
	@KafkaListener(topics = "emp_topic", groupId = "emp_group", containerFactory = "kafkaListenerContainerFactory")
	public void consumeJson(@Payload Employee msg, @Headers MessageHeaders headers) throws IOException {
		LOGGER.info("Consumed JSON Message: " + msg);
	}
	
	
}
