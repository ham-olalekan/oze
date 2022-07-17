package com.oze.staffservice.controller.advice;

import com.oze.staffservice.dto.ApiResponse;
import com.oze.staffservice.exceptions.NotFoundException;
import com.oze.staffservice.exceptions.UnpermittedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponse<?>> handleNotFound(NotFoundException ex){
        return  new ResponseEntity<>(new ApiResponse<>(false, null, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UnpermittedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ApiResponse<?>> handleUnpermittedException(UnpermittedException ex){
        return  new ResponseEntity<>(new ApiResponse<>(false, null, ex.getMessage()), HttpStatus.FORBIDDEN);
    }
}
