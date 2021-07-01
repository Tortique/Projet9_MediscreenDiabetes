package com.abernathyclinic.mediscreendiabetes.service;

import com.abernathyclinic.mediscreendiabetes.domain.DiabetesAssessmentResult;
import com.abernathyclinic.mediscreendiabetes.domain.DiabetesReport;
import com.abernathyclinic.mediscreendiabetes.domain.PatientAndNotes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiabetesAssessment {

    private AgeCalculator ageCalculator = new AgeCalculator();
    private PatientNoteReader patientNoteReader = new PatientNoteReader();

    public DiabetesReport getDiabetesReport(PatientAndNotes patientAndNotes) {
        int age = ageCalculator.getAge(patientAndNotes.getPatient().getDateOfBirth());
        int triggers = patientNoteReader.NoteReader(patientAndNotes.getPatientNote()).size();
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