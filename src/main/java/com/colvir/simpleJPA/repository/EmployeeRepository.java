package com.colvir.simpleJPA.repository;

import com.colvir.simpleJPA.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer> {
}
