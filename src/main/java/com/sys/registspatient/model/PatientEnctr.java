package com.sys.registspatient.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class PatientEnctr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hospital_record_no")
    private String hospitalRecordNo;

    @Column(name = "type_of_service")
    private String typeOfService;

    @Column(name = "brought_by")
    private String broughtBy;

    @Column(name = "chief_complaint")
    private String chiefComplaint;

    @Column(name = "consultation_type")
    private String consultationType;

    @Column(name = "consulting_doctor")
    private String consultingDoctor;

    @Column(name = "logged_at")
    private LocalDateTime loggedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // Default constructor
    public PatientEnctr() {}

    // Constructor with parameters
    public PatientEnctr(String hospitalRecordNo, LocalDateTime loggedAt, LocalDateTime createdAt, LocalDateTime updatedAt,
    LocalDateTime deletedAt, String typeOfService, String broughtBy, String chiefComplaint, 
    String consultationType, String consultingDoctor) {

        this.hospitalRecordNo = hospitalRecordNo;
        this.typeOfService = typeOfService;
        this.broughtBy = broughtBy;
        this.chiefComplaint = chiefComplaint;
        this.consultationType = consultationType;
        this.consultingDoctor = consultingDoctor;
        this.loggedAt = loggedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHospitalRecordNo() {
        return hospitalRecordNo;
    }

    public void setHospitalRecordNo(String hospitalRecordNo) {
        this.hospitalRecordNo = hospitalRecordNo;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    public String getBroughtBy() {
        return broughtBy;
    }

    public void setBroughtBy(String broughtBy) {
        this.broughtBy = broughtBy;
    }

    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }

    public String getConsultationType() {
        return consultationType;
    }

    public void setConsultationType(String consultationType) {
        this.consultationType = consultationType;
    }

    public String getConsultingDoctor() {
        return consultingDoctor;
    }

    public void setConsultingDoctor(String consultingDoctor) {
        this.consultingDoctor = consultingDoctor;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return "PatientEnctr{" +
                "id=" + id +
                ", hospitalRecordNo='" + hospitalRecordNo + '\'' +
                ", typeOfService='" + typeOfService + '\'' +
                ", broughtBy='" + broughtBy + '\'' +
                ", chiefComplaint='" + chiefComplaint + '\'' +
                ", consultationType='" + consultationType + '\'' +
                ", consultingDoctor='" + consultingDoctor + '\'' +
                ", loggedAt=" + loggedAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientEnctr that = (PatientEnctr) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
