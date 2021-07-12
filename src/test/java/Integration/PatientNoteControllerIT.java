package Integration;

import com.abernathyclinic.mediscreendiabetes.MediscreenDiabetes;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MediscreenDiabetes.class})
@WebMvcTest(PatientNoteController.class)
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class PatientNoteControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllPatientNotesTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/notes/getAll")).andDo(print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    void getPatientNoteByUuidTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/notes/getPatientNoteByUuid/eb6f5611-b845-44a1-a80b-a1ac7e2debb6"))
                .andDo(print()).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    void getPatientNoteByIdTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/notes/getPatientNoteById/60df28b3bcb60970ca4dccda"))
                .andDo(print()).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    void addPatientNoteTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/api/notes/addPatientNote")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"uuid\": \"eb6f5611-b845-44a1-a80b-a1ac7e2debb6\",\"notes\":\"notes\"}"))
                .andDo(print()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void editPatientNoteTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/api/notes/editPatientNote/60df28b3bcb60970ca4dccda")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"uuid\": \"eb6f5611-b845-44a1-a80b-a1ac7e2debb6\",\"notes\":\"notesChanged\"}"))
                .andDo(print())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(200, response.getStatus());
    }
}
