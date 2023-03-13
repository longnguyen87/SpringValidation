package com.example.validation.dto;

import com.example.validation.model.Patient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PatientRequest {

    @NotNull
    private String firstName;
    @NotNull
    private String middleName;
    @NotNull
    private String lastName;
    @Min(value = 18, message = "The age should not be lower than {value}")
    @Max(value = 150, message = "The age should not be higher than {value}")
    private Integer age;
    private String email;

    public Patient toEntity(){
        return Patient.builder()
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .age(age)
                .email(email)
                .build();
    }
}
