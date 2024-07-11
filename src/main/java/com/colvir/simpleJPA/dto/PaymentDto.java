package com.colvir.simpleJPA.dto;

import lombok.Data;

@Data
public class PaymentDto {
    private Integer id;
    private double Amount;
    private String Comment;
    private Integer employeeId;


}
