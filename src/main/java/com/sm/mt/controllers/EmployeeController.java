package com.sm.mt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sm.mt.entity.Employee;
import com.sm.mt.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService=employeeService;
	}
	
	

	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable int id) {
		return employeeService.getEmployeeById(id);
	}
	
	@GetMapping("/employees")
	public List<Employee> getEmployeeById() {
		return employeeService.getAllEmployee();
	}
	
	@PostMapping("/employee")
	public int saveEmployee(Employee employee) {
		return employeeService.addEmployee(employee);
	}
	
	@PutMapping("/employee")
	public int updateEmployee(Employee employee) {
		return employeeService.updateEmployee(employee);
	}
	
	@DeleteMapping("/employee/{id}")
	public int deleteEmployeeByI(@PathVariable int id) {
		return employeeService.deleteStudentById(id);
	}
}
