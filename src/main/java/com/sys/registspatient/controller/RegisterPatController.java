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

@RestController
@RequestMapping("/api/patients")
public class RegisterPatController {

    @Autowired
    private final RegisterPatService registerPatService;

    // Constructor injection
    public RegisterPatController(RegisterPatService registerPatService) {
        this.registerPatService = registerPatService;
    }

    @PostMapping("/add")
    public ResponseEntity<RegisterPat> addPatient(@RequestBody RegisterPat registerPat) {
        // Set 'createdAt' and 'updatedAt' fields
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

    @GetMapping("/search")
    public ResponseEntity<Object> searchPatients(
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) String middlename,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) String birthdate) {
    
        // Parse birthdate if provided, otherwise set it to null
        LocalDate parsedBirthdate = null;
        if (birthdate != null && !birthdate.isEmpty()) {
            try {
                parsedBirthdate = LocalDate.parse(birthdate); // Convert string to LocalDate
            } catch (DateTimeParseException e) {
                // Return invalid date format error message
                return new ResponseEntity<>("Invalid date format. Use yyyy-MM-dd.", HttpStatus.BAD_REQUEST);
            }
        }
    
        // Call the service to search patients based on the provided parameters
        Iterable<RegisterPat> patients = registerPatService.searchPatients(firstname, middlename, lastname, parsedBirthdate);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
    

    // GET method to get all patients
    @GetMapping("/all")
    public ResponseEntity<Iterable<RegisterPat>> getAllPatients() {
        Iterable<RegisterPat> patients = registerPatService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
