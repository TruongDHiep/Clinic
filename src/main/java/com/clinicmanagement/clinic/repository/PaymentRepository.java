package com.clinicmanagement.clinic.repository;

import com.clinicmanagement.clinic.Entities.payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<payment, Integer> {

    @Query("SELECT p FROM payment p JOIN p.appointment a WHERE a.patient.id = :patientId ORDER BY p.paymentDate DESC")
    List<payment> findByPatientId(Integer patientId);

    @Query("SELECT p FROM payment p " +
            "JOIN p.appointment a " +
            "WHERE a.patient.id = :patientId " +
            "AND (CAST(p.id AS string) LIKE %:keyword% " +
            "OR CAST(a.id AS string) LIKE %:keyword% " +
            "OR p.paymentMethod LIKE %:keyword% " +
            "OR p.status LIKE %:keyword%) " +
            "ORDER BY p.paymentDate DESC")
    List<payment> searchPayments(@Param("patientId") Integer patientId, @Param("keyword") String keyword);
}
