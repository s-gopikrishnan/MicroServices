package io.gtech.kafkaconsumer.models;

public class Employee {

	String name;
	String dept;
	Long salary;
	String location;
	public Employee() {}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", dept=" + dept + ", salary=" + salary + ", location=" + location + "]";
	}
	
	
}
