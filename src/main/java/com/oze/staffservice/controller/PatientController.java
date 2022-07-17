package com.oze.staffservice.controller;

import com.oze.staffservice.aspects.ValidStaff;
import com.oze.staffservice.db.models.Patient;
import com.oze.staffservice.dto.ApiResponse;
import com.oze.staffservice.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    @Autowired
    private PatientService patientService;

    private static String CSV_NAME = "patients.csv";

    @ValidStaff
    @GetMapping(value = "/csv", produces = "text/csv")
    public ResponseEntity<Resource> handleFetchingProfileRecord(@RequestHeader("staffID") String uuid,
                                                                @RequestParam("patientIds") List<Long> patientId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + CSV_NAME);
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");
        InputStreamResource resource = patientService.findPatientCsv(patientId);
        return new ResponseEntity<>(
                resource,
                headers,
                HttpStatus.OK
        );
    }

    @ValidStaff
    @GetMapping(value = "/profiles")
    public ResponseEntity<ApiResponse<?>> handleFetchingPatientProfile(@RequestHeader("staffID") String uuid,
                                                                       @RequestParam(name = "age", defaultValue = "2") final int age) {
        Set<Patient> patients = patientService.fetchPatientOfAge(age);
        return new ResponseEntity<>(
                new ApiResponse<>(true, patients, "successful"),
                HttpStatus.OK
        );
    }

    @ValidStaff
    @DeleteMapping(value = "/profiles")
    public ResponseEntity<ApiResponse<?>> handlePatientRecordDeletion(@RequestHeader("staffID") String uuid,
                                                                      @RequestParam(name = "from")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,
                                                                      @RequestParam(name = "to")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to){
        patientService.deletePatientsByLastVisitDate(from, to);
        return new ResponseEntity<>(new ApiResponse<>(true, null, "patients records deleted successfully"), HttpStatus.OK);

    }
}
