package com.oze.staffservice.services;

import com.oze.staffservice.db.models.Patient;
import org.springframework.core.io.InputStreamResource;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface PatientService {
    InputStreamResource findPatientCsv(List<Long> patientId);

    void deletePatientsByLastVisitDate(Date from, Date to);

    Set<Patient> fetchPatientOfAge(int age);
}
