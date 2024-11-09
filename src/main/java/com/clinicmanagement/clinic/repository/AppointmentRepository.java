package com.clinicmanagement.clinic.repository;

import com.clinicmanagement.clinic.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
