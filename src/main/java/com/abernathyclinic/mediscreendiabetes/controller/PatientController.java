package com.abernathyclinic.mediscreendiabetes.controller;

import com.abernathyclinic.mediscreendiabetes.domain.Patient;
import com.abernathyclinic.mediscreendiabetes.service.feignClients.MediscreenFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private MediscreenFeignClient mediscreenFeignClient;


    @PostMapping("/addPatient")
    public @ResponseBody
    String addNewPatient(@RequestParam String lastName, @RequestParam String firstName,
                         @RequestParam String dateOfBirth, @RequestParam String gender,
                         @RequestParam String address, @RequestParam String phone) {
        return mediscreenFeignClient.addNewPatient(lastName, firstName, dateOfBirth, gender, address, phone);
    }

    @GetMapping("/getAll")
    public @ResponseBody
    Iterable<Patient> getAllPatients() {
        return mediscreenFeignClient.getAllPatients();
    }

    @GetMapping("/getById")
    public Patient getPatientById(@RequestParam UUID id) {
        return mediscreenFeignClient.getPatientById(id);
    }

    @GetMapping("/getByFirstNameAndLastName")
    public Patient getPatientByName(@RequestParam String firstName, @RequestParam String lastName) {
        return mediscreenFeignClient.getPatientByName(firstName, lastName);
    }

    @PutMapping("/updatePatient/{id}")
    public @ResponseBody
    String updatePatient(@PathVariable("id") UUID id, @RequestBody Patient patient) {
        return mediscreenFeignClient.updatePatient(id, patient);
    }
}
