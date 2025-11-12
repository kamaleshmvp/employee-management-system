package com.cts.ems.mapper;

import com.cts.ems.entity.Employee;
import com.cts.ems.dto.EmployeeDto;

public class EmployeeMapper {
	
	public static EmployeeDto mapToEmployeeDto(Employee employee) {
		return new EmployeeDto(
				employee.getId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getEmail() 
				);
		
	}
	public static Employee mapToEmployee(EmployeeDto employeeDto) {

		return new Employee(
				employeeDto.getFirstName(),
				employeeDto.getLastName(),
				employeeDto.getEmail()
				);
	}

}
