package com.oze.staffservice.unit;

import com.oze.staffservice.db.models.Patient;
import com.oze.staffservice.db.repositories.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PatientRepositoryTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertNotNull(testEntityManager);
        assertNotNull(entityManager);
        assertNotNull(patientRepository);
    }

    @Test
    public void shouldTestPatientWithinAge() {
        // create card
        Patient patientNotToFetch = Patient
                .builder()
                .age(1)
                .createdAt(new Date())
                .firstName("test-invalid-patient")
                .lastName("test-invalid-lastname")
                .updatedAt(new Date())
                .lastVisitDate(new Date())
                .build();
        Patient patientToFetch1 = Patient
                .builder()
                .age(5)
                .createdAt(new Date())
                .firstName("test-valid-firstname1")
                .lastName("test-valid-lastname1")
                .lastVisitDate(new Date())
                .updatedAt(new Date())
                .build();
        Patient patientToFetch2 = Patient
                .builder()
                .age(10)
                .createdAt(new Date())
                .firstName("test-valid-firstname2")
                .lastName("test-valid-lastname2")
                .lastVisitDate(new Date())
                .updatedAt(new Date())
                .build();
        Set<Patient> patientSet = new HashSet<>();
        patientSet.add(patientNotToFetch);
        patientSet.add(patientToFetch1);
        patientSet.add(patientToFetch2);
        patientRepository.saveAll(patientSet);

        Set<Patient> foundPatients= patientRepository.findByAgeGreaterThanEqual(2);
        assertEquals(foundPatients.size(), 2);
        foundPatients.forEach(p -> {
            assert p.getAge() >= 2;
        });
    }
}
