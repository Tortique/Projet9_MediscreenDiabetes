package Integration;

import com.abernathyclinic.mediscreendiabetes.MediscreenDiabetes;
import com.abernathyclinic.mediscreendiabetes.controller.PatientController;
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

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MediscreenDiabetes.class})
@WebMvcTest(PatientController.class)
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class PatientControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addNewPatient() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/api/patient/addPatient?lastName=lastName&firstName=firstName&dateOfBirth=dateOfBirth&gender=gender&address=address&phone=phone"))
                .andDo(print()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("Saved", response.getContentAsString());
    }
    @Test
    void getAllPatients() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/patient/getAll"))
                .andDo(print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    void getPatientByIdTest() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/api/patient/getById?id=01584892-eeb4-4e03-a13e-2a166034759e"))
                .andDo(print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    void getPatientByName() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/patient/getByFirstNameAndLastName?firstName=Pippa&lastName=Rees"))
                .andDo(print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    void updatePatient() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/api/patient/updatePatient/" + UUID.fromString("eb6f5611-b845-44a1-a80b-a1ac7e2debb6")).contentType(MediaType.APPLICATION_JSON).content(
                "{\"lastName\": \"lastName\",\"firstName\": \"firstName\",\"dateOfBirth\": \"dateOfBirth\",\"gender\": \"gender\"}"))
                .andDo(print()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("Patient updated", response.getContentAsString());
    }
}
