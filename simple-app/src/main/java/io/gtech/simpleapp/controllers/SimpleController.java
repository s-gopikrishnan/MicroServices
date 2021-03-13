package io.gtech.simpleapp.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SimpleController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleController.class);
	
	@Value("${app.customProperty}")
	private String customProperty;

	@GetMapping()
	public String sayHello() {
		LOGGER.info("Saying Hello");
		System.out.println("Saying Hello");
		return "Hello World!";
	}
	
	@GetMapping("/custom")
	public String getCustomProperty() {
		LOGGER.info("Custom: " + customProperty);
		return customProperty;
	}
	
	@GetMapping("/hostname")
	public String getHostName() throws UnknownHostException {
		String hostname = InetAddress.getLocalHost().getHostName();
		LOGGER.info(" Host: " + hostname);
		return hostname;
	}

	@GetMapping("/whatsup/{user}")
	public String whatsup(@PathVariable String user) {
		LOGGER.info("Whats up " + user + "!!!");
		System.out.println("Whats up " + user + "!!!");
		return "Whats up " + user + "!!!";
	}
}
