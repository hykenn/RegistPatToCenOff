package com.sys.registspatient.repository;

import com.sys.registspatient.model.RegisterPat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RegisterPatRepository extends JpaRepository<RegisterPat, Integer> {

    Optional<RegisterPat> findTopByOrderByIdDesc();

    Optional<RegisterPat> findById(Integer id);

    boolean existsByHospitalRecordNo(String hospitalRecordNo); 

    Optional<RegisterPat> findByHospitalRecordNo(String hospitalRecordNo);

    @Query("SELECT p FROM RegisterPat p WHERE p.deletedAt IS NULL")
    List<RegisterPat> findAllActivePatients();

    @Query("SELECT p FROM RegisterPat p WHERE " +
    "(LOWER(p.firstName) LIKE LOWER(CONCAT('%', :firstname, '%')) OR :firstname IS NULL) AND " +
    "(LOWER(p.middleName) LIKE LOWER(CONCAT('%', :middlename, '%')) OR :middlename IS NULL) AND " +
    "(LOWER(p.lastName) LIKE LOWER(CONCAT('%', :lastname, '%')) OR :lastname IS NULL) AND " +
    "(p.birthdate = :birthdate OR :birthdate IS NULL)")
    List<RegisterPat> searchPatients(String firstname, String middlename, String lastname, LocalDate birthdate);
}