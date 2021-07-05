import com.abernathyclinic.mediscreendiabetes.MediscreenDiabetes;
import com.abernathyclinic.mediscreendiabetes.domain.PatientNote;
import com.abernathyclinic.mediscreendiabetes.service.PatientNoteReader;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MediscreenDiabetes.class})
public class PatientNoteReaderTest {

    @Autowired
    private PatientNoteReader patientNoteReader = new PatientNoteReader();

    @Test
    public void NoteReaderTest() {
        List<PatientNote> patientNoteList = new ArrayList<>();
        UUID uuid = UUID.randomUUID();
        patientNoteList.add(new PatientNote(uuid, "poids"));
        patientNoteList.add(new PatientNote(uuid, "taille"));
        patientNoteList.add(new PatientNote(uuid, "bonjour"));

        List<String> result = patientNoteReader.noteReader(patientNoteList);
        System.out.println(result);
        assertEquals(2, result.size());
    }
}
