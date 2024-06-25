package com.colvir.simpleJPA.controller;

import com.colvir.simpleJPA.model.Employee;
import com.colvir.simpleJPA.model.Payment;
import com.colvir.simpleJPA.repository.EmployeeRepository;
import com.colvir.simpleJPA.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/employees/{employeeId}/payments")
    public List<Payment> getAllPaymentsByEmployeeId(@PathVariable Integer employeeId)  {
        if (!employeeRepository.existsById(employeeId)) {
            throw new RuntimeException("Отсутствует сотрудник с ID: " + employeeId);
        }
        return paymentRepository.findAllByEmployeeId(employeeId);
    }

    @GetMapping("/payments/{id}")
    public Payment getById (@PathVariable Integer id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Отсутствует платеж с ID: " + id));
    }

    @PostMapping("/employees/{employeeId}/payments")
    public Payment createPayment (@PathVariable(value = "employeeId") Integer employeeId,
                                                 @RequestBody Payment paymentRequest) {
        Payment payment = employeeRepository.findById(employeeId).map(employee -> {
            paymentRequest.setEmployee(employee);
            return paymentRepository.save(paymentRequest);
        }).orElseThrow(() -> new RuntimeException("Отсутствует сотрудник с ID: " + employeeId));
        return paymentRequest;
    }
    @PutMapping("/payments/{id}")
    public Payment updatePayment(@PathVariable("id") Integer id, @RequestBody Payment paymentForUpdate) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Отсутствует платеж с ID: " + id));

        payment.setId(paymentForUpdate.getId());
        payment.setAmount(paymentForUpdate.getAmount());
        payment.setEmployee(paymentForUpdate.getEmployee());
        payment.setComment(paymentForUpdate.getComment());

        return paymentRepository.save(payment);
    }
    @DeleteMapping("/payments/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Отсутствует платеж с ID: " + id));

        paymentRepository.delete(payment);
        return "Удалён платёж с ID: " + id;
    }
}
