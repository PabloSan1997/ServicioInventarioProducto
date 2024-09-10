package com.project.list.products.send.serviceproducts.controller;

import com.project.list.products.send.serviceproducts.exceptions.MyBadRequestException;
import com.project.list.products.send.serviceproducts.models.dtos.ErrorDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.IOException;


@RestControllerAdvice
public class ExceptionController {
    private ResponseEntity<?> generateError(HttpStatus status, Exception e){

        return ResponseEntity.status(status).body(new ErrorDto(status, e));
    }

    @ExceptionHandler({
            MyBadRequestException.class
    })
    public ResponseEntity<?> badRequest(Exception e){
        return generateError(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler({
            NoResourceFoundException.class
    })
    public ResponseEntity<?> notFound(Exception e){
        return generateError(HttpStatus.NOT_FOUND, e);
    }
}
