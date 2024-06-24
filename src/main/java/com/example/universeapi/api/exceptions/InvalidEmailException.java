package com.example.universeapi.api.exceptions;

public class InvalidEmailException extends Exception {
    public InvalidEmailException(){
        super("Invalid email address");
    }
}