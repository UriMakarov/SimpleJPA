package com.colvir.simpleJPA.mapper;

import com.colvir.simpleJPA.dto.EmployeeDto;
import com.colvir.simpleJPA.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    EmployeeDto toDtoWithPayments(Employee employee);

    Employee toEntity(EmployeeDto employeeDto);
}
