package com.abernathyclinic.mediscreendiabetes.service.feignClients;

import com.abernathyclinic.mediscreendiabetes.domain.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "mediscreen", url = "localhost:8080")
public interface MediscreenFeignClient {

    @GetMapping("/api/patient/getAll")
    Iterable<Patient> getAllPatients();

    @GetMapping("/api/patient/getById")
    Patient getPatientById(@RequestParam UUID id);

    @GetMapping("/api/patient/getByFirstNameAndLastName")
    Patient getPatientByName(@RequestParam String firstName, @RequestParam String lastName);

    @PostMapping("/api/patient/addPatient")
    String addNewPatient(@RequestParam String lastName, @RequestParam String firstName,
                         @RequestParam String dateOfBirth, @RequestParam String gender,
                         @RequestParam String address, @RequestParam String phone);

    @PutMapping("/api/patient/updatePatient/{id}")
    String updatePatient(@PathVariable("id") UUID id,@RequestBody Patient patient);
}
