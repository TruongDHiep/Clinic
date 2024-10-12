package com.clinicmanagement.clinic.model;


import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "doctor")
public class Doctor {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FullName", nullable = false, length = 255)
    private String fullName;

    @Column(name = "Specialization", nullable = false, length = 255)
    private String specialization;

    @Column(name = "Phone", length = 15)
    private String phone;

    @Column(name = "Email", length = 255)
    private String email;

    // Constructors
    public Doctor() {}

    public Doctor(String fullName, String specialization, String phone, String email) {
        this.fullName = fullName;
        this.specialization = specialization;
        this.phone = phone;
        this.email = email;
    }

    // Getters and Setters

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
