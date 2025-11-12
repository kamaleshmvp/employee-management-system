package com.cts.ems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cts.ems.dto.DepartmentDto;
import com.cts.ems.entity.Department;
import com.cts.ems.exception.ResourceNotFoundException;
import com.cts.ems.mapper.DepartmentMapper;
import com.cts.ems.repository.DepartmentRepository;
import com.cts.ems.service.DepartmentService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor

public class DepartmentServiceImpl implements DepartmentService{
	
	private DepartmentRepository departmentRepository;
	
    @Override
	public DepartmentDto createDepartment(DepartmentDto departmentDto) {
    	
    	 Department department = DepartmentMapper.MapToDepartment(departmentDto);
	     Department savedDepartment =	departmentRepository.save(department);
	     
		    return DepartmentMapper.mapToDepartmentDto(savedDepartment);
	}

	@Override
	public DepartmentDto getDepartmentById(Long departmentId) {
	Department department =	departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Department is not exists with a given id: "+ departmentId)
				
				);
		return DepartmentMapper.mapToDepartmentDto(department);
	}

	@Override
	public List<DepartmentDto> getAllDepartments() {
		
	List<Department> departments = departmentRepository.findAll();
		return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDto(department))
				.collect(Collectors.toList());
	}

	@Override
	public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment) {
		
	Department department =	departmentRepository.findById(departmentId).orElseThrow(
		() -> new ResourceNotFoundException("Deparmtment is not exists with a given id:"+ departmentId)	
		);
	    department.setDepartmentName(updatedDepartment.getDepartmentName());
	    department.setDepartmentDescription(updatedDepartment.getDepartmentDescription());
	    
	    Department savedDepartment = departmentRepository.save(department);
	    
		return DepartmentMapper.mapToDepartmentDto(savedDepartment);
	}

	@Override
	public void deleteDepartment(Long departmentId) {
		
		departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Department is not exists with a given id: "+ departmentId)
				
				);
		departmentRepository.deleteById(departmentId);
		
		
	}

}
