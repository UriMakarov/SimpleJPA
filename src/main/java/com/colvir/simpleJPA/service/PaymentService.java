package com.colvir.simpleJPA.service;

import com.colvir.simpleJPA.dto.PaymentDto;
import com.colvir.simpleJPA.mapper.PaymentMapper;
import com.colvir.simpleJPA.model.Payment;
import com.colvir.simpleJPA.repository.EmployeeRepository;
import com.colvir.simpleJPA.repository.PaymentRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final EmployeeRepository employeeRepository;
    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentRepository paymentRepository, EmployeeRepository employeeRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.employeeRepository = employeeRepository;
        this.paymentMapper = paymentMapper;
    }

    public List<PaymentDto> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(paymentMapper::toDto)
                .toList();
    }

    public PaymentDto getPaymentById(Integer id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        return paymentMapper.toDto(payment);
    }
    public List<PaymentDto> getAllPaymentsByEmployeeId(Integer employeeId) {
        List<Payment> payments = paymentRepository.findAllByEmployeeId(employeeId);
        if (!employeeRepository.existsById(employeeId)) {
            throw new RuntimeException("Отсутствует сотрудник с ID: " + employeeId);
        }
        return payments.stream()
                .map(paymentMapper::toDto)
                .toList();
    }
}
