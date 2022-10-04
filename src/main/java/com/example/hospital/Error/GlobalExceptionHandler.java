package com.example.hospital.Error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> HandlerNotFound(NotFoundException ex){

        ErrorResponse error=new ErrorResponse(ex.getLocalizedMessage(), Arrays.asList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }
    @ExceptionHandler(DaplicateException.class)
    public ResponseEntity<?> HandlerDaplicate(DaplicateException ex){
        ErrorResponse error=new ErrorResponse(ex.getLocalizedMessage(), Arrays.asList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                             HttpStatus status, WebRequest request) {


            List<String> errors = new ArrayList<String>();
            for(FieldError error :ex.getBindingResult().getFieldErrors()) {
                errors.add(error.getDefaultMessage());
            }
            for(ObjectError error :ex.getBindingResult().getGlobalErrors()) {
                errors.add(error.getDefaultMessage());
            }
            ErrorResponse error=new ErrorResponse(ex.toString(), errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
