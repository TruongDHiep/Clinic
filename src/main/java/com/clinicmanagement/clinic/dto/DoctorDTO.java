package com.clinicmanagement.clinic.dto;

public class DoctorDTO {
    private Integer id;
    private String fullName;
    private String specialization;
    private String phone;
    private String email;

    // Constructors
    public DoctorDTO() {}

    public DoctorDTO(Integer id, String fullName, String specialization, String phone, String email) {
        this.id = id;
        this.fullName = fullName;
        this.specialization = specialization;
        this.phone = phone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
