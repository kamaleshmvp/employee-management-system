package com.cts.ems.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
	
	private Long id;
	
	@NotEmpty(message = "Department name cannot be empty")
	private String departmentName;
	 
	@NotEmpty(message = "Department name cannot be empty")
	private String departmentDescription;
	

}
