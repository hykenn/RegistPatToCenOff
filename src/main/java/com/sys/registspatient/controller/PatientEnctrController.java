package com.sys.registspatient.controller;

import com.sys.registspatient.model.PatientEnctr;
import com.sys.registspatient.service.PatientEnctrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientEnctrController {

    @Autowired
    private PatientEnctrService patientEnctrService;

    @PostMapping("/addenctr")
    public PatientEnctr createPatientEnctr(@RequestBody PatientEnctr patientEnctr) {
        return patientEnctrService.createPatientEnctr(patientEnctr);
    }

    @GetMapping("/allenctrbyhospitalrecno")
    public List<PatientEnctr> getPatientEnctrByHospitalRecordNo(@RequestParam("hospitalRecordNo") String hospitalRecordNo) {
        return patientEnctrService.getPatientEnctrByHospitalRecordNo(hospitalRecordNo);
    }
    
}
