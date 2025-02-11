package com.sys.registspatient.controller;

import com.sys.registspatient.model.PatientEnctr;
import com.sys.registspatient.service.PatientEnctrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patientenctr")
public class PatientEnctrController {

    @Autowired
    private PatientEnctrService patientEnctrService;

    @PostMapping("/createEntcr")
    public PatientEnctr createPatientEnctr(@RequestBody PatientEnctr patientEnctr) {
        return patientEnctrService.createPatientEnctr(patientEnctr);
    }

    // You can add more endpoints for GET, PUT, DELETE operations
}
