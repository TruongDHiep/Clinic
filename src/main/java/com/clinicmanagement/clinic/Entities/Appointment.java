package com.clinicmanagement.clinic.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="appontment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String status;



}
