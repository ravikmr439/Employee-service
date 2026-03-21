package com.ravi.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.employee.Exception.InvalidAgeException;
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
	public ResponseEntity<Employee> create(@RequestBody Employee employee) {

		if (employee.getAge() <= 0) {
			throw new InvalidAgeException("Please Enter the valid Age ");
		}

		Employee created = employeeService.save(employee);

		return ResponseEntity.ok(created);
	}

	@GetMapping("/getAllEmp")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> getallEmp = employeeService.findAll();
		return ResponseEntity.ok(getallEmp);
	}

	@GetMapping("/emp/{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable Long id) {
		Employee employee = employeeService.findById(id);

		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(employee);
	}

	@DeleteMapping("/emp/{id}")
	public ResponseEntity<String> deleteEmp(@PathVariable Long id) {
		employeeService.delete(id);
		return ResponseEntity.ok("Employee deleted successfully");
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
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Employee employee) {

		Employee existing = employeeService.findById(id);

		existing.setFirstName(employee.getFirstName());
		existing.setLastName(employee.getLastName());
		existing.setAge(employee.getAge());
		existing.setPhone(employee.getPhone());

		employeeService.save(existing);

		return ResponseEntity.ok("Employee details Updated");
	}
}
