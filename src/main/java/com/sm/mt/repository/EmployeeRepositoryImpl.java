package com.sm.mt.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sm.mt.entity.Employee;


@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	public EmployeeRepositoryImpl(@Qualifier("multiTenantDataSource") DataSource multiTenantDataSource) {
		this.jdbcTemplate=new JdbcTemplate(multiTenantDataSource);
	}

	@Override
	public int addEmployee(Employee employee) {

		
		String insertQuery="INSERT INTO employee (employee_name, contact_number, employee_email_id, gender, child_dept_id) VALUES (?, ?, ?, ?, ?)";
		return jdbcTemplate.update(insertQuery, employee.getEmployeeName(),employee.getContactNumber(),employee.getEmployeeEmailId(),employee.getGender(),
				employee.getChildDeptId());
	}

	@Override
	public List<Employee> getAllEmployee() {
		String selectAllQuery ="select * from employee";
		return jdbcTemplate.query(selectAllQuery, new BeanPropertyRowMapper<Employee>(Employee.class));
	}

	@Override
	public Employee getEmployeeById(int id) {
		String selectByIdQuery = "select * from employee where employee_id =?";
		Employee employee=jdbcTemplate.queryForObject(selectByIdQuery, BeanPropertyRowMapper.newInstance(Employee.class),id);
		return employee;
	}

	@Override
	public int updateEmployee(Employee employee) {
		String updateQuery="update employee set employee_name=? , contact_number=?, employee_email_id=?, gender=? where employee_id=?";
		return jdbcTemplate.update(updateQuery,employee.getEmployeeName(),employee.getContactNumber(),employee.getEmployeeEmailId(),employee.getGender(),employee.getEmployeeId());
	}

	@Override
	public int deleteStudentById(int id) {
		String deleteQuery="delete from employee where employee_id=?";
		return jdbcTemplate.update(deleteQuery,id);
	}

}
