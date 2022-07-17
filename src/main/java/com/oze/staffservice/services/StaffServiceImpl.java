package com.oze.staffservice.services;

import com.oze.staffservice.db.models.Staff;
import com.oze.staffservice.db.repositories.StaffRepository;
import com.oze.staffservice.dto.StaffDto;
import com.oze.staffservice.exceptions.NotFoundException;
import com.oze.staffservice.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService{
    private static Logger log = LoggerFactory.getLogger(StaffServiceImpl.class);

    @Autowired
    private StaffRepository repository;

    @Override
    public Staff addStaff(StaffDto staffDto) {
        log.info("Attempting to add a new staff with info [{}]", staffDto);
        Staff newStaff =  Staff
                .builder()
                .createdAt(new Date())
                .firstName(staffDto.getFirstName())
                .lastName(staffDto.getLastName())
                .registrationDate(new Date())
                .uuid(UUID.randomUUID().toString())
                .build();
        try {
            newStaff = repository.save(newStaff);
            return newStaff;
        }catch (Exception ex){
            log.error("saving new staff info failed with error [{}]", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public Staff updateStaff(String uuid, StaffDto staffDto) throws NotFoundException {
        Staff record = repository.findByUuid(uuid).orElseThrow(()-> new NotFoundException("Invalid Staff UUID "));
        if( !StringUtils.isBlank(staffDto.getFirstName())) {
            record.setFirstName(staffDto.getFirstName());
        }

        if( !StringUtils.isBlank(staffDto.getLastName())) {
            record.setLastName(staffDto.getLastName());
        }

        try {
            return repository.save(record);

        }catch (Exception ex){
            log.error("updating staff info failed with error [{}]", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public Staff findStaff(String uuid) throws NotFoundException {
        return repository.findByUuid(uuid).orElseThrow(()-> new NotFoundException("Invalid staff ID"));
    }

    @Override
    public Optional<Staff> findStaffById(String uuid) {
        return repository.findByUuid(uuid);
    }
}
