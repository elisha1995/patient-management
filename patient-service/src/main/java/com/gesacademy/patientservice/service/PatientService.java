package com.gesacademy.patientservice.service;

import com.gesacademy.patientservice.dto.PatientRequestDTO;
import com.gesacademy.patientservice.dto.PatientResponseDTO;
import com.gesacademy.patientservice.exception.EmailAlreadyExistsException;
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

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "A patient with this email " + "already exists: "
                            + patientRequestDTO.getEmail());
        }

        Patient newPatient = patientRepository.save(
                PatientMapper.toModel(patientRequestDTO));


        return PatientMapper.toDTO(newPatient);
    }
}