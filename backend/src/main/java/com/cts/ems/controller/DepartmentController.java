package com.cts.ems.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.cts.ems.dto.DepartmentDto;
import com.cts.ems.service.DepartmentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {
	
	private DepartmentService departmentService;
	
	//build add department rest api
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<DepartmentDto> createDepartment( @Valid @RequestBody DepartmentDto departmentDto){
		
		DepartmentDto department =  departmentService.createDepartment(departmentDto);
		return new ResponseEntity<>(department,HttpStatus.CREATED);
		
	}
	//bulid get department rest api
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId){
		
	DepartmentDto departmentDto =	departmentService.getDepartmentById(departmentId);
	return ResponseEntity.ok(departmentDto);
	}
    // get all departments rest api
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
		
		List<DepartmentDto> departments = departmentService.getAllDepartments();
		return ResponseEntity.ok(departments);
		
	}
    //build update department  rest api
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id")Long departmentId, 
			                                              @RequestBody DepartmentDto updatedDepartment){
		
		DepartmentDto departmentDto = departmentService.updateDepartment(departmentId, updatedDepartment);
		return ResponseEntity.ok(departmentDto);
		
	}
	
	//build delete department rest api
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteDepartment(@PathVariable("id")Long departmentId){
		
		departmentService.deleteDepartment(departmentId);
		return ResponseEntity.ok("Department deleted successfully!.");
	}
}

