package com.gk.cca.loadbalancer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gk.cca.loadbalancer.dto.Data;

@RestController
@RequestMapping("/")
public class DefaultController {
	
	private Logger logger = LoggerFactory.getLogger(DefaultController.class);
	
	private Integer num = 0;

	@PostMapping
	public String post(@RequestBody Data data) {
		logger.info("Input: " + data);
		num = data.getNum();
		return "Stored: " + num;
	}
	
	@GetMapping
	public String get() {
		logger.info("GET'ing");
		return num.toString();
	}
}
