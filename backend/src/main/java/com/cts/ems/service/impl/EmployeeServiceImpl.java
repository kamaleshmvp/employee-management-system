package com.cts.ems.service.impl;

import com.cts.ems.dto.EmployeeDto;
import com.cts.ems.entity.Employee;


import com.cts.ems.exception.ResourceNotFoundException;
import com.cts.ems.mapper.EmployeeMapper;
import com.cts.ems.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.cts.ems.service.EmployeeService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
//	private DepartmentRepository departmentRepository

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		
	/*	Department department = departmentRepository.findById(EmployeeDto.getDepartmentId())
				.orElseThrow(()-> 
				new resourceNotFoundException("Department is not exists with id"+employeeDto.getDepartmentId()));   */
				
		Employee savedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
	 Employee employee =   employeeRepository.findById(employeeId)
	         .orElseThrow(()-> 
	              new ResourceNotFoundException("Employee is not exists with given id: "+ employeeId));
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List <Employee> employees = employeeRepository.findAll();
		return employees.stream().map((employee)-> EmployeeMapper.mapToEmployeeDto(employee))
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
		 Employee employee = employeeRepository.findById(employeeId).orElseThrow(
			()-> new ResourceNotFoundException("Employee is not exists with given id: "+ employeeId)
			);
		    
		 
		 employee.setFirstName(updatedEmployee.getFirstName());
		 employee.setLastName(updatedEmployee.getLastName());
		 employee.setEmail(updatedEmployee.getEmail());
		 
		Employee updatedEmployeeObj = employeeRepository.save(employee);
		 
		 return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		
		Employee employee =   employeeRepository.findById(employeeId)
		         .orElseThrow(()-> 
		              new ResourceNotFoundException("Employee is not exists with given id: "+ employeeId));
		
		employeeRepository.deleteById(employeeId);
	}

}
