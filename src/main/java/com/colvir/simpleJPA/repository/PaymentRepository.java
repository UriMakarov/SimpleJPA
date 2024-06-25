package com.colvir.simpleJPA.repository;

import com.colvir.simpleJPA.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findAllByEmployeeId(Integer employeeId);
}
