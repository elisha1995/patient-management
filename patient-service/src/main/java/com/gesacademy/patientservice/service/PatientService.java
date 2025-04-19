package com.gesacademy.patientservice.service;

import com.gesacademy.patientservice.dto.PatientResponseDTO;
import com.gesacademy.patientservice.mapper.PatientMapper;
import com.gesacademy.patientservice.model.Patient;
import com.gesacademy.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream().map(PatientMapper::toDTO).toList();
    }
}
