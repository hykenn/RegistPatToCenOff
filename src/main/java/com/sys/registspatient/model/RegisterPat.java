package com.sys.registspatient.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
public class RegisterPat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hospital_record_no")
    private String hospitalRecordNo;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    private String sex;
    private String status;
    private String placeOfBirth;
    private String religion;
    private String contactInfo;
    private String presentAddress;
    private String permanentAddress;

    @Column(name = "present_zip_code")
    private String presentZipCode;

    @Column(name = "permanent_zip_code")
    private String permanentZipCode;

    @Column(name = "birthdate")
    private LocalDate birthdate; // Using LocalDate for birthdate

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // Default constructor
    public RegisterPat() {}

    // Constructor with parameters
    public RegisterPat(String hospitalRecordNo, String firstName, String middleName, String lastName,
                       String sex, String status, String placeOfBirth, String religion, String contactInfo,
                       String presentAddress, String permanentAddress, String presentZipCode,
                       String permanentZipCode, LocalDate birthdate, LocalDateTime createdAt,
                       LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.hospitalRecordNo = hospitalRecordNo;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.sex = sex;
        this.status = status;
        this.placeOfBirth = placeOfBirth;
        this.religion = religion;
        this.contactInfo = contactInfo;
        this.presentAddress = presentAddress;
        this.permanentAddress = permanentAddress;
        this.presentZipCode = presentZipCode;
        this.permanentZipCode = permanentZipCode;
        this.birthdate = birthdate;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPresentZipCode() {
        return presentZipCode;
    }

    public void setPresentZipCode(String presentZipCode) {
        this.presentZipCode = presentZipCode;
    }

    public String getPermanentZipCode() {
        return permanentZipCode;
    }

    public void setPermanentZipCode(String permanentZipCode) {
        this.permanentZipCode = permanentZipCode;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
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
        return "RegisterPat{" +
                "id=" + id +
                ", hospitalRecordNo='" + hospitalRecordNo + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", status='" + status + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", religion='" + religion + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", presentAddress='" + presentAddress + '\'' +
                ", permanentAddress='" + permanentAddress + '\'' +
                ", presentZipCode='" + presentZipCode + '\'' +
                ", permanentZipCode='" + permanentZipCode + '\'' +
                ", birthdate=" + birthdate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterPat that = (RegisterPat) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
