package com.sys.registspatient.service;

import com.sys.registspatient.model.RegisterPat;
import com.sys.registspatient.repository.RegisterPatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RegisterPatService {

    @Autowired
    private RegisterPatRepository registerPatRepository;

    // Method to add a new patient
    public RegisterPat addPatient(RegisterPat registerPat) {
        String hospitalRecordNo = generateUniqueHospitalRecordNo();
        registerPat.setHospitalRecordNo(hospitalRecordNo);

        LocalDateTime currentDate = LocalDateTime.now();
        registerPat.setCreatedAt(currentDate);

        return registerPatRepository.save(registerPat);
    }
    
    // Method to generate a unique hospital record number
    public String generateUniqueHospitalRecordNo() {
        String hospitalRecordNo;
        Optional<RegisterPat> latestPatient;

        do {
            // Get the latest hospital record number from the database
            latestPatient = registerPatRepository.findTopByOrderByIdDesc();
            
            if (latestPatient.isPresent()) {
                String lastHospitalRecordNo = latestPatient.get().getHospitalRecordNo();
                long newRecordNo = Long.parseLong(lastHospitalRecordNo) + 1;
                hospitalRecordNo = String.format("%010d", newRecordNo);
            } else {
                hospitalRecordNo = "0000000001"; // For the first record
            }
        } while (registerPatRepository.existsByHospitalRecordNo(hospitalRecordNo));

        return hospitalRecordNo;
    }

    // Method to get all patients excluding soft-deleted ones
    public Iterable<RegisterPat> getAllPatients() {
        // Use the findAllActivePatients method to get only active patients (those without a deletedAt value)
        return registerPatRepository.findAllActivePatients();
    }


    // Method to get a patient by their ID
    public Optional<RegisterPat> getPatientById(Integer id) {
        return registerPatRepository.findById(id);
    }

    public Optional<RegisterPat> getPatientByHospitalRecordNo(String hospitalRecordNo) {
        return registerPatRepository.findByHospitalRecordNo(hospitalRecordNo);
    }
    
    
    public Optional<RegisterPat> findByHospitalRecordNo(String hospitalRecordNo) {
        return registerPatRepository.findByHospitalRecordNo(hospitalRecordNo);
    }


    
    public String softDeletePatient(String hospitalRecordNo) {
        Optional<RegisterPat> patient = registerPatRepository.findByHospitalRecordNo(hospitalRecordNo);
        
        if (patient.isPresent()) {
            RegisterPat patientToDelete = patient.get();
            patientToDelete.setDeletedAt(LocalDateTime.now());  // Set deletedAt timestamp
            registerPatRepository.save(patientToDelete);  // Save the updated record

            return "Patient soft-deleted successfully";
        } else {
            return "Patient not found";
        }
    }

    

    // Method to search patients
    public Iterable<RegisterPat> searchPatients(String firstname, String middlename, String lastname, LocalDate birthdate) {
        if (firstname == null && middlename == null && lastname == null && birthdate == null) {
            return registerPatRepository.findAll();
        }
        return registerPatRepository.searchPatients(firstname, middlename, lastname, birthdate);
    }

    public RegisterPat save(RegisterPat patient) {
        return registerPatRepository.save(patient);
    }
}
