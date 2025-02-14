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

    public RegisterPat addPatient(RegisterPat registerPat) {
        String hospitalRecordNo = generateUniqueHospitalRecordNo();
        registerPat.setHospitalRecordNo(hospitalRecordNo);

        LocalDateTime currentDate = LocalDateTime.now();
        registerPat.setCreatedAt(currentDate);

        return registerPatRepository.save(registerPat);
    }

    public String generateUniqueHospitalRecordNo() {
        String hospitalRecordNo;
        Optional<RegisterPat> latestPatient;

        do {
            latestPatient = registerPatRepository.findTopByOrderByIdDesc();
            
            if (latestPatient.isPresent()) {
                String lastHospitalRecordNo = latestPatient.get().getHospitalRecordNo();
                long newRecordNo = Long.parseLong(lastHospitalRecordNo) + 1;
                hospitalRecordNo = String.format("%010d", newRecordNo);
            } else {
                hospitalRecordNo = "0000000001";
            }
        } while (registerPatRepository.existsByHospitalRecordNo(hospitalRecordNo));

        return hospitalRecordNo;
    }

    public Iterable<RegisterPat> getAllPatients() {
        return registerPatRepository.findAllActivePatients();
    }


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
            patientToDelete.setDeletedAt(LocalDateTime.now()); 
            registerPatRepository.save(patientToDelete); 
            return "Patient soft-deleted successfully";
        } else {
            return "Patient not found";
        }
    }

    
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
