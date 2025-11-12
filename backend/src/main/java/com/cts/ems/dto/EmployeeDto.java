package com.cts.ems.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

	private Long id;
	
	@NotEmpty(message = "Department name cannot be empty")
	private String firstName;
	
	@NotEmpty(message = "Department name cannot be empty")
	private String lastName;
	
	@Email(message = "Email should be valid") 
	private String email;
	
//	private Long departmentId;
}
