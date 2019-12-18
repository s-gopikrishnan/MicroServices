package io.gtech.kafka.services.impl;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Producer {

	@Autowired
	private KafkaProducer<String, String> mProducer;

	public static Logger logger = LoggerFactory.getLogger(Producer.class);

	public void sendMessage(String topic, String key, String value) throws InterruptedException, ExecutionException {

		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, value);
		mProducer.send(record, (recordMetadata, e) -> {
			if (e != null) {
				logger.error("Error while sending message", e);
			}
			
			logger.info("Received metadata.. \n" +
						"Topic: " + recordMetadata.topic() + "\n" + 
						"Partition: " + recordMetadata.partition() + "\n" + 
						"Offset: " + recordMetadata.offset() + "\n" + 
						"Timestamp: " + recordMetadata.timestamp());
		}).get();
	}

}
