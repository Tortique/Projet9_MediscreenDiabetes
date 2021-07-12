package webController;

import com.abernathyclinic.mediscreendiabetes.MediscreenDiabetes;
import com.abernathyclinic.mediscreendiabetes.controller.webController.HomeWebController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(HomeWebController.class)
@ContextConfiguration(classes = {MediscreenDiabetes.class})
public class HomeWebControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void homeTest() throws Exception {
        mockMvc.perform(get("/","/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }
}
