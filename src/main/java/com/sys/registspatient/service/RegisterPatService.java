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
        // Generate hospital record number dynamically
        String hospitalRecordNo = generateUniqueHospitalRecordNo();
        registerPat.setHospitalRecordNo(hospitalRecordNo);

        // Set 'createdAt' and 'updatedAt' fields
        LocalDateTime currentDate = LocalDateTime.now();
        registerPat.setCreatedAt(currentDate);

        // Save the new patient
        return registerPatRepository.save(registerPat);
    }

    // Method to generate a unique hospital record number
    public String generateUniqueHospitalRecordNo() {
        String hospitalRecordNo;
        Optional<RegisterPat> latestPatient;

        do {
            // Generate the hospital record number
            latestPatient = registerPatRepository.findTopByOrderByIdDesc();
            if (latestPatient.isPresent()) {
                // Get the last hospital record number and increment it
                String lastHospitalRecordNo = latestPatient.get().getHospitalRecordNo();
                long newRecordNo = Long.parseLong(lastHospitalRecordNo) + 1;
                hospitalRecordNo = String.format("%010d", newRecordNo); // Format as 10-digit zero-padded
            } else {
                // For the first record, return '0000000001'
                hospitalRecordNo = "0000000001";
            }
        } while (registerPatRepository.existsByHospitalRecordNo(hospitalRecordNo)); // Check if the generated HRN exists

        return hospitalRecordNo;
    }

    public Iterable<RegisterPat> searchPatients(String firstname, String middlename, String lastname, LocalDate birthdate) {
        // If all parameters are null, return all patients
        if (firstname == null && middlename == null && lastname == null && birthdate == null) {
            return registerPatRepository.findAll();
        }
    
        // Otherwise, call custom query or use the criteria builder to handle partial matches
        return registerPatRepository.searchPatients(firstname, middlename, lastname, birthdate);
    }
    

    // Method to get all patients
    public Iterable<RegisterPat> getAllPatients() {
        return registerPatRepository.findAll();
    }
}
