package com.employee.config;

public class InvalidException extends Exception { 
    public InvalidException(String errorMessage) {
        super(errorMessage);
    }
}
