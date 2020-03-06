package io.gtech.empmanager.empmanager.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.gtech.empmanager.empmanager.model.Employee;
import io.gtech.empmanager.empmanager.repositories.EmployeeRepository;

@RestController
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeRepository repository;

	@RequestMapping(method = { RequestMethod.GET }, value = { "/hello" })
	public String sayHello() {
		LOGGER.info("Saying Hello");
		System.out.println("Saying Hello");
		return "Hello World!";
	}

	@RequestMapping(method = { RequestMethod.GET }, value = { "/getEmployees" })
	public List<Employee> getAll() {
		LOGGER.info("Getting all Employees");
		Iterable<Employee> iter = repository.findAll();
		List<Employee> retList = new ArrayList<Employee>();
		iter.forEach(retList::add);
		LOGGER.info("Retrieved count of employees:" + retList.size());
		return retList;
	}
	
	@RequestMapping(method = { RequestMethod.POST }, value = { "/createEmployee" })
	public String createEmployee(@RequestBody Employee emp) {
		LOGGER.info("Creating Employee: " + emp);
		repository.save(emp);
		return "Created employee";
	}
	
	
}
