package com.sys.registspatient.repository;

import com.sys.registspatient.model.PatientEnctr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientEnctrRepository extends JpaRepository<PatientEnctr, Integer> {
    // Additional custom queries can be added here
}
