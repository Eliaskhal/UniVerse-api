package com.example.universeapi.api.exceptions;

public class AlreadyAvailableEmail extends Exception {
    public AlreadyAvailableEmail(){
        super("Email address already available");
    }
}