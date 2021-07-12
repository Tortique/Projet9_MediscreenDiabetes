package com.abernathyclinic.mediscreendiabetes.service.feignClients;

import com.abernathyclinic.mediscreendiabetes.domain.PatientNote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "mediscreennotes", url = "localhost:8082")
public interface MediscreenNotesFeignClient {

    @GetMapping("/api/notes/getAll")
    List<PatientNote> getAllPatientNotes();

    @GetMapping("/api/notes/getPatientNoteByUuid/{uuid}")
    List<PatientNote> getPatientNoteByUuid(@PathVariable("uuid") UUID uuid);

    @PostMapping("/api/notes/addPatientNote")
    String addPatientNote(@RequestBody PatientNote patientNote);

    @PutMapping("/api/notes/editPatientNote/{id}")
    String editPatientNote(@PathVariable("id") String id, @RequestBody PatientNote patientNote);

    @GetMapping("/api/notes/getPatientNoteById/{id}")
    PatientNote getPatientNoteById(@PathVariable("id") String id);
}
