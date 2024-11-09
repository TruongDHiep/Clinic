package com.clinicmanagement.clinic.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="useraccount")
public class Useracount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "patientId", nullable = true)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctorId", nullable = true)
    private Doctor doctor;

    private boolean role;
}
