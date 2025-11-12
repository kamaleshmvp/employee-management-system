package com.cts.ems.service;

import java.util.List;

import com.cts.ems.dto.DepartmentDto;

public interface DepartmentService {
   public  DepartmentDto createDepartment(DepartmentDto departmentDto);
	
   DepartmentDto getDepartmentById(Long departmentId);
   
   List<DepartmentDto> getAllDepartments();
   
   DepartmentDto updateDepartment(Long departmentId , DepartmentDto updatedDepartment);
   
   void deleteDepartment(Long departmentId);
}