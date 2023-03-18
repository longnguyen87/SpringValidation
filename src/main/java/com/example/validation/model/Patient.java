package com.example.validation.model;


import com.example.validation.dto.PatientRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "patient")
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="middle_name")
    private String middleName;

    private Integer age;

    private String email;
    @Column(name="blood_type")
    private String bloodType;

    @Column(name="consent_given")
    private Boolean consentGiven;

    @Column(name="preexisting_conditions")
    @ElementCollection
    private List<String> preexistingConditions;

    @Column(name="policy_number")
    private Integer policyNumber;

    @Column(name ="registration_date")
    private LocalDate registrationDate;

    @Column(name="date_of_birth")
    private LocalDate dateofBirth;


    public Patient updatePatient(PatientRequest updatedPatient) {
        setFirstName(updatedPatient.getFirstName());
        setMiddleName(updatedPatient.getMiddleName());
        setLastName(updatedPatient.getLastName());
        setAge(updatedPatient.getAge());
        setEmail(updatedPatient.getEmail());
        setBloodType(updatedPatient.getBloodType());
        setConsentGiven(updatedPatient.getConsentGiven());
        setPreexistingConditions(updatedPatient.getPreexistingConditions());
        setPolicyNumber(updatedPatient.getPolicyNumber());
        setRegistrationDate(updatedPatient.getRegistrationDate());
        setDateofBirth(updatedPatient.getDateofBirth());
        return this;
    }
}
