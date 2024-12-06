package com.clinicmanagement.clinic.service;


import com.clinicmanagement.clinic.Entities.payment;
import com.clinicmanagement.clinic.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository _paymentRepository;

    public List<payment> getPaymentsByPatient(int patientId) {
        return _paymentRepository.findByPatientId(patientId);
    }

    public List<payment> searchPayments(Integer patientId, String keyword) {
        return _paymentRepository.searchPayments(patientId, keyword);
    }

    public payment save(payment payment) {
        return _paymentRepository.save(payment);
    }

}
