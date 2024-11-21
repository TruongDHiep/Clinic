package com.clinicmanagement.clinic.repository;

import com.clinicmanagement.clinic.Entities.payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<payment, Integer> {


}