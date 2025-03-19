package com.sm.mt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.mt.entity.Employee;
import com.sm.mt.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeService {
	

	private EmployeeRepository employeeRepository;
	
	@Autowired
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository=employeeRepository;
	}
	
	public List<Employee> getAllEmployee(){
		return employeeRepository.getAllEmployee();
	}
	
	public Employee getEmployeeById(Integer id) {
		return employeeRepository.getEmployeeById(id);
	}
	
	public int addEmployee(Employee employee) {
		return employeeRepository.addEmployee(employee);
	}
	
	public int updateEmployee(Employee employee) {
		return employeeRepository.updateEmployee(employee);
	}
	
	public int deleteStudentById(Integer id) {
		return employeeRepository.deleteStudentById(id);
	}


}
