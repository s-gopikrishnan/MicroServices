package io.gtech.simpleapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
  private static final Logger LOGGER = LoggerFactory.getLogger(io.gtech.simpleapp.controllers.SimpleController.class);
  
  @RequestMapping(method = {RequestMethod.GET}, value = {"/hello"})
  public String sayHello() {
    LOGGER.info("Saying Hello");
    return "Hello World!";
  }
  
  @RequestMapping(method = {RequestMethod.GET}, value = {"/whatsup/{user}"})
  public String whatsup(@PathVariable String user) {
    LOGGER.info("Whats up " + user + "!!!");
    return "Whats up " + user + "!!!";
  }
}
