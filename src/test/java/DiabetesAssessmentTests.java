import com.abernathyclinic.mediscreendiabetes.MediscreenDiabetes;
import com.abernathyclinic.mediscreendiabetes.domain.*;
import com.abernathyclinic.mediscreendiabetes.service.DiabetesAssessment;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MediscreenDiabetes.class})
public class DiabetesAssessmentTests {

    private final DiabetesAssessment diabetesAssessment = new DiabetesAssessment();

    @Test
    public void getDiabetesReportWithNoneAssessmentTest() {
        Patient patient = new Patient("Dylan", "Bob", "01/07/2000", "M", "address", "phone");
        List<PatientNote> patientNoteList = new ArrayList<>();
        UUID uuid = UUID.randomUUID();
        patientNoteList.add(new PatientNote(uuid, "poids"));
        patientNoteList.add(new PatientNote(uuid, "taille"));
        patientNoteList.add(new PatientNote(uuid, "bonjour"));
        PatientAndNotes patientAndNotes = new PatientAndNotes(patient, patientNoteList);

        DiabetesReport diabetesReport = diabetesAssessment.getDiabetesReport(patientAndNotes);

        assertEquals(DiabetesAssessmentResult.NONE, diabetesReport.getDiabetesAssessmentResult());
    }

    @Test
    public void getDiabetesReportWithBorderlineAssessmentTest() {
        Patient patient = new Patient("Dylan", "Bob", "01/07/1980", "M", "address", "phone");
        List<PatientNote> patientNoteList = new ArrayList<>();
        UUID uuid = UUID.randomUUID();
        patientNoteList.add(new PatientNote(uuid, "poids"));
        patientNoteList.add(new PatientNote(uuid, "taille"));
        patientNoteList.add(new PatientNote(uuid, "bonjour"));
        PatientAndNotes patientAndNotes = new PatientAndNotes(patient, patientNoteList);

        DiabetesReport diabetesReport = diabetesAssessment.getDiabetesReport(patientAndNotes);

        assertEquals(DiabetesAssessmentResult.BORDERLINE, diabetesReport.getDiabetesAssessmentResult());
    }

    @Test
    public void getDiabetesReportWithInDangerAssessmentTest() {
        Patient patient = new Patient("Dylan", "Bob", "01/07/2000", "M", "address", "phone");
        List<PatientNote> patientNoteList = new ArrayList<>();
        UUID uuid = UUID.randomUUID();
        patientNoteList.add(new PatientNote(uuid, "poids"));
        patientNoteList.add(new PatientNote(uuid, "taille"));
        patientNoteList.add(new PatientNote(uuid, "fumeur"));
        PatientAndNotes patientAndNotes = new PatientAndNotes(patient, patientNoteList);

        DiabetesReport diabetesReport = diabetesAssessment.getDiabetesReport(patientAndNotes);

        assertEquals(DiabetesAssessmentResult.IN_DANGER, diabetesReport.getDiabetesAssessmentResult());
    }

    @Test
    public void getDiabetesReportWithEarlyOnsetAssessmentTest() {
        Patient patient = new Patient("Dylan", "Bob", "01/07/2000", "M", "address", "phone");
        List<PatientNote> patientNoteList = new ArrayList<>();
        UUID uuid = UUID.randomUUID();
        patientNoteList.add(new PatientNote(uuid, "poids"));
        patientNoteList.add(new PatientNote(uuid, "taille"));
        patientNoteList.add(new PatientNote(uuid, "fumeur"));
        patientNoteList.add(new PatientNote(uuid, "microalbumine"));
        patientNoteList.add(new PatientNote(uuid, "Vertige"));
        PatientAndNotes patientAndNotes = new PatientAndNotes(patient, patientNoteList);

        DiabetesReport diabetesReport = diabetesAssessment.getDiabetesReport(patientAndNotes);

        assertEquals(DiabetesAssessmentResult.EARLY_ONSET, diabetesReport.getDiabetesAssessmentResult());
    }
}
