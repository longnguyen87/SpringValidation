package com.example.validation.controller;

import com.example.validation.dto.PatientRequest;
import com.example.validation.dto.PatientResponse;
import com.example.validation.service.PatientsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private PatientsService patientsService;

    public PatientController(PatientsService patientsService) {
        this.patientsService = patientsService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAllPatients (){
        return ResponseEntity.ok(patientsService.getAllPatients());
    }

    @GetMapping("/id")
    public ResponseEntity<PatientResponse> getPatientById(@RequestParam("id") Long id){
        System.out.println("Request by id: "+ id);
        PatientResponse response = patientsService.getPatientById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PatientResponse> createPatient (@RequestBody PatientRequest patientRequest){
        validatePatient(patientRequest);
        return ResponseEntity.ok(patientsService.createPatient(patientRequest));
    }
    private void validatePatient (PatientRequest request){
        if (request.getFirstName().isBlank()){
            throw new IllegalArgumentException("First name should be provided");
        }
        if (request.getAge()<0){
            throw new IllegalArgumentException("Age is incorrect");
        }

    }
}
