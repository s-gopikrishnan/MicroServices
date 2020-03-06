package io.gtech.empmanager.empmanager.repositories;

import org.springframework.data.repository.CrudRepository;

import io.gtech.empmanager.empmanager.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	Employee findByName(String name);
}
