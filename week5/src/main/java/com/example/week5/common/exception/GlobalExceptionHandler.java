package com.example.week5.common.exception;

import com.example.week5.common.exception.custom.*;
import com.example.week5.common.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<?>> handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(APIResponse.fail(message));
    }

    @ExceptionHandler(DuplicatedException.class)
    public ResponseEntity<APIResponse<?>> handleUserDuplicatedException(DuplicatedException e) {
        String message = e.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(APIResponse.fail(message));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<APIResponse<?>> handleBadRequestException(BadRequestException e) {
        String message = e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(APIResponse.fail(message));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse<?>> handleResourceNotFoundException(ResourceNotFoundException e) {
        String message = e.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.fail(message));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<APIResponse<?>> handleUnauthorizedException(UnauthorizedException e) {
        String message = e.getMessage();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(APIResponse.fail(message));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<APIResponse<?>> handleUnauthenticatedException(ForbiddenException e) {
        String message = e.getMessage();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(APIResponse.fail(message));
    }
}
