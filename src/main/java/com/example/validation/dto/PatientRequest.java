package com.example.validation.dto;

import com.example.validation.customvalidation.ValidAddress;
import com.example.validation.model.Patient;
import com.example.validation.validationgroup.OnCreate;
import com.example.validation.validationgroup.OnUpdate;
import jakarta.validation.constraints.*;
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
    @Email
    private String email;
    @AssertTrue(groups = OnCreate.class)
    @NotNull(groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
    private Boolean consentGiven;

    @Size(min=0, max= 10)
    private List<String> preexistingConditions;
    @PositiveOrZero
    private Integer policyNumber;

    @FutureOrPresent
    private LocalDate registrationDate;

    @Past
    private LocalDate dateofBirth;

    @Pattern(regexp = "(A|B|AB|O)[+-]")
    private String bloodType;

    @ValidAddress(value=StateFormat.ISO)
    private Address address;

    public Patient toEntity(){
        return Patient.builder()
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .age(age)
                .email(email)
                .bloodType(bloodType)
                .consentGiven(consentGiven)
                .preexistingConditions(preexistingConditions)
                .policyNumber(policyNumber)
                .registrationDate(registrationDate)
                .dateofBirth(dateofBirth)
                .build();
    }
}
