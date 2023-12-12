//package com.example.tickets.advice;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//@Slf4j
//public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(UserRoleNotFoundException.class)
//    public ResponseEntity<Map<String, Object>> handleNullUserException(UserRoleNotFoundException exception) {
//        String message = String.format("The role %s is not found", exception.getRole());
//
//        Map<String, Object> errorResponse = new HashMap<>();
//        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
//        errorResponse.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
//        errorResponse.put("message", message);
//        log.error(message);
//
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
//
//}
