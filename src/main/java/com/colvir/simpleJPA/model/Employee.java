package com.colvir.simpleJPA.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "employees")
@Entity
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
    @SequenceGenerator(name = "emp_seq", sequenceName = "employee_seq", allocationSize = 1)
    private Integer id;
    private String firstName;
    private String lastName;
    private double salary;
    private String department;
}

