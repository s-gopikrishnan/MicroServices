package io.gtech.kafkaconsumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import io.gtech.kafkaconsumer.models.Employee;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
	
	@Value("${kafkaServer}")
	private String kafkaServer;
	
	@Bean
	public ConsumerFactory<String, Employee> employeeConsumerFactory() {
		
		JsonDeserializer<Employee> jsonDeserializer = new JsonDeserializer<>(Employee.class);
		jsonDeserializer.setRemoveTypeHeaders(false);
		jsonDeserializer.addTrustedPackages("*");
		jsonDeserializer.setUseTypeMapperForKey(true);

		
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "emp_group");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	    config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
				jsonDeserializer);
	}

	@Bean
	@ConditionalOnMissingBean(name = "kafkaListenerContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<String, Employee> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Employee> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(employeeConsumerFactory());
		return factory;
	}

}
