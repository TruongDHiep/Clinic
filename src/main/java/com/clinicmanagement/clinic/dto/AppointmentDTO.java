package com.clinicmanagement.clinic.dto;
import com.clinicmanagement.clinic.Entities.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDTO {



    private int _id;



    private LocalDate _appointmentDate;
    private LocalTime _appointmentTime;
    private String _status;


    //private Long patientId;
    //private Long doctorId;
    public AppointmentDTO() {
    }

    public AppointmentDTO(int id, LocalDate appointmentDate, LocalTime appointmentTime, String status) {
        this._id = id;
        this._appointmentDate = appointmentDate;
        this._appointmentTime = appointmentTime;
        this._status = status;

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public LocalTime get_appointmentTime() {
        return _appointmentTime;
    }

    public void set_appointmentTime(LocalTime _appointmentTime) {
        this._appointmentTime = _appointmentTime;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }

    public LocalDate get_appointmentDate() {
        return _appointmentDate;
    }

    public void set_appointmentDate(LocalDate _appointmentDate) {
        this._appointmentDate = _appointmentDate;
    }
}
