package com.abernathyclinic.mediscreendiabetes.domain;

import java.util.*;

public class PatientAndNotes {

    private Patient patient;
    private List<PatientNote> patientNote;

    public PatientAndNotes() {}

    public PatientAndNotes(Patient patient, List<PatientNote> patientNote) {
        this.patient = patient;
        this.patientNote = patientNote;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<PatientNote> getPatientNote() {
        return patientNote;
    }

    public void setPatientNote(List<PatientNote> patientNote) {
        this.patientNote = patientNote;
    }

    @Override
    public String toString() {
        return "PatientAndNotes{" +
                "patient=" + patient +
                ", patientNote=" + patientNote +
                '}';
    }
}
