package com.example.validation.controller;

import com.example.validation.dto.PatientRequest;
import com.example.validation.dto.PatientResponse;
import com.example.validation.model.Patient;
import com.example.validation.service.PatientsService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatientById(@RequestParam("id") Long id){
        PatientResponse response = patientsService.getPatientById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PatientResponse> createPatient (@Validated @RequestBody PatientRequest patientRequest){
        return ResponseEntity.ok(patientsService.createPatient(patientRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> updatePatient (@PathVariable Long id, @Validated @RequestBody PatientRequest updatedPatient){
        return ResponseEntity.ok(patientsService.updatePatient(id, updatedPatient));
    }
}
