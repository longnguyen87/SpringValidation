package com.example.validation.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConstrainViolation {

    private String fieldName;
    private String message;
    private String rejectedValue;
}
