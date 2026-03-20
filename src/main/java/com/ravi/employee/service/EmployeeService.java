package com.ravi.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ravi.employee.entity.Employee;

@Service
public interface EmployeeService {

	Employee save(Employee employee);

	List<Employee> findAll();

	void delete(Long id);

	Employee findById(Long id);

}
