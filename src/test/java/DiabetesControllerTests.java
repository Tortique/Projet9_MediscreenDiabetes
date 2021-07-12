import com.abernathyclinic.mediscreendiabetes.MediscreenDiabetes;
import com.abernathyclinic.mediscreendiabetes.controller.DiabetesController;
import com.abernathyclinic.mediscreendiabetes.domain.Patient;
import com.abernathyclinic.mediscreendiabetes.service.feignClients.MediscreenFeignClient;
import com.abernathyclinic.mediscreendiabetes.service.feignClients.MediscreenNotesFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MediscreenDiabetes.class})
@WebMvcTest(DiabetesController.class)
public class DiabetesControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private MediscreenFeignClient mediscreenFeignClient;

    @MockBean
    private MediscreenNotesFeignClient mediscreenNotesFeignClient;

    @Test
    void getAssessmentTest() throws Exception {
        when(mediscreenFeignClient.getPatientById(any(UUID.class))).thenReturn(new Patient("lastName", "firstName", "05/07/1980", "M", "address", "phone"));
        when(mediscreenNotesFeignClient.getPatientNoteByUuid(any(UUID.class))).thenReturn(new ArrayList<>());

        MvcResult mvcResult = mockMvc.perform(get("/api/diabetes/getAssessment?uuid=b42a8ef5-8baa-4bc2-89aa-d18cdc3239f8"))
                .andDo(print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
        verify(mediscreenFeignClient, times(1)).getPatientById(any(UUID.class));
        verify(mediscreenNotesFeignClient, times(1)).getPatientNoteByUuid(any(UUID.class));
    }
}
