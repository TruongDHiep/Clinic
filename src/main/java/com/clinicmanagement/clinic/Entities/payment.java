package com.clinicmanagement.clinic.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="payment")
public class payment {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "appointmentId")
    private Appointment appointment;

    private LocalDate paymentDate;
    private double totalAmount;
    private String paymentMethod;
    private String status;
}
