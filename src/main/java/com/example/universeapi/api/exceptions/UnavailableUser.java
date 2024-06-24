package com.example.universeapi.api.exceptions;

public class UnavailableUser extends Exception{
    public UnavailableUser(){
        super("User not available");
    }
}
