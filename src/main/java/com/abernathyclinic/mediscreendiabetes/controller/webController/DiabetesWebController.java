package com.abernathyclinic.mediscreendiabetes.controller.webController;

import com.abernathyclinic.mediscreendiabetes.domain.PatientAndNotes;
import com.abernathyclinic.mediscreendiabetes.service.DiabetesAssessment;
import com.abernathyclinic.mediscreendiabetes.service.feignClients.MediscreenFeignClient;
import com.abernathyclinic.mediscreendiabetes.service.feignClients.MediscreenNotesFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

@ApiIgnore
@Controller
public class DiabetesWebController {

    @Autowired
    private MediscreenFeignClient mediscreenFeignClient;

    @Autowired
    private MediscreenNotesFeignClient mediscreenNotesFeignClient;

    private final DiabetesAssessment diabetesAssessment = new DiabetesAssessment();

    @GetMapping("/diabetes/list/{uuid}")
    public ModelAndView getAssessment(@PathVariable("uuid") UUID uuid) {
        ModelAndView modelAndView = new ModelAndView();
        PatientAndNotes patientAndNotes = new PatientAndNotes(mediscreenFeignClient.getPatientById(uuid), mediscreenNotesFeignClient.getPatientNoteByUuid(uuid));
        modelAndView.addObject("diabetes", diabetesAssessment.getDiabetesReport(patientAndNotes));
        modelAndView.setViewName("diabetes/list");
        return modelAndView;
    }

}
