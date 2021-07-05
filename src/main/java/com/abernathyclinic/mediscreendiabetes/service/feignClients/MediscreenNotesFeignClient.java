package com.abernathyclinic.mediscreendiabetes.service.feignClients;

import com.abernathyclinic.mediscreendiabetes.domain.PatientNote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "mediscreennotes", url = "localhost:8082")
public interface MediscreenNotesFeignClient {

    @GetMapping("/api/getAll")
    List<PatientNote> getAllPatientNotes();

    @GetMapping("/api/getPatientNoteByUuid/{uuid}")
    List<PatientNote> getPatientNoteByUuid(@PathVariable("uuid") UUID uuid);

    @PostMapping("/api/addPatientNote")
    String addPatientNote(@RequestBody PatientNote patientNote);

    @PutMapping("/api/editPatientNote/{id}")
    String editPatientNote(@PathVariable("id") String id, @RequestBody PatientNote patientNote);

    @GetMapping("/api/getPatientNoteById/{id}")
    PatientNote getPatientNoteById(@PathVariable("id") String id);
}
