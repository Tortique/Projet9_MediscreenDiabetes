package com.abernathyclinic.mediscreendiabetes.service;

import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Component
public class AgeCalculator {
    public int getAge(String birthDate) {
        DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDateFormatted = LocalDate.parse(birthDate, simpleDateFormat);
        LocalDate now = LocalDate.now();
        Period age = Period.between(birthDateFormatted, now);
        return age.getYears();
    }
}
