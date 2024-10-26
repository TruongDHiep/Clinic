package com.clinicmanagement.clinic.dto;

import java.time.LocalDate;

public class PatientDTO {

    private int id;
    private String fullName;
    private LocalDate dob;
    private String address;
    private String email;
    private String phone;

    // Constructors, getters and setters
    public PatientDTO() {
    }

    public PatientDTO(int id, String fullName, LocalDate dob, String address, String email, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.dob = dob;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}


