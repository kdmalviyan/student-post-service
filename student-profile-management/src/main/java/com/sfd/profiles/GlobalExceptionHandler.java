package com.sfd.profiles;

import com.sfd.profiles.role.RoleException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author kuldeep
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RoleException.class)
    public ResponseEntity<?> handleException(RoleException exception){
        return ResponseEntity.status(exception.getStatusCode())
                .body(exception.getMessage());
    }
}
