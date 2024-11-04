package com.clinicmanagement.clinic.Entities;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Specialization name is required")
    private String specialization;

    @Length(min = 10, max = 10, message = "Phone must be 10 characters")
    @Pattern(regexp = "^[0-9]*$", message = "Phone must be number")
    private String phone;

    @Column(name = "email", length = 50, unique = true)
    @Size(max = 50, message = "Email must be less than 50 characters")
    @Email
    private String email;



}
