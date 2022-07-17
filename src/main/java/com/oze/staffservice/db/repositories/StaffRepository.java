package com.oze.staffservice.db.repositories;

import com.oze.staffservice.db.models.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {
    Optional<Staff> findByUuid(String staffId);
}
