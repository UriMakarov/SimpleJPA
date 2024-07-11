package com.colvir.simpleJPA.mapper;

import com.colvir.simpleJPA.dto.PaymentDto;
import com.colvir.simpleJPA.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "employeeId", source = "employee.id")
    PaymentDto toDto(Payment payment);

    PaymentDto toDtoWithEmployee(Payment payment);

    Payment toEntity(PaymentDto paymentDto);

}
