package com.oze.staffservice.controller;

import com.oze.staffservice.aspects.ValidStaff;
import com.oze.staffservice.db.models.Staff;
import com.oze.staffservice.dto.ApiResponse;
import com.oze.staffservice.dto.StaffDto;
import com.oze.staffservice.exceptions.NotFoundException;
import com.oze.staffservice.services.StaffService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
@AllArgsConstructor
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping
    public ResponseEntity<ApiResponse<Staff>> handleAddingStaffMembers(@RequestBody StaffDto staffDto){
        Staff staff = staffService.addStaff(staffDto);
        return new ResponseEntity<>(new ApiResponse<>(true, staff, "successful"), HttpStatus.CREATED);
    }

    @ValidStaff
    @PutMapping("/{uuid}/update")
    public ResponseEntity<ApiResponse<Staff>> handleUpdatingStaffMembers(@PathVariable("uuid") final String uuid,
                                                                         @RequestBody StaffDto staffDto) throws NotFoundException {
            Staff staff = staffService.updateStaff(uuid,staffDto);
            return new ResponseEntity<>(new ApiResponse<>(true, staff, "successful"), HttpStatus.CREATED);
    }
}
