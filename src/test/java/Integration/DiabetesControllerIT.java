package Integration;

import com.abernathyclinic.mediscreendiabetes.MediscreenDiabetes;
import com.abernathyclinic.mediscreendiabetes.controller.DiabetesController;
import com.abernathyclinic.mediscreendiabetes.controller.PatientController;
import com.abernathyclinic.mediscreendiabetes.controller.PatientNoteController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Integration Tests for Mediscreen Diabetes, Mediscreen and Mediscreen Notes
 * Launch Mediscreen and Mediscreen Notes application before these tests
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MediscreenDiabetes.class})
@WebMvcTest(DiabetesController.class)
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class DiabetesControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAssessmentTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/diabetes/getAssessment?uuid=01584892-eeb4-4e03-a13e-2a166034759e"))
                .andDo(print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }


}
