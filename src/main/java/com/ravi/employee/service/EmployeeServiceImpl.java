package com.ravi.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.employee.Exception.InvalidAgeException;
import com.ravi.employee.Exception.ResourceNotFoundException;
import com.ravi.employee.entity.Employee;
import com.ravi.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee save(Employee employee) {

		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public void delete(Long id) {

		employeeRepository.deleteById(id);

	}

	public Employee findById(Long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee  not found with id: " + id));
	}

}
