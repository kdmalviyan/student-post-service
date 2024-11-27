package com.sfd;

import com.sfd.role.RoleException;
import com.sfd.security.InvalidCredentialsException;
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

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleException(InvalidCredentialsException exception){
        return ResponseEntity.status(exception.getStatusCode())
                .body(exception.getMessage());
    }

}
