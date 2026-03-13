package com.project.Fitness_App.Exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> HandleValidationsErrors(MethodArgumentNotValidException eh) {
        Map<String, String> errors = new HashMap<>();
        eh.getBindingResult()
                .getFieldErrors()
                .forEach(er -> errors
                        .put(er.getField(), er.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler({IllegalArgumentException.class,
            SignatureException.class,
            ExpiredJwtException.class,
            MalformedJwtException.class,})
    public ResponseEntity<String> HandleJwtError() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid Token");
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String>HandleUserNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User Not Found");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String>HandleAccessDenied(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Access Denied");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String>HandleLoginError(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Invalid Email or Password");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String>HandleDBError(){
        return ResponseEntity.badRequest()
                .body("Database Error ");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String>HandleExceptions(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal Server Error ");
    }
}