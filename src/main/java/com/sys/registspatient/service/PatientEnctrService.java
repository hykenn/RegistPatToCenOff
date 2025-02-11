package com.sys.registspatient.service;

import com.sys.registspatient.model.PatientEnctr;
import com.sys.registspatient.repository.PatientEnctrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientEnctrService {

    @Autowired
    private PatientEnctrRepository patientEnctrRepository;

    public PatientEnctr createPatientEnctr(PatientEnctr patientEnctr) {
        return patientEnctrRepository.save(patientEnctr);
    }

    // Add more service methods as needed
}
