package com.colvir.simpleJPA.controller;


import com.colvir.simpleJPA.model.Employee;
import com.colvir.simpleJPA.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAll(){
       return employeeService.getAllEmployees();
    }
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id)
                .orElseThrow(() -> new RuntimeException("Отсутствует сотрудник с ID: " + id));
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Integer id, @RequestBody Employee employeeForUpdate){
        return employeeService.updateEmployee(employeeForUpdate);
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        Employee employee = employeeService.getEmployeeById(id)
                .orElseThrow(() -> new RuntimeException("Отсутствует сотрудник с ID: " + id));

        employeeService.deleteEmployee(id);
        return "Удалён сотрудник с ID: " + id;
    }
}
