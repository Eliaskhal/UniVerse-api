package com.example.universeapi.api.exceptions;

public class LoginUserNotFound extends Exception {
    public LoginUserNotFound(){
        super("Username not found");
    }
}
