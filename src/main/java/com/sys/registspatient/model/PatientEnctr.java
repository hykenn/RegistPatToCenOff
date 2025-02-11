package com.sys.registspatient.model;

import jakarta.persistence.*;

@Entity
public class PatientEnctr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_record_no", referencedColumnName = "hospital_record_no", nullable = false)
    private RegisterPat registerPat;  // Foreign key to RegisterPat

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

    // Default constructor
    public PatientEnctr() {}

    // Constructor with parameters
    public PatientEnctr(RegisterPat registerPat, String typeOfService, String broughtBy, String chiefComplaint, 
                        String consultationType, String consultingDoctor) {
        this.registerPat = registerPat;
        this.typeOfService = typeOfService;
        this.broughtBy = broughtBy;
        this.chiefComplaint = chiefComplaint;
        this.consultationType = consultationType;
        this.consultingDoctor = consultingDoctor;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RegisterPat getRegisterPat() {
        return registerPat;
    }

    public void setRegisterPat(RegisterPat registerPat) {
        this.registerPat = registerPat;
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

    @Override
    public String toString() {
        return "PatientEnctr{" +
                "id=" + id +
                ", registerPat=" + registerPat.getHospitalRecordNo() +
                ", typeOfService='" + typeOfService + '\'' +
                ", broughtBy='" + broughtBy + '\'' +
                ", chiefComplaint='" + chiefComplaint + '\'' +
                ", consultationType='" + consultationType + '\'' +
                ", consultingDoctor='" + consultingDoctor + '\'' +
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
