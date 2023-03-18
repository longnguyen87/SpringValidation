package com.example.validation.service;

import com.example.validation.dto.PatientRequest;
import com.example.validation.dto.PatientResponse;
import com.example.validation.model.Patient;
import com.example.validation.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.validation.dto.PatientResponse.fromEntity;

@Service
public class PatientsService {
    private PatientRepository patientRepository;

    public PatientsService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponse> getAllPatients (){
        return patientRepository.findAll().stream()
                .map(patient -> fromEntity(patient))
                .collect(Collectors.toList());
    }

    public PatientResponse getPatientById(Long id){
        return patientRepository.findById(id)
                .map(patient -> fromEntity(patient))
                .orElseThrow();
    }

    public PatientResponse createPatient(PatientRequest patientRequest){
        Patient patient = patientRepository.save(patientRequest.toEntity());
        return fromEntity(patient);
    }

    public PatientResponse updatePatient(Long id, PatientRequest updatedPatient) {
        return patientRepository.findById(id)
                .map(patient -> patient.updatePatient(updatedPatient))
                .map(patient -> patientRepository.save(patient))
                .map(patient -> fromEntity(patient))
                .orElseThrow(()-> new IllegalArgumentException("Patient with id:" + id +" not found"));
    }
}
