package com.project.list.products.send.serviceproducts.exceptions;

public class MyBadRequestException extends RuntimeException{
    public MyBadRequestException() {
    }

    public MyBadRequestException(String message) {
        super(message);
    }
}
