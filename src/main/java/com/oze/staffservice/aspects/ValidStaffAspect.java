package com.oze.staffservice.aspects;

import com.oze.staffservice.exceptions.UnpermittedException;
import com.oze.staffservice.services.StaffService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
public class ValidStaffAspect {

    @Autowired
    private StaffService staffService;

    @Before("@annotation(ValidStaff)")
    public void isStaffValid(JoinPoint joinPoint) throws Throwable{
        String uuid = (String) joinPoint.getArgs()[0];
        staffService.findStaffById(uuid)
                .orElseThrow(()-> new UnpermittedException("Valid staff ID is required to access this resource"));
    }
}
