package com.clinicmanagement.clinic.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter

public class AppointmentDTO {
    @JsonProperty("id")
    private int _id;

    @JsonProperty("appointmentDate")
    private LocalDate _appointmentDate;

    @JsonProperty("appointmentTime")
    private LocalTime _appointmentTime;

    @JsonProperty("status")
    private String _status;

    private Integer patientId;

    private Integer doctorId;
    public AppointmentDTO() {
    }

    public AppointmentDTO(int id, LocalDate appointmentDate, LocalTime appointmentTime, String status) {
        this._id = id;
        this._appointmentDate = appointmentDate;
        this._appointmentTime = appointmentTime;
        this._status = status;

    }
}
