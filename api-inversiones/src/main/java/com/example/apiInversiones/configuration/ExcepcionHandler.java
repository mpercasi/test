package com.example.apiInversiones.configuration;

import com.example.apiInversiones.exceptions.NonExistentException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExcepcionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NonExistentException.class})
    protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request){
        return handleExceptionInternal(ex, "Item no encontrado", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
//
//    @ExceptionHandler({DuplicatedException.class})
//    protected ResponseEntity<Object> handleDuplicate(Exception ex, WebRequest request){
//        return handleExceptionInternal(ex, "Item ya existente", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }


}
