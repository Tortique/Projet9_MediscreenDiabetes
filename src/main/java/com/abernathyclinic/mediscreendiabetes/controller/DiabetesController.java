package com.abernathyclinic.mediscreendiabetes.controller;

import com.abernathyclinic.mediscreendiabetes.domain.DiabetesReport;
import com.abernathyclinic.mediscreendiabetes.domain.PatientAndNotes;
import com.abernathyclinic.mediscreendiabetes.service.DiabetesAssessment;
import com.abernathyclinic.mediscreendiabetes.service.feignClients.MediscreenFeignClient;
import com.abernathyclinic.mediscreendiabetes.service.feignClients.MediscreenNotesFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/diabetes")
public class DiabetesController {

    @Autowired
    private MediscreenFeignClient mediscreenFeignClient;

    @Autowired
    private MediscreenNotesFeignClient mediscreenNotesFeignClient;

    private final DiabetesAssessment diabetesAssessment = new DiabetesAssessment();

    @PostMapping("/getAssessment")
    public DiabetesReport getDiabetesReport(@RequestBody PatientAndNotes patientAndNotes) {
        return diabetesAssessment.getDiabetesReport(patientAndNotes);
    }

    @GetMapping("/getAssessment")
    public DiabetesReport getDiabetesReportByUuid(@RequestParam UUID uuid) {
        PatientAndNotes patientAndNotes = new PatientAndNotes(mediscreenFeignClient.getPatientById(uuid), mediscreenNotesFeignClient.getPatientNoteByUuid(uuid));
        return diabetesAssessment.getDiabetesReport(patientAndNotes);
    }
}
