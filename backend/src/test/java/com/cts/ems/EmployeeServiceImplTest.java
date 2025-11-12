package com.cts.ems;

import static org.junit.jupiter.api.Assertions.*;

import com.cts.ems.dto.EmployeeDto;
import com.cts.ems.entity.Employee;
import com.cts.ems.exception.ResourceNotFoundException;
import com.cts.ems.mapper.EmployeeMapper;
import com.cts.ems.repository.EmployeeRepository;
import com.cts.ems.service.impl.EmployeeServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
public class EmployeeServiceImplTest {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    private Employee employee;
    private EmployeeDto employeeDto;

    @BeforeEach
    public void setUp() {
        employee = new Employee(1L, "John", "Doe", "john.doe@example.com");
        employeeDto = new EmployeeDto(1L, "John", "Doe", "john.doe@example.com");
    }

    @Test
    public void testCreateEmployee() {
        EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);
        assertNotNull(createdEmployee);
        assertEquals(employeeDto.getFirstName(), createdEmployee.getFirstName());
    }

    @Test
    public void testGetEmployeeById() {
        EmployeeDto foundEmployee = employeeService.getEmployeeById(1L);
        assertNotNull(foundEmployee);
        assertEquals(employeeDto.getFirstName(), foundEmployee.getFirstName());
    }

    @Test
    public void testGetEmployeeById_NotFound() {
        assertThrows(ResourceNotFoundException.class, () -> employeeService.getEmployeeById(999L));
    }

    @Test
    public void testGetAllEmployees() {
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployees();
        assertNotNull(employeeDtos);
        assertFalse(employeeDtos.isEmpty());
    }

    @Test
    public void testUpdateEmployee() {
        EmployeeDto updatedEmployee = employeeService.updateEmployee(1L, employeeDto);
        assertNotNull(updatedEmployee);
        assertEquals(employeeDto.getFirstName(), updatedEmployee.getFirstName());
    }

    @Test
    public void testDeleteEmployee() {
        assertDoesNotThrow(() -> employeeService.deleteEmployee(1L));
    }

    @Test
    public void testDeleteEmployee_NotFound() {
        assertThrows(ResourceNotFoundException.class, () -> employeeService.deleteEmployee(999L));
    }
}