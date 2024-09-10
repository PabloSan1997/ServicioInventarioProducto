package com.project.list.products.send.serviceproducts.models.dtos;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Calendar;
import java.util.Date;

@Data
public class ErrorDto {
    private String error;
    private int statusCode;
    private Long timestamp;
    private String message;

    public ErrorDto() {
    }

    public ErrorDto(HttpStatus status, Exception e) {
        Calendar data = Calendar.getInstance();
        this.error = status.getReasonPhrase();
        this.timestamp = new Date().getTime();
        this.message = e.getMessage();
        this.statusCode = status.value();
    }
}
