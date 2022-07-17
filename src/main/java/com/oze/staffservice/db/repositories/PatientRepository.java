package com.oze.staffservice.db.repositories;

import com.oze.staffservice.db.models.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
    Set<Patient> findByAgeGreaterThanEqual(int age);

    void deleteByLastVisitDateBetween(Date from, Date to);
}
