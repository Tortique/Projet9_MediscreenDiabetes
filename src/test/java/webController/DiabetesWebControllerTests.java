package webController;

import com.abernathyclinic.mediscreendiabetes.MediscreenDiabetes;
import com.abernathyclinic.mediscreendiabetes.controller.webController.DiabetesWebController;
import com.abernathyclinic.mediscreendiabetes.domain.Patient;
import com.abernathyclinic.mediscreendiabetes.domain.PatientAndNotes;
import com.abernathyclinic.mediscreendiabetes.domain.PatientNote;
import com.abernathyclinic.mediscreendiabetes.service.feignClients.MediscreenFeignClient;
import com.abernathyclinic.mediscreendiabetes.service.feignClients.MediscreenNotesFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(DiabetesWebController.class)
@ContextConfiguration(classes = {MediscreenDiabetes.class})
public class DiabetesWebControllerTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    MediscreenFeignClient mediscreenFeignClient;

    @MockBean
    MediscreenNotesFeignClient mediscreenNotesFeignClient;

    @Test
    void getAssessment() throws Exception {
        Patient patient = new Patient("Dylan", "Bob", "01/07/2000", "M", "address", "phone");
        List<PatientNote> patientNoteList = new ArrayList<>();
        UUID uuid = UUID.randomUUID();
        patientNoteList.add(new PatientNote(uuid, "poids"));
        patientNoteList.add(new PatientNote(uuid, "taille"));
        patientNoteList.add(new PatientNote(uuid, "fumeur"));
        patientNoteList.add(new PatientNote(uuid, "microalbumine"));
        patientNoteList.add(new PatientNote(uuid, "Vertige"));
        patientNoteList.add(new PatientNote(uuid, "anormal"));

        when(mediscreenFeignClient.getPatientById(uuid)).thenReturn(patient);
        when(mediscreenNotesFeignClient.getPatientNoteByUuid(uuid)).thenReturn(patientNoteList);

        mockMvc.perform(get("/diabetes/list/" + uuid))
                .andExpect(status().isOk())
                .andExpect(view().name("diabetes/list"));
    }
}
