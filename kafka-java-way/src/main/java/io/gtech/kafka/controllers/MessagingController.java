package io.gtech.kafka.controllers;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.gtech.kafka.dto.KafkaMessage;
import io.gtech.kafka.services.impl.Producer;

@RestController
public class MessagingController {
	
	@Autowired
	Producer producer;
	
	@RequestMapping(method = RequestMethod.POST, value = "/sendMsg")
	public void sendMessage(@RequestBody KafkaMessage msg) throws InterruptedException, ExecutionException {
		producer.sendMessage(msg.getTopic(), msg.getKey(), msg.getValue());
	}

}
