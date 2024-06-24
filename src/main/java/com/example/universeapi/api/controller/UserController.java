package com.example.universeapi.api.controller;

import com.example.universeapi.api.model.User;
import com.example.universeapi.api.response.Response;
import com.example.universeapi.api.response.UserResponse;
import com.example.universeapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/api/login")
    public UserResponse login(@RequestParam String login, @RequestParam String password){
        try {
            User user = userService.getUser(login, password);
            return new UserResponse(true, user);
        } catch (Exception e){
            return new UserResponse(false, e.getMessage());
        }
    }

    @GetMapping("/api/user")
    public UserResponse getUser(@RequestParam int id){
        try {
            User user = userService.getUser(id);
            return new UserResponse(true, user);
        } catch (Exception e){
            return new UserResponse(false);
        }
    }

    @GetMapping("api/user/username")
    public UserResponse getUserByUsername(@RequestParam String username){
        try{
            User user = userService.getUserByUsername(username);
            return new UserResponse(true, user);
        } catch (Exception e){
            return new UserResponse(false);
        }
    }

    @DeleteMapping("/api/user")
    public Response removeUser(@RequestParam int id) {
        try {
            userService.removeUser(id);
            return new Response(true, "User removed");
        } catch (Exception e){
            return new Response(false, "User unavailable");
        }
    }

    @PostMapping("/api/register")
    public Response addUser(@RequestParam String username, @RequestParam String password, @RequestParam String email,
                                      @RequestParam String fullname, @RequestParam String role) {
        try {
            userService.addUser(username, password, email, fullname, role);
            return new Response(true, "Registered successfully");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @PutMapping("/api/user")
    public Response updateUser(@RequestParam int id, @RequestParam String choice, @RequestParam String value) throws Exception {
        try {
            userService.updateUser(id, choice, value);
            return new Response(true, "User Updated Successfully");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @GetMapping("/api/user/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
