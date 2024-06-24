package com.example.universeapi.api.exceptions;

public class AlreadyAvailableUsername extends Exception {
    public AlreadyAvailableUsername(){
        super("Username already available");
    }
}