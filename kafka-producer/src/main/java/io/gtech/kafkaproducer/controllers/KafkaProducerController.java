package io.gtech.kafkaproducer.controllers;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.gtech.kafkaproducer.models.Employee;

@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerController.class);
	
	@Autowired
	private KafkaTemplate<String, Employee> kafka;
	
	@Value("${employeeTopic}")
	private String topic;
	
	@RequestMapping(method = RequestMethod.POST, value = "/sendEmployee")
	public String sendEmployee(@RequestBody Employee employee) throws InterruptedException, ExecutionException {
		
		LOGGER.info("Sending employee details to Kafka: \n" + employee);
		RecordMetadata recordMetadata = kafka.send(topic, employee).get().getRecordMetadata();
		
		/*
		Message<Employee> msg = MessageBuilder.withPayload(employee)
									.setHeader(KafkaHeaders.TOPIC, topic)
									.build();
		kafka.send(msg);
		 */
		String kafkaReceipt = "Received metadata.. \n" +
				"Topic: " + recordMetadata.topic() + "\n" + 
				"Partition: " + recordMetadata.partition() + "\n" + 
				"Offset: " + recordMetadata.offset() + "\n" + 
				"Timestamp: " + recordMetadata.timestamp();
		LOGGER.info(kafkaReceipt);
		
		return kafkaReceipt;
	}

}
