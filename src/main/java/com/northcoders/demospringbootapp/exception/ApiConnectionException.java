package com.northcoders.demospringbootapp.exception;

public class ApiConnectionException extends RuntimeException {
    public ApiConnectionException(String apiName) {
        super("Something went wrong while reaching out to " + apiName + " API!");
    }
}
