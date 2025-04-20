package com.gesacademy.patientservice.controller;

import com.gesacademy.patientservice.dto.PatientRequestDTO;
import com.gesacademy.patientservice.dto.PatientResponseDTO;
import com.gesacademy.patientservice.dto.validator.CreatePatientValidationGroup;
import com.gesacademy.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API for managing Patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok().body(patients);
    }

    @PostMapping
    @Operation(summary = "Create a new Patient")
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Validated({Default.class, CreatePatientValidationGroup.class})
            @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patient = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patient);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a new Patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable("id") UUID id,
            @Validated(Default.class) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patient = patientService.updatePatient(
                id, patientRequestDTO);
        return ResponseEntity.ok().body(patient);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Patient")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
