package io.gtech.kafkaproducer.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import io.gtech.kafkaproducer.models.Employee;

@Configuration
public class KafkaProducerConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerConfig.class);
	
	@Value("${kafkaServer}")
	private String kafkaServer;
	
	@Bean
	public ProducerFactory<String, Employee> getProducerFactory() {
		Map<String, Object> config = new HashMap<String, Object>();
		
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<String, Employee>(config);
	}

	@Bean
	public KafkaTemplate<String, Employee> getKafkaTemplate() {
		return new KafkaTemplate<String, Employee>(getProducerFactory());
	}
	
	@PreDestroy
	public void closeConnection() {
		LOGGER.info("Close Connection");
	}
}
