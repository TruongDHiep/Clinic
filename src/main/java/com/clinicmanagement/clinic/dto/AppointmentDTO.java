//package com.clinicmanagement.clinic.dto;
//import com.clinicmanagement.clinic.Entities.Appointment;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//public class AppointmentDTO {
//    private Long id;
//    private Long patientId;
//    private Long doctorId;
//    private LocalDate appointmentDate;
//    private LocalTime appointmentTime;
//    private String status;
//
//    public AppointmentDTO() {
//    }
//
//    public AppointmentDTO(Appointment appointment) {
//        this.id = appointment.getId();
//        this.patientId = appointment.getPatient().getId();
//        this.doctorId = appointment.getDoctor().getId();
//        this.appointmentDate = appointment.getAppointmentDate();
//        this.appointmentTime = appointment.getAppointmentTime();
//        this.status = appointment.getStatus();
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//
//    public Long getPatientId() {
//        return patientId;
//    }
//
//    public void setPatientId(Long patientId) {
//        this.patientId = patientId;
//    }
//
//    public Long getDoctorId() {
//        return doctorId;
//    }
//
//    public void setDoctorId(Long doctorId) {
//        this.doctorId = doctorId;
//    }
//
//
//    public LocalDate getAppointmentDate() {
//        return appointmentDate;
//    }
//
//    public void setAppointmentDate(LocalDate appointmentDate) {
//        this.appointmentDate = appointmentDate;
//    }
//
//    public LocalTime getAppointmentTime() {
//        return appointmentTime;
//    }
//
//    public void setAppointmentTime(LocalTime appointmentTime) {
//        this.appointmentTime = appointmentTime;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//
//}
