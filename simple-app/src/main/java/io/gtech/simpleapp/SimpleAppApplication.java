package io.gtech.simpleapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleAppApplication {
  private static final Logger LOGGER = LoggerFactory.getLogger(io.gtech.simpleapp.SimpleAppApplication.class);
  
  public static void main(String[] args) {
    SpringApplication.run(io.gtech.simpleapp.SimpleAppApplication.class, args);
    LOGGER.info("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
    LOGGER.info("Started Simple app");
    LOGGER.info("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
  }
}
