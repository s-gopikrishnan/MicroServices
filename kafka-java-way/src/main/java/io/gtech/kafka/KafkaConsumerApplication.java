package io.gtech.kafka;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import io.gtech.kafka.services.impl.Producer;

@SpringBootApplication
public class KafkaConsumerApplication {

	public static Logger logger = LoggerFactory.getLogger(KafkaConsumerApplication.class);

	@Value("${bootstrapServer}")
	private String bootstrapServer;
	
	KafkaProducer<String, String> mProducer;

	public static void main(String[] args) {
		logger.info("before run");
		SpringApplication.run(KafkaConsumerApplication.class, args);
		logger.info("after run");
	}

	private Properties producerProps(String bootstrapServer) {
		String serializer = StringSerializer.class.getName();
		Properties props = new Properties();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, serializer);
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, serializer);

		return props;
	}

	@Bean
	public KafkaProducer<String, String> getKafkaProducer() {
		mProducer = new KafkaProducer<String, String>(producerProps(bootstrapServer));
		logger.info("Producer Initialized");
		return mProducer;
	}
	
	@PreDestroy
	public void closeKafka() {
		logger.info("Closing Producer Connection");
		mProducer.close();
	}

	/*
	 * @Autowired private KafkaTemplate<String, String> template;
	 * 
	 * private final CountDownLatch latch = new CountDownLatch(3);
	 * 
	 * @Override public void run(String... args) throws Exception {
	 * this.template.send("myTopic", "foo1"); this.template.send("myTopic", "foo2");
	 * this.template.send("myTopic", "foo3"); latch.await(60, TimeUnit.SECONDS);
	 * logger.info("All received"); }
	 * 
	 * @KafkaListener(topics = "myTopic") public void listen(ConsumerRecord<?, ?>
	 * cr) throws Exception { logger.info(cr.toString()); latch.countDown(); }
	 */

}
