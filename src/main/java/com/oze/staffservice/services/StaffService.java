package com.oze.staffservice.services;

import com.oze.staffservice.db.models.Staff;
import com.oze.staffservice.dto.StaffDto;
import com.oze.staffservice.exceptions.NotFoundException;

import java.util.Optional;

public interface StaffService {
    Staff addStaff(StaffDto staffDto);
    Staff updateStaff(String uuid, StaffDto staffDto) throws NotFoundException;

    Staff findStaff(String uuid) throws NotFoundException;

    Optional<Staff> findStaffById(String uuid) ;
}
