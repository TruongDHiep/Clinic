package com.clinicmanagement.clinic.service;

import com.clinicmanagement.clinic.Entities.Appointment;
import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.Entities.Patient;
import com.clinicmanagement.clinic.dto.AppointmentDTO;
import com.clinicmanagement.clinic.repository.AppointmentRepository;
import com.clinicmanagement.clinic.repository.DoctorRepository;
import com.clinicmanagement.clinic.repository.PatientRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;


import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository _appointmentRepository;

    @Autowired
    private PatientRepository _patientRepository;

    @Autowired
    private DoctorRepository _doctorRepository;

    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    public List<Appointment> GetAllUsers() {
        return _appointmentRepository.findAll();
    }

    public Appointment GetAppointmentById(int id) {
        return _appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found appointment by id: " + id));
    }

    public Appointment AddAppointment(AppointmentDTO appointmentDTO) {
        System.out.println(appointmentDTO.get_status());

        Patient patient = _patientRepository.findById(appointmentDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = _doctorRepository.findById(appointmentDTO.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDTO.get_appointmentDate());
        appointment.setAppointmentTime(appointmentDTO.get_appointmentTime());
        appointment.setStatus(appointmentDTO.get_status());

        _appointmentRepository.save(appointment);
        return appointment;
    }

    public Appointment UpdateAppointment(AppointmentDTO appointmentDTO) {

        logger.info("Updating appointment with ID: " + appointmentDTO.get_id());

        try {
            if(appointmentDTO.get_id() <= 0) {
                throw new RuntimeException("Invalid Appointment ID" + appointmentDTO.get_id());
            }

            Appointment existingAppointment = _appointmentRepository.findById(appointmentDTO.get_id())
                    .orElseThrow(() -> new RuntimeException("Not Found Appointment"));

            if(appointmentDTO.getPatientId() == null || appointmentDTO.getPatientId() <= 0) {
                throw new RuntimeException("Invalid Patient ID");
            }

            Patient existingPatient = _patientRepository.findById(appointmentDTO.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Not Found Patient"));

            if(appointmentDTO.getDoctorId() == null || appointmentDTO.getDoctorId() <= 0) {
                throw new RuntimeException("Invalid Doctor ID");
            }
            Doctor existingDoctor = _doctorRepository.findById(appointmentDTO.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Not Found Doctor"));

            if (appointmentDTO.get_appointmentDate() == null) {
                throw new RuntimeException("Appointment date cannot be null");
            }

            if (appointmentDTO.get_appointmentTime() == null) {
                throw new RuntimeException("Appointment time cannot be null");
            }

            existingAppointment.setPatient(existingPatient);
            existingAppointment.setDoctor(existingDoctor);
            existingAppointment.setAppointmentDate(appointmentDTO.get_appointmentDate());
            existingAppointment.setAppointmentTime(appointmentDTO.get_appointmentTime());
            existingAppointment.setStatus(appointmentDTO.get_status());

            _appointmentRepository.save(existingAppointment);
            return existingAppointment;
        } catch (Exception e) {
            logger.error("Error: " + e);
            throw e;
        }
    }

    public String DeleteAppointment(int id) {
        Appointment appointment = _appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found Appointment"));

        _appointmentRepository.delete(appointment);
        return "Delete success with Id: " + id;
    }


    public List<Appointment> GetAppointmentByDocTorId(int id) {
        return _appointmentRepository.GetAppointmentByDocTorId(id);
    }
}
