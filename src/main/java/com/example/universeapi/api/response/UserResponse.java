package com.example.universeapi.api.response;

import com.example.universeapi.api.model.User;

public class UserResponse extends Response{
    private User user;
    public UserResponse(boolean success, User user) {
        super(success);
        this.user = user;
    }

    public UserResponse(boolean success) {
        super(success);
        this.user = null;
    }

    public UserResponse(boolean success, String message){
        super(success, message);
    }

    public User getUser() {
        return user;
    }
}
