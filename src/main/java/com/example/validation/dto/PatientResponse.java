package com.example.validation.dto;

import com.example.validation.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PatientResponse {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer age;
    private String email;
    private String bloodType;
    private Boolean consentGiven;
    private List<String> preexistingConditions;
    private Integer policyNumber;
    private LocalDate registrationDate;
    private LocalDate dateOfBirth;
    public static PatientResponse fromEntity (Patient patient){
        return PatientResponse.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .middleName(patient.getMiddleName())
                .age(patient.getAge())
                .email(patient.getEmail())
                .bloodType(patient.getBloodType())
                .consentGiven(patient.getConsentGiven())
                .policyNumber(patient.getPolicyNumber())
                .registrationDate(patient.getRegistrationDate())
                .dateOfBirth(patient.getDateofBirth())
                .build();
    }
}
