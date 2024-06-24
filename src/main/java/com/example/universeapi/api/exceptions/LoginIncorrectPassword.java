package com.example.universeapi.api.exceptions;

public class LoginIncorrectPassword extends Exception {
    public LoginIncorrectPassword(){
        super("Incorrect Password");
    }
}
