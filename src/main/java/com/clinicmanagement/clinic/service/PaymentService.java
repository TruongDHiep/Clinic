package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.payment;
import com.clinicmanagement.clinic.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public payment createPayment(payment payment) {
        payment.setPaymentDate(LocalDate.now());
        payment.setStatus("Pending");
        return paymentRepository.save(payment);
    }

    public Optional<payment> getPaymentById(Integer id) {
        return paymentRepository.findById(id);
    }

    public payment updatePaymentStatus(Integer id, String status) {
        Optional<payment> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isPresent()) {
            payment payment = paymentOptional.get();
            payment.setStatus(status);
            return paymentRepository.save(payment);
        }
        throw new IllegalArgumentException("Payment not found with ID: " + id);
    }
}
