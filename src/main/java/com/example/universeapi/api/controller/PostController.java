package com.example.universeapi.api.controller;

import com.example.universeapi.api.model.Post;
import com.example.universeapi.api.response.PostByUserResponse;
import com.example.universeapi.api.response.Response;
import com.example.universeapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/api/post/new")
    public Response addPost(@RequestParam("id") int userID, @RequestParam("content") String content){
        try {
            postService.addPost(userID, content);
            return new Response(true, "Post added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false, e.getMessage());
        }
    }

    @GetMapping("/api/post/all")
    public List<Post> getAllPosts(){ return postService.getAllPosts(); }

    @GetMapping("api/post/user")
    public PostByUserResponse getAllPostsByUser(@RequestParam int userID){
        try {
            List<Post> posts = postService.getAllPostsByUser(userID);
            return new PostByUserResponse(true, posts);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostByUserResponse(false, e.getMessage());
        }
    }
}
