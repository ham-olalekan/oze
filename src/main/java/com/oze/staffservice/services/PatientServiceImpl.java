package com.oze.staffservice.services;

import com.oze.staffservice.db.models.Patient;
import com.oze.staffservice.db.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository repository;

    @Override
    public InputStreamResource findPatientCsv(List<Long> patientIds) {
        String[] csvHeader = {
                "id", "First-name", "Last-name", "Age","last-visit-date"
        };

        List<List<String>> csvBody = new ArrayList<>();
        repository.findAllById(patientIds).forEach( p -> {
            csvBody.add(Arrays.asList(String.valueOf(p.getId()), p.getFirstName(), p.getLastName(), p.getLastName()));
        });
        ByteArrayInputStream byteArrayOutputStream;

        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                // defining the CSV printer
                CSVPrinter csvPrinter = new CSVPrinter(
                        new PrintWriter(out),
                        // withHeader is optional
                        CSVFormat.DEFAULT.withHeader(csvHeader)
                );
        ) {
            // populating the CSV content
            for (List<String> record : csvBody)
                csvPrinter.printRecord(record);

            // writing the underlying stream
            csvPrinter.flush();

            byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return new InputStreamResource(byteArrayOutputStream);
    }

    @Override
    public void deletePatientsByLastVisitDate(Date from, Date to) {
        repository.deleteByLastVisitDateBetween(from, to);
    }

    @Override
    public Set<Patient> fetchPatientOfAge(int age) {
        return repository.findByAgeGreaterThanEqual(age);
    }
}
