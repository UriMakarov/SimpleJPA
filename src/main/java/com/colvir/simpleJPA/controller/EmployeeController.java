package com.colvir.simpleJPA.controller;


import com.colvir.simpleJPA.model.Employee;
import com.colvir.simpleJPA.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAll(){
       return employeeRepository.findAll();
    }
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Integer id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Отсутствует сотрудник с ID: " + id));
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }


    @PutMapping("/{id}")
    public Employee update(@PathVariable Integer id, @RequestBody Employee employeeForUpdate){
        Employee employee =  employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Отсутствует сотрудник с ID: " + id));

        employee.setFirstName(employeeForUpdate.getFirstName());
        employee.setLastName(employeeForUpdate.getLastName());
        employee.setDepartment(employeeForUpdate.getDepartment());
        employee.setSalary(employeeForUpdate.getSalary());

        return employeeRepository.save(employee);
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Отсутствует сотрудник с ID: " + id));

        employeeRepository.delete(employee);
        return "Удалён сотрудник с ID: " + id;
    }
}
