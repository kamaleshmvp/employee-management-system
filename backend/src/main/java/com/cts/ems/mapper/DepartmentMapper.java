package com.cts.ems.mapper;

import com.cts.ems.dto.DepartmentDto;
import com.cts.ems.entity.Department;

public class DepartmentMapper {
	
	//convert departmnet jpa entity into department dto 
	public static DepartmentDto mapToDepartmentDto(Department department) {
		   
		return new DepartmentDto(
				department.getId(),
				department.getDepartmentName(),
				department.getDepartmentDescription()
				
				);
		
	}
	//convert departmnet dto into jpa entity 
	public static Department MapToDepartment(DepartmentDto departmentDto) {
		return new Department(
				departmentDto.getId(),
				departmentDto.getDepartmentName(),
				departmentDto.getDepartmentDescription()
				);
		
	}

}
