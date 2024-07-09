package com.colvir.simpleJPA.dto;

import lombok.Data;

import java.util.List;
@Data
public class EmployeeDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String department;
    private double salary;
    private List<PaymentDto> payments;


}
