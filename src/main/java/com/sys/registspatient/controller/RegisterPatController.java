package com.sys.registspatient.controller;

import com.sys.registspatient.model.RegisterPat;
import com.sys.registspatient.service.RegisterPatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class RegisterPatController {

    @Autowired
    private final RegisterPatService registerPatService;

    public RegisterPatController(RegisterPatService registerPatService) {
        this.registerPatService = registerPatService;
    }

    // Endpoint to generate a unique hospital record number
    @GetMapping("/generate-hospital-record-no")
    public String generateHospitalRecordNo() {
        return registerPatService.generateUniqueHospitalRecordNo();
    }

    // Endpoint to add a new patient
    @PostMapping("/add")
    public ResponseEntity<RegisterPat> addPatient(@RequestBody RegisterPat registerPat) {
        LocalDateTime currentDate = LocalDateTime.now();
        registerPat.setCreatedAt(currentDate);
        
        // If checkbox is checked, copy present address to permanent address
        if (registerPat.getPermanentAddress() == null || registerPat.getPermanentAddress().isEmpty()) {
            registerPat.setPermanentAddress(registerPat.getPresentAddress());
            registerPat.setPermanentZipCode(registerPat.getPresentZipCode());
        }
    
        // Save the new patient using the service layer
        RegisterPat savedPatient = registerPatService.addPatient(registerPat);
    
        // Return the saved patient with HTTP 201 Created status
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    // Endpoint to search patients
    @GetMapping("/search")
    public ResponseEntity<Object> searchPatients(@RequestParam(required = false) String firstname,
                                                 @RequestParam(required = false) String middlename,
                                                 @RequestParam(required = false) String lastname,
                                                 @RequestParam(required = false) String birthdate) {

        LocalDate parsedBirthdate = null;
        if (birthdate != null && !birthdate.isEmpty()) {
            try {
                parsedBirthdate = LocalDate.parse(birthdate); // Convert string to LocalDate
            } catch (DateTimeParseException e) {
                return new ResponseEntity<>("Invalid date format. Use yyyy-MM-dd.", HttpStatus.BAD_REQUEST);
            }
        }
        
        Iterable<RegisterPat> patients = registerPatService.searchPatients(firstname, middlename, lastname, parsedBirthdate);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Endpoint to get all active (not soft-deleted) patients
    @GetMapping("/all")
    public ResponseEntity<Iterable<RegisterPat>> getAllPatients() {
        Iterable<RegisterPat> patients = registerPatService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }


    // Endpoint to find patient by ID
    @GetMapping("/findid")
    public ResponseEntity<Object> getPatientById(@RequestParam Integer id) {
        Optional<RegisterPat> patient = registerPatService.getPatientById(id);

        if (patient.isPresent()) {
            // Return the patient details if found
            return new ResponseEntity<>(patient.get(), HttpStatus.OK);
        } else {
            // If patient is not found, return NOT_FOUND status
            return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getbyid/{hospitalRecordNo}")
    public ResponseEntity<RegisterPat> getPatientByHospitalRecordNo(@PathVariable String hospitalRecordNo) {
        Optional<RegisterPat> patient = registerPatService.getPatientByHospitalRecordNo(hospitalRecordNo);
    
        if (patient.isPresent()) {
            return new ResponseEntity<>(patient.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Patient not found
        }
    }

    @PutMapping("/updatebyhospitalRecordNo/{hospitalRecordNo}")
    public ResponseEntity<RegisterPat> updatePatientByHospitalRecordNo(
            @PathVariable String hospitalRecordNo, 
            @RequestBody RegisterPat updatedPatient) {

        Optional<RegisterPat> existingPatient = registerPatService.findByHospitalRecordNo(hospitalRecordNo);

        if (existingPatient.isPresent()) {
            RegisterPat patient = existingPatient.get();

            // Update fields
            patient.setFirstName(updatedPatient.getFirstName());
            patient.setMiddleName(updatedPatient.getMiddleName());
            patient.setLastName(updatedPatient.getLastName());
            patient.setSex(updatedPatient.getSex());
            patient.setStatus(updatedPatient.getStatus());
            patient.setPlaceOfBirth(updatedPatient.getPlaceOfBirth());
            patient.setReligion(updatedPatient.getReligion());
            patient.setContactInfo(updatedPatient.getContactInfo());
            patient.setPresentAddress(updatedPatient.getPresentAddress());
            patient.setPermanentAddress(updatedPatient.getPermanentAddress());
            patient.setPresentZipCode(updatedPatient.getPresentZipCode());
            patient.setPermanentZipCode(updatedPatient.getPermanentZipCode());
            patient.setBirthdate(updatedPatient.getBirthdate());
            patient.setUpdatedAt(LocalDateTime.now()); // Set the updated timestamp

            // Save updated patient
            RegisterPat updatedRecord = registerPatService.save(patient);

            return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/softdelete/{hospitalRecordNo}")
    public ResponseEntity<String> softDeletePatient(@PathVariable String hospitalRecordNo) {
        String result = registerPatService.softDeletePatient(hospitalRecordNo);
        
        if (result.equals("Patient soft-deleted successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }
    
}