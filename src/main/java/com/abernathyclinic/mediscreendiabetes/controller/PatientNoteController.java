package com.abernathyclinic.mediscreendiabetes.controller;

import com.abernathyclinic.mediscreendiabetes.domain.PatientNote;
import com.abernathyclinic.mediscreendiabetes.service.feignClients.MediscreenNotesFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notes")
public class PatientNoteController {
    @Autowired
    private MediscreenNotesFeignClient mediscreenNotesFeignClient;

    @GetMapping("/getAll")
    public List<PatientNote> getAllPatientNotes() {
        return mediscreenNotesFeignClient.getAllPatientNotes();
    }

    @GetMapping("/getPatientNoteById/{id}")
    public PatientNote getPatientNoteById(@PathVariable("id") String id)  {
        return mediscreenNotesFeignClient.getPatientNoteById(id);
    }

    @GetMapping("/getPatientNoteByUuid/{uuid}")
    public List<PatientNote> getPatientNoteByUuid(@PathVariable("uuid") UUID uuid) {
        return mediscreenNotesFeignClient.getPatientNoteByUuid(uuid);
    }

    @PostMapping("/addPatientNote")
    public String addPatientNote(@RequestBody PatientNote patientNote) {
        return mediscreenNotesFeignClient.addPatientNote(patientNote);
    }

    @PutMapping("/editPatientNote/{id}")
    public String editPatientNote(@PathVariable("id") String id, @RequestBody PatientNote patientNote) {
        return mediscreenNotesFeignClient.editPatientNote(id, patientNote);
    }
}
