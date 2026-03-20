package com.ravi.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.employee.entity.Employee;
import com.ravi.employee.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/msg")
	public String getGreetings() {
		return "Hello Guys...!";
	}

	@PostMapping("/newEmployee")
	public Employee create(@RequestBody Employee employee) {
		return employeeService.save(employee);
	}

	@GetMapping("/getAllEmp")
	public List<Employee> getAllEmployee() {
		return employeeService.findAll();
	}
	
	@GetMapping("emp/{id}")
	public Employee getEmpById(@PathVariable Long id)
	{
		return employeeService.findById(id);
	}

	@DeleteMapping("/emp/{id}")
	public String deleteEmp(@PathVariable Long id) {
		employeeService.delete(id);
		return "Employee deleted successfully";
	}

	/*
	 * @PutMapping("/emp/{id}") public String update(@PathVariable Long
	 * id, @RequestBody Employee employee) {
	 * 
	 * employee.setId(id); employeeService.save(employee);
	 * 
	 * return "Employee details Updated"; }
	 */
	
	@PutMapping("/emp/{id}")
	public String update(@PathVariable Long id, @RequestBody Employee employee) {

	    Employee existing = employeeService.findById(id);

	    existing.setFirstName(employee.getFirstName());
	    existing.setLastName(employee.getLastName());
	    existing.setAge(employee.getAge());
	    existing.setPhone(employee.getPhone());

	    employeeService.save(existing);

	    return "Employee details Updated";
	}
}
