package com.sys.registspatient.repository;

import com.sys.registspatient.model.PatientEnctr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientEnctrRepository extends JpaRepository<PatientEnctr, Integer> {

        List<PatientEnctr> findByHospitalRecordNo(String hospitalRecordNo);
}
