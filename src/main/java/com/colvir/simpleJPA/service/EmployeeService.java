package com.colvir.simpleJPA.service;

import com.colvir.simpleJPA.dto.EmployeeDto;
import com.colvir.simpleJPA.mapper.EmployeeMapper;
import com.colvir.simpleJPA.model.Employee;
import com.colvir.simpleJPA.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }


    public ResponseEntity< List<EmployeeDto>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return ResponseEntity.ok(employees.stream()
                .map(employeeMapper::toDto)
                .toList());
    }

    public Optional<EmployeeDto> getEmployeeById(Integer id) {
        return employeeRepository.findById(id).map(employeeMapper::toDto);
    }

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }



    public EmployeeDto updateEmployee(Integer id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setSalary(employeeDto.getSalary());

        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
}

