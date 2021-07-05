package com.abernathyclinic.mediscreendiabetes.service;

import com.abernathyclinic.mediscreendiabetes.domain.DiabetesAssessmentResult;
import com.abernathyclinic.mediscreendiabetes.domain.DiabetesReport;
import com.abernathyclinic.mediscreendiabetes.domain.PatientAndNotes;
import org.springframework.stereotype.Service;

@Service
public class DiabetesAssessment {

    private final AgeCalculator ageCalculator = new AgeCalculator();
    private final PatientNoteReader patientNoteReader = new PatientNoteReader();

    public DiabetesReport getDiabetesReport(PatientAndNotes patientAndNotes) {
        int age = ageCalculator.getAge(patientAndNotes.getPatient().getDateOfBirth());
        int triggers = patientNoteReader.noteReader(patientAndNotes.getPatientNote()).size();
        DiabetesAssessmentResult diabetesAssessmentResult = DiabetesAssessmentResult.NONE;
        String gender = patientAndNotes.getPatient().getGender();

        if(triggers == 2 && age >30) {
            diabetesAssessmentResult = DiabetesAssessmentResult.BORDERLINE;
        } else if(gender.equals("M") && age < 30 && triggers >= 5 || gender.equals("F") && age < 30 && triggers >= 7 || age > 30 && triggers >= 8) {
            diabetesAssessmentResult = DiabetesAssessmentResult.EARLY_ONSET;
        } else if(gender.equals("M") && age < 30 && triggers >= 3 || gender.equals("F") && age < 30 && triggers >= 4 || age > 30 && triggers == 6) {
            diabetesAssessmentResult = DiabetesAssessmentResult.IN_DANGER;
        }

        return new DiabetesReport(patientAndNotes.getPatient().getLastName(), patientAndNotes.getPatient().getFirstName(), age, diabetesAssessmentResult);
    }
}