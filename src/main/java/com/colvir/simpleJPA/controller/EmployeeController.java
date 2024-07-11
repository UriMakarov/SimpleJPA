package com.colvir.simpleJPA.controller;


import com.colvir.simpleJPA.dto.EmployeeDto;
import com.colvir.simpleJPA.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll (){
       return ResponseEntity.ok(employeeService.getAllEmployees().getBody());
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id).map(ResponseEntity::ok).orElseThrow(() -> new RuntimeException("Отсутствует сотрудник с ID: " + id));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employeeDto){
        return ResponseEntity.ok(employeeService.createEmployee(employeeDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable Integer id, @RequestBody EmployeeDto employeeForUpdate){
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeForUpdate));
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return "Удалён сотрудник с ID: " + id;
    }
}
